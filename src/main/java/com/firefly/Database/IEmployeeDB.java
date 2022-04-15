package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.List;

public interface IEmployeeDB {
    int addEmployee(EmployeeInfo e);
    List<EmployeeInfo> delEmployeeRetTop5(String searchCol, String searchValue, String option2);
    int delEmployeeRetCnt(String searchCol, String searchValue, String option2);
    int searchEmployeeCnt(String searchCol, String searchValue, String option2);
    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String option2);
    int modEmployee(String searchCol, String searchValue, String modCol, String modValue, String option2);
}
