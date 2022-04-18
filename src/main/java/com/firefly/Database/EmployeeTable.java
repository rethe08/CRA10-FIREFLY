package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//class EmployeeTable<E> extends ArrayList<E> {
//
//}

class EmployeeTable  {

    static EmployeeTable employeeTable = new EmployeeTable();

    private EmployeeTable(){
    }

    static EmployeeTable getInstance(){
        return employeeTable;
    }

    List<EmployeeInfo> empList = new ArrayList();


    public EmployeeInfo get(int index){
        return empList.get(index);
    }

    public void clear() {
        empList.clear();
    }

    public int size() {
        return empList.size();
    }

    public void add(EmployeeInfo e) {
        empList.add(e);
    }

    public void remove(EmployeeInfo e) {
        empList.remove(e);
    }

/*
employeeNum
name
 f
 l
cl
phoneNum
 m
 l
birthday
 y
 m
 d
certi

* */


    List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){
        List<EmployeeInfo> emps = new LinkedList<>();

        if(searchCol.equals("employeeNum")){
            for(int i = 0; i< empList.size(); i++){
                EmployeeInfo e = empList.get(i);
                if(e.getEmployeeNumByString().equals(searchValue) )
                    emps.add(e);
            }
        }
        else if(searchCol.equals("name")){
            if( "f".equals(option2) ){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getFirstName().equals(searchValue) )
                        emps.add(e);
                }
            }
            else if( "l".equals(option2) ){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getLastName().equals(searchValue) )
                        emps.add(e);
                }

            }
            else{
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getName().equals(searchValue) )
                        emps.add(e);
                }

            }

        }
        else if(searchCol.equals("cl")){
            for(int i = 0; i< empList.size(); i++){
                EmployeeInfo e = empList.get(i);
                if(e.getClByString().equals(searchValue) )
                    emps.add(e);
            }

        }
        else if(searchCol.equals("phoneNum")){
            if( "m".equals(option2)){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getPhoneNumMidByString().equals(searchValue) )
                        emps.add(e);
                }
            }
            else if("l".equals(option2)){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getPhoneNumLastByString().equals(searchValue) )
                        emps.add(e);
                }

            }
            else {
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if (e.getPhoneNumByString().equals(searchValue))
                        emps.add(e);
                }
            }

        }
        else if(searchCol.equals("birthday")){
            if( "y".equals(option2)){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getBirthYearByString().equals(searchValue) )
                        emps.add(e);
                }
            }
            else if("m".equals(option2)){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getBirthMonthByString().equals(searchValue) )
                        emps.add(e);
                }

            }
            else if("d".equals(option2)){
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if(e.getBirthDayOnlyByString().equals(searchValue) )
                        emps.add(e);
                }

            }
            else {
                for(int i = 0; i< empList.size(); i++){
                    EmployeeInfo e = empList.get(i);
                    if (e.getBirthdayByString().equals(searchValue))
                        emps.add(e);
                }
            }

        }
        else if(searchCol.equals("certi")){
            for(int i = 0; i< empList.size(); i++){
                EmployeeInfo e = empList.get(i);
                if(e.getCertiByString().equals(searchValue))
                    emps.add(e);
            }

        }
        else{
            return null;
        }

        return emps;

    }


}
