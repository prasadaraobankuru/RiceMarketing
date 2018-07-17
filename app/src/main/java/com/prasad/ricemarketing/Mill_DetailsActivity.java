
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Mill_DetailsActivity extends AppCompatActivity {

	TextView tv_millname,tv_brand,mill_address,phn_no,gst_no,account_no,ifsc_code,bank_name,bank_address;
	LinearLayout ll_update;
	private DbHelper dbHelper ;
	String Id,Mill_name,Mill_Brand,Mill_Gst_No,Mill_PhoneNumber,Mill_BankAccountNo,Mill_Ifsc,Mill_bank,Mill_bank_address,Mill_address;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mill_details);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Rice Mill Details");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		tv_millname= (TextView) findViewById(R.id.tv_millname);
		tv_brand= (TextView) findViewById(R.id.tv_brand);
		mill_address= (TextView) findViewById(R.id.mill_address);
		phn_no= (TextView) findViewById(R.id.phn_no);
		gst_no= (TextView) findViewById(R.id.gst_no);
		account_no= (TextView) findViewById(R.id.account_no);
		ifsc_code= (TextView) findViewById(R.id.ifsc_code);
		bank_name= (TextView) findViewById(R.id.bank_name);
		bank_address= (TextView) findViewById(R.id.bank_address);


		ll_update=(LinearLayout) findViewById(R.id.ll_update);



		Intent i = getIntent();
		Mill_name = i.getStringExtra("Mill_name");
		Mill_Brand = i.getStringExtra("Mill_Brand");
		Mill_Gst_No = i.getStringExtra("Mill_Gst_No");
		Mill_PhoneNumber = i.getStringExtra("Mill_PhoneNumber");
		Mill_BankAccountNo = i.getStringExtra("Mill_BankAccountNo");
		Mill_Ifsc = i.getStringExtra("Mill_Ifsc");
		Mill_bank = i.getStringExtra("Mill_bank");
		Mill_bank_address = i.getStringExtra("Mill_bank_address");
		Mill_address = i.getStringExtra("Mill_address");
		Id = i.getStringExtra("Id");

		tv_millname.setText(Mill_name);
		tv_brand.setText(Mill_Brand);
		mill_address.setText(Mill_address);
		phn_no.setText(Mill_PhoneNumber);
		account_no.setText(Mill_BankAccountNo);
		gst_no.setText(Mill_Gst_No);
		ifsc_code.setText(Mill_Ifsc);
		bank_name.setText(Mill_bank);
		bank_address.setText(Mill_bank_address);

		ll_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(Mill_DetailsActivity.this,  UpdateMillActivity.class);

				Bundle params = new Bundle();
				params.putString("Id", Id);
				params.putString("Mill_name", Mill_name);
				params.putString("Mill_Brand",Mill_Brand);
				params.putString("Mill_address", Mill_address);
				params.putString("Mill_PhoneNumber",Mill_PhoneNumber);
				params.putString("Mill_BankAccountNo", Mill_BankAccountNo);
				params.putString("Mill_Gst_No", Mill_Gst_No);
				params.putString("Mill_Ifsc",Mill_Ifsc);
				params.putString("Mill_bank", Mill_bank);
				params.putString("Mill_bank_address",Mill_bank_address);
				i.putExtras(params);
				startActivity(i);

			}
		});




	}


}
