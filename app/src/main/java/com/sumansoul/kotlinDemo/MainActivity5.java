package com.sumansoul.kotlinDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        List<Integer> lists=new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        reverseKGroup(lists,2);
    }
    public void reverseKGroup(List<Integer> list,int k){
        HashMap<Integer ,List<Integer>> map=new HashMap<Integer ,List<Integer>>();
        List<Integer> res=new ArrayList<>();
        List<Integer> order=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
           if(i%k==0 && i!=0){
               map.put(i,res);
               res=new ArrayList<>();
           }
            res.add( list.get(i) );
           if(i==list.size()-1){
               map.put(list.size(),res);
           }
        }
        Log.e("reverseKGroup",new Gson().toJson(map));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            List t = (List) entry.getValue();
            Collections.reverse(t);
            order.addAll(t);
        }
        Log.e("reverseKGroup",new Gson().toJson(order));
    }
}