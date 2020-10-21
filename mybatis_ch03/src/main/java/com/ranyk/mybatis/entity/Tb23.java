package com.ranyk.mybatis.entity;

/**
 * (Tb2)实体类
 *
 * @author makejava
 * @since 2020-05-24 21:31:45
 */

public class Tb23 {
    private static final long serialVersionUID = 571796332287869377L;
    /**
    * 主键
    */
    private String id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 课程
    */
    private String course;
    /**
    * 成绩
    */
    private String score;
    /**
     * 测试字段
     */
    private String testFiled;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setTestFiled(String testFiled) {
        this.testFiled = testFiled;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    /*public String getName() {
        return null;
    }*/

    public String getCourse() {
        return course;
    }

    public String getScore() {
        return score;
    }

    public String getTestFiled() {
        return testFiled;
    }
}