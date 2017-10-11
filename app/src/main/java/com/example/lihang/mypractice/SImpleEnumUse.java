package com.example.lihang.mypractice;

/**
 * Created by lihang on 2017/9/28.
 */
 enum Spiciness{
    NOT,MEDIUM,MILD,HOT,FLAMING
}

public class SImpleEnumUse {

    Spiciness degree;

    public SImpleEnumUse(Spiciness degree){
        this.degree=degree;
        print(degree.toString());
    }

    public void describe(){
//        print("this burrito is");
        switch (degree){
            case NOT:
                print("this burrito is not ");
                break;
            case MEDIUM:
                print("this bruttio is mwdium");
                break;
            default:
                print("no idea");
        }
    }
    public static void main(String... args){

        SImpleEnumUse enumUse=new SImpleEnumUse(Spiciness.HOT);
        enumUse.describe();
    }

    static void print(String str){
        System.out.println(str);
    }

   public class Spiltfire{

    }
}

