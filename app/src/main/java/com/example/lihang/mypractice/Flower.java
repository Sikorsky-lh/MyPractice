package com.example.lihang.mypractice;

/**
 * Created by lihang on 2017/9/27.
 */

class Soup {
    static int count;

    private Soup() {
    }

    private static Soup soup=new Soup();

    public static Soup makeSoup() {
        return soup;
    }

    void print(){
        System.out.println("this is only soup");
    }
}

public class Flower {
    int petalCount=0;
    String s="initial value";
    Flower(int petals){
        petalCount=petals;
        print("int arg only, petalCount= "+petalCount);
    }
    Flower(String ss){
        print("String arg only, s= "+ss);
        s=ss;
    }
    Flower(int petals,String s){
        this(petals);
        this.s=s;
        print(s);
    }
    static void print(String str){
        System.out.println(str);
    }
    static void print(boolean str){
        System.out.println(str);
    }

    public static void main(String[] args){
//        new Flower(5,"hahah");
        Soup soup1=Soup.makeSoup();
        Soup soup2=Soup.makeSoup();
        print(soup1.equals(soup2));
    }

}
