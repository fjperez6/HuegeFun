package com.philips.lighting.quickstart;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amazon.ags.api.AGResponseHandle;
import com.amazon.ags.api.leaderboards.LeaderboardsClient;
import com.amazon.ags.api.leaderboards.SubmitScoreResponse;

public class GameActivity extends Activity
{
	//
	//PHBridge bridge;
	//private PHHueSDK phHueSDK;
	//
	private Button buttonRed, buttonYellow, buttonBlue, buttonGreen;
	//
	private ArrayList<Integer> userList;
	private ArrayList<Integer> answerList;
	
	private TextView feedback;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        userList = new ArrayList<Integer>();
        
        /*answerList = new ArrayList<Integer>();
        answerList.add(1); answerList.add(1);
        answerList.add(4); answerList.add(3);*/
        
        //get array (with key "countries") from the input bundle data
        Bundle data = getIntent().getExtras();
        int[] p = data.getIntArray("answers");
        
        answerList = new ArrayList<Integer>();
        
        feedback = (TextView) findViewById(R.id.feedback);
        
        //loop through the array and set class variable words from bundle data
        if( p != null)
        {
        	System.out.println("Not Null");
        	//answerList = new ArrayList<Integer>();
        	for(int k=0; k < p.length; k++)
        	{
        		answerList.add( p[k] );
        	}	
        }
        else
        {
        	System.out.println("NULL");
        }
        
        System.out.println( answerList.toString() );
        
        buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(buttonListener);
        buttonYellow = (Button) findViewById(R.id.buttonYellow);
        buttonYellow.setOnClickListener(buttonListener);
        buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonBlue.setOnClickListener(buttonListener);
        buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(buttonListener);
        
    }

	//anonymous inner class
		private OnClickListener buttonListener = new OnClickListener()
		{
			public void onClick(View v)
			{

				if( v.getId() == R.id.buttonRed )
				{
					userList.add( 0 );
				}
				else if( v.getId() == R.id.buttonYellow )
				{
					userList.add( 1 );
				}
				else if( v.getId() == R.id.buttonBlue )
				{
					userList.add( 2 );
				}
				else if( v.getId() == R.id.buttonGreen )
				{
					userList.add( 3 );
				}
				
				if( userList.size() == answerList.size() )
				{
					Intent intent = getIntent();
					Bundle b = new Bundle();
					//int scoreChange = 0;
					
					String feedbackString = "";
					
					if( userList.equals(answerList) )
					{
						System.out.println("true-------");
						//scoreChange = 1;
						feedbackString = "Correct!";
						setResult(Activity.RESULT_OK, intent);
				        finish();
					}
					else
					{
						finish();
						finish();
						finish();
						Intent fail = new Intent(GameActivity.this, FailActivity.class);
						startActivityForResult(fail, 1);
						
						//scoreChange = -1;
						feedbackString = "Incorrect!";
						GameLogic.discoLights();
						LeaderboardsClient lbClient = MainActivity.agsClient.getLeaderboardsClient();
						AGResponseHandle<SubmitScoreResponse> handle = lbClient.submitScore("huege_leaderboard", GameLogic.roundNr);
					}
					

			        feedback.setText(feedbackString);
					
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			        //b.putParcelableArray("countries", countries);
			        intent.putExtras(b);
			        
			        setResult(Activity.RESULT_OK, intent);
			        finish();*/
				}
				
			}

		};
	
}
