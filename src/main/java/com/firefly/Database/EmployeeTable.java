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


    interface  ExecuteMapper{
        String getColumnDataFromEmployee(EmployeeInfo e);
    }

    class EmployeeSelectColumn {
        String selectColumnName;
        ExecuteMapper executeMapper;
        EmployeeSelectColumn(String selectColumnName, ExecuteMapper executeMapper){
            this.selectColumnName = selectColumnName;
            this.executeMapper = executeMapper;
        }
    }

    List<EmployeeSelectColumn> selectColList = Arrays.asList(
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



    String getColumnDataFromEmployee(EmployeeInfo e, EmployeeSelectColumn selectColumnName){

        ExecuteMapper exe = selectColumnName.executeMapper;
        return exe.getColumnDataFromEmployee(e);
    }





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
            empTableMap.get(empSelCol.selectColumnName).get(getColumnDataFromEmployee(e,empSelCol)).remove(e);
        }

    }





    public int add(EmployeeInfo e) {

        List<EmployeeInfo> eList = null;
        for(EmployeeSelectColumn empSelCol : selectColList){
            eList = empTableMap.get(empSelCol.selectColumnName).get(getColumnDataFromEmployee(e,empSelCol));
            if( eList ==null) {
                eList = new ArrayList();
                empTableMap.get(empSelCol.selectColumnName).put(getColumnDataFromEmployee(e,empSelCol),eList);
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
