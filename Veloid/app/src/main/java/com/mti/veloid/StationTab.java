package com.mti.veloid;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
*
*  Define a view that encapsulate the detail of a station
 */
public class StationTab extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(getIntent().getIntExtra("position", 0));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = mViewPager.getCurrentItem();
                Float[] position = Stations.getInstance().getmArrayList().get(pos).getFields().getPosition();

                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + position[0] + "," + position[1]);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_station_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_about:
                Intent intent = new Intent(StationTab.this, About.class);

                startActivity(intent);
                return true;
            case R.id.action_share: //TODO: Need to change the infos
                /*int pos = mViewPager.getCurrentItem();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String shareBody = Stations.getInstance().getmArrayList().get(pos).getFields().getName()
                        +;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Station Informations:");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));*/

                 //TODO: WORK IN PROCESS HERE
                int pos = mViewPager.getCurrentItem();
                Float[] position = Stations.getInstance().getmArrayList().get(pos).getFields().getPosition();
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + position[0] + "," + position[1]);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                // IntentChooser?
                Intent sharingIntent = new Intent(Intent.ACTION_SEND, gmmIntentUri);
                sharingIntent.setType("text/plain");
                sharingIntent.setPackage("com.google.android.apps.maps");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        TextView tv_name, tv_bikes, tv_address, tv_last_update;
        ImageView iv_status;

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Fill data
         * @param inflater
         * @param container
         * @param savedInstanceState
         * @return view
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_station_tab, container, false);

            ArrayList<VelibStation> velibs = Stations.getInstance().getmArrayList();
            int pos = getArguments().getInt(ARG_SECTION_NUMBER) - 1;

            tv_name = (TextView)rootView.findViewById(R.id.tv_name);
            iv_status = (ImageView)rootView.findViewById(R.id.iv_status);
            tv_bikes = (TextView)rootView.findViewById(R.id.tv_bikes);
            tv_address = (TextView)rootView.findViewById(R.id.tv_address);
            tv_last_update = (TextView)rootView.findViewById(R.id.tv_last_update);

            tv_name.setText(velibs.get(pos).getFields().getName());
            tv_bikes.setText(velibs.get(pos).getFields().getAvailable_bikes()
            + "/" + velibs.get(pos).getFields().getBike_stands());
            tv_address.setText(velibs.get(pos).getFields().getAddress());
            tv_last_update.setText(velibs.get(pos).getFields().getLast_update());

            if (velibs.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getFields().getStatus().equals("CLOSED"))
                iv_status.setImageResource(android.R.drawable.presence_busy);
            else if (velibs.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getFields().getAvailable_bikes() == 0)
                iv_status.setImageResource(android.R.drawable.presence_away);
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return Stations.getInstance().getmArrayList().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (Stations.getInstance().getmArrayList().isEmpty())
                return null;
            else
                return Stations.getInstance().getmArrayList().get(position).getFields().getName();
        }
    }
}
