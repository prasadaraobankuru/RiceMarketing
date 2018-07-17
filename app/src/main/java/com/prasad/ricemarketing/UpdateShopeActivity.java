
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class UpdateShopeActivity extends AppCompatActivity {

	EditText et_shopname,et_gstno,et_phonenumber,et_aria;
	LinearLayout ll_addshope;
	private DbHelper dbHelper ;
	String shopname,gstno,phno,address,Id;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateshop);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Update Shop Details");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		Bundle bundle = getIntent().getExtras();
		shopname = bundle.getString("shopname", "");
		gstno = bundle.getString("GstNo", "");
		phno = bundle.getString("Phone_Number", "");
		address = bundle.getString("ShopAria", "");
		Id = bundle.getString("Id", "");

		et_shopname= (EditText) findViewById(R.id.et_shopname);
		et_gstno= (EditText) findViewById(R.id.et_gstno);
		et_phonenumber= (EditText) findViewById(R.id.et_phonenumber);
		et_aria= (EditText) findViewById(R.id.et_aria);
		ll_addshope=(LinearLayout) findViewById(R.id.ll_updateshp);


		et_shopname.setText(shopname);
		et_gstno.setText(gstno);
		et_phonenumber.setText(phno);
		et_aria.setText(address);


		dbHelper = new DbHelper(this);

		ll_addshope.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				shopname=et_shopname.getText().toString();
				gstno=et_gstno.getText().toString();
				phno=et_phonenumber.getText().toString();
				address=et_aria.getText().toString();

				if(dbHelper.updatePerson(Integer.valueOf(Id),shopname,gstno,phno,address)) {

					Toast.makeText(UpdateShopeActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();

					Intent i = new Intent(getApplicationContext(),  ShopsListActivity.class);
					startActivity(i);
				}

				else {

					Toast.makeText(getApplicationContext(), "Could not Updated", Toast.LENGTH_SHORT).show();
				}
			}
		});


	}


}
