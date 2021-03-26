package com.example.framework.spring.upload;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


@Service
@Slf4j
public class BaseFileServiceImpl implements BaseFileService {



    @Override
    public void chunkUpload(String path, MultipartFile file, int chuck, int chuckTotal) {
        String filename = file.getOriginalFilename();
        File tmp = new File(path + File.separator + filename + "_tmp");
        try {
            RandomAccessFile raf = new RandomAccessFile(tmp, "rw");
            long offset = chuck * file.getSize();
            raf.seek(offset);
            raf.write(file.getBytes());
            raf.close();
            boolean flag = checkUploadProgress(path, filename, chuck, chuckTotal);
            if (flag) {
                renameFile(tmp, filename);
                log.info("file upload completed. [fileName: {}]", filename);
            }
        } catch (Exception e) {
            log.error("file upload error...");
            e.printStackTrace();
        }
    }

    private boolean checkUploadProgress(String path, String fileName, int chuck, int chuckTotal) throws IOException {

        File confFile = new File(path, fileName + ".conf");
        try (RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw")) {

            //把该分段标记为 true 表示完成
            accessConfFile.setLength(chuckTotal);
            accessConfFile.seek(chuck);
            accessConfFile.write(Byte.MAX_VALUE);

            //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
            byte[] completeList = FileUtils.readFileToByteArray(confFile);
            byte isComplete = Byte.MAX_VALUE;
            for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
                //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
                isComplete = (byte) (isComplete & completeList[i]);
            }
            log.info("check the file has been uploaded --->[fileName : {} ,result : {}]", fileName, isComplete);
            return isComplete == Byte.MAX_VALUE;
        }
    }

    private boolean renameFile(File toBeRenamed, String toFileNewName) throws IOException {
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            log.info("File does not exist: {}" , toBeRenamed.getName());
            return false;
        }
        String p = toBeRenamed.getParent();
        File newFile = new File(p + File.separatorChar + toFileNewName);
        //修改文件名
        return toBeRenamed.renameTo(newFile);
    }
}
