
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class UpdateMillActivity extends AppCompatActivity {

	EditText et_millname,et_brand,et_address,et_gstnumber,et_accno,et_ifsc,et_bankname,et_bankadd,et_phnnumber;
	LinearLayout ll_updatemill;
	private DbHelper dbHelper ;
	String Id,Mill_name,Mill_Brand,Mill_Gst_No,Mill_PhoneNumber,Mill_BankAccountNo,Mill_Ifsc,Mill_bank,Mill_bank_address,Mill_address;
	String millname,brand,gstno,accno,address,ifsc,bankname,bankaddress,phnnumber;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatemill);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Update Mill Details");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		Bundle bundle = getIntent().getExtras();
		Mill_name = bundle.getString("Mill_name", "");
		Mill_Brand = bundle.getString("Mill_Brand", "");
		Mill_address = bundle.getString("Mill_address", "");
		Mill_PhoneNumber = bundle.getString("Mill_PhoneNumber", "");
		Mill_BankAccountNo = bundle.getString("Mill_BankAccountNo", "");
		Mill_Gst_No = bundle.getString("Mill_Gst_No", "");
		Mill_Ifsc = bundle.getString("Mill_Ifsc", "");
		Mill_bank = bundle.getString("Mill_bank", "");
		Mill_bank_address = bundle.getString("Mill_bank_address", "");
		Id = bundle.getString("Id", "");

		et_millname= (EditText) findViewById(R.id.et_millname);
		et_brand= (EditText) findViewById(R.id.et_brand);
		et_address= (EditText) findViewById(R.id.et_address);
		et_gstnumber= (EditText) findViewById(R.id.et_gstnumber);
		et_accno= (EditText) findViewById(R.id.et_accno);
		et_ifsc= (EditText) findViewById(R.id.et_ifsc);
		et_bankname= (EditText) findViewById(R.id.et_bankname);
		et_bankadd= (EditText) findViewById(R.id.et_bankadd);
		et_phnnumber= (EditText) findViewById(R.id.et_phnnumber);

		ll_updatemill=(LinearLayout) findViewById(R.id.ll_updatemill);



		et_millname.setText(Mill_name);
		et_brand.setText(Mill_Brand);
		et_address.setText(Mill_address);
		et_gstnumber.setText(Mill_Gst_No);
		et_accno.setText(Mill_BankAccountNo);
		et_ifsc.setText(Mill_Ifsc);
		et_bankname.setText(Mill_bank);
		et_bankadd.setText(Mill_bank_address);
		et_phnnumber.setText(Mill_PhoneNumber);


		dbHelper = new DbHelper(this);

		ll_updatemill.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				millname=et_millname.getText().toString();
				brand=et_brand.getText().toString();
				address=et_address.getText().toString();
				gstno=et_gstnumber.getText().toString();
				accno=et_accno.getText().toString();
				ifsc=et_ifsc.getText().toString();
				bankname=et_bankname.getText().toString();
				bankaddress=et_bankadd.getText().toString();
				phnnumber=et_phnnumber.getText().toString();

				if(dbHelper.Mill_dataupdate(Integer.valueOf(Id),millname,brand,gstno,phnnumber,accno,ifsc,bankname,bankaddress,address)) {

					Toast.makeText(UpdateMillActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();

					Intent i = new Intent(getApplicationContext(),  Mills_ListActivity.class);
					startActivity(i);
				}

				else {

					Toast.makeText(getApplicationContext(), "Could not Updated", Toast.LENGTH_SHORT).show();
				}
			}
		});


	}


}
