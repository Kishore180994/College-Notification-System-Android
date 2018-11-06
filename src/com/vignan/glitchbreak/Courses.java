package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Courses extends Activity{
	private ListView listView;
	private String[] courses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stu
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.courses);
		listView = (ListView) findViewById(R.id.veg1listview);
        courses = getResources().getStringArray(R.array.courses_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.courses_list, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
             
             Intent myIntent = null;
             
             switch(position)
             {
             
             case 0: myIntent = new Intent (Courses.this, Btech.class);
                     break;
                     
             case 1: myIntent = new Intent (Courses.this, Mtech.class);
                     break;
                   
             case 2: myIntent = new Intent (Courses.this, Pg.class);
                     break;
             
           
              
             default : break;
            }	
             startActivity(myIntent);
        }; 
   });

}
}
