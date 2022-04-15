package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

public interface IEmployeeDB {
    int addEmployee(EmployeeInfo e);
    int delEmployee(String searchCol, String searchValue);
    int searchEmployeeCnt(String searchCol, String searchValue);
    int modEmployee(String searchCol, String searchValue, String modCol, String modValue);
}
