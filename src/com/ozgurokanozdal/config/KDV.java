package com.ozgurokanozdal.config;

import com.ozgurokanozdal.entity.Item;

import java.util.ArrayList;

public class KDV {

    public static final int KDV0 = 0;
    public static final int KDV1 = 1;
    public static final int KDV10 = 10;
    public static final int KDV20 = 20;


    public static ArrayList<Item> kdvList(){
        ArrayList<Item> kdvlist = new ArrayList<>();

        kdvlist.add(new Item(KDV0,"0"));
        kdvlist.add(new Item(KDV1,"1"));
        kdvlist.add(new Item(KDV10,"10"));
        kdvlist.add(new Item(KDV20,"20"));

        return kdvlist;
    }
}
