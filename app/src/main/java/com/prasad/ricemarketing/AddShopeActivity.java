
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddShopeActivity extends AppCompatActivity {

	EditText et_shopname,et_gstno,et_phonenumber,et_aria;
	LinearLayout ll_addshope;
	private DbHelper dbHelper ;
	String shopname,gstno,phno,address;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addshop);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		//toolbar.setTitle("Add Your Shop");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		        et_shopname= (EditText) findViewById(R.id.et_shopname);
				et_gstno= (EditText) findViewById(R.id.et_gstno);
		        et_phonenumber= (EditText) findViewById(R.id.et_phonenumber);
				et_aria= (EditText) findViewById(R.id.et_aria);
		        ll_addshope=(LinearLayout) findViewById(R.id.ll_addshope);

		dbHelper = new DbHelper(this);

		ll_addshope.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (validate()) {
					shopname = et_shopname.getText().toString();
					gstno = et_gstno.getText().toString();
					phno = et_phonenumber.getText().toString();
					address = et_aria.getText().toString();

					if (dbHelper.insertVideos(shopname, gstno, phno, address)) {

						Toast.makeText(AddShopeActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();

						Intent i = new Intent(getApplicationContext(), ShopsListActivity.class);
						startActivity(i);
					} else {

						Toast.makeText(getApplicationContext(), "Could not Insert video", Toast.LENGTH_SHORT).show();
					}

				}

			}
		});


	}

	public boolean validate()
	{
		boolean flag = false;
		if (et_shopname.getText().toString().trim().equals("")) {

			et_shopname.setError("Please Enter Shope Name");
			//Helper.showShortToast(SignUpActivity.this,"Please Enter Mobile No");
			//et_millname.showCustomToast(SignUpActivity.this, "no", "Please Enter Mobile No", Toast.LENGTH_LONG);
			et_shopname.requestFocus();

		} else if (et_gstno.getText().toString().trim().equals("")) {
			et_gstno.setError("Please Enter GstNo");
			//Helper.showShortToast(SignUpActivity.this,"Please enter valid Mobile Number");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please enter valid Mobile Number", Toast.LENGTH_LONG);
			et_gstno.requestFocus();
		}
		else {

			flag = true;
		}
		return flag;



	}
}
