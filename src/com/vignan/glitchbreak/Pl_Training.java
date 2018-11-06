package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Pl_Training extends Activity implements OnClickListener{
	private Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pl_training);
		
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent in = new Intent(Pl_Training.this,Pl_Training_Details.class);
		startActivity(in);	
	}
}