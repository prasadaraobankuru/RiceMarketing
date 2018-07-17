
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DetailsShopeActivity extends AppCompatActivity {

	TextView tv_shopname,tv_gstno,tv_phonenumber,tv_aria;
	LinearLayout ll_addshope;
	private DbHelper dbHelper ;
	String shopname,gstno,phno,address,Id;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_details);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Shop Details");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		tv_shopname= (TextView) findViewById(R.id.tv_shopname);
		tv_gstno= (TextView) findViewById(R.id.tv_gstno);
		tv_phonenumber= (TextView) findViewById(R.id.tv_phonenumber);
		tv_aria= (TextView) findViewById(R.id.tv_aria);
		ll_addshope=(LinearLayout) findViewById(R.id.ll_addshope);

	/*	params.putString("shop_name", shop_name);
		params.putString("Gst_No",Gst_No);
		params.putString("PhoneNumber", PhoneNumber);
		params.putString("Shop_Aria",Shop_Aria);*/

		Intent i = getIntent();
		shopname = i.getStringExtra("shop_name");
		gstno = i.getStringExtra("Gst_No");
		phno = i.getStringExtra("PhoneNumber");
		address = i.getStringExtra("Shop_Aria");
		Id = i.getStringExtra("Id");

		tv_shopname.setText(shopname);
		tv_gstno.setText(gstno);
		tv_phonenumber.setText(phno);
		tv_aria.setText(address);



		ll_addshope.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(DetailsShopeActivity.this,  UpdateShopeActivity.class);

				Bundle params = new Bundle();
				params.putString("Id", Id);
				params.putString("shopname", shopname);
				params.putString("GstNo",gstno);
				params.putString("Phone_Number", phno);
				params.putString("ShopAria",address);
				i.putExtras(params);
				startActivity(i);

			}
		});




	}


}
