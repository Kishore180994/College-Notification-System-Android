package com.vignan.glitchbreak;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsList extends Activity {

	ListView lvContactsList;
	Context context;
	ContactsAdapter adapter;
	ArrayList<ContactsGetter> list = new ArrayList<ContactsGetter>();
	CheckBox cbSelectAll;
	
	String strSelectedBranch = "", strUserName = "", strTableName = "";
	TextView tvUserDetails, tvSelectedBranch, tvDelete, tvSMS, tvMail, tvLogout;
	
	DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.contacts_list);
		context = this;
		getActionBar().setDisplayHomeAsUpEnabled(true);
		databaseHelper = new DatabaseHelper(ContactsList.this, 1);
		if (getIntent().getExtras() != null) {
			Bundle b = getIntent().getExtras();
			strSelectedBranch = b.getString("Selected Branch");
			strUserName = getIntent().getExtras().getString("UserName");
		}
		tvUserDetails = (TextView) findViewById(R.id.tvUserDetails);
		tvSelectedBranch = (TextView) findViewById(R.id.tvSelectedBranch);
		tvDelete = (TextView) findViewById(R.id.tvDelete);
		tvSMS = (TextView) findViewById(R.id.tvSMS);
		tvMail = (TextView) findViewById(R.id.tvMail);
		
		tvDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuilder phoneNos = new StringBuilder("");
				for (ContactsGetter bean : list) {
					if(bean.isSelected() == true){
						phoneNos.append(bean.getPhoneNo().trim());
						phoneNos.append(";");
					}
				}
				
				if(phoneNos.length() != 0){
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContactsList.this);
					alertDialog.setMessage("Are you sure you want to delete this selected contacts ?");
					alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface dialog, int which) {
							for (ContactsGetter bean : list) {
								if(bean.isSelected() == true){
									 SQLiteDatabase db = databaseHelper.getWritableDatabase();
									 db.execSQL("UPDATE "+strTableName+" SET deleted=1 where email="+"'"+bean.getEmail().toString().trim()+"'");
									 adapter.notifyDataSetChanged();
									 Toast.makeText(context, "Contacts deleted successfully", Toast.LENGTH_LONG).show();
									 finish();
								}
							}
						}
					});
					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					alertDialog.show();
				}else{
					Toast.makeText(context, "Please Select Atleast Any One Contact", Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		
		tvSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuilder phoneNos = new StringBuilder("");
				
				for (ContactsGetter bean : list) {
					if(bean.isSelected() == true){
						phoneNos.append(bean.getPhoneNo().trim());
						phoneNos.append(";");
					}
				}
				
				if(phoneNos.length() != 0){
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", String.valueOf(phoneNos), null));
					ContactsList.this.startActivity(intent);  
				}else{
					Toast.makeText(context, "Please Select Atleast Any One Contact", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		tvMail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int size = 0;
				
				for (ContactsGetter bean : list) {
					if(bean.isSelected() == true){
						size++;
					}
				}
				
				if(size != 0){
					String[] emails = new String[size];
					int i = 0;
					for (ContactsGetter bean : list) {
						if(bean.isSelected() == true){
							emails[i] = bean.getEmail();
							i++;
						}
					}
					  
					Intent email = new Intent(android.content.Intent.ACTION_SEND);
					email.putExtra(android.content.Intent.EXTRA_EMAIL, emails ); 
					email.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
					email.setType("message/rfc822");
					email.putExtra(android.content.Intent.EXTRA_TEXT, "message");
					startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}else{
					Toast.makeText(context, "Please Select Atleast Any One Contact", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		tvUserDetails.setText("Welcome , " + strUserName);
		tvSelectedBranch.setText(strSelectedBranch);
		
		cbSelectAll = (CheckBox) findViewById(R.id.chkSelectALl);
		lvContactsList = (ListView) findViewById(R.id.lvContacts);
		lvContactsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

			}
		});

		cbSelectAll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox chkBox = (CheckBox) v;
				if (chkBox.isChecked()) {
					for (ContactsGetter bean : list) {
						bean.setSelected(true);
						adapter.notifyDataSetChanged();
					}
				} else {
					for (ContactsGetter bean : list) {
						bean.setSelected(false);
						adapter.notifyDataSetChanged();
					}
				}
			}
		});

		adapter = new ContactsAdapter(ContactsList.this, context, R.layout.contacts_list_layout, list);
		lvContactsList.setAdapter(adapter);

		loadContactsList();
		
		tvLogout = (TextView) findViewById(R.id.tvLogout);
		tvLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openLogOutDialog();
			}
		});
		
		if("You".equalsIgnoreCase(strUserName.toString().trim())){
			tvLogout.setVisibility(View.GONE);
		}

		super.onCreate(savedInstanceState);
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
	
	private void openLogOutDialog() {
		try {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContactsList.this);
			alertDialog.setMessage("Are you sure you want to logout");
			alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(ContactsList.this, Main.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
			});
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alertDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadContactsList() {
		try {
			if(strSelectedBranch.equalsIgnoreCase("HOD")){
				getContactsFromDB("HODContacts");
				strTableName = "HODContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("Exam")){
				getContactsFromDB("EXAMContacts");
				strTableName = "EXAMContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("Admin")){
				getContactsFromDB("ADMINContacts");
				strTableName = "ADMINContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("CSE")){
				getContactsFromDB("CSEContacts");
				strTableName = "CSEContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("CSE-N")){
				getContactsFromDB("CSENContacts");
				strTableName = "CSENContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("ECE")){
				getContactsFromDB("ECEContacts");
				strTableName = "ECEContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("EIE")){
				getContactsFromDB("EIEContacts");
				strTableName = "EIEContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("EEE")){
				getContactsFromDB("EEEContacts");
				strTableName = "EEEContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("MBA")){
				getContactsFromDB("MBAContacts");
				strTableName = "MBAContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("BSH")){
				getContactsFromDB("BSHContacts");
				strTableName = "BSHContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("MECH")){
				getContactsFromDB("MECHContacts");
				strTableName = "MECHContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("Civil")){
				getContactsFromDB("CivilContacts");
				strTableName = "CivilContacts";
			} else if(strSelectedBranch.equalsIgnoreCase("All Contacts")){
				tvDelete.setVisibility(View.GONE);
				getContactsFromDB("HODContacts");
				getContactsFromDB("EXAMContacts");
				getContactsFromDB("ADMINContacts");
				getContactsFromDB("CSEContacts");
				getContactsFromDB("CSENContacts");
				getContactsFromDB("ECEContacts");
				getContactsFromDB("EIEContacts");
				getContactsFromDB("EEEContacts");
				getContactsFromDB("MBAContacts");
				getContactsFromDB("BSHContacts");
				getContactsFromDB("MECHContacts");
				getContactsFromDB("CivilContacts");
			} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getContactsFromDB(String strTableName) {
		try {
			 SQLiteDatabase db = databaseHelper.getWritableDatabase();
			  Cursor cursor = db.rawQuery("select name,phoneNo,email from "+strTableName+" where deleted=0", null);
			  if(cursor.getCount() != 0){
				  if (cursor.moveToFirst()) {
		            do {
		            	ContactsGetter contactsGetter = new ContactsGetter(cursor.getString(0).toString(), cursor.getString(1).toString(), cursor.getString(2).toString(), false, strTableName);
		            	list.add(contactsGetter);
		            } while (cursor.moveToNext());
				  }
			  }
			  adapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}



class ContactsAdapter extends ArrayAdapter<ContactsGetter> {

	public int getCount() {
		return contactsList.size();
	}

	ViewHolder holder;
	LinearLayout l;
	Context context;
	ContactsList activity;
	DatabaseHelper databaseHelper;

	private ArrayList<ContactsGetter> contactsList = new ArrayList<ContactsGetter>();

	public ContactsAdapter(ContactsList contactsActivity, Context context, int textViewResourceId, ArrayList<ContactsGetter> List) {
		super(context, textViewResourceId, List);

		this.contactsList = new ArrayList<ContactsGetter>();
		this.contactsList = List;
		this.activity = contactsActivity;
		this.context = context;
	}

	private class ViewHolder {
		LinearLayout lContacts;
		TextView tvName;
		TextView tvPhone;
		TextView tvEmail;
		CheckBox chkSelect;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		holder = null;

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.contacts_list_layout, null);
			holder = new ViewHolder();
			holder.lContacts = (LinearLayout) convertView.findViewById(R.id.lContacts);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
			holder.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
			holder.chkSelect = (CheckBox) convertView.findViewById(R.id.chkSelect);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.chkSelect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!isChecked) {
					activity.cbSelectAll.setChecked(false);
				}

				CheckBox cb = (CheckBox) buttonView;
				ContactsGetter cblist = (ContactsGetter) cb.getTag();
				cblist.setSelected(cb.isChecked());
			}
		});

		holder.lContacts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				l = new LinearLayout(context);
				l = (LinearLayout) v;
				l.setBackgroundResource(R.color.white);
				openOptions(position, ((LinearLayout) v).getContentDescription().toString().trim(), context);
			}
		});
		
		
		ContactsGetter contacts = contactsList.get(position);
		holder.tvName.setText(contacts.getName());
		holder.tvPhone.setText(contacts.getPhoneNo());
		holder.tvEmail.setText(contacts.getEmail());
		holder.chkSelect.setChecked(contacts.isSelected());
		holder.chkSelect.setTag(contacts);
		holder.lContacts.setContentDescription(contacts.getEmail().toString().trim());
		
		return convertView;
	}
	

	protected void openOptions(final int position, final String strEmail, final Context context) {
		try {
			databaseHelper = new DatabaseHelper(getContext(), 1);
			final Dialog dialog = new Dialog(context, R.style.Theme_CustomAlertDialog);
			dialog.setContentView(R.layout.file_options);

			LinearLayout lDelete = (LinearLayout) dialog.findViewById(R.id.lDelete);
			LinearLayout lEdit = (LinearLayout) dialog.findViewById(R.id.lEdit);
			LinearLayout lCall = (LinearLayout) dialog.findViewById(R.id.lCall);
			LinearLayout lSMS = (LinearLayout) dialog.findViewById(R.id.lSMS);
			LinearLayout lEmail = (LinearLayout) dialog.findViewById(R.id.lEmail);

			dialog.setOnKeyListener(new OnKeyListener() {

				public boolean onKey(DialogInterface dialog1, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						dialog.dismiss();
						l.setBackgroundResource(0);
					}
					return true;
				}
			});

			lDelete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
					alertDialog.setMessage("Are you sure you want Delete "+strEmail+" contact ?");
					alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ContactsGetter contacts = contactsList.get(position);
							
							 SQLiteDatabase db = databaseHelper.getWritableDatabase();
							 db.execSQL("UPDATE "+contacts.getTableName().toString().trim()+" SET deleted=1 where email="+"'"+strEmail+"'");
							 contactsList.remove(position);
							 l.setBackgroundResource(0);
							 notifyDataSetChanged();
							 dialog.dismiss();
						}
					});
					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					alertDialog.show();
				}
			});

			lEdit.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					final AlertDialog.Builder alert = new AlertDialog.Builder(context);
					final ContactsGetter contacts = contactsList.get(position);
					
					alert.setTitle("Edit Contact");
					LinearLayout layout = new LinearLayout(context);
					layout.setOrientation(LinearLayout.VERTICAL);
					
					final EditText etName = new EditText(context);
					final EditText etPhoneNumber = new EditText(context);
					final EditText etEmail = new EditText(context);
					etName.setText(contacts.getName().toString().trim());
					etPhoneNumber.setText(contacts.getPhoneNo().toString().trim());
					etEmail.setText(contacts.getEmail().toString().trim());
					
					
					
					etName.setHint("Name");
					etPhoneNumber.setHint("Phone Number");
					etEmail.setHint("Email");
					
					layout.addView(etName);
					layout.addView(etPhoneNumber);
					layout.addView(etEmail);
					
					alert.setView(layout);
					
					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  String strName = etName.getText().toString().trim();
					  String strPhoneNumber = etPhoneNumber.getText().toString().trim();
					  String strEmail = etEmail.getText().toString().trim();
					  final String strName1 = contacts.getName().toString().trim();
						final String strEmail1 = contacts.getEmail().toString().trim();
					  
						  if(strName != null && strName.length() > 0 && strPhoneNumber != null && strPhoneNumber.length() > 0 && strEmail != null && strEmail.length() > 0){
							  ContactsGetter contacts = contactsList.get(position);
							  SQLiteDatabase db = databaseHelper.getWritableDatabase();
							  db.execSQL("UPDATE "+contacts.getTableName().toString().trim()+" SET name="+"'"+strName+"'"+",phoneNo="+"'"+strPhoneNumber+"'"+",email="+"'"+strEmail+"'"+" where email="+"'"+strEmail1+"'"+" and name="+"'"+strName1+"'");
							  l.setBackgroundResource(0);
							  notifyDataSetChanged();
							  dialog.dismiss();
							  Toast.makeText(context, "Updated Successfully", Toast.LENGTH_LONG).show();
							  ((Activity) context).finish();
						  }
					  }
					});

					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
						  
					  }
					});

					alert.show();
				}
			});

			lCall.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ContactsGetter contacts = contactsList.get(position);
					dialog.dismiss();
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts.getPhoneNo().toString().trim()));
					getContext().startActivity(intent);
				}
			});
			
			lSMS.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ContactsGetter contacts = contactsList.get(position);
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contacts.getPhoneNo().toString().trim(), null));
					getContext().startActivity(intent);  
				}
			});
			
			lEmail.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ContactsGetter contacts = contactsList.get(position);
	                Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(android.content.Intent.EXTRA_EMAIL, contacts.getEmail().toString().trim() ); 
					email.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
					email.setType("message/rfc822");
					email.putExtra(android.content.Intent.EXTRA_TEXT, "message");
					getContext().startActivity(Intent.createChooser(email, "Choose an Email client :"));
				}
			});

			dialog.setCancelable(true);
			dialog.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ContactsGetter {

	String name = null, email = null, phoneNo = null, tableName = null;
	boolean selected = false;

	public ContactsGetter(String name, String phone, String email, boolean selected, String strTableName) {
		super();
		this.name = name;
		this.phoneNo = phone;
		this.email = email;
		this.selected = selected;
		this.tableName = strTableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
