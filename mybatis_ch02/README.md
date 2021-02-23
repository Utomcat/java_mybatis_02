# 说明

## 项目说明

### 1. 此项目用于学习`java`的基础知识，如`String`类中split方法、substring方法的使用...

### 2. 学习一些常用的处理方法的编写，如将字符串中的每个字符的的个数进行统计、从某个字符串中查询某个字符的个数...

### 3. 注解的学习

> 注解的定义： 如下是一个 `Factory` 注解的定义
```java
    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Factory {
    
        /**
         * 工厂的名称
         */
        Class type() default Class.class;
    
        /**
         * 用于确定应实例化哪个项目的标识符
         */
        String id() default "";
    
    }
```

> 注解的定义是使用 `@interface` 来定义
> 对于注解的定义有几个元注解使用，用来对注解进行修饰
> 1. `@Target` : 标识注解所使用的范围,其定义的属性值为: `ElementType` ,取值范围如下：
>   1.  `CONSTRUCTOR` : 构造器的声明
>   2.  `FIELD` : 域声明（包括enum实例）
>   3.  `LOCAL_VARIABLE` : 局部变量声明
>   4.  `METHOD` : 方法声明
>   5.  `PACKAGE` : 包声明
>   6.  `PARAMETER` : 参数声明
>   7.  `TYPE` : 类、接口（包括注解类型）或enum声明
> 2. `@Retention` : 表示需要在什么级别保存该注解信息,其定义的属性值为: `RetentionPolicy` ,取值范围如下：
>   1.  `SOURCE` : 注解将被编译器丢弃
>   2.  `CLASS` : 注解在class文件中可用，但会被虚拟机丢弃
>   3.  `RUNTIME` : 虚拟机将在运行期间保留注解，因此可以通过反射机制读取注解的信息 (一般自定义注解均是使用该类型，以方便利用反射进行对其注解的处理)
> 3. `@Document` : 在javadoc中包含注解
> 4. `@Inherited` : 允许子类继承该注解

> 一般在对注解进行定义后，会同时定义一个对注解的处理类，以便对其进行的注解进行处理，如示例中定义的 `Factory` (工厂注解)，会定义一个对工厂注解进行处理的接口 `BeanFactory`,<br/>
> 然后在其实现类 `AnnApplicationContext` 中对使用 `Factory` 注解的类进行处理(获得所有使用 `Factory` 注解的类,然后创建其具体的实例，存放于一个 `Map` 集合中这样通过工厂方式进行<br/>
> 创建的实例就可以通过该类进行在使用前将所需要的实例对象创建好，之后将会直接通过 `key` 从 `Map` 中获取即可),之后即使用注解进行工厂模式的定义，使用前需要先通过工厂接口获得所有的实例，<br/>
> 在通过 `key` 获得对应的实例对象。

><font color="red" size="4px">注意：在使用自定义注解的时候，主要注意两个类，一个是对注解的扫描类 `AnnotationScanner` , 一个是对注解的处理类 `AnnApplicationContext` ,具体代码如下</font>

> `AnnotationScanner.java`
```java
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
```

> `AnnApplicationContext.java`
```java

import com.ranyk.mybatis.annotation.util.AnnotationScanner;
import com.ranyk.mybatis.annotation.annotation03.annotationDefinition.Factory;
import com.ranyk.mybatis.annotation.annotation03.annotationProcessor.BeanFactory;
import com.ranyk.mybatis.exception.MyException;

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
```


### 4. java 操作 Excel 报表

> [参考链接一 【Java】JXL和POI操作Excel](https://www.cnblogs.com/dflmg/p/5977582.html)
> [参考链接二 Java生成和操作Excel文件](https://www.cnblogs.com/mingforyou/p/3282922.html)
