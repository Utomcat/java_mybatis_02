package com.ranyk.mybatis.variable;

/**
 * ClassName:MyTest6
 * Description:
 *
 * @author ranyi
 * @date 2020-06-22 15:52
 * Version: V1.0
 */
public class MyTest6 {

    private final int SHARED_SHIFT = 16;

    public static void main(String[] args) {
        MyTest6 myTest6 = new MyTest6();
        System.out.println(myTest6.two2ten(myTest6.SHARED_SHIFT));
        System.out.println((1 << myTest6.SHARED_SHIFT));
        System.out.println(myTest6.two2ten((1 << myTest6.SHARED_SHIFT) - 1));
    }


    public String two2ten(int num){

        StringBuffer result = new StringBuffer();
        int divisor = 2;
        int dividend = num;
        int residue = 0;
        int quotient = 0;

        if (num == 0){
           return  "0";
        }

        if (num == 1){
            return "1";
        }

        while (true){
            quotient = dividend / divisor;
            residue = dividend % divisor;

            if ( 0 == quotient && 0 == residue ){
                return result.reverse().toString();
            }else {
                result.append(String.valueOf(residue));
                dividend = quotient;
            }
        }
    }


}
