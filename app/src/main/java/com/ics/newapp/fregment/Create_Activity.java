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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ics.newapp.R;

public class Create_Activity extends Fragment implements
        AdapterView.OnItemSelectedListener {
    Spinner act_deact,act_event;
    TextView private_selection;
    String[] country = {"--Select--","Active", "Deactive"};
    String[] event = {"--Select Event Type--","Blog", "Community","Locality"};

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


        private_selection = view.findViewById(R.id.private_selection);


        private_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment Favorite_List = new Selection_Screen();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, Favorite_List);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
//        act_deact = view.findViewById(R.id.act_deact);
//        act_deact.setOnItemSelectedListener(this);
//
//        act_event = view.findViewById(R.id.act_event);
//        act_event.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
       // act_deact.setAdapter(aa);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter bb = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, event);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
      //  act_event.setAdapter(bb);

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getActivity(), country[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
