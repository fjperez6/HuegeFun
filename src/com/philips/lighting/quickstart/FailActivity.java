package com.philips.lighting.quickstart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;

public class FailActivity extends Activity
{
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
	}
}