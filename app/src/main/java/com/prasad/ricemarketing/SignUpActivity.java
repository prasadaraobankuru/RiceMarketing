package com.prasad.ricemarketing;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;



public class SignUpActivity extends Activity implements OnClickListener
{

	EditText et_name, et_mobileno, et_email, et_password,et_company;
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("[a-zA-Z0-9+._%-+]{1,100}" + "@"
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,10}" + "(" + "."
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,20}" + ")+");

	TextView mt_submitBtn;
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signupnew);
		 setTheme(R.style.MyMaterialTheme);
		 setTheme(android.R.style.Theme_Holo);
		 //To get the device width and height
		   
		
		et_name = (EditText) findViewById(R.id.et_name);		
		et_mobileno = (EditText) findViewById(R.id.et_mobileno);
	//	et_email = (EditText) findViewById(R.id.et_email);
		et_password = (EditText) findViewById(R.id.et_password);
		et_company = (EditText) findViewById(R.id.et_company);
	
		
		
		mt_submitBtn = (TextView) findViewById(R.id.mt_submitBtn);

		pref = this.getSharedPreferences("marketing", MODE_PRIVATE);
		
		
		
	}

	public void onStart() 
	{
		super.onStart();  
		// To check internet cavailability,, through asynchronous
		new gettingNetworkStatus().execute();
	}

	public class gettingNetworkStatus extends AsyncTask<Void, Void, Void> 
	{

		@Override
		protected Void doInBackground(Void... params)
		{
			// TODO Auto-generated method stub
			Void SucessFlag = null;
			try {

			} catch (Exception e) {
				System.out.println("in doInBackground() exception" + e);
			}
			return SucessFlag;
		}

		protected void onPostExecute(Void flag) {
			System.out.println("in onPostExecutet()");

			// checking network connectivity through Wifi or mobile packet data

				mt_submitBtn.setOnClickListener(new OnClickListener() 
				{
					
					@Override
					public void onClick(View v)
					{
						if (validate()) 
						{

							String uname = et_name.getText().toString().trim();	
						//	ConstantClass.User_name=uname;
							String mobileno = et_mobileno.getText().toString().trim();
							//String email = et_email.getText().toString().trim();
							String password = et_password.getText().toString().trim();
							String company = et_company.getText().toString().trim();
							
							//String name=uname.replaceAll("\\s", "");

							editor = pref.edit();
							editor.putString("uname",uname);
							editor.putString("mobileno",mobileno);
							editor.putString("password",password);
							editor.putString("company",company);
							editor.commit();
							
							Intent in = new Intent(getApplicationContext(), SignInActivity.class);
							startActivity(in);
						  }     
						}
					
				});
		}
	}

	public boolean validate() 
	{
		boolean flag = false;
		if (et_mobileno.getText().toString().trim().equals("")) {

			et_mobileno.setError("Please Enter Mobile No");
			//Helper.showShortToast(SignUpActivity.this,"Please Enter Mobile No");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please Enter Mobile No", Toast.LENGTH_LONG);
			et_mobileno.requestFocus();

		} else if (et_mobileno.getText().toString().trim().length() < 10) {
			et_mobileno.setError("Please enter valid Mobile Number");
			//Helper.showShortToast(SignUpActivity.this,"Please enter valid Mobile Number");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please enter valid Mobile Number", Toast.LENGTH_LONG);
			et_mobileno.requestFocus();
		} else if (!(et_mobileno.getText().toString().trim().startsWith("9")
				|| et_mobileno.getText().toString().trim().startsWith("8") || et_mobileno
				.getText().toString().trim().startsWith("7"))) {
			 et_mobileno.setError("Mobile Number must start with 9,8,7");
			//Helper.showShortToast(SignUpActivity.this,	"Mobile Number must start with 9,8,7");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Mobile Number must start with 9,8,7", Toast.LENGTH_LONG);
			et_mobileno.requestFocus();
			et_mobileno.setText("");
		} else if (et_password.getText().toString().trim().equals("")) {
			et_password.setError("Please Enter Password");
			//Helper.showShortToast(SignUpActivity.this,"Please Enter Password");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please Enter Password", Toast.LENGTH_LONG);
			et_password.requestFocus();
		}
		else if (et_name.getText().toString().trim().equals("")) {

			et_name.setError("Please Enter your Name"); 
			//Helper.showShortToast(SignUpActivity.this,"Please Enter your Name");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please Enter your Name", Toast.LENGTH_LONG);
			et_name.requestFocus();

		}
		else if (et_company.getText().toString().trim().equals("")) {

			et_name.setError("Please Enter Company Name");
			//Helper.showShortToast(SignUpActivity.this,"Please Enter your Name");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please Enter your Name", Toast.LENGTH_LONG);
			et_name.requestFocus();

		}

		else {
			flag = true;
		}
		return flag;

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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}