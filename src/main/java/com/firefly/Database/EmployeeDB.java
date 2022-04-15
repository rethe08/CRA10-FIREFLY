package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
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

    /**
     employeeNum
     name
     cl
     phoneNum
     birthday
     certi
     */

    @Override
    public int searchEmployeeCnt(String searchCol, String searchValue) {
        int searchCnt = 0;

        if(searchCol.equals("employeeNum")){
            for(EmployeeInfo e : empList) {
                e.getEmployeeNumByString().equals(searchValue);
                searchCnt++;
            }
        }
        else if(searchCol.equals("name")){
            for(EmployeeInfo e : empList) {
                e.getName().equals(searchValue);
                searchCnt++;
            }

        }
        else if(searchCol.equals("cl")){
            for(EmployeeInfo e : empList) {
                e.getCl().equals(EmployeeInfo.CareerLevel.valueOf(searchValue)                        );
                searchCnt++;
            }

        }
        else if(searchCol.equals("phoneNum")){
            for(EmployeeInfo e : empList) {
                e.getPhoneNumByString().equals(searchValue);
                searchCnt++;
            }

        }
        else if(searchCol.equals("birthday")){
            for(EmployeeInfo e : empList) {
                e.getBirthdayByString().equals(searchValue);
                searchCnt++;
            }

        }
        else if(searchCol.equals("certi")){
            for(EmployeeInfo e : empList) {
                e.getCerti().equals(EmployeeInfo.Certificate.valueOf(searchValue));
                searchCnt++;
            }

        }
        else{
            return -1;
        }

        return searchCnt;
    }


    @Override
    public int delEmployee(String searchCol, String searchValue) {
        return 0;
    }



    @Override
    public int modEmployee(String searchCol, String searchValue, String modCol, String modValue) {
        return 0;
    }
}
