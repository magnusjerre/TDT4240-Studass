package com.example.magnus.closingthegame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.EmptyStackException;
import sheep.game.Game;

public class MainActivity extends ActionBarActivity {

    Game game;  //Store the game as a property so that it can be accessed later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializing the game will be done in the onResume method instead
    }

    @Override
    public void onResume() {
        super.onResume();
        //The onResume method will be responsible for initializing the game stack and pushing the
        //title screen on the stack
        game = new Game(this, null);
        game.pushState(new TitleScreen());
        setContentView(game);
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            //For as long as there are any states pushed onto the stack we want to pop the states
            //from the stack. When there are no more states to be popped, an EmptyStackException will
            //be thrown and then caught to avoid a crash.
            while (true) {
                game.popState();
            }
        } catch (EmptyStackException e) {

        }
    }
}
