package com.vignan.glitchbreak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity2 extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://www.facebook.com/VITSTROLLS");
		
		webView.setWebViewClient(new WebViewClient());
		getActionBar().setDisplayHomeAsUpEnabled(true);

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
	             Intent upIntent = new Intent(this, Notifications.class);
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
	
	 
}
