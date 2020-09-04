package com.ranyk.mybatis.annotation.annotation03.annotationProcessor;

/**
 * ClassName:MealFactory
 * Description:工厂注解处理器类
 *
 * @author ranyi
 * @date 2020-08-07 10:27
 * Version: V1.0
 */
public interface BeanFactory {

    /**
     * 获取指定id的实例对象
     * @param id 用于确定应实例化哪个项目的标识符
     * @return 返回 Object 对象
     * @throws Exception
     */
    Object getBean(String id) throws Exception;

    /**
     * 通过指定的 id 和 对应的类型获取实例对象
     * @param id 用于确定应实例化哪个项目的标识符
     * @param type 类型
     * @return 返回 Object 对象
     * @throws Exception
     */
    Object getBean(String id,Class<?> type) throws Exception;

}
