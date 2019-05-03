package com.ics.newapp.fregment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ics.newapp.Navigation;
import com.ics.newapp.R;


public class ListFragment extends Fragment {
    LinearLayout fav_list,add_event,view_profile;
    Button btn_M_view,btn_L_view,view_event,list_join;
    TextView tv_comment_send;
    ImageView profile_image;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_list, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

        getActivity().setTitle("Miley");

        fav_list=(LinearLayout)view.findViewById(R.id.fav_list);
        add_event=(LinearLayout)view.findViewById(R.id.add_event);
        view_profile=(LinearLayout)view.findViewById(R.id.provile_view);
        btn_L_view=view.findViewById(R.id.l_view);
        btn_M_view=view.findViewById(R.id.m_view);
       view_event=view.findViewById(R.id.view_event);
        profile_image=view.findViewById(R.id.profile_image);
        list_join=view.findViewById(R.id.list_join);


        fav_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment Favorite_List=new Favorite_list();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,Favorite_List);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment profile_List=new Event_Host_User_Profile();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,profile_List);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        btn_M_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mapview=new MapFregment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,mapview);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    view_event.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Fragment view_event=new Guest_Event_Screen();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,view_event);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
});

    add_event.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment add_event=new Host_Event_Screen();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,add_event);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    });

        list_join.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle("Miley App")
                    .setMessage("Are you sure, you want to join");

            dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    exitLauncher();
                }

                private void exitLauncher() {
                    Toast.makeText(getContext(), "Join Success", Toast.LENGTH_SHORT).show();

                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();

        }
    });


    }

}
