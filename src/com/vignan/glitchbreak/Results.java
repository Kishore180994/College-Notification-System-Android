package com.vignan.glitchbreak;
import com.vignan.glitchbreak.WebViewActivity;
import com.vignan.glitchbreak.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Results extends Activity {
	private Button button5,button6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Context context = this;
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.results);
		button5=(Button) findViewById(R.id.button5);
		button6=(Button) findViewById(R.id.button6);
		

		button5.setOnClickListener(new OnClickListener() {

			

			

			@Override
			public void onClick(View arg0) {

				//Context context = null;
				Intent intent = new Intent(context, WebViewActivity4.class);
				startActivity(intent);
			}
		});
		
		button6.setOnClickListener(new OnClickListener() {

			

			

			@Override
			public void onClick(View arg0) {

				//Context context = null;
				Intent intent = new Intent(context, WebViewActivity5.class);
				startActivity(intent);
			}
		});
	}
	

}
