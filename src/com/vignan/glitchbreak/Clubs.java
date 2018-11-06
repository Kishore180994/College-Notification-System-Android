package com.vignan.glitchbreak;

import java.util.ArrayList;

import com.vignan.glitchbreak.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class Clubs extends Activity {
    /** Called when the activity is first created. */
 
    private ImageAdapter mAdapter;
    private ArrayList<String> listName;
    private ArrayList<Integer> listClubs;
 
    private GridView gridView;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.clubs);
 
        prepareList();
 
        // prepared arraylist and passed it to the Adapter class
        mAdapter = new ImageAdapter(this, listName, listClubs);
 
        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridview1);
        gridView.setAdapter(mAdapter);
 
        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
            	Intent myintent = null;
                switch(position){
         case 0: myintent = new Intent (Clubs.this, Code.class);
                break;
                
        case 1: myintent = new Intent (Clubs.this, Toast.class);
                break;
              
        case 2: myintent = new Intent (Clubs.this, Street.class);
                break;
        
        case 3: myintent = new Intent (Clubs.this, Arts.class);
                break;
        case 4: myintent = new Intent (Clubs.this, Aero.class);
                break;
        case 5: myintent = new Intent (Clubs.this, Vrc.class);
        break;
        
                default: break;
                
                }
                startActivity(myintent);
                
            }
        });
 
    }
 
    public void prepareList()
    {
          listName = new ArrayList<String>();
 
          listName.add("Code Anime");
          listName.add("Toastmasters");
          listName.add("Vignan Street Cause");
          listName.add("Arts Club");
          listName.add("Aero Club");
          listName.add("VRC");
          
 
          listClubs = new ArrayList<Integer>();
          listClubs.add(R.drawable.code);
          listClubs.add(R.drawable.toast);
          listClubs.add(R.drawable.street);
          listClubs.add(R.drawable.arts);
          listClubs.add(R.drawable.aero);
          listClubs.add(R.drawable.robot);
          
    }
}