package com.seanazlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DirReduction {
    public static String[] dirReduc(String[] arr) {
        ArrayList<String> list = new ArrayList(Arrays.asList(arr));
        while(true){
            Iterator<String> curr = list.iterator();
            Iterator<String> next = list.iterator();
            if(!next.hasNext()) {
                break;
            }
            String currString = curr.next();
            String nextString = next.next();
            if(!next.hasNext()) {
                break;
            }
            nextString = next.next();

            while(true) {
                System.out.println("Comparing " + currString + " to " + nextString);
                if ((currString == "NORTH" && nextString == "SOUTH") || (currString == "SOUTH" && nextString == "NORTH") ||
                        (currString == "WEST" && nextString == "EAST") || (currString == "EAST" && nextString == "WEST"))
                {
                    curr.remove();
                    curr.next();
                    curr.remove();
//                    System.out.println("Removed 2");
//                    System.out.println("New State: " + list);
                    break;
                }
                else {
                    if(!next.hasNext()) {
//                        System.out.println(Arrays.toString(arr));
//                        System.out.println(list);
                        return list.toArray(new String[0]);
                    }
                    currString = curr.next();
                    nextString = next.next();
                }
            }

        }

        System.out.println(arr);
        System.out.println(list);
        return list.toArray(new String[0]);
    }
}
