package com.vignan.glitchbreak;

import com.vignan.glitchbreak.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class pages extends Fragment {
	
	public pages(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.pages, container, false);
         
        return rootView;
    }
}
