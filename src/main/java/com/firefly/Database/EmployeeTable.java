package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, List<EmployeeInfo>>> empTableMap = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)

    private final List<String> searchColList = Arrays.asList(
            "employeeNum",
            "name",
            "name-f",
            "name-l",
            "cl",
            "phoneNum",
            "phoneNum-m",
            "phoneNum-l",
            "birthday",
            "birthday-y",
            "birthday-m",
            "birthday-d",
            "certi"
    );



    private EmployeeTable(){
        clear();
    }

    static EmployeeTable getInstance(){
        return empTable;
    }



    public void clear() {
        empTableMap.clear();
        for(String colName : searchColList){
            empTableMap.put(colName, new HashMap<String, List<EmployeeInfo>>());
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

        empTableMap.get("employeeNum").get(e.getEmployeeNumByString()).remove(e);
        empTableMap.get("name").get(e.getName()).remove(e);
        empTableMap.get("name-f").get(e.getFirstName()).remove(e);
        empTableMap.get("name-l").get(e.getLastName()).remove(e);
        empTableMap.get("cl").get(e.getClByString()).remove(e);
        empTableMap.get("phoneNum").get(e.getPhoneNumByString()).remove(e);
        empTableMap.get("phoneNum-m").get(e.getPhoneNumMidByString()).remove(e);
        empTableMap.get("phoneNum-l").get(e.getPhoneNumLastByString()).remove(e);
        empTableMap.get("birthday").get(e.getBirthdayByString()).remove(e);
        empTableMap.get("birthday-y").get(e.getBirthYearByString()).remove(e);
        empTableMap.get("birthday-m").get(e.getBirthMonthByString()).remove(e);
        empTableMap.get("birthday-d").get(e.getBirthDayOnlyByString()).remove(e);
        empTableMap.get("certi").get(e.getCertiByString()).remove(e);

    }





    public int add(EmployeeInfo e) {

        List<EmployeeInfo> eList = null;

        eList = empTableMap.get("employeeNum").get(e.getEmployeeNumByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("employeeNum").put(e.getEmployeeNumByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("name").get(e.getName());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("name").put(e.getName(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("name-f").get(e.getFirstName());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("name-f").put(e.getFirstName(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("name-l").get(e.getLastName());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("name-l").put(e.getLastName(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("cl").get(e.getClByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("cl").put(e.getClByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("phoneNum").get(e.getPhoneNumByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("phoneNum").put(e.getPhoneNumByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("phoneNum-m").get(e.getPhoneNumMidByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("phoneNum-m").put(e.getPhoneNumMidByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("phoneNum-l").get(e.getPhoneNumLastByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("phoneNum-l").put(e.getPhoneNumLastByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("birthday").get(e.getBirthdayByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("birthday").put(e.getBirthdayByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("birthday-y").get(e.getBirthYearByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("birthday-y").put(e.getBirthYearByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("birthday-m").get(e.getBirthMonthByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("birthday-m").put(e.getBirthMonthByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("birthday-d").get(e.getBirthDayOnlyByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("birthday-d").put(e.getBirthDayOnlyByString(),eList);
        }
        eList.add(e);


        eList = empTableMap.get("certi").get(e.getCertiByString());
        if( eList ==null) {
            eList = new ArrayList();
            empTableMap.get("certi").put(e.getCertiByString(),eList);
        }
        eList.add(e);


        return 0;

    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return (List<EmployeeInfo>) empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList()).stream().collect(Collectors.toList());
    }


}
