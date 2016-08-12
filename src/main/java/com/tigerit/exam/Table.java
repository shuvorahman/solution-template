package com.tigerit.exam;

/*
* this class structures the tables of a query
*/
public class Table {
	private String tableName;
	private int row, col;
	private String[] colName;
    private int[][] tableData;

    Table(){}

    Table(String tableName, int row, int col){
        this.tableName = tableName;
        this.row = row;
        this.col = col;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String[] getColName() {
        return colName;
    }

    public void setColName(String[] colName) {
        this.colName = colName;
    }

    public int[][] getTableData() {
        return tableData;
    }

    public void setTableData(int[][] tableData) {
        this.tableData = tableData;
    }
}
