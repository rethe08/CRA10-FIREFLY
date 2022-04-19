package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.*;
import java.util.stream.Collectors;

class EmployeeTable  {

    private static final EmployeeTable empTable = new EmployeeTable();

    private final Map<String, Map<String, Set<EmployeeInfo>>> empTableMap = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)
    private final Map<String, Map<String, Set<EmployeeInfo>>> empTableMapTop5 = new HashMap<>(); // employeeNum -> "11111111" -> (emp1, emp2, ...)

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


    public void remove(EmployeeInfo e) {

        for(EmployeeSelectColumn empSelCol : selectColList){

            Set<EmployeeInfo> allEmps = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            Set<EmployeeInfo> empsTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            allEmps.remove(e);
            empsTop5.remove(e);




            if( empsTop5.size() == 4 ) {
                if(allEmps.size() == 4 ) return;
                removeTop5(allEmps, empsTop5);
            }
        }

    }

    void removeTop5(Set<EmployeeInfo> allEmps,Set<EmployeeInfo> empsTop5){

        EmployeeInfo maxTopEmp = null;

        for(EmployeeInfo candEmp : allEmps){
            if(empsTop5.contains(candEmp)) {
                continue;
            }

            if(maxTopEmp==null) {
                maxTopEmp = candEmp;
                continue;
            }
            else{
                if(candEmp.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) > 0){
                    maxTopEmp = candEmp;
                }
            }

        }

        if(maxTopEmp != null) addInTop5(maxTopEmp) ;
    }


    public int add(EmployeeInfo e) {

        Set<EmployeeInfo> eList = null;
        for(EmployeeSelectColumn empSelCol : selectColList){
            eList = empTableMap.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
            if( eList ==null) {
                eList = new HashSet();
                empTableMap.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),eList);
            }
            eList.add(e);
        }

        addInTop5(e);
        return 0;

    }


    private void addInTop5(EmployeeInfo e) {

        Set<EmployeeInfo> eListTop5 = null;
        for(EmployeeSelectColumn empSelCol : selectColList){

            eListTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));

            addInTop5List(e, eListTop5, empSelCol);
        }
    }

    private void addInTop5List(EmployeeInfo e, Set<EmployeeInfo> eListTop5, EmployeeSelectColumn empSelCol) {
        if( eListTop5 == null) {
            eListTop5 = new HashSet<>();
            empTableMapTop5.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e), eListTop5);
            eListTop5.add(e);
        }
        else if(eListTop5.size()<5){
            eListTop5.add(e);

        }
        else{
            EmployeeInfo maxTopEmp = null;
            for(EmployeeInfo candEmp : eListTop5){
                if(maxTopEmp==null) maxTopEmp = candEmp;
                else{
                    if(candEmp.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) > 0){
                        maxTopEmp = candEmp;
                    }
                }

            }
            if(e.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) < 0)
            {
                eListTop5.remove(maxTopEmp);
                eListTop5.add(e);
            }

        }
    }
//
//    private void addInTop5(EmployeeInfo e) {
//
//        List<EmployeeInfo> eListTop5 = null;
//        for(EmployeeSelectColumn empSelCol : selectColList){
//
//            eListTop5 = empTableMapTop5.get(empSelCol.selectColumnName).get(empSelCol.exeMap.getColumnDataFromEmployee(e));
//            if( eListTop5 == null) {
//                eListTop5 = new ArrayList<>();
//                empTableMapTop5.get(empSelCol.selectColumnName).put(empSelCol.exeMap.getColumnDataFromEmployee(e),eListTop5);
//                eListTop5.add(e);
//            }
//            else if(eListTop5.size()<5){
//                eListTop5.add(e);
//
//            }
//            else{
//                EmployeeInfo maxTopEmp = null;
//                for(EmployeeInfo candEmp : eListTop5){
//                    if(maxTopEmp==null) maxTopEmp = candEmp;
//                    else{
//                        if(candEmp.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) > 0){
//                            maxTopEmp = candEmp;
//                        }
//                    }
//
//                }
//                if(e.getEmployeeNum10digitsString().compareTo(maxTopEmp.getEmployeeNum10digitsString()) < 0)
//                {
//                    eListTop5.remove(maxTopEmp);
//                    eListTop5.add(e);
//                }
//
//            }
//        }
//    }


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return (List<EmployeeInfo>) empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new HashSet()).stream().collect(Collectors.toList());
    }

    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2){

        String searchColWithOption = searchCol + ((option2==null || option2.equals(" "))?"":""+option2);
        return (List<EmployeeInfo>) empTableMapTop5.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new HashSet()).stream().collect(Collectors.toList());
//        return (List<EmployeeInfo>) empTableMap.getOrDefault(searchColWithOption, new HashMap<>()).getOrDefault(searchValue,new ArrayList()).stream().collect(Collectors.toList());

    }



}
