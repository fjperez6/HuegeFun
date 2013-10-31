package com.philips.lighting.quickstart;

//import com.amazon.device.ads.AdLayout;
//import com.amazon.device.ads.AdRegistration;
//import com.amazon.device.ads.AdSize;
//import com.amazon.device.ads.AdTargetingOptions;

import java.util.EnumSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amazon.ags.api.AmazonGamesCallback;
import com.amazon.ags.api.AmazonGamesClient;
import com.amazon.ags.api.AmazonGamesFeature;
import com.amazon.ags.api.AmazonGamesStatus;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;

public class MainActivity extends Activity
{

	private AdLayout adView;
	static public AmazonGamesClient agsClient;
	  
	AmazonGamesCallback callback = new AmazonGamesCallback() {
	        @Override
	        public void onServiceNotReady(AmazonGamesStatus status) {
	            //unable to use service
	        }
	        @Override
	        public void onServiceReady(AmazonGamesClient amazonGamesClient) {
	            agsClient = amazonGamesClient;
	            //ready to use GameCircle
	        }
	};
	 
	//list of features your game uses (in this example, achievements and leaderboards)
	EnumSet<AmazonGamesFeature> myGameFeatures = EnumSet.of(
	        AmazonGamesFeature.Leaderboards);    
	 
	@Override
	public void onResume() {
	    super.onResume();
	    AmazonGamesClient.initialize(this, callback, myGameFeatures);
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdRegistration.setAppKey("68b092f37bfc4bb9b11a8bad7f36ee92");
        
        Button goButton = (Button) findViewById(R.id.eventStart);
        goButton.setOnClickListener(buttonListener);
        
        AdRegistration.enableTesting(true);
        AdRegistration.enableLogging(true);
        
        // No explicit size requested = Auto Ad Size.      

      // this.adView = new AdLayout(this, AdSize.SIZE_600x90);

        //LinearLayout layout = (LinearLayout)findViewById(R.id.activity_main);



         //Set the correct width and height of the ad.

        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(600, 90);

        //layout.addView(this.adView, lp);
        
//     // Programmatically create the AmazonAdLayout      
//        this.adView = new AdLayout(this);
//        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
//        // Set the correct width and height of the ad.
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//           LinearLayout.LayoutParams.MATCH_PARENT,
//           LinearLayout.LayoutParams.MATCH_PARENT);
//        layout.addView(this.adView,lp);
     
        // If you declared AdLayout in your xml you would instead
        // replace the 3 lines above with the following line
        this.adView = (AdLayout) findViewById( R.id.adview );
        
        this.adView.loadAd( new AdTargetingOptions().enableGeoLocation(false)); // async task to retrieve an ad
        
        
//        GameLogic gameLogic = new GameLogic( this );
//        
//        gameLogic.declareColors();
//        
//        try {
//        gameLogic.startRound();
//        }catch (Exception e) {}
        
        
       /* try {
        	gameLogic.startRound();	
        }
        catch( Exception e) {}*/
        
    }

	//anonymous inner class
	private OnClickListener buttonListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			if( v.getId() == R.id.eventStart )
			{
				System.out.println("go....");
				Intent newGame = new Intent(MainActivity.this, StartLights.class);
				//newGame.putExtra("countries", countries);
				startActivityForResult(newGame, 1);
			}
		}

	};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.adView.destroy();
    }
}
