package com.seanazlin;

import java.util.*;

public class PokerHand {
    public enum Result { TIE, WIN, LOSS }
    public enum HandStrength {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH,
    }
    private ArrayList<Card> allCards = new ArrayList<>();
    private HashMap<Integer, Integer> valueCounts;
    private ArrayList<Integer> totalStrength = new ArrayList<>();

    public ArrayList<Integer> getTotalStrength() {
        return totalStrength;
    }

    PokerHand(String hand){
        for (String card: hand.split(" ")){
            allCards.add(new Card(card));
        }

        // Sort the hand by card value/strength
        Collections.sort(allCards);

        // Count number of cards by card value
        valueCounts = getValueCounts(allCards);

        // Calculate strength of hand for future comparison
        calculateTotalStrength();
    }

    private ArrayList<Integer> calculateTotalStrength(){
        if(isRoyalFlush()) {
            totalStrength.add(HandStrength.ROYAL_FLUSH.ordinal());
        }
        else if(isStraightFlush()) {
            totalStrength.add(HandStrength.STRAIGHT_FLUSH.ordinal());
            totalStrength.add(allCards.get(0).value);
        }
        else if(is4kind()) {
            totalStrength.add(HandStrength.FOUR_KIND.ordinal());
            int type4Value = 0, type1Value = 0;
            for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
                if(entry.getValue() == 4) {
                    type4Value = entry.getKey();
                } else {
                    type1Value = entry.getKey();
                }
            };
            totalStrength.add(type4Value);
            totalStrength.add(type1Value);
        }
        else if(isFullHouse()) {
            totalStrength.add(HandStrength.FULL_HOUSE.ordinal());
            int type3Value = 0, type2Value = 0;
            for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
                if(entry.getValue() == 3) {
                    type3Value = entry.getKey();
                } else {
                    type2Value = entry.getKey();
                }
            };
            totalStrength.add(type3Value);
            totalStrength.add(type2Value);
        }
        else if(isFlush()) {
            totalStrength.add(HandStrength.FLUSH.ordinal());
            totalStrength.add(allCards.get(0).value);
            totalStrength.add(allCards.get(1).value);
            totalStrength.add(allCards.get(2).value);
            totalStrength.add(allCards.get(3).value);
            totalStrength.add(allCards.get(4).value);
        }
        else if(isStraight()) {
            totalStrength.add(HandStrength.STRAIGHT.ordinal());
            totalStrength.add(allCards.get(0).value);
        }
        else if(is3kind()) {
            totalStrength.add(HandStrength.THREE_KIND.ordinal());
            int type3Value = 0, nextHighest = 0, nextLowest = 99;
            for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
                if(entry.getValue() == 3) {
                    type3Value = entry.getKey();
                } else {
                    if (entry.getKey() > nextHighest) nextHighest = entry.getKey();
                    if (entry.getKey() < nextLowest) nextLowest = entry.getKey();
                }
            };
            totalStrength.add(type3Value);
            totalStrength.add(nextHighest);
            totalStrength.add(nextLowest);
        }
        else if(numPairs() == 2) {
            totalStrength.add(HandStrength.TWO_PAIR.ordinal());
            int highPairValue = 0, lowPairValue = 99, fifthCardValue = 0;
            for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
                if(entry.getValue() == 2) {
                    if (entry.getKey() > highPairValue) highPairValue = entry.getKey();
                    if (entry.getKey() < lowPairValue) lowPairValue = entry.getKey();
                } else {
                    fifthCardValue = entry.getKey();
                }
            };
            totalStrength.add(highPairValue);
            totalStrength.add(lowPairValue);
            totalStrength.add(fifthCardValue);
        }
        else if(numPairs() == 1) {
            totalStrength.add(HandStrength.PAIR.ordinal());
            int pairValue = 0;
            ArrayList<Integer> nonPairCards = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
                if(entry.getValue() == 2) {
                    pairValue = entry.getKey();
                } else {
                    nonPairCards.add(entry.getKey());
                }
            };
            Collections.sort(nonPairCards, Collections.reverseOrder());
            totalStrength.add(pairValue);
            totalStrength.add(nonPairCards.get(0));
            totalStrength.add(nonPairCards.get(1));
            totalStrength.add(nonPairCards.get(2));
        }
        else {
            totalStrength.add(HandStrength.HIGH_CARD.ordinal());
            totalStrength.add(allCards.get(0).value);
            totalStrength.add(allCards.get(1).value);
            totalStrength.add(allCards.get(2).value);
            totalStrength.add(allCards.get(3).value);
            totalStrength.add(allCards.get(4).value);
        }
        return totalStrength;
    }

    private boolean isFlush(){
        String suit = allCards.get(0).suit;
        if(!(allCards.get(1).suit.equalsIgnoreCase(suit))) return false;
        if(!(allCards.get(2).suit.equalsIgnoreCase(suit))) return false;
        if(!(allCards.get(3).suit.equalsIgnoreCase(suit))) return false;
        if(!(allCards.get(4).suit.equalsIgnoreCase(suit))) return false;
        return true;
    }

    private boolean isStraight(){
        if(!(allCards.get(0).value == allCards.get(1).value + 1)) return false;
        if(!(allCards.get(1).value == allCards.get(2).value + 1)) return false;
        if(!(allCards.get(2).value == allCards.get(3).value + 1)) return false;
        if(!(allCards.get(3).value == allCards.get(4).value + 1)) return false;
        return true;
    }

    private boolean isStraightFlush(){
        return isFlush() && isStraight();
    }

    private boolean isRoyalFlush(){
        return isStraightFlush() && allCards.get(0).value == Card.VALUE_MAP.get("a");
    }

    private static HashMap<Integer, Integer> getValueCounts(List<Card> cards){
        HashMap<Integer, Integer> valueCounts = new HashMap<>();
        for (Card card: cards){
            valueCounts.put(card.value, valueCounts.getOrDefault(card.value, 0) + 1);
        }
        return valueCounts;
    }

    private boolean isNKind(int N){
        for (Integer valueCount : valueCounts.values()){
            if (valueCount == N) return true;
        }
        return false;
    }

    private boolean is4kind() {
        return isNKind(4);
    }

    private boolean is3kind() {
        return isNKind(3);
    }

    private int numPairs() {
        int numPairs = 0;
        for (Integer valueCount : valueCounts.values()){
            if (valueCount == 2) ++numPairs;
        }
        return numPairs;
    }

    private boolean isFullHouse(){
        return is3kind() && numPairs() == 1;
    }

    public Result compareWith(PokerHand hand) {
        System.out.println("Comparing\n" + hand + " to " + this);
        System.out.println("Strengths\n" + hand.totalStrength + " vs " + this.totalStrength);
        Iterator<Integer> handStrengthIterator = totalStrength.iterator();
        Iterator<Integer> otherHandStrengthIterator = hand.getTotalStrength().iterator();

        while(handStrengthIterator.hasNext() && otherHandStrengthIterator.hasNext()) {
            int handValue = handStrengthIterator.next();
            int otherHandValue = otherHandStrengthIterator.next();
            if (handValue > otherHandValue) return Result.WIN;
            else if (otherHandValue > handValue) return Result.LOSS;
        }
        return Result.TIE;
    }

    @Override
    public String toString() {
        return allCards.toString();
    }

    public static class Card implements Comparable<Card>{
        public int value;
        public String suit;
        public final static HashMap<String,Integer> VALUE_MAP = new HashMap<String, Integer>() {{
            put("a",14);
            put("k",13);
            put("q",12);
            put("j",11);
            put("t",10);
            put("9",9);
            put("8",8);
            put("7",7);
            put("6",6);
            put("5",5);
            put("4",4);
            put("3",3);
            put("2",2);
        }};

        public Card(int value, String suit){
            this.value = value;
            this.suit = suit;
        }

        public Card(String card){
            this(VALUE_MAP.get(card.substring(0,1).toLowerCase()), card.substring(1,2).toLowerCase());
        }

        @Override
        public int compareTo(Card otherCard){
            return otherCard.value - value;
        }

        @Override
        public String toString() {
            return value + suit;
        }
    }
}
