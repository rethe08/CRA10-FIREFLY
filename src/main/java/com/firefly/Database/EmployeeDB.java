package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDB implements IEmployeeDB {

    List<EmployeeInfo> empList = new ArrayList<>();

    public int getEmpNum(){
        return empList.size();
    }

    @Override
    public int addEmployee(EmployeeInfo e) {
        empList.add(e);
        return 0;
    }



    @Override
    public int searchEmployeeCnt(String searchCol, String searchValue, String option2) {

        return searchEmployee(searchCol,searchValue,option2).size();
    }



    @Override
    public List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2) {
        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        emps.sort((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNumByString().compareTo( e2.getEmployeeNumByString() ) );

        return emps.size() <= 5 ? emps : emps.subList(0,5);
    }



    @Override
    public List<EmployeeInfo> delEmployeeRetTop5(String searchCol, String searchValue, String option2) {

        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
        }

        emps.sort((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNumByString().compareTo( e2.getEmployeeNumByString() ) );

        return emps.size() <= 5 ? emps : emps.subList(0,5);

    }


    @Override
    public int delEmployeeRetCnt(String searchCol, String searchValue, String option2){
        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
        }

        return emps.size();
    }



    @Override
    public int modEmployee(String searchCol, String searchValue, String modCol, String modValue, String option2) {
        return 0;
    }



    private List<EmployeeInfo> searchEmployee(String searchCol, String searchValue, String option2){
        List<EmployeeInfo> emps = new LinkedList<>();

        if(searchCol.equals("employeeNum")){
            for(EmployeeInfo e : empList) {
                if(e.getEmployeeNumByString().equals(searchValue) )
                    emps.add(e);
            }
        }
        else if(searchCol.equals("name")){
            if( "f".equals(option2) ){
                for(EmployeeInfo e : empList) {
                    if(e.getFirstName().equals(searchValue) )
                        emps.add(e);
                }
            }
            else if( "l".equals(option2) ){
                for(EmployeeInfo e : empList) {
                    if(e.getLastName().equals(searchValue) )
                        emps.add(e);
                }

            }
            else{
                for(EmployeeInfo e : empList) {
                    if(e.getName().equals(searchValue) )
                        emps.add(e);
                }

            }

        }
        else if(searchCol.equals("cl")){
            for(EmployeeInfo e : empList) {
                if(e.getCl().equals(EmployeeInfo.CareerLevel.valueOf(searchValue)))
                    emps.add(e);
            }

        }
        else if(searchCol.equals("phoneNum")){
            if( "m".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getPhoneNumMid() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }
            }
            else if("l".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getPhoneNumLast() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }

            }
            else {
                for (EmployeeInfo e : empList) {
                    if (e.getPhoneNumByString().equals(searchValue))
                        emps.add(e);
                }
            }

        }
        else if(searchCol.equals("birthday")){
            if( "y".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getBirthYear() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }
            }
            else if("m".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getBirthMonth() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }

            }
            else if("d".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getBirthDayOnly() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }

            }
            else {
                for (EmployeeInfo e : empList) {
                    if (e.getBirthdayByString().equals(searchValue))
                        emps.add(e);
                }
            }

        }
        else if(searchCol.equals("certi")){
            for(EmployeeInfo e : empList) {
                if(e.getCerti().equals(EmployeeInfo.Certificate.valueOf(searchValue)))
                    emps.add(e);
            }

        }
        else{
            return null;
        }

        return emps;

    }



}
