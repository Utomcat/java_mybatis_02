package com.ranyk.mybatis.io.fileio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * ClassName:FileIoTest02
 * Description:IO测试二
 *
 * @author ranyi
 * @date 2020-09-21 22:47
 * Version: V1.0
 */
public class FileIoTest02 {

    public static void main(String[] args) {


    }

    public static InputStream getInputStream(){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return inputStream;
    }

}
