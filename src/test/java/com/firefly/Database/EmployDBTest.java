package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployDBTest {

    List<EmployeeInfo> testEmpList = new ArrayList<>();

    IEmployeeDB db = new EmployeeDB();

    @BeforeEach
    void setup(){
        addTest();

    }

    @Test
    void addTest(){
        testEmpList.add(new EmployeeInfo("10000001", "BOBBY KIM",    "CL2", "010-3333-7777", "19850715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000002", "NA LEE",       "CL2", "010-1111-1321", "19770315", "PRO"));
        testEmpList.add(new EmployeeInfo("88000003", "ALL JI",       "CL3", "010-3333-1231", "19990115", "PRO"));
        testEmpList.add(new EmployeeInfo("10000004", "GILJA HA",     "CL3", "010-4444-5555", "19910215", "PRO"));
        testEmpList.add(new EmployeeInfo("88000005", "GILDONG HONG", "CL2", "010-3333-4444", "19900115", "PRO"));
        testEmpList.add(new EmployeeInfo("10000006", "PIO KIM",      "CL4", "010-2000-1321", "19880715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000007", "NAMI KIM",     "CL4", "010-0000-0001", "19880715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000008", "MINO KANG",    "CL3", "010-0000-0002", "19880715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000009", "BOBBY PARK",   "CL3", "010-0000-0003", "19880715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000010", "JINA JANG",    "CL4", "010-0000-0004", "19880715", "PRO"));
        testEmpList.add(new EmployeeInfo("10000011", "TAESUNG NA",   "CL4", "010-0000-0005", "19880715", "PRO"));

        ((EmployeeDB)db).clear();

        for(EmployeeInfo e : testEmpList){
            Assertions.assertEquals (0, db.addEmployee(e) );
        }
    }


    @Test
    void addEmployeeTest(){
        Assertions.assertEquals (11, ((EmployeeDB)db).getEmpNum());

    }

    @Test
    void modTest(){

        int cnt ;

        cnt =0;
        for(EmployeeInfo e : db.modEmployeeRetToTop5("cl","CL2" ,null,"certi","EX")){
            Assertions.assertEquals( EmployeeInfo.CareerLevel.valueOf("CL2") , e.getCl());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        Assertions.assertEquals(11,((EmployeeDB)db).getEmpNum());
        System.out.println();

        Assertions.assertEquals(3, db.modEmployeeRetToCnt("certi","EX" ,null,"cl","CL3"));
        Assertions.assertEquals(11,((EmployeeDB)db).getEmpNum());

        Assertions.assertEquals(7, db.searchEmployeeCnt("cl","CL3",null ));


    }


    @Test
    void delTest(){

        int cnt ;

        cnt =0;
        for(EmployeeInfo e : db.delEmployeeRetToTop5("cl","CL2" ,null)){
            Assertions.assertEquals( EmployeeInfo.CareerLevel.valueOf("CL2" ) , e.getCl());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        Assertions.assertEquals(8,((EmployeeDB)db).getEmpNum());
        System.out.println();


        db.addEmployee(testEmpList.get(0));
        db.addEmployee(testEmpList.get(1));
        db.addEmployee(testEmpList.get(4));


        Assertions.assertEquals(11,((EmployeeDB)db).getEmpNum());

        Assertions.assertEquals(3, db.delEmployeeRetToCnt("name","KIM" ,"-l"));
        Assertions.assertEquals(8,((EmployeeDB)db).getEmpNum());


    }





    @Test
    void searchCntEmployeeTest(){

        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","88000005",null ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("employeeNum","10000001" ,null));
        Assertions.assertEquals(1, db.searchEmployeeCnt("name","GILJA HA" ,null));
        Assertions.assertEquals(3, db.searchEmployeeCnt("cl","CL2" ,null));
        Assertions.assertEquals(1, db.searchEmployeeCnt("phoneNum","010-4444-5555",null ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","19910215",null ));
        Assertions.assertEquals(11, db.searchEmployeeCnt("certi","PRO",null ));


        Assertions.assertEquals(4, db.searchEmployeeCnt("cl","CL3",null ));


        Assertions.assertEquals(0, db.searchEmployeeCnt("employeeNum","10000000",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("name","NO",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("cl","CL1",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("phoneNum","010-4444-0909",null ));
        Assertions.assertEquals(0, db.searchEmployeeCnt("birthday","19910101" ,null));
        Assertions.assertEquals(0, db.searchEmployeeCnt("certi","EX",null ));



        Assertions.assertEquals(1, db.searchEmployeeCnt("name","GILJA" ,"-f"));
        Assertions.assertEquals(3, db.searchEmployeeCnt("name","KIM" ,"-l"));


        Assertions.assertEquals(3, db.searchEmployeeCnt("phoneNum","3333","-m" ));
        Assertions.assertEquals(1, db.searchEmployeeCnt("phoneNum","4444","-l" ));


        Assertions.assertEquals(1, db.searchEmployeeCnt("birthday","1991" ,"-y"));
        Assertions.assertEquals(2, db.searchEmployeeCnt("birthday","01" ,"-m"));
        Assertions.assertEquals(11, db.searchEmployeeCnt("birthday","15" ,"-d"));



    }


    @Test
    void searchEmployeeTest(){

        int cnt ;

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("employeeNum","88000005" ,null)){
            Assertions.assertEquals("88000005",e.getEmployeeNumByString());
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
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","19910215" ,null)){
            Assertions.assertEquals("19910215",e.getBirthdayByString());
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

        int cnt ;



        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("name","GILJA" ,"-f")){
            Assertions.assertEquals("GILJA",e.getFirstName());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();


        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("name","KIM" ,"-l")){
            Assertions.assertEquals("KIM",e.getLastName());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        System.out.println();



        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("phoneNum","3333","-m" ) ){
            Assertions.assertEquals(3333,  e.getPhoneNumMid());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(3,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("phoneNum","4444","-l" ) ){
            Assertions.assertEquals(4444,e.getPhoneNumLast());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();




        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","1991" ,"-y") ){
            Assertions.assertEquals(1991,e.getBirthYear());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(1,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","01" ,"-m") ){
            Assertions.assertEquals(01,e.getBirthMonth());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(2,cnt);
        System.out.println();

        cnt =0;
        for(EmployeeInfo e : db.searchEmployeeTop5("birthday","15" ,"-d" ) ){
            Assertions.assertEquals(15,e.getBirthDayOnly());
            System.out.println(e.getEmployeeNumByString());
            cnt ++;
        }
        Assertions.assertEquals(5,cnt);
        System.out.println();




    }


}
