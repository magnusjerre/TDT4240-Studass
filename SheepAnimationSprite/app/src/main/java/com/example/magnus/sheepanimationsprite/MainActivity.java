package com.example.magnus.sheepanimationsprite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.EmptyStackException;

import sheep.game.Game;


public class MainActivity extends ActionBarActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getSize(Singleton.getInstance().screenSize);
        Singleton.getInstance().resources = getResources();
    }

    @Override
    public void onResume() {
        game = new Game(this, null);
        game.pushState(new GameState());
        setContentView(game);
        super.onResume();
    }

    @Override
    public void onPause() {
        try {
            while(true) {
                game.popState();
            }
        } catch (EmptyStackException e) {

        }
        super.onPause();
    }

}
