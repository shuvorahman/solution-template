package com.tigerit.exam;

import static com.tigerit.exam.IO.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/*
* This class will output the result created from the table data
* It assumes to join two tables
* */

public class Result {
    private int table1[][];
    private int table2[][];
    private int index1;
    private int index2;
    private boolean selectAll;
    private int index[];

    public Result(int[][] table1, int[][] table2, int index1, int index2, boolean selectAll) {
        this.table1 = table1;
        this.table2 = table2;
        this.index1 = index1;
        this.index2 = index2;
        this.selectAll = selectAll;
    }

    public void setIndex(int[] index) {
        this.index = index;
    }

    public void output(){
        //arraylist to store the output of the join request as list of strings. every row is trated as string.
        ArrayList <String> out = new ArrayList <String>();
        //for all rows in first table it search for mathces in the second table
        for (int i=0; i<table1.length; i++){
            for(int j=0; j<table2.length; j++){
                if (table1[i][index1] == table2[j][index2]){
                    String temp = "";
                    //if all the colouns are selected to show
                    if (this.selectAll){
                        for (int k=0; k<table1[i].length; k++){
                            temp += table1[i][k]+" ";
                        }
                        for (int k=0; k<table2[j].length; k++){
                            temp += table2[j][k]+" ";
                        }
                    } else { //if only the selected rows have to be showed in order
                        for (int k = 0; k < index.length; k++) {
                            temp = index[k] < table1[i].length ? temp + table1[i][index[k]] + " " : temp + table2[j][index[k] - table1[i].length] + " ";
                        }
                    }
                    out.add(temp);
                }
            }
        }


        //sort the result lexicographically
        Collections.sort(out, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //print the answer
        Iterator print = out.iterator();
        while(print.hasNext()){
            printLine(print.next());
        }
    }
}
