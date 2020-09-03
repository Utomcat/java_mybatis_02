package com.ranyk.mybatis_ch02.annotation.annotation03.annotationProcessor.impl;

import com.ranyk.mybatis_ch02.annotation.util.AnnotationScanner;
import com.ranyk.mybatis_ch02.annotation.annotation03.annotationDefinition.Factory;
import com.ranyk.mybatis_ch02.annotation.annotation03.annotationProcessor.BeanFactory;
import com.ranyk.mybatis_ch02.exception.MyException;

import java.io.IOException;
import java.util.*;

/**
 * ClassName:AnnApplicationContext
 * Description:工厂接口实现类
 *
 * @author ranyi
 * @date 2020-08-07 13:44
 * Version: V1.0
 */
public class AnnApplicationContext implements BeanFactory {

    /**
     * 定义的用来进行存放具体的实例的 Map 集合
     */
    private Map<String, Object> factoryClasses = new LinkedHashMap<String, Object>();

    /**
     * 用来存放指定包下的所有的 Class 对象的 Set 集合，用 Set 集合的原因是每个类对应的 Class 对象只有一个
     */
    private Set<Class<?>> classSet = new HashSet<>();

    /**
     * 自定义的注解扫描对象
     */
    AnnotationScanner annotationScanner = new AnnotationScanner();

    /**
     * 定义的两个 Map 的 key
     */
    /**
     * 定义的对应的工厂需要创建实例的类型(对应的类名) 存放的 key
     */
    private static final String FACTROY_TYPE_KEY = "type";
    /**
     * 定义存放实例的 key
     */
    private static final String FACTROY_INSTANCE_KEY = "instance";


    /**
     * 对应工厂实现类的构造函数
     *
     * @param packageName 需要扫描的包名
     */
    public AnnApplicationContext(String packageName) throws Exception {
        try {
            /**
             * 获得指定 classPath 下的所有类，获得对应 class 的 Set 集合
             */
            classSet = annotationScanner.getClassFile(packageName);

            /**
             * 遍历所有的类，找出所有含有 Factory 注解的类，放进 linkedHashMap 中
             */
            for (Class<?> cls : classSet) {
                Factory factory = cls.getAnnotation(Factory.class);
                if (factory != null) {
                    try {

                        /**
                         * 判断存放存放实例的对象中是否已经存在相同的 key 的对象，存在则抛出异常; 反之正常进行;
                         */
                        if (factoryClasses.containsKey(factory.id())) {
                            throw new MyException("系统中存在两个 id 为 " + factory.id() + " 的Bean定义,系统中不能声明或创建两个相同 ID 的 Bean ,请核查！");
                        }

                        /**
                         * 将含有对应 Factory 注解的对象放进 Map 集合中，对应的 key 为声明的id
                         */
                        Map<String, Object> objectMap = new HashMap<>(16);
                        objectMap.put(FACTROY_TYPE_KEY, factory.type());
                        objectMap.put(FACTROY_INSTANCE_KEY, cls.newInstance());

                        factoryClasses.put(factory.id(), objectMap);

                    } catch (IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取指定id的实例对象
     *
     * @param id 用于确定应实例化哪个项目的标识符
     * @return
     */
    @Override
    public Object getBean(String id) throws Exception{

        return getInstance(id).get(FACTROY_INSTANCE_KEY);
    }

    /**
     * 通过指定的 id 和 对应的类型获取实例对象
     *
     * @param id   用于确定应实例化哪个项目的标识符
     * @param type 类型
     * @return
     */
    @Override
    public Object getBean(String id, Class<?> type) throws Exception {

        Map<String, Object> bean = getInstance(id);

        /**
         * 判断当前根据ID获得的实例对象和所需要类型的实例对象是否相同，不同则抛出异常
         */
        if (!Objects.equals(bean.get(FACTROY_TYPE_KEY), type)) {

            StringBuilder stringBuilder = new StringBuilder();

            /**
             * 获取对应的类型
             */
            int index = type.toString().lastIndexOf(".") + 1;
            stringBuilder.append(type.toString().substring(index)).append(".class");

            throw new MyException("没有找到 ID 为 " + id + " , type 为 " + stringBuilder.toString() + " 的 Bean,请核实有关参数书写或定义是否正确！");

        }
        return bean.get(FACTROY_INSTANCE_KEY);
    }

    /**
     * 获取对应id的实例
     *
     * @param id 用于确定对应实例化那个项目的标识符
     * @return 返回获得的 Map 对象，该 Map 对象有两个值 一个是该实例是属于那个类型，另一个是具体的实例对象
     */
    private Map<String, Object> getInstance(String id) throws Exception {
        /**
         * 直接通过 id 从 Map 中获取对应对象
         */
        Map<String, Object> bean = (Map<String, Object>) factoryClasses.get(id);

        /**
         * 当没有获取到对象时，首字母小写获取对象
         */
        if (Objects.isNull(bean)) {
            StringBuilder strBuilder = new StringBuilder();
            char[] chars = id.toCharArray();
            /**
             * 首字母小写
             */
            for (int i = 0; i < chars.length; i++) {
                /**
                 * 将传入的 id 的首字母转换为小写的
                 */
                if (0 == i) {
                    strBuilder.append(Character.toLowerCase(chars[i]));
                } else {
                    strBuilder.append(chars[i]);
                }
            }

            bean = (Map<String, Object>) factoryClasses.get(strBuilder.toString());

            if (Objects.isNull(bean)) {

                throw new MyException("bean ID 为 " + id + "的对象为找到，请检查是否已经定义或对应id的大小写错误！");

            }

        }

        return bean;
    }
}
