package com.ranyk.mybatis_ch02.io.fileio;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * ClassName:FileIoTest
 * Description:文件IO流测试一
 *
 * @author ranyi
 * @date 2020-08-21 18:39
 * Version: V1.0
 */
@Slf4j
public class FileIoTest {

    public static void main(String[] args) {
        FileIoTest fileIoTest = new FileIoTest();
        List<String> params = new ArrayList<>();
        params.add("1710027");
        System.out.println(JSON.toJSONString(params));

        try {
            String data = fileIoTest.getDataFromZip("http://127.0.0.1:9006/cApi/api/getZip", params);
            log.info(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String getDataFromZip(String imgUrl, List<String> params) throws Exception {

        URL url = null;
        HttpURLConnection conn = null;
        BufferedWriter out = null;
        InputStream in = null;
        StringBuilder paramsStr = null;


        if (params != null) {
            paramsStr = new StringBuilder();
            paramsStr.append(JSON.toJSONString(params));
        }

        try {

            url = new URL(imgUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(3 * 10000);
            conn.setReadTimeout(3 * 10000);

            //conn.connect();

            out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            out.write(paramsStr.toString());
            out.flush();

            in = conn.getInputStream();
            File nullFile = new File("");
            String zipDirectoryPath = nullFile.getCanonicalPath() + File.separator + "temp";
            String zipPath = nullFile.getCanonicalPath() + File.separator + "temp" + File.separator + "temp.zip";

            File zipDirectoryFile = new File(zipDirectoryPath);
            if (!zipDirectoryFile.exists()) {
                zipDirectoryFile.mkdir();
            }

            writeZipFileFromInputStream(in, zipPath);

            String result = readZipFile(zipPath);

            if (deleteFile(zipDirectoryFile)) {
                log.info("成功删除临时存放的zip文件的目录！");
            } else {
                log.info("成功删除临时存放的zip文件的目录！");
            }

            if (null != in) {
                if (null != result) {
                    return result;
                } else {
                    throw new Exception("读取的文件对象为空");
                }
            } else {
                throw new Exception("请求响应为空！");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            assert out != null;
            out.close();
            assert conn != null;
            conn.disconnect();
        }

    }


    private void writeZipFileFromInputStream(InputStream in, String filePath) {

        FileOutputStream downloadFile = null;
        try {
            downloadFile = new FileOutputStream(filePath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = in.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, length);
                downloadFile.flush();
            }
            downloadFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String readZipFile(String filePath) {
        FileInputStream in = null;
        ZipInputStream zipIn = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new FileInputStream(filePath);
            zipIn = new ZipInputStream(new BufferedInputStream(in), Charset.defaultCharset());
            while ((zipIn.getNextEntry()) != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(zipIn, Charset.defaultCharset()));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            zipIn.closeEntry();
            in.close();
            zipIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }
    }

    private boolean deleteFile(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files.length > 0) {
                    for (File f : files) {
                        if (!f.isDirectory()) {
                            f.delete();
                        } else {
                            deleteFile(f);
                        }
                    }
                } else {
                    file.delete();
                }
            }
        } else {
            return false;
        }
        return true;

    }

    private File readIoStringToFile(String ioStr, File file) {

        FileOutputStream fos = null;
        File fileTmp = null;
        try {

            fileTmp = new File(file.getCanonicalPath() + File.separator + "temp" + File.separator + "temp.zip");

            if (fileTmp.exists()) {
                fileTmp.delete();
            }

            BASE64Decoder decoder = new BASE64Decoder();

            byte[] bytes = decoder.decodeBuffer(ioStr);
            fos = new FileOutputStream(fileTmp);
            fos.write(bytes);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return fileTmp;
    }


    private void readZipFile(File file) {
        ZipFile zip = null;
        InputStream in = null;
        ZipInputStream zin = null;
        try {
            String path = file.getCanonicalPath() + File.separator + "temp" + File.separator + "temp.zip";
            zip = new ZipFile(path);
            in = new BufferedInputStream(new FileInputStream(path));
            zin = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (!ze.isDirectory()) {
                    System.err.println("file - " + ze.getName() + " : "
                            + ze.getSize() + " bytes");
                    long size = ze.getSize();
                    if (size > 0) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(zip.getInputStream(ze)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                        br.close();
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zin.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void getPath() throws IOException {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getPath());
    }

}
