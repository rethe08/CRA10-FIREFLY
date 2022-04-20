package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.Database.IEmployeeDB;
import com.firefly.EmployeeInfo.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchEmployeeTest {

    EmployeeInfo info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11, info12, info13, info14, info15;

    IEmployeeDB db = new EmployeeDB();

    @BeforeEach
    void setup(){
        info1 = new EmployeeInfo("10000001", "BOBBY KIM", "CL2",
                "010-3333-7777", "19850715", "PRO");
        info2 = new EmployeeInfo("10000002", "NA LEE", "CL2",
                "010-1111-1321", "19770315", "PRO");
        info3 = new EmployeeInfo("10000003", "ALL JI", "CL3",
                "010-3333-1231", "19990115", "PRO");
        info4 = new EmployeeInfo("10000004", "GILJA HA", "CL3",
                "010-4444-5555", "19910215", "PRO");
        info5 = new EmployeeInfo("10000005", "GILDONG HONG", "CL2",
                "010-3333-4444", "19900115", "PRO");
        info6 = new EmployeeInfo("10000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info7 = new EmployeeInfo("690000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info8 = new EmployeeInfo("75000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info9 = new EmployeeInfo("00000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info10 = new EmployeeInfo("20000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info11 = new EmployeeInfo("11000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info12 = new EmployeeInfo("01000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info13 = new EmployeeInfo("70000006", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info14 = new EmployeeInfo("01000012", "PIO PARK", "CL4",
                "010-2000-1321", "19880715", "PRO");
        info15 = new EmployeeInfo("70000011", "PIO PARK", "CL4",
                "010-2000-1321", "19880715", "PRO");

        db.addEmployee(info1);
        db.addEmployee(info2);
        db.addEmployee(info3);
        db.addEmployee(info4);
        db.addEmployee(info5);
        db.addEmployee(info6);
        db.addEmployee(info7);
        db.addEmployee(info8);
        db.addEmployee(info9);
        db.addEmployee(info10);
        db.addEmployee(info11);
        db.addEmployee(info12);
        db.addEmployee(info13);
        db.addEmployee(info14);
        db.addEmployee(info15);
    }

    @Test
    void searchTestSorting(){
        SearchEmployee schTester = new SearchEmployee();

        HashMap<String, String> inputValue = new HashMap<>();
        inputValue.put("COMMAND","SCH");
        inputValue.put("OPTION1","-p");
        inputValue.put("OPTION2","-f");
        inputValue.put("OPTION3","");
        inputValue.put("VALUE1","name");
        inputValue.put("VALUE2","PIO");

        List<String> result = schTester.action(inputValue);

        Assertions.assertEquals(5, result.size());
    }

    @Test
    void searchTestUpToFive(){
        SearchEmployee schTester = new SearchEmployee();

        HashMap<String, String> inputValue = new HashMap<>();
        inputValue.put("COMMAND","SCH");
        inputValue.put("OPTION1","-p");
        inputValue.put("OPTION2","-l");
        inputValue.put("OPTION3","");
        inputValue.put("VALUE1","name");
        inputValue.put("VALUE2","KIM");

        List<String> result = schTester.action(inputValue);

        Assertions.assertEquals(5, result.size());
    }

    @Test
    void searchTestFoundNum(){
        SearchEmployee schTester = new SearchEmployee();

        HashMap<String, String> inputValue = new HashMap<>();
        inputValue.put("COMMAND","SCH");
        inputValue.put("OPTION1","");
        inputValue.put("OPTION2","-l");
        inputValue.put("OPTION3","");
        inputValue.put("VALUE1","name");
        inputValue.put("VALUE2","KIM");

        List<String> result = schTester.action(inputValue);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void searchTestByEmployeeNum(){
        SearchEmployee schTester = new SearchEmployee();

        HashMap<String, String> inputValue = new HashMap<>();
        inputValue.put("COMMAND","SCH");
        inputValue.put("OPTION1","-p");
        inputValue.put("OPTION2","-l");
        inputValue.put("OPTION3","");
        inputValue.put("VALUE1","employeeNum");
        inputValue.put("VALUE2","01000012");

        List<String> result = schTester.action(inputValue);

        Assertions.assertEquals(1, result.size());
    }
}