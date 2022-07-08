package math;

import java.io.*;


/*
* Greedy 란
*
* Greedy is an algorithmic paradigm that builds up a solution piece by piece,
* always choosing the next piece that offers the most obvious and immediate benefit.
* So the problems where choosing locally optimal also leads to global solution are the best fit for Greedy.
*
* For example consider the Fractional Knapsack Problem.
* The local optimal strategy is to choose the item that has maximum value vs weight ratio. T
* his strategy also leads to a globally optimal solution because we are allowed to take fractions of an item.
*
* Activity Selection Problem
*
*
*
*
* */


public class GiantsBattle {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(br.readLine()); // a팀의 score
        int b = Integer.parseInt(br.readLine()); // b팀의 score



        br.close();

        System.out.println((a-b)%3!=0?-1:(a/3 +" "+b%3+" "+b/3));

        //bw.write((a-b)%3!=0?String.valueOf(-1):(a/3 +" "+b%3+" "+b/3));
        //bw.flush();
        //bw.close();


        int diff = a>b?a-b:b-a;
        // awin = a/3; bwin = b/3; draw = a%3 = b%3;

        if((diff)%3!=0)
            System.out.println(-1);
        else
            System.out.println((a/3)+" "+a%3+" "+ b/3);

    }


}


