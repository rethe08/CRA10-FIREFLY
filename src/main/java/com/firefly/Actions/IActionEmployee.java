package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;

import java.util.HashMap;
import java.util.List;

public interface IActionEmployee {
    List<String> action(HashMap<String, String> inputCommand);
}
