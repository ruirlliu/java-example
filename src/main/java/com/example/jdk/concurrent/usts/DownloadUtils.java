package com.example.jdk.concurrent.usts;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author liurui
 * @date 2019/7/9 11:43
 */
@Slf4j
public class DownloadUtils {

    private static final String DIR = "F:\\graduatePic";
    private static final int TIMEOUT = 1000;

    public static void main(String[] args) {
        String url = "http://photo2019.usts.edu.cn/";
        try {
            List<Relation> list = getUrls(url);
            //con(list);
            // 试试线程
            ExecutorService pool = Executors.newCachedThreadPool();
            list.forEach(x -> pool.execute(() -> {
                boolean b = downloadAndSave(x);
                System.out.println(x + " = " + b);
            }));
            // 关闭
            pool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Relation> getUrls(String url) throws Exception{
        List<Relation> list = new ArrayList<>();
        Document document = Jsoup.connect(url).timeout(30 * TIMEOUT).get();
        System.out.println(document.title());
        Elements aeles = document.getElementsByTag("a");
        aeles.forEach(x -> list.add(new Relation(x.html(), url + x.attr("href"))));
        return list;
    }

    public static boolean downloadAndSave(Relation r) {
        String url = "http://photo2019.usts.edu.cn";
        File file = null;
        List<Element> picUrls = null;
        try {
            System.out.println("**********" + r.getName() + "*************");
            System.out.println("请求的url ==  "+ r.getUrl());
            Document document = Jsoup.connect(r.getUrl()).timeout(TIMEOUT*1000).get();

            file = new File(DIR + r.getName());
            if (!file.exists()) {
                log.info("{} 不存在，即将创建", r.getName());
                System.out.printf("%s不存在，即将创建", r.getName() + "\n");
                boolean newFile = file.mkdirs();
                String flag = newFile ? "文件创建成功" : "文件创建失败";
                log.info("{} : {}" , r.getName(), flag);
            }

            Elements aeles = document.getElementsByTag("a");
            //picUrls = aeles.stream().filter(x -> x.attr("href").endsWith("jpg") || x.attr("href").endsWith("JPG")).collect(Collectors.toList());
            picUrls = aeles.stream().filter(DownloadUtils::IsValidPic).collect(Collectors.toList());
            for (Element ele : picUrls){
            //picUrls.forEach(x -> {
                String picUrl = url + ele.attr("href").replaceAll("\\\\", "/");
                System.out.println("图片url==" + picUrl + " -- " + Thread.currentThread().getName());
                try {
                    // URL 带中文
                    StringBuilder sb = new StringBuilder();
                    char[] chars = picUrl.toCharArray();
                    for (char c : chars) {
                        if (String.valueOf(c).getBytes(StandardCharsets.UTF_8).length > 1){  // 汉字
                            sb.append(URLEncoder.encode(String.valueOf(c), StandardCharsets.UTF_8.name()));
                        }else {
                            sb.append(c);
                        }
                    }
                    System.out.println("内容为：" + sb.toString());
                    URL url1 = new URL(sb.toString());
                    HttpURLConnection httpConnection = (HttpURLConnection) url1.openConnection();
                    httpConnection.setRequestMethod("GET");
                    httpConnection.setDoOutput(false);
                    httpConnection.setRequestProperty("contentType", "GBK"); // 中文
                    httpConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                    httpConnection.setRequestProperty("Accept","*/*");
                    httpConnection.connect();
                    System.out.println("响应码== " + httpConnection.getResponseCode());
                    boolean isError = httpConnection.getResponseCode() >= 400;
                    InputStream inputStream = isError ? httpConnection.getErrorStream() : httpConnection.getInputStream();
                    String picFilePath = file.getPath() + "/" + picUrl.substring(picUrl.lastIndexOf("/")+1);
                    System.out.println("图片路径==  " + picFilePath);
                    @Cleanup BufferedInputStream bis = new BufferedInputStream(inputStream);
                    @Cleanup BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picFilePath));
                    byte[] buf = new byte[2048];
                    while (true) {
                        int len = bis.read(buf);
                        if (len == -1) { break; }
                        bos.write(buf, 0, len);
                    }
                    bos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file == null || picUrls == null){
            return false;
        }
        System.out.println("文件数量：" + file.list().length);
        System.out.println("url数量：" + picUrls.size());
        return picUrls.size() != 0 && file.list().length != 0 && file.list().length == picUrls.size();
    }

    private static boolean IsValidPic(Element ele) {
        String picUrl = ele.attr("href");
        return picUrl.toLowerCase().endsWith("jpg");
    }

    private static void con(List<Relation> list) {
        try {
            List<Relation> badList = new ArrayList<>();
            for (Relation relation : list) {
                boolean b = downloadAndSave(relation);
                if (!b){
                    badList.add(relation);
                }
            }
            if (badList.size() != 0){
                con(badList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
