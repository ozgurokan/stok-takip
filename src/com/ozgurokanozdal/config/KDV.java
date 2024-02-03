package com.ozgurokanozdal.config;

import com.ozgurokanozdal.dto.Item;

import java.util.ArrayList;

public class KDV {

    public static final int KDV0 = 0;
    public static final int KDV1 = 1;
    public static final int KDV10 = 10;
    public static final int KDV20 = 20;

    public static ArrayList<Item<Integer,String>> kdvList(){
        ArrayList<Item<Integer,String>> kdvlist = new ArrayList<>();

        kdvlist.add(new Item<Integer,String>(KDV0,"0"));
        kdvlist.add(new Item<Integer,String>(KDV1,"1"));
        kdvlist.add(new Item<Integer,String>(KDV10,"10"));
        kdvlist.add(new Item<Integer,String>(KDV20,"20"));

        return kdvlist;
    }
}
