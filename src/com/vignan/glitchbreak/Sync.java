package com.vignan.glitchbreak;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Sync extends Activity implements View.OnClickListener{
	
	TextView tvYes, tvNO, tvpleaseWait, tvSyncHeading;
	 DatabaseHelper databaseHelper;
	 ProgressBar progressStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sync);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		databaseHelper = new DatabaseHelper(Sync.this, 1);
		
		tvYes = (TextView) findViewById(R.id.tvYes);
		tvNO = (TextView) findViewById(R.id.tvNO);
		
		tvYes.setOnClickListener(this);
		tvNO.setOnClickListener(this);
		
		tvSyncHeading = (TextView) findViewById(R.id.tvSyncHeading);
		tvpleaseWait = (TextView) findViewById(R.id.tvpleaseWait);
		progressStatus = (ProgressBar) findViewById(R.id.progressStatus);
		
		tvpleaseWait.setVisibility(View.GONE);
		progressStatus.setVisibility(View.GONE);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvYes:
			tvYes.setVisibility(View.GONE);
			tvNO.setVisibility(View.GONE);
			tvSyncHeading.setVisibility(View.GONE);
			
			tvpleaseWait.setVisibility(View.VISIBLE);
			progressStatus.setVisibility(View.VISIBLE);
			insertNewContacts();
			break;
		case R.id.tvNO:
			Intent i = new Intent(Sync.this, Main.class);
			finish();
			startActivity(i);
			break;
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
	
	public void insertNewContacts() {
		try {
			insertPrincipalSigninDetails();
			insertHODSigninDetails();
			insertExaminationSigninDetails();
			insertContactsSigninDetails();
			
			insertCSEContacts();
			insertCSENContacts();
			insertECEContacts();
			insertMBAContacts();
			insertEIEContacts();
			insertEEEContacts();
			insertBSHContacts();
			insertMECHContacts();
			insertEXAMContacts();
			insertADMINContacts();
			insertHODContacts();
			insertCivilContacts();
			
			Intent i = new Intent(Sync.this, Main.class);
			finish();
			startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertCivilContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("Civil");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from CivilContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from CivilContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("CivilContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("CivilContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void insertHODContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("HOD");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from HODContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from HODContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("HODContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("HODContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void insertCSENContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("CSE-N");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from CSENContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from CSENContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("CSENContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("CSENContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void insertADMINContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("ADMIN OFFICE");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from ADMINContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from ADMINContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("ADMINContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("ADMINContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertEXAMContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("EXAM BRANCH");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from EXAMContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from EXAMContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("EXAMContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("EXAMContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertMECHContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("Mechanical");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from MECHContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from MECHContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("MECHContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("MECHContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertBSHContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("BSH");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from BSHContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from BSHContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("BSHContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("BSHContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertEEEContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("EEE");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from EEEContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from EEEContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("EEEContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("EEEContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertEIEContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("EIE");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from EIEContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from EIEContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("EIEContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("EIEContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertMBAContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("MBA");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from MBAContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from MBAContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("MBAContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("MBAContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertECEContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("ECE");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from ECEContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from ECEContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("ECEContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("ECEContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertCSEContacts() {
		try {
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("CSE");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell celSno = sheet.getCell(0, k);
				Cell celName = sheet.getCell(1, k);
				Cell celContact = sheet.getCell(2, k);
				Cell celMail = sheet.getCell(3, k);
				Cell celStatus = sheet.getCell(4, k);
				
				if(celStatus.getContents().toString().trim().equalsIgnoreCase("New")){
					 SQLiteDatabase db = databaseHelper.getWritableDatabase();
					  Cursor cursor = db.rawQuery("select name,deleted from CSEContacts where email="+"'"+celMail.getContents().trim()+"'", null);
					  if(cursor.getCount() != 0){
						  if (cursor.moveToFirst()) {
				            do {

				                if(cursor.getInt(1) != 1){
				                	db.execSQL("delete from CSEContacts where email="+"'"+celMail.getContents().trim()+"'");
				                	
				                	  ContentValues values = new ContentValues(); 
									  values.put("name", celName.getContents().toString().trim());
									  values.put("phoneNo", celContact.getContents().toString());
									  values.put("email", celMail.getContents().toString());
									  values.put("status", 0);
									  db.insert("CSEContacts", null, values);
				                }
				            } while (cursor.moveToNext());
						  }
					  }else{
						  ContentValues values = new ContentValues(); 
						  values.put("name", celName.getContents().toString().trim());
						  values.put("phoneNo", celContact.getContents().toString());
						  values.put("email", celMail.getContents().toString());
						  values.put("status", 0);
						  db.insert("CSEContacts", null, values);
					  }
				     cursor.close();
				}
				
				k++;
			}
			
			w.close();
			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertPrincipalSigninDetails() {
		try {
		     SQLiteDatabase db = databaseHelper.getWritableDatabase();
  	         db.execSQL("delete from principalSignin");
			
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("Principal_Login");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				
				Cell celSno = sheet.getCell(0, k);
				Cell celUserName = sheet.getCell(1, k);
				Cell celPassword = sheet.getCell(2, k);
				Cell celStatus = sheet.getCell(3, k);
				
				ContentValues values = new ContentValues(); 
				values.put("userName", celUserName.getContents().toString().trim());
				values.put("password", celPassword.getContents().toString());
				db.insert("principalSignin", null, values);

				k++;
			}
			
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertHODSigninDetails() {
		try {
		     SQLiteDatabase db = databaseHelper.getWritableDatabase();
  	         db.execSQL("delete from HODSignin");
			
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("HOD_Login");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				
				Cell celSno = sheet.getCell(0, k);
				Cell celUserName = sheet.getCell(1, k);
				Cell celPassword = sheet.getCell(2, k);
				Cell celStatus = sheet.getCell(3, k);
				
				ContentValues values = new ContentValues(); 
				values.put("userName", celUserName.getContents().toString().trim());
				values.put("password", celPassword.getContents().toString());
				db.insert("HODSignin", null, values);

				k++;
			}
			
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertExaminationSigninDetails() {
		try {
			 SQLiteDatabase db = databaseHelper.getWritableDatabase();
  	         db.execSQL("delete from ExaminationSignin");
			
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("Examination_Login");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				
				Cell celSno = sheet.getCell(0, k);
				Cell celUserName = sheet.getCell(1, k);
				Cell celPassword = sheet.getCell(2, k);
				Cell celStatus = sheet.getCell(3, k);
				
				ContentValues values = new ContentValues(); 
				values.put("userName", celUserName.getContents().toString().trim());
				values.put("password", celPassword.getContents().toString());
				db.insert("ExaminationSignin", null, values);

				k++;
			}
			
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertContactsSigninDetails() {
		try {
			 SQLiteDatabase db = databaseHelper.getWritableDatabase();
  	         db.execSQL("delete from contactsSignin");
			
			AssetManager assetManager = getAssets();
			InputStream input = assetManager.open("contacts.xls");
			
			Workbook w = Workbook.getWorkbook(input);
			Sheet sheet = w.getSheet("Contacts_Login");
			int k = 1;
			for (int i = 1; i < sheet.getRows(); i++) {
				//No creditals
//				Cell celSno = sheet.getCell(0, k);
//				Cell celUserName = sheet.getCell(1, k);
//				Cell celPassword = sheet.getCell(2, k);
//				Cell celStatus = sheet.getCell(3, k);
//				
//				ContentValues values = new ContentValues(); 
//				values.put("userName", celUserName.getContents().toString().trim());
//				values.put("password", celPassword.getContents().toString());
//				db.insert("contactsSignin", null, values);

				k++;
			}
			
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
