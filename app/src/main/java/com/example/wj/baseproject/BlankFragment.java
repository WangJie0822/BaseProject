package com.example.wj.baseproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wj.baseproject.base.BaseFragment;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    @Inject
    TestB b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Logger.d(b);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
