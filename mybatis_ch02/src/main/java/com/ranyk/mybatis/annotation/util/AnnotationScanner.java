package com.ranyk.mybatis.annotation.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * ClassName:AnnotationScanner
 * Description:注解扫描类
 *
 * @author ranyi
 * @date 2020-08-07 11:00
 * Version: V1.0
 */
@Slf4j
public class AnnotationScanner {

    public static final String CLASS_SUFFIX = ".class";

    private ClassLoader classLoader = getClass().getClassLoader();

    /**
     * 扫描指定包路径下的文件，获取该路径下的 class 对象的 Set 集合
     *
     * @param packageName 需要扫描的包路径
     * @return 返回 Set 集合
     * @throws IOException IO 异常
     */
    public Set<Class<?>> getClassFile(String packageName) throws IOException {

        HashMap<String, String> classMap = new HashMap<>(32);

        String path = packageName.replace(".", "/");

        /**
         * 通过 classLoader 加载文件，循环遍历文件，转换 class 文件
         */
        Enumeration<URL> urls = classLoader.getResources(path);

        while (urls != null && urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String protocol = url.getProtocol();

            if (Objects.equals("file",protocol)) {
                String decode = URLDecoder.decode(url.getFile(), "UTF-8");
                File file = new File(decode);
                if (file.isDirectory()) {
                    parseClassFile(file, classMap);
                } else {
                    throw new IllegalArgumentException("file must be directory");
                }
            }

        }

        Set<Class<?>> set = new HashSet<>(classMap.size());

        for (String key : classMap.keySet()) {
            String className = classMap.get(key);

            try {
                set.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return set;
    }

    /**
     * 将所有的class 都放进 Map 中
     *
     * @param file     放进的文件路径
     * @param classMap 被放进的 Map 集合
     */
    private void parseClassFile(File file, HashMap<String, String> classMap) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                parseClassFile(file1, classMap);
            }
        } else if (file.getName().endsWith(CLASS_SUFFIX)) {
            String name = file.getPath();
            name = name.substring(name.indexOf("classes") + 8).replace("\\", ".");
            addToClassMap(name, classMap);
        }
    }

    /**
     * 将对应的 class 放进 Map 集合中
     *
     * @param name     需要放进的 key 和 value
     * @param classMap 需要被放入的集合
     * @return 返回放入是否成功 默认返回 true
     */
    private boolean addToClassMap(String name, HashMap<String, String> classMap) {

        if (!classMap.containsKey(name)) {
            /**
             * 去掉 .class 后缀
             */
            classMap.put(name, name.substring(0, name.length() - 6));
        }

        return true;
    }

}
