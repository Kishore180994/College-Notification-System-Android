package com.vignan.glitchbreak;



import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Pl_Objectives extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pl_objective);
	}

}