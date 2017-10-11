package com.example.lihang.mypractice;

import android.widget.Toast;

import com.example.lihang.mypractice.activity.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihang on 2017/9/30.
 */

class Display{

    private String name;

    Display(String str){
        name=str;
    }
    @Override
    public String toString() {
        return name+" string";
    }
}
public class ExceptionsMethods {

    void f(){
        Print.print("f()");
    }

    static List toList(int index,List<Display> list){
        list.clear();
        for (int i=0;i<index;i++){
            list.add(new Display(i+""));
        }
        return list;
    }

    List<Display> displays=new ArrayList<>();

    public static void main(String[] args){
//        try {
//            throw new Exception("My Exception");
//        }catch (Exception e){
//            Print.print("Caught Exception");
//            Print.print("getMessage: "+e.getMessage());
//            Print.print("getLocalizedMessage: "+e.getLocalizedMessage());
//            Print.print("toString: "+e);
//            Print.print("printStackTrace: ");
//            e.printStackTrace();
//        }
        String str="Arline ate eight";

        ExceptionsMethods methods=new ExceptionsMethods();
        methods.displays=toList(10,methods.displays);
        System.out.println(methods.displays);

        System.out.println("-5678 abc".matches("^-\\d+\\sabc\\+"));
        System.out.println("+5678".matches("(-|\\+)\\d+"));
        System.out.println("+5678".matches("-?\\d+"));
    }
}
