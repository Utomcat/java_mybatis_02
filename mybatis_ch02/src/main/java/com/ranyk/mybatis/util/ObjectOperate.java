package com.ranyk.mybatis.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * CLASS_NAME: ObjectOperate<br/>
 * Description: 对象操作工具类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public class ObjectOperate {


    /**
     * 验证对象是否为空静态方法
     *
     * @param obj 需要判断的对象
     * @return 如果判断对象为空则, 返回true;不为空则返回,false;
     */
    @SuppressWarnings("rawtypes")
    public static Boolean objectIsEmpty(Object obj) {
        //1. 判断对象本身是否为 null
        if (null == obj) {
            return true;
        }
        //2. 当对象是字符串时,使用字符串长度是否为0,判断
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        //3. 当对象是 Collection 集合时,先将对象转成 Collection 集合,再判断是否为空
        if (obj instanceof Collection) {
            Collection castCollectionResult = cast(obj);
            return (castCollectionResult).isEmpty();
        }
        //4. 当对象是 Map 集合时,先将对象转成 Map 集合,再判断是否为空
        if (obj instanceof Map) {
            Map castMapResult = cast(obj);
            return (castMapResult).isEmpty();
        }
        //5. 当对象是数组时,先将对象转成数组,再判断是否为空
        if (obj instanceof Object[]) {
            Object[] objects = (Object[]) obj;
            if (objects.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : objects) {
                if (!objectIsEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        //6. 默认返回 false,即验证对象不为空
        return false;
    }


    /**
     * 判断对象的所有属性是否均为空
     *
     * @param obj 需要判断的对象
     * @param isObj 判断的对象是否为自定义Class的实例化对象
     * @return 如果判断对象属性全为空,则返回true;不为空则返回,false;
     */
    public static Boolean objectIsEmpty(Object obj, Boolean isObj) {
        //1. 判读是否是对象,不是则直接调用判空方法
        if (isObj) {
            //1.1. 获取对象的class 对象
            Class<?> objClass = obj.getClass();
            //1.2. 获取class对象的所有字段(Field)对象
            Field[] fields = objClass.getDeclaredFields();
            //1.3. 初始化返回结果值,默认为true(即对象属性全为空)
            boolean empty = true;
            //1.4. 遍历字段数组,逐个字段判断是否为空
            for (Field field : fields) {
                //1.4.1. 设置私有属性的可见性
                field.setAccessible(true);
                //1.4.2. 获取属性值,并判断有关属性是否为空
                try {
                    //1.4.2.1. 获取对象的属性值
                    Object value = field.get(obj);
                    //1.4.2.2. 判断属性值是否为空
                    empty = objectIsEmpty(value);
                    //1.4.2.3. 存在一个属性值不为空的话则退出循环
                    if (!empty){
                        break;
                    }
                } catch (Exception e) {
                    log.error("在对对象的属性值是否全为空判断时出错,错误为: " + e.getMessage());
                    return true;
                }
            }
            //1.5. 返回遍历结果
            return empty;
        }
        //2. 不为对象,直接调用判空值方法
        else {
            return objectIsEmpty(obj);
        }
    }


    /**
     * 强制类型转换方法
     *
     * @param obj 需要进行类型转换的对象
     * @param <T> 需要转换成的类型
     * @return 返回转换后的对象
     */
    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj) {
        return (T) obj;
    }


}
