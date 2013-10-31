package com.philips.lighting.quickstart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartLights extends Activity {
	
	private GameLogic gameLogic;
	private TextView textValueRoundNumber, textValueUserScore;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startlights);
        
        Button goButton = (Button) findViewById(R.id.lightsStart);
        goButton.setOnClickListener(buttonListener);
        
        gameLogic = new GameLogic( this );
        
        textValueRoundNumber = (TextView) findViewById(R.id.textValueRoundNumber);
        
        System.out.println( "roundNum: " + gameLogic.getRoundNr() );
        
        textValueRoundNumber.setText( ""+gameLogic.getRoundNr() );
        textValueUserScore = (TextView) findViewById(R.id.textValueUserScore);
        textValueRoundNumber.setText( "" );
        
    }

	//anonymous inner class
		private OnClickListener buttonListener = new OnClickListener()
		{
			public void onClick(View v)
			{
				if( v.getId() == R.id.lightsStart )
				{
					System.out.println("go....");
					
					
			        gameLogic.declareColors();
			        try {
			        	gameLogic.startRound();
			        }catch (Exception e) {}
					
					Intent newGame = new Intent(StartLights.this, GameActivity.class);
					//newGame.putExtra("countries", countries);
					
					//Integer[] array = gameLogic.arrayPattern.toArray(new Integer[0]);
					//int[] array = (int[]) gameLogic.arrayPattern.toArray();
					int[] array = new int[gameLogic.arrayPattern.size()];
					for(int k=0; k < array.length; k++)
					{
						array[k] = gameLogic.arrayPattern.get(k);
					}
					
					
					for(int k=0; k < array.length; k++)
					{
						System.out.println( array[k] );
					}
					
					newGame.putExtra("answers", array);
					//newGame.putIntegerArrayListExtra("answers", array);
					//newGame.putExtra(name, value) 
					
					startActivityForResult(newGame, 1);
				}
			}

		};
	
}
