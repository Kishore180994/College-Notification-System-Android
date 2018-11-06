package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Admission extends Activity{

	private ListView listView;
	
	private String[] admission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admission);
		listView = (ListView) findViewById(R.id.lv4);
        admission = getResources().getStringArray(R.array.admission_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.admission_list, admission);
        listView.setAdapter(adapter);
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
             
             Intent myIntent = null;
             
             switch(position)
             {
             
             case 0: myIntent = new Intent (Admission.this, AdmissionProcedure.class);
                     break;
                     
             case 1: myIntent = new Intent (Admission.this, AdmissionCriteria.class);
                     break;
                   
             case 2: myIntent = new Intent (Admission.this, FeeStructure.class);
                     break;
             
            
              
             default : break;
            }	
             startActivity(myIntent);
        }; 
   });

}
}

