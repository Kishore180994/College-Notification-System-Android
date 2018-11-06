package com.vignan.glitchbreak;

import com.vignan.glitchbreak.WebViewActivity;
import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Reality extends Activity{
	private Button button,button3,button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Context context = this;
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reality);
		button = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				//Context context = null;
				Intent intent = new Intent(context, WebViewActivity.class);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				//Context context = null;
				Intent intent = new Intent(context, WebViewActivity2.class);
				startActivity(intent);
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				//Context context = null;
				Intent intent = new Intent(context, WebViewActivity3.class);
				startActivity(intent);
			}
		});
	}

}
