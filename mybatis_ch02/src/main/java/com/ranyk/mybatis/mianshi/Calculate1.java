package com.ranyk.mybatis.mianshi;

import java.util.Stack;

/**
 * 2 * @Author: zhouqy
 * 3 * @Description:
 * 4 * @Date: 2020/6/17 18:05
 * 5
 */
public class Calculate1 {

    public static Stack StrToStack(String str) {
        String[] split = str.split("");
        int len = split.length;
        Stack<String> stack = new Stack();
        for (int i = 0; i < len; i++) {
            stack.push(split[i]);
        }
        return stack;
    }
    public static Stack offset(Stack<String> stack){

        Stack result = new Stack();
        String value = null;
        boolean flag = false;
        while (!stack.isEmpty()){
            String pop = stack.pop();
            if(!pop.equals(value)){
                value = pop;
                result.push(pop);
            }else{
                flag =true;
                if(!result.isEmpty()&&result.peek().equals(value)){
                    result.pop();
                }
            }
        }
        if(flag){
            return offset(result);
        }
        return result;
    }
    public static void print(Stack stack) {
        System.out.println(stack.toString());
    }
    public static String getMinResult(String str){
        Stack stack = offset(StrToStack(str));
        print(stack);
        StringBuffer a = new StringBuffer();
        for (Object o : stack) {
            a.append(o);
        }
        return a.toString();
    }

}
