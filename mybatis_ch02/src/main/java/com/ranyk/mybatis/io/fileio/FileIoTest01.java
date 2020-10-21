package com.ranyk.mybatis.io.fileio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:FileIoTest01
 * Description:FileIo流测试类二
 *
 * @author ranyi
 * @date 2020-08-27 19:18
 * Version: V1.0
 */
public class FileIoTest01 {

    public static void main(String[] args) {

 /*       String path = "C:\\Users\\ranyi\\scoop\\apps\\aria2";
        File file = new File(path);


        if (file.exists()) {
            //循环遍历该文件夹获取对应的文件夹下有多少文件/文件夹,如果是文件夹则依次内推
            traverseFile(file);
        } else {
            System.out.println("指定的文件夹/文件不存在!");
        }*/

        lsFile("C:\\Users\\ranyi\\scoop\\apps\\aria2");
        try {
            Process dir = Runtime.getRuntime().exec("dir");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void traverseFile(File file) {
        /*if (file.isDirectory()){
            System.out.println("文件夹: " + file.getName() + "下有 " + file.listFiles().length + "个" + (!file.isDirectory()?"文件夹.":"文件."));
        }*/

        traverseFile(file.getName(),file.listFiles());

    }

    public static void traverseFile(String fileName,File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                traverseFile(file);
            } else {
                System.out.println(file.getPath() + file.getName() + "是一个文件, 数量为 1");
            }
        }

    }


    public static void lsFile(String path){
        File file = new File(path);

        if(!file.exists() || !file.isDirectory()){
            return;
        }
        File[] subFiles = file.listFiles();

        List<File> fileList = new ArrayList<>();
        List<File> dirList = new ArrayList<>();
        for (File subFile : subFiles) {
            if (subFile.isDirectory()){
                dirList.add(subFile);
            }else{
                fileList.add(subFile);
            }
        }

        System.out.printf("%s 有%d个文件夹、%d个文件",file.getAbsolutePath(),dirList.size(),fileList.size());
        System.out.println();

        for (File f : dirList) {
            System.out.println(f.getName());
        }
        for (File f : fileList) {
            System.out.println(f.getName());
        }

        for (File f : dirList) {
            lsFile(f.getAbsolutePath());
        }
    }

    // c:dir  有n个文件夹、n个文件
    // dsfsfsdfds
    // fsdfdsfsdf
    //  sdfdsfsdf
    // a.txt
    // b.txt

    //
}
