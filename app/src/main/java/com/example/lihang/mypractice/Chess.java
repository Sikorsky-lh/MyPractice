package com.example.lihang.mypractice;

import com.example.lihang.mypractice.activity.Print;

/**
 * Created by lihang on 2017/9/28.
 */

class Game{
    Game(int i){
        Print.print("Game constructor");
    }

}
class BoardGame extends Game{

    BoardGame(int i) {
        super(i);
        Print.print("BoardGame constructor");
    }
}

public class Chess extends BoardGame{
    Chess(int i) {
        super(i);
        Print.print("Chess constructor");
    }
    public static void main(String... atrs){
        Chess chess=new Chess(2);
    }
}
