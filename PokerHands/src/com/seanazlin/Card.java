//package com.seanazlin;
//
//import java.util.HashMap;
//
//public class Card implements Comparable<Card>{
//    public int value;
//    public String suit;
//    public final static HashMap<String,Integer> VALUE_MAP = new HashMap<String, Integer>() {{
//        put("a",14);
//        put("k",13);
//        put("q",12);
//        put("j",11);
//        put("t",10);
//        put("9",9);
//        put("8",8);
//        put("7",7);
//        put("6",6);
//        put("5",5);
//        put("4",4);
//        put("3",3);
//        put("2",2);
//    }};
//
//    public Card(int value, String suit){
//        this.value = value;
//        this.suit = suit;
//    }
//
//    public Card(String card){
//        this(VALUE_MAP.get(card.substring(0,1).toLowerCase()), card.substring(1,2).toLowerCase());
//    }
//
//    @Override
//    public int compareTo(Card otherCard){
//        return otherCard.value - value;
//    }
//
//    @Override
//    public String toString() {
//        return value + suit;
//    }
//}
