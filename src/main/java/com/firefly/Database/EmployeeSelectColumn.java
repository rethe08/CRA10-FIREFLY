package com.firefly.Database;

public class EmployeeSelectColumn {

    String selectColumnName;
    ISelectExecuteMapper exeMap;

    EmployeeSelectColumn(String selectColumnName, ISelectExecuteMapper executeMapper){
        this.selectColumnName = selectColumnName;
        this.exeMap = executeMapper;
    }
}
