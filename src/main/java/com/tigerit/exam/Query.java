package com.tigerit.exam;

/**
 * This class take the query syntax as constructor/s argument and structures the query
 * so that it can process the query
 */

public class Query {
    private boolean selectAll;
    private String firstTableName;
    private String secondTabeleName;
    private String firstTableShortName;
    private String secondTabeleShortName;
    private String selectedColoumns[];
    private String equalColoumnFirstTable;
    private String equalColoumnSecondTable;


    Query(){}

    Query(String selectPart, String fromPart, String joinPart, String onPart){
        //getting first table name, short name
        String fromTable[] = fromPart.split(" ");
        this.firstTableName = fromTable[1];
        if (fromTable.length > 2){
            this.firstTableShortName = fromTable[2];
        }

        //getting second table name and short name
        String joinTable[] = joinPart.split(" ");
        this.secondTabeleName = joinTable[1];
        if (joinTable.length > 2){
            this.secondTabeleShortName = joinTable[2];
        }

        //checking if all the coloumns of both table are selected to join
        if (selectPart.contains("*")){
            this.selectAll= true;
        } else {
            this.selectAll = false;
            //if not all coloumns are selected then find out the selected coloumns name with repective table name
            selectPart = selectPart.replace(",","");
            String splittedCol[] = selectPart.split(" ");
            this.selectedColoumns = new String[splittedCol.length - 1];

            for (int i=1; i<splittedCol.length; i++){
                selectedColoumns[i-1] = splittedCol[i];
            }
        }

        //Find out the logic on which the join case is depended
        onPart = onPart.replaceFirst("ON ","");
        String equalCol[] = onPart.replace(" ","").split("=");

        for (int i=0; i<equalCol.length; i++){
            if (equalCol[i].split("\\.")[0].equals(firstTableName) || equalCol[i].split("\\.")[0].equals(firstTableShortName)) {
                this.equalColoumnFirstTable = equalCol[i].split("\\.")[1];
            } else {
                this.equalColoumnSecondTable = equalCol[i].split("\\.")[1];
            }
        }
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    public String getFirstTableName() {
        return firstTableName;
    }

    public String getSecondTabeleName() {
        return secondTabeleName;
    }

    public String getFirstTableShortName() {
        return firstTableShortName;
    }

    public String[] getSelectedColoumns() {
        return selectedColoumns;
    }

    public String getEqualColoumnFirstTable() {
        return equalColoumnFirstTable;
    }

    public String getEqualColoumnSecondTable() {
        return equalColoumnSecondTable;
    }
}
