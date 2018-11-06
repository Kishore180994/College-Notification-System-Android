package com.vignan.glitchbreak;

import java.util.ArrayList;

import com.vignan.glitchbreak.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
 
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
 
    private ImageAdapter mAdapter;
    private ArrayList<String> listCountry;
    private ArrayList<Integer> listFlag;
 
    private GridView gridView;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainactivity);
 
        prepareList();
 
        // prepared arraylist and passed it to the Adapter class
        mAdapter = new ImageAdapter(this, listCountry, listFlag);
 
        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);
 
        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
            	Intent myintent = null;
                switch(position){
         case 0: myintent = new Intent (MainActivity.this, About.class);
                break;
                
        case 1: myintent = new Intent (MainActivity.this, Admission.class);
                break;
              
        case 2: myintent = new Intent (MainActivity.this, Clubs.class);
                break;
        
        case 3: myintent = new Intent (MainActivity.this, Map.class);
                break;
        case 4: myintent = new Intent (MainActivity.this, Courses.class);
                break;
        case 5: myintent = new Intent (MainActivity.this, Facilities.class);
        break;
        case 6: myintent = new Intent (MainActivity.this, Fests.class);
        break;
        case 7: myintent = new Intent (MainActivity.this, GalleryView.class);
        break;
        case 8: myintent = new Intent (MainActivity.this, Contact.class);
        break;
        case 9: myintent = new Intent (MainActivity.this, Placements.class);
        break;
        case 10: myintent = new Intent (MainActivity.this, Reality.class);
        break;
        case 11: myintent = new Intent (MainActivity.this, Notifications.class);
        break;
        case 12: myintent = new Intent (MainActivity.this, Results.class);
        break;
                default: break;
                
                }
                startActivity(myintent);
                
            }
        });
 
    }
 
    public void prepareList()
    {
          listCountry = new ArrayList<String>();
 
          listCountry.add("About Us");
          listCountry.add("Admissions");
          listCountry.add("Clubs");
          listCountry.add("Map");
          listCountry.add("Courses");
          listCountry.add("Facilities");
          listCountry.add("Fests");
          listCountry.add("Gallery");
          listCountry.add("Contact Us");
          listCountry.add("Placements");
          listCountry.add("VITS Page");
          listCountry.add("Notifications");
          listCountry.add("Results");
          
 
          listFlag = new ArrayList<Integer>();
          listFlag.add(R.drawable.about);
          listFlag.add(R.drawable.admissions);
          listFlag.add(R.drawable.clubs);
          listFlag.add(R.drawable.map);
          listFlag.add(R.drawable.courses);
          listFlag.add(R.drawable.facilities);
          listFlag.add(R.drawable.fests);
          listFlag.add(R.drawable.gallery);
          listFlag.add(R.drawable.contact);
          listFlag.add(R.drawable.placements);
          listFlag.add(R.drawable.reality);
          listFlag.add(R.drawable.notification);
          listFlag.add(R.drawable.jntuh);
          
    }
}