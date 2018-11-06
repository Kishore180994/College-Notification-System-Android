/**
 * 
 */
package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

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


public class MessageActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		((TextView) findViewById(R.id.page_header)).setText("Messages");

		String message = getIntent().getStringExtra(
				App42GCMService.EXTRA_MESSAGE);
		App42GCMService.resetMsgCount();
		((TextView) findViewById(R.id.text)).setText(message);

		Log.d("MessageActivity-onCreate", "Message Recieved :" + message);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public void onClick(View view) {
	finish();
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

	public void onResume() {
		super.onResume();
		String message = getIntent().getStringExtra(
				App42GCMService.EXTRA_MESSAGE);
		Log.d("MessageActivity-onResume", "Message Recieved :" + message);
		IntentFilter filter = new IntentFilter(
				App42GCMService.DISPLAY_MESSAGE_ACTION);
		filter.setPriority(2);
		registerReceiver(mBroadcastReceiver, filter);
	}

	@Override
	public void onPause() {
		unregisterReceiver(mBroadcastReceiver);
		super.onPause();
	}

	final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			// Right here do what you want in your activity
			String message = intent
					.getStringExtra(App42GCMService.EXTRA_MESSAGE);
			Log.i("MessageActivity-BroadcastReceiver", "Message Recieved "
					+ " : " + message);
			((TextView) findViewById(R.id.text)).setText(message);
			App42GCMService.resetMsgCount();

		}
	};

}
