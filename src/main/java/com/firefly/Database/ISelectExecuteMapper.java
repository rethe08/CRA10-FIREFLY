package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

@FunctionalInterface
interface ISelectExecuteMapper {
    String getColumnDataFromEmployee(EmployeeInfo e);
}
