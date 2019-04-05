package org.example.pacman;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {

	Game game;

    int h,w; //used for storing our height and width of the view

	public void setGame(Game game)
	{
		this.game = game;
	}


	/* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
	public GameView(Context context) {
		super(context);

	}

	public GameView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}


	public GameView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context,attrs,defStyleAttr);
	}

	//In the onDraw we put all our code that should be
	//drawn whenever we update the screen.
	@Override
	protected void onDraw(Canvas canvas) {

		if(!game.InitCoins()){
			game.SetCoins(true);
			game.initCoins();
		}

		if(!game.InitEnemies()){
			game.SetEnemies(true);
			game.initEnemies();
		}

		//Here we get the height and weight
		h = canvas.getHeight();
		w = canvas.getWidth();
		//update the size for the canvas to the game.
		game.setSize(h,w);
		Log.d("GAMEVIEW","h = "+h+", w = "+w);
		//Making a new paint object
		Paint paint = new Paint();
		canvas.drawColor(Color.BLUE); //clear entire canvas to blue color

		//draw the pacman
		canvas.drawBitmap(game.getPacBitmap(), game.getPacx(),game.getPacy(), paint);
		for (GoldCoin g: game.getCoins()) {
			if (g.isTaken() == false){
				canvas.drawBitmap(game.getCoinbitmap(), g.getGoldx(), g.getGoldy(), paint);
			}
		}

		for (Enemy e: game.getEnemies()) {
			canvas.drawBitmap(game.getGhostBitmap(), e.getGhostx(), e.getGhosty(), paint);
		}


		super.onDraw(canvas);
	}

}
