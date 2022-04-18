package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDB implements IEmployeeDB {

    static List<EmployeeInfo> empList = new ArrayList<>();

    public void clear(){
        empList.clear();
    }

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
    public List<EmployeeInfo> delEmployeeRetToTop5(String searchCol, String searchValue, String option2) {

        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
        }

        emps.sort((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNumByString().compareTo( e2.getEmployeeNumByString() ) );

        return emps.size() <= 5 ? emps : emps.subList(0,5);

    }


    @Override
    public int delEmployeeRetToCnt(String searchCol, String searchValue, String option2){
        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
        }

        return emps.size() <= 5 ? emps.size() : emps.subList(0,5).size();
    }



    @Override
    public int modEmployeeRetToCnt(String searchCol, String searchValue,String option2, String modCol, String modValue) {
        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
            EmployeeInfo newEmp = makeNewModifiedEmployeeInList(e,modCol,modValue);
            empList.add(newEmp);
        }

        return emps.size();
    }


    @Override
    public List<EmployeeInfo> modEmployeeRetToTop5(String searchCol, String searchValue, String option2, String modCol, String modValue) {
        List<EmployeeInfo> emps = searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empList.remove(e);
            EmployeeInfo newEmp = makeNewModifiedEmployeeInList(e,modCol,modValue);
            empList.add(newEmp);
        }

        emps.sort((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNumByString().compareTo( e2.getEmployeeNumByString() ) );

        return emps.size() <= 5 ? emps : emps.subList(0,5);
    }


    private EmployeeInfo makeNewModifiedEmployeeInList(EmployeeInfo e, String modCol, String modValue){
        EmployeeInfo newEmp ;
        if(modCol.equals("employeeNum")){
            newEmp = new EmployeeInfo(modValue, e.getName(), e.getCl().toString(),
                                    e.getPhoneNumByString(), e.getBirthdayByString(), e.getCerti().toString());
        }
        else if(modCol.equals("name")){
            newEmp = new EmployeeInfo(e.getEmployeeNumByString(), modValue, e.getCl().toString(),
                    e.getPhoneNumByString(), e.getBirthdayByString(), e.getCerti().toString());

        }
        else if(modCol.equals("cl")){
            newEmp = new EmployeeInfo(e.getEmployeeNumByString(), e.getName(), modValue,
                    e.getPhoneNumByString(), e.getBirthdayByString(), e.getCerti().toString());


        }
        else if(modCol.equals("phoneNum")){
            newEmp = new EmployeeInfo(e.getEmployeeNumByString(), e.getName(),  e.getCl().toString(),
                    modValue, e.getBirthdayByString(), e.getCerti().toString());

        }
        else if(modCol.equals("birthday")){
            newEmp = new EmployeeInfo(e.getEmployeeNumByString(), e.getName(),  e.getCl().toString(),
                    e.getPhoneNumByString(), modValue, e.getCerti().toString());

        }
        else if(modCol.equals("certi")){
            newEmp = new EmployeeInfo(e.getEmployeeNumByString(), e.getName(),  e.getCl().toString(),
                    e.getPhoneNumByString(), e.getBirthdayByString(), modValue);


        }
        else{
            return null;
        }

        return newEmp;
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
            if( "-f".equals(option2) ){
                for(EmployeeInfo e : empList) {
                    if(e.getFirstName().equals(searchValue) )
                        emps.add(e);
                }
            }
            else if( "-l".equals(option2) ){
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
            if( "-m".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getPhoneNumMid() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }
            }
            else if("-l".equals(option2)){
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
            if( "-y".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getBirthYear() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }
            }
            else if("-m".equals(option2)){
                for(EmployeeInfo e : empList) {
                    if(e.getBirthMonth() == Integer.parseInt(searchValue) )
                        emps.add(e);
                }

            }
            else if("-d".equals(option2)){
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
