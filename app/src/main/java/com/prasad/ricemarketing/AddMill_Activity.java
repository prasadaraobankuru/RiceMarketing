
package com.prasad.ricemarketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class AddMill_Activity extends AppCompatActivity {

	EditText et_millname,et_brand,et_address,et_gstnumber,et_accno,et_ifsc,et_bankname,et_bankadd,et_phnnumber;
	LinearLayout ll_addmill;
	private DbHelper dbHelper ;
	String millname,brand,gstno,accno,address,ifsc,bankname,bankaddress,phnnumber;
	Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmill);

		toolbar = (Toolbar) findViewById(R.id.toolbar);

		//toolbar.setTitle("Add Your Rice Mill");

		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		et_millname= (EditText) findViewById(R.id.et_millname);
		et_brand= (EditText) findViewById(R.id.et_brand);
		et_address= (EditText) findViewById(R.id.et_address);
		et_gstnumber= (EditText) findViewById(R.id.et_gstnumber);
		et_accno= (EditText) findViewById(R.id.et_accno);
		et_ifsc= (EditText) findViewById(R.id.et_ifsc);
		et_bankname= (EditText) findViewById(R.id.et_bankname);
		et_bankadd= (EditText) findViewById(R.id.et_bankadd);
		et_phnnumber= (EditText) findViewById(R.id.et_phnnumber);

		ll_addmill=(LinearLayout) findViewById(R.id.ll_addmill);

		dbHelper = new DbHelper(this);

		ll_addmill.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (validate()) {

					millname = et_millname.getText().toString();
					brand = et_brand.getText().toString();
					address = et_address.getText().toString();
					gstno = et_gstnumber.getText().toString();
					accno = et_accno.getText().toString();
					brand = et_brand.getText().toString();
					ifsc = et_ifsc.getText().toString();
					bankname = et_bankname.getText().toString();
					bankaddress = et_bankadd.getText().toString();
					phnnumber = et_phnnumber.getText().toString();

					if (dbHelper.Milldata_insert(millname, brand, gstno, phnnumber, accno, ifsc, bankname, bankaddress, address)) {

						Toast.makeText(AddMill_Activity.this, "Mill Added Successfully", Toast.LENGTH_LONG).show();

						Intent i = new Intent(getApplicationContext(), Mills_ListActivity.class);
						startActivity(i);
					} else {

						Toast.makeText(getApplicationContext(), "Could not Insert Mill", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});


	}
	public boolean validate()
	{
		boolean flag = false;
		if (et_millname.getText().toString().trim().equals("")) {

			et_millname.setError("Please enter Mill Name");
			//Helper.showShortToast(SignUpActivity.this,"Please Enter Mobile No");
			//et_millname.showCustomToast(SignUpActivity.this, "no", "Please Enter Mobile No", Toast.LENGTH_LONG);
			et_millname.requestFocus();

		} else if (et_brand.getText().toString().trim().equals("")) {
			et_brand.setError("Please enter brand");
			//Helper.showShortToast(SignUpActivity.this,"Please enter valid Mobile Number");
			//Helper.showCustomToast(SignUpActivity.this, "no", "Please enter valid Mobile Number", Toast.LENGTH_LONG);
			et_brand.requestFocus();
		}
		else {

			flag = true;
		}
		return flag;



	}

}
