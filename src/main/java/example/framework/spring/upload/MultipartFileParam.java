package example.framework.spring.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author lr
 * @date 2020/11/23
 */
@Data
public class MultipartFileParam {


    @PositiveOrZero(message = "分片数不能小于0")
    private int chunk;

    @Positive(message = "分片总数必须大于1")
    private int chunkTotal;

    @NotNull(message = "指定文件")
    private MultipartFile file;

}