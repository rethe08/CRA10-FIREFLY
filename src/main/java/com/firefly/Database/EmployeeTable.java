package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMap = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)
    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMapTop5 = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)

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
        empTableMapTop5.clear();
        for(EmployeeSelectColumn selectColumn : selectColList){
            empTableMap.put(selectColumn.selectColumnName, new HashMap<String, List<EmployeeInfo>>());
            empTableMapTop5.put(selectColumn.selectColumnName, new HashMap<String, List<EmployeeInfo>>());
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
            empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e)).remove(e);
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

        addInTop5(e);
        return 0;

    }

    private void addInTop5(EmployeeInfo e) {

        List<EmployeeInfo> eListTop5 = null;
        for(EmployeeSelectColumn empSelCol : selectColList){

            eListTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            if( eListTop5 ==null) {
                eListTop5 = new ArrayList<>();
                empTableMapTop5.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),eListTop5);
                eListTop5.add(e);
            }
            else if(eListTop5.size()<5){
                eListTop5.add(e);

            }
            else{
                for(EmployeeInfo empToReplace : eListTop5){
                    if(e.getEmployeeNum10digitsString().compareTo(empToReplace.getEmployeeNum10digitsString()) < 0){
                        eListTop5.remove(empToReplace);
                        eListTop5.add(e);
                        break;
                    }
                }
            }
        }
    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return (List<EmployeeInfo>) empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList()).stream().collect(Collectors.toList());
    }

    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return (List<EmployeeInfo>) empTableMapTop5.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList()).stream().collect(Collectors.toList());
    }


    private EmployeeTable(){
        clear();
    }

}
