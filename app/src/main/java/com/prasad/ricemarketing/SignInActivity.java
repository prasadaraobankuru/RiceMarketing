package com.prasad.ricemarketing;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SignInActivity extends Activity implements OnClickListener
{

	public  static  String networkstatus,mobileno,password,uname,company,username,pswd;

	EditText et_Username, et_Password;
	TextView btn_Login;
	TextView SignUp;
	ProgressDialog loadProgress;
	ImageView showpassword;
	boolean flag=true;
	int deviceWidth = 0;
	int deviceHeight = 0;
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newlogin);
		
	    //To get the device width and height
			DisplayMetrics metrics = this.getResources().getDisplayMetrics();
			deviceWidth = metrics.widthPixels;
			deviceHeight = metrics.heightPixels;
			System.out.println("screen width in pixels "+deviceWidth+" height "+deviceHeight); 
        	et_Username = (EditText) findViewById(R.id.et_mobileno);
		    et_Password = (EditText) findViewById(R.id.et_password);
		    btn_Login = (TextView) findViewById(R.id.signinBtn);		
	  	    SignUp = (TextView) findViewById(R.id.signUp);
	  	  showpassword=(ImageView)findViewById(R.id.showpassword);

		pref = this.getSharedPreferences("marketing", MODE_PRIVATE);

		if (pref.contains("mobileno")) {

			mobileno=pref.getString("mobileno", "");
			password=pref.getString("password", "");
			uname=pref.getString("uname", "");
			company=pref.getString("company", "");

			Intent intent = new Intent(SignInActivity.this, ShopsListActivity.class);
			startActivity(intent);
		}

	  	showpassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
		if(flag){
				
			et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
			et_Password.setSelection(et_Password.getText().length());
				flag=false;
				System.out.println("hiiiiiiiiiii"+flag);
			}

			else{
				
				et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				et_Password.setSelection(et_Password.getText().length());
				flag=true;
				System.out.println("helloo"+flag);
			}
			}
			
			
		});
           }
	
	
	    public void onStart() 
	{
		super.onStart();
		// To check internet cavailability through asynchronous
		new gettingNetworkStatus().execute();
	}

	public class gettingNetworkStatus extends AsyncTask<Void, Void, Void>
	{
		 protected void onPreExecute()
		   {
			 loadProgress = ProgressDialog.show(SignInActivity.this, "Getting Owner Response",
						"Please Wait....", true);
		   }

		 @Override
		protected Void doInBackground(Void... params) 
		{
			// TODO Auto-generated method stub
			Void SucessFlag = null;
			try {
				

				System.out.println("the networkstatus is " + networkstatus);
			} catch (Exception e) {
				System.out.println("in doInBackground() exception" + e);
			}
			return SucessFlag;
		}

		protected void onPostExecute(Void flag) 
		{
			    System.out.println("in onPostExecutet()");
	            btn_Login.setOnClickListener(SignInActivity.this);
				SignUp.setOnClickListener(SignInActivity.this);
				
				loadProgress.dismiss();
			
		}
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		 case R.id.signinBtn:
			 

			 
			 if(validate())
			 {
				 mobileno=pref.getString("mobileno", "");
				 password=pref.getString("password", "");
				username= et_Username.getText().toString();
				pswd= et_Password.getText().toString();

				 if (username.equalsIgnoreCase(mobileno)&&pswd.equalsIgnoreCase(password)) {

					 Intent sign = new Intent(getApplicationContext(), ShopsListActivity.class);
					 startActivity(sign);
				 }
				 else{

					 Toast.makeText(getApplicationContext(),"Please Enter Valid Credintials",Toast.LENGTH_LONG);
				 }
        	}
			break;


		case R.id.signUp:
			 
		Intent sign = new Intent(getApplicationContext(), SignUpActivity.class);
			startActivity(sign);

			 break;

		default:
			break;
		}

	
	
			
	  	};
	
		public boolean validate() 
		{
			boolean flag = false;
  					if (et_Username.getText().toString().trim().equals("")) {

  						et_Username.setError("Please Enter Mobile No");
  						//Helper.showShortToast(SignUpActivity.this,"Please Enter Mobile No");
  						//Helper.showCustomToast(SignUpActivity.this, "no", "Please Enter Mobile No", Toast.LENGTH_LONG);
  						et_Username.requestFocus();

  					} else if (et_Username.getText().toString().trim().length() < 10) {
  						et_Username.setError("Please enter valid Mobile Number");
  						//Helper.showShortToast(SignUpActivity.this,"Please enter valid Mobile Number");
  						//Helper.showCustomToast(SignUpActivity.this, "no", "Please enter valid Mobile Number", Toast.LENGTH_LONG);
  						et_Username.requestFocus();
  					} else if (!(et_Username.getText().toString().trim().startsWith("9")
  							|| et_Username.getText().toString().trim().startsWith("8") || et_Username
  							.getText().toString().trim().startsWith("7"))) {
  						 et_Username.setError("Mobile Number must start with 9,8,7");
  						//Helper.showShortToast(SignUpActivity.this,	"Mobile Number must start with 9,8,7");
  						//Helper.showCustomToast(SignUpActivity.this, "no", "Mobile Number must start with 9,8,7", Toast.LENGTH_LONG);
  						et_Username.requestFocus();
  						et_Username.setText("");
  					} 
  					else if (et_Password.getText().toString().trim().length() == 0) {
  						//AlertBox(this, "Please Enter Password");
  						et_Password.setError("Please Enter Password");
  						et_Password.requestFocus();
  					} else {
  						
  						flag = true;
  			}
					return flag;
  	        
  	    
  	
  	 } 
public static void AlertBox(Context ctx, String msg) 
	{
		new AlertDialog.Builder(ctx).setTitle("Message!").setMessage(msg)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).show();
	}

	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}
	/*
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

			new AlertDialog.Builder(this)
					.setTitle("Message!")
					.setMessage("Do You want to Exit? This Application")
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Intent intent = new Intent(
											Intent.ACTION_MAIN);
									intent.addCategory(Intent.CATEGORY_HOME);
									intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									startActivity(intent);
								}
							})
					.setNegativeButton("NO",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).show();
		}

		return super.onKeyDown(keyCode, event);
		}
		*/
	
	
	
}
