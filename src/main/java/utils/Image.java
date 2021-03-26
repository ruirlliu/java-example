package utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Data
public class Image {
    private Integer imageId;

    private String imageUuid;

    private Byte imageStatus;

    private Byte imageScanStatus;

    private String imageName;

    private String imageLabel;

    private String imageTag;

    private Long imageSize;

    private String imageType;

    private String imageDesc;
    // 所属应用
    private Integer applicationId;

    private String applicationName;
    @JsonSerialize(using = DateSerializer.class)
    private Date imageCreatetime;

    private Integer userId;

    private String imageScanPath;

    private Integer registryId;

    // 中间字段 仓库ID们
    private List<String> registryIds;

    private Integer envId;

    private Byte deployStatus;
    // 增加查询条件appliIds
    private List<Integer> appliIds;
    // 增加查询条件appliIds
    private List<Integer> projectIdList;

    private String registryName;

    private Integer projectId;

    // 租户名称 for 1.8.2
    private String envName;

    // 仓库IP for 1.8.3
    private String serviceIP;

    // 辅助查询字段，仪表盘统计用
    private Integer clusterId;

    private Boolean scan;

    private String imageSource;

    private String imageAuthor;

    private String imageDockerFile;

    //镜像扫描后，该镜像是否安全(扫描失败，归于不安全)
    private Byte isSafe;
    
    // 镜像使用次数
    private Integer useCount;

    /**
     * 镜像目录
     * @since boc3.0
     */
    private Integer imageCatalogId;

    /**
     * 镜像下载次数
     * @since boc3.0
     */
    private Integer downloadCount;

    public Image(String imageUuid, Byte imageStatus, Byte imageScanStatus, String imageName, String imageTag,
                 String imageType, Date imageCreatetime, Integer userId, Integer registryId, Integer envId,
                 Byte deployStatus, String envName, String imageLabel, String imageSource, String imageAuthor,
                 String imageDockerFile) {
        super();
        this.imageUuid = imageUuid;
        this.imageStatus = imageStatus;
        this.imageScanStatus = imageScanStatus;
        this.imageName = imageName;
        this.imageTag = imageTag;
        this.imageType = imageType;
        this.imageCreatetime = imageCreatetime;
        this.userId = userId;
        this.registryId = registryId;
        this.envId = envId;
        this.deployStatus = deployStatus;
        this.envName = envName;
        this.imageLabel = imageLabel;
        this.imageSource = imageSource;
        this.imageAuthor = imageAuthor;
        this.imageDockerFile = imageDockerFile;
    }

    public Image() {
        super();
    }

    public Image(String imageName, String imageTag) {
        super();
        this.imageName = imageName;
        this.imageTag = imageTag;
    }

    public Image(Integer registryId, Integer imageCatalogId) {
        this.registryId = registryId;
        this.imageCatalogId = imageCatalogId;
    }

    public Image(Integer imageCatalogId) {
        super();
        this.imageCatalogId = imageCatalogId;
    }


    public void setImageSize(Long imageSize) {
        if (imageSize != null && imageSize > 0) {
            this.imageSize = imageSize;
        }
    }

    /**
     * null值比非null值小的非自然排序
     */
    public static <T extends Comparable<T>> Comparator<Image> nullsLastUnNaturalComparator(Function<Image, T> mapper) {
        return (i1, i2) -> {
            T o1 = mapper.apply(i1);
            T o2 = mapper.apply(i2);
            if (o1 == null) {
                return o2 == null ? 0 : 1;
            } else if (o2 == null) {
                return -1;
            } else {
                return o2.compareTo(o1);
            }
        };
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageName, image.imageName) &&
                Objects.equals(imageTag, image.imageTag) &&
                Objects.equals(imageCatalogId, image.imageCatalogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName, imageTag, imageCatalogId);
    }
}