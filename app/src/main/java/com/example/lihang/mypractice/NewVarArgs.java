package com.example.lihang.mypractice;

/**
 * Created by lihang on 2017/9/27.
 */

public class NewVarArgs {
    static void printArray(Object... args){
        for(Object obf:args){
            System.out.print(obf+"");
            System.out.println();
        }
    }

    public static void main(String[] args){
//        printArray(new Integer(47),new Float(3.15),new Double(11.11));
//        printArray("1","2","3");
//        printArray(new Integer[]{1,2,3,4});
//        printArray();
    }
}
