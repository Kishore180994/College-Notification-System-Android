package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Placements extends Activity{
	private ListView lv;
	private String[] placement;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.placements);
		lv =(ListView)findViewById(R.id.lv3);
		placement = getResources().getStringArray(R.array.placement_list);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.placement_list,placement));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			 public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent myIntent = null;
				switch(position)
				{
				case 0: myIntent = new Intent(Placements.this,Pl_About.class);
						break;
				case 1: myIntent = new Intent(Placements.this,Pl_StudentsPlaced.class);
						break;
				case 2: myIntent = new Intent(Placements.this,Pl_Objectives.class);
						break;
				case 3: myIntent = new Intent(Placements.this,Pl_Training.class);
						break;

				default: break;
				}
				startActivity(myIntent);
				
			}
		});
		lv.setCacheColorHint(Color.BLACK);
	}
}

