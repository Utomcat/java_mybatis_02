package com.ranyk.mybatis.io.fileio;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * ClassName:FileIoTest03
 * Description:文件下载测试
 *
 * @author ranyi
 * @date 2020-09-24 16:25
 * Version: V1.0
 */
@Slf4j
public class FileIoTest03 {

    public static void main(String[] args) {

        String url = "http://172.16.2.106:81/group1/M00/00/00/CgBkBV9q7POAQy1FAAPBWLHNiTM714.pdf";
        String saveDir = "D:\\123\\";
        String fileName = "AAA.pdf";
        try {
            downloadByNIO(url,saveDir,fileName);
        } catch (IOException e) {
            log.error("错误信息为:  错误行: "+ e.getStackTrace()[0].getLineNumber() + " ; 错误信息: " + e.getMessage() + " .");
        }

        log.error("下载完成");

    }

    public static void downloadByNIO(String url, String saveDir, String fileName) throws IOException {

        if (Strings.isEmpty(url)) {
            throw new IOException("不能存在的连接或连接为空!");
        }

        if (!(new File(saveDir).exists())){
            new File(saveDir).mkdirs();
        }

        if (Strings.isEmpty(fileName)){
            throw new IOException("下载的文件名为空！");
        }

        InputStream connection = new URL(url).openStream();
        Path savePath = Paths.get(saveDir, fileName);
        Files.createDirectories(savePath.getParent());
        Files.copy(connection, savePath, StandardCopyOption.REPLACE_EXISTING);
    }


}
