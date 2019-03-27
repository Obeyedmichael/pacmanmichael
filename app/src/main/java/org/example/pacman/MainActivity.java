package org.example.pacman;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    //reference to the main view
    GameView gameView;
    //reference to the game class.
    Game game;

    private Timer myTimer;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //saying we want the game to run in one mode only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        gameView =  findViewById(R.id.gameView);
        TextView textView = findViewById(R.id.points);
        game = new Game(this,textView);
        myTimer = new Timer();
        game.running = true; //should the game be running?
        //We will call the timer 5 times each second
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 200);


        game.setGameView(gameView);
        gameView.setGame(game);

        game.newGame();

        Button buttonLeft = findViewById(R.id.moveLeft);
        //listener of our pacman, when somebody clicks it
        buttonLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.move = 1;
            }
        });

        Button buttonRight = findViewById(R.id.moveRight);
        //listener of our pacman, when somebody clicks it
        buttonRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.move = 2;
            }
        });

        Button buttonUp = findViewById(R.id.moveUp);
        //listener of our pacman, when somebody clicks it
        buttonUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.move = 3;
            }
        });

        Button buttonDown = findViewById(R.id.moveDown);
        //listener of our pacman, when somebody clicks it
        buttonDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.move = 4;
            }
        });

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.running = true;
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v) {
                game.running = false;
            }
        });
    }



    @Override
    protected void onStop() {
        super.onStop();
        //just to make sure if the app is killed, that we stop the timer.
        myTimer.cancel();
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);

    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.
            // so we can draw
            if (game.running)
            {
                counter++;
                //update the counter - notice this is NOT seconds in this example
                //you need TWO counters - one for the time and one for the pacman

                switch (game.move)
                {
                    case 1: if(game.running) {game.movePacmanLeft(20);}
                    break;

                    case 2: if(game.running) {game.movePacmanRight(20);}
                    break;

                    case 3: if(game.running) {game.movePacmanUp(20);}
                    break;

                    case 4: if(game.running) {game.movePacmanDown(20);}
                }
            }

        }
    };





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this,"settings clicked",Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_newGame) {

            counter = 0;
            Toast.makeText(this,"New Game clicked",Toast.LENGTH_LONG).show();
            game.newGame();
            game.running = false;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //if anything is pressed - we do the checks here

}
