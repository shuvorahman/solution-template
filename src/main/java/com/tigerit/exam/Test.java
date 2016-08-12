package com.tigerit.exam;

/*
* For every test cases, we generate a test class to hold the variables for a test
* */
public class Test {
    private int numOfTable;
    private int numOfQuery;
    private Table table[];
    private Query query[];

    Test(){

    }
    Test(int noQuery, int noTable){
        this.numOfQuery = noQuery;
        this.numOfTable = noTable;
        table = new Table[noTable];
        query = new Query[noQuery];
    }

    public int getNumOfTable() {
        return numOfTable;
    }

    public void setNumOfTable(int numOfTable) {
        this.numOfTable = numOfTable;
    }

    public int getNumOfQuery() {
        return numOfQuery;
    }

    public void setNumOfQuery(int numOfQuery) {
        this.numOfQuery = numOfQuery;
    }

    public Table[] getTable() {
        return table;
    }

    public void setTable(Table[] table) {
        this.table = table;
    }

    public Query[] getQuery() {
        return query;
    }

    public void setQuery(Query[] query) {
        this.query = query;
    }
}
