package com.vignan.glitchbreak;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contacts/"+"Contacts.sqlite";
	
	public static final String Principal_SignIn = "principalSignin";
	public static final String HOD_SignIn = "HODSignin";
	public static final String Examination_SignIn = "ExaminationSignin";
	public static final String Contacts_SignIn = "contactsSignin";
	
	public static final String CSE_Contacts = "CSEContacts";
	public static final String CSE_N_Contacts = "CSENContacts";
	public static final String ECE_Contacts = "ECEContacts";
	public static final String MBA_Contacts = "MBAContacts";
	public static final String EIE_Contacts = "EIEContacts";
	public static final String EEE_Contacts = "EEEContacts";
	public static final String BSH_Contacts = "BSHContacts";
	public static final String MECH_Contacts = "MECHContacts";
	public static final String EXAM_Contacts = "EXAMContacts";
	public static final String ADMIN_Contacts = "ADMINContacts";
	public static final String HOD_Contacts = "HODContacts";
	public static final String Civil_Contacts = "CivilContacts";
	

	public DatabaseHelper(Context context, int version) {
		super(context, DATABASE_NAME, null, 1);
		this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String strprincipalSigninTable = "CREATE TABLE "+Principal_SignIn+"(userName VARCHAR, password VARCHAR)";
		String strHODSigninTable = "CREATE TABLE "+HOD_SignIn+"(userName VARCHAR, password VARCHAR)";
		String strExaminationSigninTable = "CREATE TABLE "+Examination_SignIn+"(userName VARCHAR, password VARCHAR)";
		String strContactsSigninTable = "CREATE TABLE "+Contacts_SignIn+"(userName VARCHAR, password VARCHAR)";
		
		String strCSEContactsTable = "CREATE TABLE "+CSE_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strCSENContactsTable = "CREATE TABLE "+CSE_N_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strECEContactsTable = "CREATE TABLE "+ECE_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strMBAContactsTable = "CREATE TABLE "+MBA_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strEIEContactsTable = "CREATE TABLE "+EIE_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strEEEContactsTable = "CREATE TABLE "+EEE_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strBSHContactsTable = "CREATE TABLE "+BSH_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strMECHContactsTable = "CREATE TABLE "+MECH_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strEXAMContactsTable = "CREATE TABLE "+EXAM_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strADMINContactsTable = "CREATE TABLE "+ADMIN_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strHODContactsTable = "CREATE TABLE "+HOD_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		String strCivilContactsTable = "CREATE TABLE "+Civil_Contacts+"(name VARCHAR, phoneNo VARCHAR, email VARCHAR, status INTEGER, deleted INTEGER DEFAULT 0)";
		
		db.execSQL(strprincipalSigninTable);
		db.execSQL(strHODSigninTable);
		db.execSQL(strExaminationSigninTable);
		db.execSQL(strContactsSigninTable);
		
		db.execSQL(strCSEContactsTable);
		db.execSQL(strCSENContactsTable);
		db.execSQL(strECEContactsTable);
		db.execSQL(strMBAContactsTable);
		db.execSQL(strEIEContactsTable);
		db.execSQL(strEEEContactsTable);
		db.execSQL(strBSHContactsTable);
		db.execSQL(strMECHContactsTable);
		db.execSQL(strEXAMContactsTable);
		db.execSQL(strADMINContactsTable);
		db.execSQL(strHODContactsTable);
		db.execSQL(strCivilContactsTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}


}