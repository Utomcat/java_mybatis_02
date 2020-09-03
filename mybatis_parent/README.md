# 项目说明

> 此项目用于对整个工程的依赖的版本进行控制，此工程下的其他地方的只需要引入依赖即可，无需指定对应的版本

**此工程下的所有项目均需要以此项目为父级，即所有的项目业务模块都需引入如下的内容：**
```xml
<parent>
    <groupId>com.ranyk</groupId>
    <artifactId>mybatis_parent</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</parent>
```
