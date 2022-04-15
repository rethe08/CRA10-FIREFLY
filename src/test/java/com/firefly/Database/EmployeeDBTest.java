package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeDBTest {

    EmployeeInfo info1, info2, info3;

    @BeforeEach
    void setup(){

        info1 = new EmployeeInfo("00123456", "GILDONG HONG", "CL2",
                "010-3333-4444", "19900130", "ADV");
        info2 = new EmployeeInfo("00123457", "GILJA HA", "CL3",
                "010-4444-5555", "19910205", "PRO");
        info3 = new EmployeeInfo("00123458", "BOBBY KIM", "CL3",
                "010-3333-7777", "19850715", "PRO");
    }

    @Test
    void addEmployeeTest(){
        IEmployeeDB db = new EmployeeDB();
        Assertions.assertEquals (0, db.addEmployee(info1) );
        Assertions.assertEquals (0, db.addEmployee(info2) );
        Assertions.assertEquals (0, db.addEmployee(info3) );
        Assertions.assertEquals (3, ((EmployeeDB)db).getEmpNum());

    }


    /**
     employeeNum
     name
     cl
     phoneNum
     birthday
     certi
     */
    /*

        info2 = new EmployeeInfo("00123457", "GILJA HA", "CL3",
                "010-4444-5555", "19910205", "PRO");
    * */




    @Test
    void delEmployeeTest(){


    }

    @Test
    void searchEmployeeTest(){
        IEmployeeDB db = new EmployeeDB();
        db.addEmployee(info1);
        db.addEmployee(info2);
        db.addEmployee(info3);

        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","00123457" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","00123456" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("name","GILJA HA" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("cl","CL2" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("phoneNum","010-4444-5555" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","19910205" ));
        Assertions.assertEquals(2, db.searchEmployeeCnt("certi","PRO" ));


        Assertions.assertEquals(2, db.searchEmployeeCnt("cl","CL3" ));


        Assertions.assertEquals(0, db.searchEmployeeCnt("employeeNum","00123450" ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("name","NO" ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("cl","CL1" ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("phoneNum","010-4444-0909" ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("birthday","19910101" ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("certi","EX" ));

    }

    @Test
    void modEmployeeTest(EmployeeInfo e){

    }

}

