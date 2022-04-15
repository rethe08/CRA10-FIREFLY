package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployDBTest {

    EmployeeInfo info1, info2, info3, info4, info5, info6;

    @BeforeEach
    void setup(){

        info1 = new EmployeeInfo("10000010", "GILDONG HONG", "CL2",
                "010-3333-4444", "19900115", "PRO");
        info2 = new EmployeeInfo("10000008", "GILJA HA", "CL3",
                "010-4444-5555", "19910215", "PRO");
        info3 = new EmployeeInfo("10000001", "BOBBY KIM", "CL2",
                "010-3333-7777", "19850715", "PRO");
        info4 = new EmployeeInfo("10000006", "ALL JI", "CL3",
                "010-3333-1231", "19990115", "PRO");
        info5 = new EmployeeInfo("10000002", "NA LEE", "CL2",
                "010-1111-1321", "19770315", "PRO");
        info6 = new EmployeeInfo("10000015", "PIO KIM", "CL4",
                "010-2000-1321", "19880715", "PRO");
    }

    @Test
    void addEmployeeTest(){
        IEmployeeDB db = new EmployeeDB();
        Assertions.assertEquals (0, db.addEmployee(info1) );
        Assertions.assertEquals (0, db.addEmployee(info2) );
        Assertions.assertEquals (0, db.addEmployee(info3) );
        Assertions.assertEquals (0, db.addEmployee(info4) );
        Assertions.assertEquals (0, db.addEmployee(info5) );
        Assertions.assertEquals (0, db.addEmployee(info6) );
        Assertions.assertEquals (6, ((EmployeeDB)db).getEmpNum());

    }



    @Test
    void searchCntEmployeeTest(){
        IEmployeeDB db = new EmployeeDB();
        db.addEmployee(info1);
        db.addEmployee(info2);
        db.addEmployee(info3);
        db.addEmployee(info4);
        db.addEmployee(info5);
        db.addEmployee(info6);

        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","10000010",null ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","10000001" ,null));
        Assertions.assertEquals(1, db.searchEmployeeCnt("name","GILJA HA" ,null));
        Assertions.assertEquals(3, db.searchEmployeeCnt("cl","CL2" ,null));
        Assertions.assertEquals(1, db.searchEmployeeCnt("phoneNum","010-4444-5555",null ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","19910215",null ));
        Assertions.assertEquals(6, db.searchEmployeeCnt("certi","PRO",null ));


        Assertions.assertEquals(2, db.searchEmployeeCnt("cl","CL3",null ));


        Assertions.assertEquals(0, db.searchEmployeeCnt("employeeNum","10000000",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("name","NO",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("cl","CL1",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("phoneNum","010-4444-0909",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("birthday","19910101" ,null));
        Assertions.assertEquals(0, db.searchEmployeeCnt("certi","EX",null ));



        Assertions.assertEquals(1, db.searchEmployeeCnt("name","GILJA" ,"f"));
        Assertions.assertEquals(2, db.searchEmployeeCnt("name","KIM" ,"l"));


        Assertions.assertEquals(3, db.searchEmployeeCnt("phoneNum","3333","m" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("phoneNum","4444","l" ));


        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","1991" ,"y"));
        Assertions.assertEquals(2, db.searchEmployeeCnt("birthday","01" ,"m"));
        Assertions.assertEquals(6, db.searchEmployeeCnt("birthday","15" ,"d"));



    }


    @Test
    void searchEmployeeTest(){
        IEmployeeDB db = new EmployeeDB();
        db.addEmployee(info1);
        db.addEmployee(info2);
        db.addEmployee(info3);
        db.addEmployee(info4);
        db.addEmployee(info5);
        db.addEmployee(info6);

        int cnt ;

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("employeeNum","10000010" ,null)){
            Assertions.assertEquals("10000010",e.getEmployeeNumByString());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();


        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("employeeNum","10000001",null )){
            Assertions.assertEquals("10000001",e.getEmployeeNumByString());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("name","GILJA HA" ,null)){
            Assertions.assertEquals("GILJA HA",e.getName());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();


        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("cl","CL2",null )){
            Assertions.assertEquals(EmployeeInfo.CareerLevel.valueOf("CL2"),e.getCl());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("phoneNum","010-4444-5555" ,null)){
            Assertions.assertEquals("010-4444-5555",e.getPhoneNumByString());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","19910205" ,null)){
            Assertions.assertEquals("19910205",e.getBirthdayByString());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("certi","PRO" ,null)){
            Assertions.assertEquals(EmployeeInfo.Certificate.valueOf("PRO"),e.getCerti());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(5,cnt);
        System.out.println();


    }



    @Test
    void searchEmployeeOptionTest(){
        IEmployeeDB db = new EmployeeDB();
        db.addEmployee(info1);
        db.addEmployee(info2);
        db.addEmployee(info3);
        db.addEmployee(info4);
        db.addEmployee(info5);
        db.addEmployee(info6);

        int cnt ;



        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("name","GILJA" ,"f")){
            Assertions.assertEquals("GILJA",e.getFirstName());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();


        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("name","KIM" ,"l")){
            Assertions.assertEquals("KIM",e.getLastName());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(2,cnt);
        System.out.println();



        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("phoneNum","3333","m" ) ){
            Assertions.assertEquals(3333,  e.getPhoneNumMid());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("phoneNum","4444","l" ) ){
            Assertions.assertEquals(4444,e.getPhoneNumLast());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();




        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","1991" ,"y") ){
            Assertions.assertEquals(1991,e.getBirthYear());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","01" ,"m") ){
            Assertions.assertEquals(01,e.getBirthMonth());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(2,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","15" ,"d" ) ){
            Assertions.assertEquals(15,e.getBirthDayOnly());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(5,cnt);
        System.out.println();


//
//
//        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","1991" ,"y"));
//        Assertions.assertEquals(2, db.searchEmployeeCnt("birthday","01" ,"m"));
//        Assertions.assertEquals(6, db.searchEmployeeCnt("birthday","15" ,"d"));
//



    }


}
