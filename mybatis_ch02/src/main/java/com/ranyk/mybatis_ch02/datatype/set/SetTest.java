package com.ranyk.mybatis_ch02.datatype.set;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName:SetTest
 * Description:set集合测试类一
 *
 * @author ranyi
 * @date 2020-08-27 10:25
 * Version: V1.0
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        //set.add("a");
        //set.add("b");
        //set.add("c");
        //set.add("d");
        //set.add("e");

        Set<String> set1 = new HashSet<>();
        //set1.add("f");
        //set1.add("g");

        System.out.println("==========================  判断Set集合中是否存在指定的值  ================================");
        //boolean result = set.add("a");
        //System.out.println(result);
        //boolean result1 = set.add("f");
        //System.out.println(result1);
//        if (set.contains("a")) {
//            System.out.println("存在");
//        }else{
//            System.out.println("不存在");
//        }
        set.addAll(set1);
        if (set.size() > 0){
        for (String s : set) {
            System.out.println(s);
        }
        }else {
            System.out.println("set集合中不存在值！");
        }



        try {
            /**
             * 定义声明请求链接对象
             */
            HttpURLConnection conn = null;
            /**
             * 定义声明返回的流对象
             */
            InputStream content = null;

            String url ="" ;// UploadFileUriUtils.createFileUrl(houImg.getNewFileId());
            //构造文件地址访问的URL对象,传入访问地址参数
            URL requestUrl = new URL(url);

            //文件访问URL 对象打开连接进行访问
            conn = (HttpURLConnection) requestUrl.openConnection();

            //设置而连接超时时间
            conn.setConnectTimeout(3 * 1000);

            //获得访问连接的响应结果
            content = conn.getInputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            FileOutputStream outputStream = new FileOutputStream("D:\\123.dwg");
            while ((length = content.read()) != -1){
                outputStream.write(length);
            }

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
