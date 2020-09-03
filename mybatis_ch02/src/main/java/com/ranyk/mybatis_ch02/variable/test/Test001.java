package com.ranyk.mybatis_ch02.variable.test;

/**
 * ClassName:Test001
 * Description:
 *
 * @author s
 * ranyi
 * @date 2020-06-23 13:32
 * Version: V1.0
 */
public class Test001 {



    /*private final Type type1 = new Type("aaa",1);

    private Type0 type2 = new Type0("aaa",1);*/
    static String a = new String("aa");

    /*public static void main(String[] args) {
        Test001 test001 = new Test001();


        System.out.println("操作之前 a = " + a);
        test001.aa(a);
        System.out.println("操作之后 a = " + a);

        System.out.println( "操作前" + test001.type2.getName());
        test001.bb(test001.type2);
        System.out.println( "操作后" + test001.type2.getName());


    }

    public void aa(String a){
        a = "bb";
        System.out.println( "操作时" + a);
    }


    public void bb(Type0 a){
        a.setName("bbbb");
        System.out.println( "操作时" + a.getName());
    }*/



}


/*class Type0 {

    private String name;
    private int id;

    public Type0() {
    }

    public Type0(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}*/
