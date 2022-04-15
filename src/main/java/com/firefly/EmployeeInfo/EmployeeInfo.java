package com.firefly.EmployeeInfo;

public class EmployeeInfo {
    public enum CareerLevel{
        CL1,
        CL2,
        CL3,
        CL4,
    }

    public enum Certificate{
        ADV,
        PRO,
        EX,
    }

    private int employeeNum;
    private String name;
    private CareerLevel cl;
    private int phoneNum;
    private int birthday;
    private Certificate certi;

    public EmployeeInfo(int employeeNum, String name, CareerLevel cl, int phoneNum, int birthday, Certificate certi){
        this.employeeNum = employeeNum;
        this.name = name;
        this.cl = cl;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.certi = certi;
    }

    public int getEmployeeNum(){
        return employeeNum;
    }

    public String getName(){
        return name;
    }

    public String getFirstName(){
        return name.split(" ")[0];
    }

    public String getLastName(){
        return name.split(" ")[1];
    }

    public CareerLevel getCl(){
        return cl;
    }

    public int getPhoneNum(){
        return phoneNum;
    }

    public int getPhoneNumMid(){
        return phoneNum / 10000;
    }

    public int getPhoneNumLast(){
        return phoneNum % 10000;
    }

    public int getBirthday(){
        return birthday;
    }

    public int getBirthYear(){
        return birthday / 10000;
    }

    public int getBirthMonth(){
        return birthday / 100 % 100;
    }

    public int getBirthDayOnly(){
        return birthday % 100;
    }

    public Certificate getCerti(){
        return certi;
    }
}
