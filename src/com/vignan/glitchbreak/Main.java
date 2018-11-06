package com.vignan.glitchbreak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements View.OnClickListener {

	TextView tvPrincipal, tvHOD, tvExamination, tvContacts;
	final private static int DIALOG_LOGIN = 1;
	String strClick = "";
	 DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseHelper = new DatabaseHelper(Main.this, 1);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		tvPrincipal = (TextView) findViewById(R.id.tvPrincipal);
		tvHOD = (TextView) findViewById(R.id.tvHOD);
		tvExamination = (TextView) findViewById(R.id.tvExamination);
		tvContacts = (TextView) findViewById(R.id.tvContacts);

		tvPrincipal.setOnClickListener(this);
		tvHOD.setOnClickListener(this);
		tvExamination.setOnClickListener(this);
		tvContacts.setOnClickListener(this);
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvPrincipal:
			strClick = "Principal Login";
			showDialog(DIALOG_LOGIN);
			break;
		case R.id.tvHOD:
			strClick = "HOD Login";
			showDialog(DIALOG_LOGIN);
			break;
		case R.id.tvExamination:
			strClick = "Examination Login";
			showDialog(DIALOG_LOGIN);
			break;
		case R.id.tvContacts:
			strClick = "Contacts";
			Intent iAll = new Intent(Main.this, AdminList.class);
			iAll.putExtra("UserLogin", strClick);
			iAll.putExtra("UserName", "You");
			startActivity(iAll);
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		AlertDialog dialogDetails = null;

		switch (id) {
		case DIALOG_LOGIN:
			LayoutInflater inflater = LayoutInflater.from(this);
			View dialogview = inflater.inflate(R.layout.signin, null);

			AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
			dialogbuilder.setView(dialogview);
			dialogDetails = dialogbuilder.create();

			break;
		}

		return dialogDetails;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {
		case DIALOG_LOGIN:
			final AlertDialog alertDialog = (AlertDialog) dialog;
			TextView tvSigninHeader = (TextView) alertDialog.findViewById(R.id.tvSigninHeader);
			tvSigninHeader.setText(strClick);
			final EditText etUserName = (EditText) alertDialog.findViewById(R.id.etUserName);
			final EditText etPassword = (EditText) alertDialog.findViewById(R.id.etPassword);
			Button btnSignIn = (Button) alertDialog.findViewById(R.id.btnSignIn);
			Button btnCancel = (Button) alertDialog.findViewById(R.id.btnCancel);
			
			btnSignIn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(strClick.equalsIgnoreCase("Principal Login")){
						loginValidation(etUserName, etPassword, "principalSignin", alertDialog);
					}else if(strClick.equalsIgnoreCase("HOD Login")){
						loginValidation(etUserName, etPassword, "HODSignin", alertDialog);
					}else if(strClick.equalsIgnoreCase("Examination Login")){
						loginValidation(etUserName, etPassword, "ExaminationSignin", alertDialog);
					}
				}
			});
			
			btnCancel.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
				}
			});

			break;
		}
	}

	private void loginValidation(EditText etUserName, EditText etPassword, String strTableName, AlertDialog alertDialog) {
	
		
		 SQLiteDatabase db = databaseHelper.getWritableDatabase();
		  Cursor cursor = db.rawQuery("select userName,password from "+strTableName+" where userName="+"'"+etUserName.getText().toString().trim()+"'", null);
		  if(cursor.getCount() != 0){
			  if (cursor.moveToFirst()) {
	            do {
	            	String strUserName = cursor.getString(0);
	            	String strPassword = cursor.getString(1);
	            	if(strUserName.equalsIgnoreCase(etUserName.getText().toString().trim()) && strPassword.equalsIgnoreCase(etPassword.getText().toString())){
			            	if(strClick.equalsIgnoreCase("Principal Login")){
								Intent iPrincipal = new Intent(Main.this, PrincipalList.class);
								iPrincipal.putExtra("UserLogin", strClick);
								iPrincipal.putExtra("UserName", strUserName);
								startActivity(iPrincipal);
							}else if(strClick.equalsIgnoreCase("HOD Login")){
								Intent iHOD = new Intent(Main.this, HODList.class);
								iHOD.putExtra("UserLogin", strClick);
								iHOD.putExtra("UserName", strUserName);
								startActivity(iHOD);
							}else if(strClick.equalsIgnoreCase("Examination Login")){
								Intent iExam = new Intent(Main.this, ExamList.class);
								iExam.putExtra("UserLogin", strClick);
								iExam.putExtra("UserName", strUserName);
								startActivity(iExam);
							}
			            	alertDialog.dismiss();
	            	}else{
	            		Toast.makeText(Main.this, "Please Enter Correct UserName and Password", Toast.LENGTH_LONG).show();
	            	}
	            	
	            } while (cursor.moveToNext());
			  }
		  }else{
			  Toast.makeText(Main.this, "Sync before login", Toast.LENGTH_LONG).show();
		  }
	}
}
