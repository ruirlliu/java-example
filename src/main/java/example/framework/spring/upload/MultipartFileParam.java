package example.framework.spring.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author lr
 * @date 2020/11/23
 */
@Data
public class MultipartFileParam {

    private int chunk;

    private int chunkTotal;

    private MultipartFile file;

}