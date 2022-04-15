package com.firefly.EmployeeInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeInfoTest {
    @Test
    void employeeInfoTest(){
        EmployeeInfo info = new EmployeeInfo("00123456", "GILDONG HONG", "CL2",
                "010-3333-4444", "19900130", "ADV");

        Assertions.assertEquals("00123456", info.getEmployeeNumByString());
        Assertions.assertEquals("GILDONG HONG", info.getName());
        Assertions.assertEquals("GILDONG", info.getFirstName());
        Assertions.assertEquals("HONG", info.getLastName());
        Assertions.assertEquals(EmployeeInfo.CareerLevel.CL2, info.getCl());
        Assertions.assertEquals("010-3333-4444", info.getPhoneNumByString());
        Assertions.assertEquals(3333, info.getPhoneNumMid());
        Assertions.assertEquals(4444, info.getPhoneNumLast());
        Assertions.assertEquals("19900130", info.getBirthdayByString());
        Assertions.assertEquals(1990, info.getBirthYear());
        Assertions.assertEquals(01, info.getBirthMonth());
        Assertions.assertEquals(30, info.getBirthDayOnly());
        Assertions.assertEquals(EmployeeInfo.Certificate.ADV, info.getCerti());
    }
}
