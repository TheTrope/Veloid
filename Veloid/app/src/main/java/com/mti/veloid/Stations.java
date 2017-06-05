package com.mti.veloid;

import java.util.ArrayList;

/**
 * Created by Brase-dev on 23/05/2017.
 */

/**
 * Define
 */
class Stations {
    private static final Stations ourInstance = new Stations();

    private ArrayList<VelibStation> mArrayList;

    public ArrayList<VelibStation> getmArrayList() {
        return mArrayList;
    }

    public void setmArrayList(ArrayList<VelibStation> mArrayList) {
        this.mArrayList = mArrayList;
    }

    static Stations getInstance() {
        return ourInstance;
    }

    private Stations() {
        mArrayList = new ArrayList<>();
    }
}
