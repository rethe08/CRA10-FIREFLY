package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.List;

public class EmployeeDB implements IEmployeeDB {

    private EmployeeTable empTable = EmployeeTable.getInstance();

    void clear(){
        empTable.clear();
    }

    int getEmpNum(){
        return empTable.size();
    }

    @Override
    public int addEmployee(EmployeeInfo e) {

        return empTable.add(e);
    }



    @Override
    public int searchEmployeeCnt(String searchCol, String searchValue, String option2) {

        return empTable.searchEmployee(searchCol,searchValue,option2).size();
    }




    @Override
    public List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2) {
        List<EmployeeInfo> empsTop5 = empTable.searchEmployeeTop5(searchCol,searchValue,option2);

        sortEmpList(empsTop5);
        return empsTop5;
    }



    @Override
    public List<EmployeeInfo> delEmployeeRetToTop5(String searchCol, String searchValue, String option2) {

        List<EmployeeInfo> emps = empTable.searchEmployee(searchCol,searchValue,option2);
        List<EmployeeInfo> empsTop5 = empTable.searchEmployeeTop5(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empTable.remove(e);
        }

        sortEmpList(empsTop5);
        return empsTop5;

    }


    @Override
    public int delEmployeeRetToCnt(String searchCol, String searchValue, String option2){
        List<EmployeeInfo> emps = empTable.searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empTable.remove(e);
        }

        return emps.size();
    }



    @Override
    public int modEmployeeRetToCnt(String searchCol, String searchValue,String option2, String modCol, String modValue) {
        List<EmployeeInfo> emps = empTable.searchEmployee(searchCol,searchValue,option2);

        for(EmployeeInfo e : emps){
            empTable.remove(e);
            EmployeeInfo newEmp = makeNewModifiedEmployee(e,modCol,modValue);
            empTable.add(newEmp);
        }

        return emps.size();
    }


    @Override
    public List<EmployeeInfo> modEmployeeRetToTop5(String searchCol, String searchValue, String option2, String modCol, String modValue) {
        List<EmployeeInfo> emps = empTable.searchEmployee(searchCol,searchValue,option2);
        List<EmployeeInfo> empsTop5 = empTable.searchEmployeeTop5(searchCol,searchValue,option2);

        EmployeeInfo newEmp = null;
        for(EmployeeInfo e : emps){
            empTable.remove(e);
            newEmp = makeNewModifiedEmployee(e,modCol,modValue);
            empTable.add(newEmp);
        }

        sortEmpList(empsTop5);
        return empsTop5;
    }


    private void sortEmpList(List<EmployeeInfo> emps){

        emps.sort((EmployeeInfo e1, EmployeeInfo e2) -> e1.getEmployeeNum10digitsString().compareTo( e2.getEmployeeNum10digitsString() ) );
    }


    private EmployeeInfo makeNewModifiedEmployee(EmployeeInfo e, String modCol, String modValue){
        EmployeeInfo newEmp = null ;
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

}
