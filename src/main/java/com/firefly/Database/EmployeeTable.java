package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMap = new HashMap<>();
    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMapTop5 = new HashMap<>();


    private EmployeeTable(){
        clear();
    }

    static EmployeeTable getInstance(){
        return empTable;
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


    void clear() {
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

        List<EmployeeInfo> allEmps=null;
        List<EmployeeInfo> empsTop5=null;

        for(EmployeeSelectColumn empSelCol : selectColList){

            allEmps = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            empsTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            allEmps.remove(e);
            empsTop5.remove(e);




            if( empsTop5.size() == 4 ) {
                if(allEmps.size() == 4 ) {
                    return;
                }
                EmployeeInfo minTopEmp = null;

                for(EmployeeInfo candEmp : allEmps){
                    if(empsTop5.contains(candEmp)) {
                        continue;
                    }

                    if(minTopEmp==null) {
                        minTopEmp = candEmp;
                        continue;
                    }

                    if(candEmp.getEmployeeNum10digitsString().compareTo(minTopEmp.getEmployeeNum10digitsString()) < 0){
                        minTopEmp = candEmp;
                    }

                }

                if(minTopEmp != null) {
                    addInTop5(minTopEmp);
                }
                else {
                    return ;
                }
            }
        }

    }



    public int add(EmployeeInfo e) {

        List<EmployeeInfo> eList = null;
        for(EmployeeSelectColumn empSelCol : selectColList){
            eList = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            if( eList ==null) {
                eList = new ArrayList<>();
                empTableMap.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),eList);
            }
            eList.add(e);
        }

        addInTop5(e);
        return 0;

    }


    private void addInTop5(EmployeeInfo e) {

        List<EmployeeInfo> eListTop5 = null;
        EmployeeInfo maxTopEmp = null;
        for(EmployeeSelectColumn empSelCol : selectColList){

            eListTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            if( eListTop5 == null) {
                eListTop5 = new ArrayList<>();
                empTableMapTop5.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e), eListTop5);
                eListTop5.add(e);
            }
            else if(eListTop5.size()<5){
                eListTop5.add(e);

            }
            else{
                maxTopEmp = null;
                for(EmployeeInfo candEmp : eListTop5){
                    if(maxTopEmp==null) maxTopEmp = candEmp;
                    else{
                        if(candEmp.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) > 0){
                            maxTopEmp = candEmp;
                        }
                    }

                }
                if( maxTopEmp == null) return ;
                if(e.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) < 0)
                {
                    if(maxTopEmp == null) return;
                    eListTop5.remove(maxTopEmp);
                    eListTop5.add(e);
                }

            }
        }
    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList<>()).stream().collect(Collectors.toList());
    }

    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return empTableMapTop5.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList<>()).stream().collect(Collectors.toList());

    }



}
