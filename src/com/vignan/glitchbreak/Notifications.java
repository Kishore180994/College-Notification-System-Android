package com.vignan.glitchbreak;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.vignan.glitchbreak.R;

import com.shephertz.app42.paas.sdk.android.App42API;

public class Notifications extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		((TextView) findViewById(R.id.page_header)).setText("Notifications");
		 App42API.initialize(
	                this,
	                "ea46d5f56fb2f07f181b56c4fea760f786d898404567fb23d6e573a265902710",
	                "cae57098f4769304b1f1444908a3b0872bfb628ac59dfc0cddf486b0807377a7");
	        App42API.setLoggedInUser("<Logged In User>") ;
	        Util.registerWithApp42("935567936388");
	        
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	public void onClick(View view) {
		Intent intent = new Intent(this, MessageActivity.class);
		startActivity(intent);

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
	
	public void onStart() {
		super.onStart();

	}

	
	public void onStop() {
		super.onStop();

	}

	public void onDestroy() {
		super.onDestroy();

	}

	
	public void onReStart() {
		super.onRestart();

	}

	public void onPause() {
		super.onPause();
		unregisterReceiver(mBroadcastReceiver);

	}

	/*
	 * called when activity is resume
	 * 
	 * @override method of superclass (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	public void onResume() {
		super.onResume();
		String message = getIntent().getStringExtra(App42GCMService.EXTRA_MESSAGE); 
	        Log.d("MainActivity-onResume", "Message Recieved :"+message);
	        IntentFilter filter = new IntentFilter(App42GCMService.DISPLAY_MESSAGE_ACTION);
	        filter.setPriority(2);
	        registerReceiver(mBroadcastReceiver, filter);
	}
	
	 final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		  
	        @Override
	        public void onReceive(Context context, Intent intent) {
	        	
	        	String message = intent.getStringExtra(App42GCMService.EXTRA_MESSAGE);
	        	Log.i("MainActivity-BroadcastReceiver", "Message Recieved " +" : " +message);
	        	((TextView) findViewById(R.id.text)).setText(message);
	        	
	        }
	    };
}

