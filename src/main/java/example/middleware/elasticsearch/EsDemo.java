package example.middleware.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.Closeable;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author lr
 * @date 2021/4/1
 */
public class EsDemo implements Closeable {

    private static final int connectTimeOut = 3000;
    private static final int socketTimeOut = 3000;
    private static final int connectionRequestTimeOut = 3000;
    private static final int maxConnectNum = 20;
    private static final int maxConnectPerRoute = 20;
    private String scheme = "http";

    private String host;

    private int port;

    private RestHighLevelClient client;

    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]+");

    private static final String type = "_doc";


    public EsDemo(String address, int port, String scheme) {
        this.host = address;
        this.port = port;
        this.scheme = scheme;
        init();
    }

    public void init() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, scheme));
        // 异步httpclient的连接延时配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeOut);
                builder.setSocketTimeout(socketTimeOut);
                builder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return builder;
            }
        });
        // 异步httpclient的连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient (HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });
        client = new RestHighLevelClient(builder);
    }

    /**
     * 创建索引
     * @param index
     * @return
     * @throws IOException
     */
    public boolean createIndex(String index) throws IOException {
        if (UPPER_CASE.matcher(index).find()) {
            throw new RuntimeException("索引必须为全部小写");
        }
        if (existIndex(index)) {
            throw new RuntimeException("索引 [" + index + " ]已存在");
        }
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        // 加载数据类型
        Map<String, Object> settings = new HashMap<>();
        // 分区数、副本数、缓存刷新时间
        settings.put("number_of_shards", 10);
        settings.put("number_of_replicas", 1);
        settings.put("refresh_interval", "5s");
        createIndexRequest.settings(settings);
        // 设置别名
        createIndexRequest.alias(new Alias("test"));
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean ack = createIndexResponse.isAcknowledged();
        return ack;
    }

    public boolean existIndex(String index) throws IOException {
        return existIndex(index, type);
    }

    /**
     * 检查索引是否存在
     * @param index
     * @param type
     * @return
     * @throws IOException
     */
    public boolean existIndex(String index, String type) throws IOException {
        GetIndexRequest getRequest = new GetIndexRequest();
        getRequest.indices(index);
        getRequest.types(type);
        getRequest.local(false);
        getRequest.humanReadable(true);
        boolean exists = client.indices().exists(getRequest, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 插入数据
     * @param index
     * @param type
     * @param id
     * @param data
     * @return
     */
    public String addData(String index, String type, String id, String data) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        indexRequest.source(data, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        if (indexResponse.status() == RestStatus.OK) {
            return indexResponse.getId();
        }
        throw new RuntimeException("插入失败: " + indexResponse.toString());
    }

    public Object search(String index, String queryName, String queryValue, String keyWord, Date begin, int page, int size) throws IOException {
        String s = Optional.ofNullable(index).orElse("filebeat-*");
        SearchRequest searchRequest = new SearchRequest(s);
        SearchSourceBuilder searchRequestBuilder = searchRequest.source();

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(queryName, queryValue);
        // 必须匹配
        queryBuilder.must(termQueryBuilder);
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(keyWord);
        queryBuilder.must(queryStringQueryBuilder);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        // 范围查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp");
        String startTimeString = begin.toInstant().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()
                .format(formatter);
        rangeQueryBuilder.from(startTimeString);
        // rangeQueryBuilder.to(endTimeString)
        queryBuilder.must(rangeQueryBuilder);

        searchRequestBuilder.query(queryBuilder);
        searchRequestBuilder.size(size);
        searchRequestBuilder.from((page - 1) * size);
        // 时间倒叙
        searchRequestBuilder.sort("@timestamp", SortOrder.DESC);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = response.getHits();
        return searchHits;
    }

    @Override
    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}
