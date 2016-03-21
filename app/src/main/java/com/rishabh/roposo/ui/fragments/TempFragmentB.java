package com.rishabh.roposo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabh.roposo.R;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class TempFragmentB extends Fragment {

    public static TempFragmentB newInstance() {
        return new TempFragmentB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        return view;
    }
}
