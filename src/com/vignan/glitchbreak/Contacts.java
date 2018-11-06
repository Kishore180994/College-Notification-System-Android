package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Contacts extends Fragment {
	
	public Contacts(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		RelativeLayout mRelativeLayout =  (RelativeLayout) inflater.inflate(R.layout.contacts,
                container, false);

        
        Button b1 = (Button) mRelativeLayout.findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent in = new Intent(getActivity(),Sync.class);
			     startActivity(in);
				
			}
		});
       
         
        return mRelativeLayout;
    }
}
