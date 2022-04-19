package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, Set<EmployeeInfo>>> empTableMap = new HashMap<>();
    private final Map<String, Map<String, Set<EmployeeInfo>>> empTableMapTop5 = new HashMap<>();


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
            empTableMap.put(selectColumn.selectColumnName, new HashMap<String, Set<EmployeeInfo>>());
            empTableMapTop5.put(selectColumn.selectColumnName, new HashMap<String, Set<EmployeeInfo>>());
        }
    }

    public int size() {
        int retSize = 0;
        for(Set<EmployeeInfo> l: empTableMap.get("employeeNum").values()){
            retSize+=l.size();
        }
        return retSize;
    }


    public int remove(EmployeeInfo e) {

        Set<EmployeeInfo> allEmps=null;
        Set<EmployeeInfo> empsTop5=null;

        for(EmployeeSelectColumn empSelCol : selectColList){

            allEmps = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            empsTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            allEmps.remove(e);
            empsTop5.remove(e);

            addInTop5IfRemoved(allEmps, empsTop5);
        }
        return 0;

    }

    private void addInTop5IfRemoved(Set<EmployeeInfo> allEmps, Set<EmployeeInfo> empsTop5) {
        if( empsTop5.size() == 4 ) {
            if(allEmps.size() == 4 ) {
                return;
            }

            EmployeeInfo minEmp = getMinEmployeeInfoNotInTop5(allEmps, empsTop5);

            if(minEmp != null) {
                empsTop5.add(minEmp);
            }
        }
    }

    private EmployeeInfo getMinEmployeeInfoNotInTop5(Set<EmployeeInfo> allEmps, Set<EmployeeInfo> empsTop5) {
        EmployeeInfo minEmp = null;

        for(EmployeeInfo candEmp : allEmps){
            if(empsTop5.contains(candEmp)) {
                continue;
            }

            if(minEmp==null) {
                minEmp = candEmp;
                continue;
            }

            if(candEmp.getEmployeeNum10digitsString().compareTo(minEmp.getEmployeeNum10digitsString()) < 0){
                minEmp = candEmp;
            }

        }
        return minEmp;
    }


    public int add(EmployeeInfo e) {

        Set<EmployeeInfo> emps = null;
        for(EmployeeSelectColumn empSelCol : selectColList){
            emps = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            if( emps ==null) {
                emps = new LinkedHashSet<>();
                empTableMap.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),emps);
            }
            emps.add(e);
        }

        addInAllTop5(e);
        return 0;
    }


    private int addInAllTop5(EmployeeInfo e) {

        Set<EmployeeInfo> empsTop5 = null;
        EmployeeInfo maxTopEmp = null;
        for(EmployeeSelectColumn empSelCol : selectColList){

            empsTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            if( empsTop5 == null) {
                empsTop5 = new LinkedHashSet<>();
                empTableMapTop5.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e), empsTop5);
                empsTop5.add(e);
            }
            else if(empsTop5.size()<5){
                empsTop5.add(e);

            }
            else{
                maxTopEmp = getMaxEmployeeInfoInTop5(empsTop5);
                if(maxTopEmp != null && e.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) < 0)
                {
                    empsTop5.remove(maxTopEmp);
                    empsTop5.add(e);
                }

            }
        }
        return 0;
    }

    private EmployeeInfo getMaxEmployeeInfoInTop5(Set<EmployeeInfo> empsTop5) {
        EmployeeInfo maxTopEmp = null;
        for(EmployeeInfo candEmp : empsTop5){
            if(maxTopEmp==null) maxTopEmp = candEmp;
            else{
                if(candEmp.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) > 0){
                    maxTopEmp = candEmp;
                }
            }
        }
        return maxTopEmp;
    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new LinkedHashSet<>()).stream().collect(Collectors.toList());
    }

    List<EmployeeInfo> searchEmployeeTop5Sorted(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return empTableMapTop5.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new LinkedHashSet<>())
                .stream().sorted((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNum10digitsString().compareTo( e2.getEmployeeNum10digitsString() ) )
                .collect(Collectors.toList());

    }



}
