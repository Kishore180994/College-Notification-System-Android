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

public class Facilities extends Activity{
		private ListView listView;
		private String[] facilities;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.facilities);
	        
	        listView = (ListView) findViewById(R.id.veglistview);
	        facilities = getResources().getStringArray(R.array.facilities_list);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.facilities_list, facilities);
	        listView.setAdapter(adapter);
	        listView.setCacheColorHint(Color.TRANSPARENT);
	        listView.setOnItemClickListener(new OnItemClickListener() {
	        	 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	             
	             Intent myIntent = null;
	             
	             switch(position)
	             {
	             
	             case 0: myIntent = new Intent (Facilities.this, Library.class);
	                     break;
	                     
	             case 1: myIntent = new Intent (Facilities.this, Hostel.class);
	                     break;
	                   
	             case 2: myIntent = new Intent (Facilities.this, Transportation.class);
	                     break;
	             
	             case 3: myIntent = new Intent (Facilities.this, Health.class);
	             		break;
	             		
	             case 4: myIntent = new Intent (Facilities.this, Physical.class);
                 break;
                 
	             case 5: myIntent = new Intent (Facilities.this, Canteen.class);
                 break;
                 
	             case 6: myIntent = new Intent (Facilities.this, Stationery.class);
                 break;
	              
	             default : break;
	            }	
	             startActivity(myIntent);
	        }; 
	   });

	}
	}

