package com.vignan.glitchbreak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AdminList extends Activity implements View.OnClickListener{
	
	TextView tvAdmin, tvAllContacts, tvCSE, tvCSEN, tvECE, tvEIE, tvEEE, tvMBA, tvBSH, tvMECH, tvCivil;
	String strSelectedBranch = "", strUserName = "";
	TextView tvUserDetails, tvLogout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_list);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		tvAdmin = (TextView) findViewById(R.id.tvAdmin);
		tvAllContacts = (TextView) findViewById(R.id.tvAllContacts);
		tvCSE = (TextView) findViewById(R.id.tvCSE);
		tvCSEN = (TextView) findViewById(R.id.tvCSEN);
		tvECE = (TextView) findViewById(R.id.tvECE);
		tvEIE = (TextView) findViewById(R.id.tvEIE);
		tvEEE = (TextView) findViewById(R.id.tvEEE);
		tvMBA = (TextView) findViewById(R.id.tvMBA);
		tvBSH = (TextView) findViewById(R.id.tvBSH);
		tvMECH = (TextView) findViewById(R.id.tvMECH);
		tvCivil = (TextView) findViewById(R.id.tvCivil);
		
		tvAdmin.setOnClickListener(this);
		tvAllContacts.setOnClickListener(this);
		tvCSE.setOnClickListener(this);
		tvCSEN.setOnClickListener(this);
		tvECE.setOnClickListener(this);
		tvEIE.setOnClickListener(this);
		tvEEE.setOnClickListener(this);
		tvMBA.setOnClickListener(this);
		tvBSH.setOnClickListener(this);
		tvMECH.setOnClickListener(this);
		tvCivil.setOnClickListener(this);
		
		if(getIntent().getExtras() != null){
			strUserName = getIntent().getExtras().getString("UserName");
		}
		tvUserDetails = (TextView) findViewById(R.id.tvUserDetails);
		tvUserDetails.setText("Welcome , "+strUserName);
		
		tvLogout = (TextView) findViewById(R.id.tvLogout);
		tvLogout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvAdmin:
			strSelectedBranch = "Admin";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvAllContacts:
			strSelectedBranch = "All Contacts";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvLogout:
			break;
		case R.id.tvCSE:
			strSelectedBranch = "CSE";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvCSEN:
			strSelectedBranch = "CSE-N";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvECE:
			strSelectedBranch = "ECE";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvEIE:
			strSelectedBranch = "EIE";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvEEE:
			strSelectedBranch = "EEE";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvMBA:
			strSelectedBranch = "MBA";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvBSH:
			strSelectedBranch = "BSH";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvMECH:
			strSelectedBranch = "MECH";
			navigateToContacts(strSelectedBranch);
			break;
		case R.id.tvCivil:
			strSelectedBranch = "Civil";
			navigateToContacts(strSelectedBranch);
			break;
		}
	}

	private void navigateToContacts(String strSelectedBranch) {
		try {
			Intent intent = new Intent(AdminList.this, ContactsList.class);
			intent.putExtra("Selected Branch", strSelectedBranch);
			intent.putExtra("UserName", strUserName);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      getMenuInflater().inflate(R.menu.notification, menu);
	      return true;
	   }  
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case android.R.id.home:
	             Intent upIntent = new Intent(this, MainActivity.class);
	             if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
	                 NavUtils.navigateUpTo(this, upIntent);
	                 finish();
	             } else {
	                 finish();
	             }
	             return true;
	         default: return super.onOptionsItemSelected(item);
	     }
	 }
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AdminList.this, Main.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}
