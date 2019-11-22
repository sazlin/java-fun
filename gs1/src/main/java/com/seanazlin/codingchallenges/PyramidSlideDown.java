package com.seanazlin.codingchallenges;


//https://www.codewars.com/kata/551f23362ff852e2ab000037/
public class PyramidSlideDown {

    public static int solve(int[][] py){
        for(int i = py.length - 2; i>=0; --i){
            for(int j = i; j>=0; --j){
                py[i][j] += Math.max(py[i+1][j+1], py[i+1][j]);
            }
        }
        return py[0][0];
    }

    public static void main(String[] args) {
        int[][] py = {{1},{2,3},{4,5,6},{4,2,6,8}};
        System.out.println(solve(py)); // 18
        int[][] py2 = {{3},{6,2},{1,7,4},{5,8,3,9},{4,1,8,5,2}};
        System.out.println(solve(py2)); // 32
    }
}
