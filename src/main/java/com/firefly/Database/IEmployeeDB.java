package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.List;

public interface IEmployeeDB {
    int addEmployee(EmployeeInfo e);
    int searchEmployeeCnt(String searchCol, String searchValue, String option2);
    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2);
    int delEmployeeRetToCnt(String searchCol, String searchValue, String option2);
    List<EmployeeInfo> delEmployeeRetToTop5(String searchCol, String searchValue, String option2);
    int modEmployeeRetToCnt(String searchCol, String searchValue, String option2, String modCol, String modValue);
    List<EmployeeInfo> modEmployeeRetToTop5(String searchCol, String searchValue,String option2, String modCol, String modValue);
}
