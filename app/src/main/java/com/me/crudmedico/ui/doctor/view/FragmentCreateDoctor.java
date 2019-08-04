package com.me.crudmedico.ui.doctor.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.crudmedico.R;

import butterknife.ButterKnife;


public class FragmentCreateDoctor extends Fragment {


    public static FragmentCreateDoctor newInstance(String param1, String param2) {
        FragmentCreateDoctor fragment = new FragmentCreateDoctor();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_doctor, container, false);
        ButterKnife.bind(this, view);
        return view;

    }


}
