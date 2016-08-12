package com.tigerit.exam;

import static com.tigerit.exam.IO.*;
import com.tigerit.exam.Test;
import com.tigerit.exam.Table;
import com.tigerit.exam.Query;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    // your application entry point
    @Override
    public void run() {
        /*############################################### INPUT ###############################################*/
        Integer numberOfTest = readLineAsInteger();
        Test test[] = new Test[numberOfTest];

        for (int i=0; i<numberOfTest; i++){
            //number of tables
            Integer numberOfTables = readLineAsInteger();
            test[i] = new Test();
            test[i].setNumOfTable(numberOfTables);
            Table table[] = new Table[numberOfTables];

            for (int j=0; j<numberOfTables; j++){

                //name of the table
                String tableName = readLine();

                //number of rows and coloumns
                String rowAndCol = readLine();
                int row = Integer.parseInt(rowAndCol.split(" ")[0]);
                int col = Integer.parseInt(rowAndCol.split(" ")[1]);
                table[j] = new Table(tableName, row, col);

                //name of the coloumns
                String colName[] = new String[col];
                String coloumnsName = readLine();
                for (int k=0; k<col; k++){
                    colName[k] = coloumnsName.split(" ")[k];
                }
                table[j].setColName(colName);

                //inputting the table data
                int data[][] = new int[row][col];
                for (int m=0; m<row; m++){
                    String rowData = readLine();
                    for (int n=0; n<col; n++){
                        data[m][n] = Integer.parseInt(rowData.split(" ")[n]);
                    }
                }
                table[j].setTableData(data);
            }

            test[i].setTable(table);

            //taking queries
            int numOfQueries = readLineAsInteger();
            test[i].setNumOfQuery(numOfQueries);
            Query query[] = new Query[numOfQueries];

            for (int q=0; q<numOfQueries; q++){
                //A query consists of 4 clauses. SELECT, FROM, JOIN, ON
                String selectPart = readLine();
                String fromPart = readLine();
                String joinPart = readLine();
                String onPart = readLine();
                query[q] = new Query(selectPart, fromPart, joinPart, onPart);
                readLine();
                //printLine(selectPart+" "+fromPart+" "+joinPart+" "+onPart);
            }
            test[i].setQuery(query);
        }

        /*################################################# OUTPUT ####################################################*/

        //for every test case
        for (int i=0; i<test.length; i++){
            printLine("Test: "+(i+1));
            int nq = test[i].getNumOfQuery();
            Query queryList[] = test[i].getQuery();

            //for every query in a test case
            for (int j=0; j<test[i].getNumOfQuery(); j++){
                String t1 = test[i].getQuery()[j].getFirstTableName();
                String t2 = test[i].getQuery()[j].getSecondTabeleName();
                Table firstTable = new Table();
                Table secondTable = new Table();
                String coloumnNames = "";

                //Getting the first table's object to calculate
                for (Table table : test[i].getTable()){
                    if (table.getTableName().equals(t1)){
                        firstTable = table;
                        break;
                    }
                }
                //Getting the second table object
                for (Table table : test[i].getTable()){
                    if (table.getTableName().equals(t2)){
                        secondTable = table;
                        break;
                    }
                }
                //Getting the first table;s coloumn on which the join query was based
                int index1=0, index2=0;
                for (int t=0; t<firstTable.getColName().length; t++){
                    if(firstTable.getColName()[t].equals(queryList[j].getEqualColoumnFirstTable())){
                        index1 = t;
                        break;
                    }
                }
                //Getting the second table's coloumn on which the join query was based
                for (int t=0; t<secondTable.getColName().length; t++){
                    if(secondTable.getColName()[t].equals(queryList[j].getEqualColoumnSecondTable())){
                        index2 = t;
                        break;
                    }
                }

                //If we select all coloumns to show
                if (queryList[j].isSelectAll()){
                    for (int k=0; k<firstTable.getCol(); k++){
                        coloumnNames += firstTable.getColName()[k]+" ";
                    }
                    for (int k=0; k<secondTable.getCol(); k++){
                        coloumnNames += secondTable.getColName()[k]+" ";
                    }
                    printLine(coloumnNames);

                    //Create new result class to finalize the query and showing the output
                    Result result = new Result(firstTable.getTableData(), secondTable.getTableData(), index1, index2, true);
                    result.output();
                    printLine("");

                //if we select particular coloumns to show
                } else {
                    int index[] = new int[test[i].getQuery()[j].getSelectedColoumns().length];
                    int count = 0;
                    for (String name : test[i].getQuery()[j].getSelectedColoumns()){
                        coloumnNames += name.split("\\.")[1]+" ";
                        if (name.split("\\.")[0].equals(test[i].getQuery()[j].getFirstTableName()) || name.split("\\.")[0].equals(test[i].getQuery()[j].getFirstTableShortName())){
                            //coloumn is in the first table
                            for (int t=0; t<firstTable.getCol(); t++){
                                if (name.split("\\.")[1].equals(firstTable.getColName()[t])) {
                                    index[count] = t;
                                    break;
                                }
                            }
                        } else {
                            //coloumn is in the second table
                            for (int t=0; t<secondTable.getCol(); t++){
                                if (name.split("\\.")[1].equals(secondTable.getColName()[t])) {
                                    index[count] = t+firstTable.getCol();
                                    break;
                                }
                            }
                        }
                        count++;
                    }
                    printLine(coloumnNames);

                    //Create new result class to finalize the query and showing the output
                    Result result = new Result(firstTable.getTableData(), secondTable.getTableData(), index1, index2, false);
                    result.setIndex(index);
                    result.output();
                    printLine("");

                }
            }
        }
    }
}
