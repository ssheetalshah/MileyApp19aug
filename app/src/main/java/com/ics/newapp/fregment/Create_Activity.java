package com.ics.newapp.fregment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ics.newapp.R;

public class Create_Activity  extends Fragment {

    TextView private_selection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.create_activity, container, false);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        MenuItem item1 = menu.findItem(R.id.action_share);
   //     MenuItem item2 = menu.findItem(R.id.action_create);
        item.setVisible(false);
        item1.setVisible(false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

        setHasOptionsMenu(true);
        getActivity().setTitle("Create Activity");


        private_selection=view.findViewById(R.id.private_selection);

        private_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment Favorite_List=new Selection_Screen();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,Favorite_List);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


    }
}
