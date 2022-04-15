package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.List;

public interface IEmployeeDB {
    int addEmployee(EmployeeInfo e);
    int delEmployee(String searchCol, String searchValue);
    int searchEmployeeCnt(String searchCol, String searchValue, String Option2);
    List<EmployeeInfo> searchEmployeeTop5(String searchCol, String searchValue, String Option2);
    int modEmployee(String searchCol, String searchValue, String modCol, String modValue);
}
