package com.example.lihang.mypractice;

import com.example.lihang.mypractice.activity.Print;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lihang on 2017/9/28.
 */



class Homer{
    char doh(char c){
        Print.print("doh(char)");
        return c;
    }
    float doh(float f){
        Print.print("doh(float)");
        return f;
    }
}

class Lh1 implements Lh{

    private String name;


    public Lh1(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }

    void haha(String str){
        Print.print(this+str);
    }

    @Override
    public void run(String s) {

    }
}

class CollectionSeauence extends AbstractCollection<String>{

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}

public class Bart extends Homer {


    public static void main(String... args){
//        Bart bart=new Bart();
//        bart.doh('a');
//        bart.doh(1);
//        bart.doh(new MilHouse());
//        for (SB sb:SB.values()){
//            Print.print(sb+"");
//        }




        };

    }
    enum SB{
        HH,JD,DJ
    }

    class Hj{}

