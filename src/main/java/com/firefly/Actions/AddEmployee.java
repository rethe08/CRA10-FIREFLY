package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.Database.IEmployeeDB;
import com.firefly.EmployeeInfo.EmployeeInfo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AddEmployee implements IActionEmployee {
    private List<String> emptyList = new ArrayList();

    @Override
    public void printResult() {

    }

    @Override
    public List<String> action(HashMap<String, String> inputCommand) {
        IEmployeeDB db = new EmployeeDB();
        db.addEmployee(new EmployeeInfo(inputCommand.get("VALUE1"),inputCommand.get("VALUE2"),inputCommand.get("VALUE3"),inputCommand.get("VALUE4"),inputCommand.get("VALUE5"),inputCommand.get("VALUE6")));
        return emptyList;
    }
}
