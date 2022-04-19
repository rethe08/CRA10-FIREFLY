package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMap = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)


    static EmployeeTable getInstance(){
        return empTable;
    }

    private interface  SelectExecuteMapper{
        String getColumnDataFromEmployee(EmployeeInfo e);
    }

    private class EmployeeSelectColumn {
        String selectColumnName;
        SelectExecuteMapper exeMap;
        EmployeeSelectColumn(String selectColumnName, SelectExecuteMapper executeMapper){
            this.selectColumnName = selectColumnName;
            this.exeMap = executeMapper;
        }
    }

    private List<EmployeeSelectColumn> selectColList = Arrays.asList(
            new EmployeeSelectColumn("employeeNum",EmployeeInfo::getEmployeeNumByString ),
            new EmployeeSelectColumn("name",EmployeeInfo::getName ),
            new EmployeeSelectColumn("name-f",EmployeeInfo::getFirstName ),
            new EmployeeSelectColumn("name-l",EmployeeInfo::getLastName ),
            new EmployeeSelectColumn("cl",EmployeeInfo::getClByString ),
            new EmployeeSelectColumn("phoneNum",EmployeeInfo::getPhoneNumByString ),
            new EmployeeSelectColumn("phoneNum-m",EmployeeInfo::getPhoneNumMidByString ),
            new EmployeeSelectColumn("phoneNum-l",EmployeeInfo::getPhoneNumLastByString ),
            new EmployeeSelectColumn("birthday",EmployeeInfo::getBirthdayByString ),
            new EmployeeSelectColumn("birthday-y",EmployeeInfo::getBirthYearByString ),
            new EmployeeSelectColumn("birthday-m",EmployeeInfo::getBirthMonthByString ),
            new EmployeeSelectColumn("birthday-d",EmployeeInfo::getBirthDayOnlyByString ),
            new EmployeeSelectColumn("certi",EmployeeInfo::getCertiByString )
    );


    public void clear() {
        empTableMap.clear();
        for(EmployeeSelectColumn selectColumn : selectColList){
            empTableMap.put(selectColumn.selectColumnName, new HashMap<String, List<EmployeeInfo>>());
        }
    }

    public int size() {
        int retSize = 0;
        for(List<EmployeeInfo> l: empTableMap.get("employeeNum").values()){
            retSize+=l.size();
        }
        return retSize;
    }

    public void remove(EmployeeInfo e) {

        for(EmployeeSelectColumn empSelCol : selectColList){
            empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e)).remove(e);
        }

    }

    public int add(EmployeeInfo e) {

        List<EmployeeInfo> eList = null;
        for(EmployeeSelectColumn empSelCol : selectColList){
            eList = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            if( eList ==null) {
                eList = new ArrayList();
                empTableMap.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),eList);
            }
            eList.add(e);
        }
        return 0;

    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + (option2==null?"":""+option2);
        return (List<EmployeeInfo>) empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList()).stream().collect(Collectors.toList());
    }


    private EmployeeTable(){
        clear();
    }

}
