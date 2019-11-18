package com.seanazlin.gs1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentExample1 {
    public static void main(String args[]){
        Map<String,String> map = new HashMap<>();
        map = Collections.synchronizedMap(map);
//        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "4");
        map.put("e", "5");
        String data = map.putIfAbsent("a", "3");
        System.out.println(map);
        System.out.println(data);
        for(Map.Entry<String,String> entry : map.entrySet()){
            if("c".equals(entry.getKey()) && "3".equals(entry.getValue())){
                map.put("f", "6");

            }
        }

    }
}
