package com.prasad.ricemarketing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Window;


public class SplashActivity extends Activity
{

        int deviceWidth = 0;
	    int deviceHeight = 0;
		 // Splash screen timer
	    private static int SPLASH_TIME_OUT = 5000;


		public void onCreate(Bundle savedInstanceState)
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			super.onCreate(savedInstanceState);

			setContentView(R.layout.splash);

			DisplayMetrics metrics = this.getResources().getDisplayMetrics();
       		deviceWidth = metrics.widthPixels;
       		deviceHeight = metrics.heightPixels;
       		System.out.println("screen width in pixels "+deviceWidth+" height "+deviceHeight);


		}
	public void onStart()
		{
			super.onStart();
			new gettingNetworkStatus().execute();
		}

		public class gettingNetworkStatus extends AsyncTask<Void, Void, Void>
		{

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				Void SucessFlag = null;
				try{

				}
				catch(Exception e){
					System.out.println("in doInBackground() exception"+e);
				}
		    	return SucessFlag;
			}

		  protected void onPostExecute(Void flag)
		  {
			  System.out.println("in onPostExecutet()");

					new Handler().postDelayed(new Runnable()
					{
			            @Override
			            public void run()
			            {
			            startActivity(new Intent(SplashActivity.this,SignInActivity.class));
			            }
			        }, SPLASH_TIME_OUT);
			}
	  }


		
}

