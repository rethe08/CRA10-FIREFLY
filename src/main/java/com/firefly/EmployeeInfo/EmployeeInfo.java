package com.firefly.EmployeeInfo;

public class EmployeeInfo {
    public enum CareerLevel {
        CL1,
        CL2,
        CL3,
        CL4,
    }

    public enum Certificate {
        ADV,
        PRO,
        EX,
    }

    private int employeeNum;
    private String name;
    private CareerLevel cl;
    private int phoneNumMid;
    private int phoneNumLast;
    private int birthday;
    private Certificate certi;

    public EmployeeInfo(String employeeNum, String name, String cl, String phoneNum, String birthday, String certi) {
        this.employeeNum = Integer.parseInt(employeeNum);
        this.name = name;
        this.cl = CareerLevel.valueOf(cl);
        this.phoneNumMid = Integer.parseInt(phoneNum.split("-")[1]);
        this.phoneNumLast = Integer.parseInt(phoneNum.split("-")[2]);
        this.birthday = Integer.parseInt(birthday);
        this.certi = Certificate.valueOf(certi);
    }

    public String getEmployeeNum10digitsString(){
        int year = Integer.parseInt(String.format("%08d", employeeNum).substring(0, 2));

        if(year >= 69 && year <= 99 ){
            return "19"+String.format("%08d", employeeNum);
        }

        return "20"+String.format("%08d", employeeNum);
    }

    public int getEmployeeNum10digitsInt(){
        int year = Integer.parseInt(String.format("%08d", employeeNum).substring(0, 2));

        if(year >= 69 && year <= 99 ){
            return employeeNum + 1900000000;
        }

        return employeeNum + 2000000000;
    }

    public int getEmployeeNumYear() {
        int year = Integer.parseInt(String.format("%08d", employeeNum).substring(0, 2));

        if(year >= 69 && year <= 99 ){
            return year + 1900;
        }

        return year + 2000;
    }

    public int getEmployeeNumExceptYear(){
        return Integer.parseInt(String.format("%08d", employeeNum).substring(2, 8));
    }

    public int getEmployeeNumByInt(){
        return employeeNum;
    }

    public String getEmployeeNumByString(){
        return String.format("%08d", employeeNum);
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

    public String getClByString() { return cl.toString(); }

    public String getPhoneNumByString() {
        return "010-" + String.format("%04d", phoneNumMid) + "-" + String.format("%04d",phoneNumLast);
    }

    public int getPhoneNum8digitsInt(){
        return phoneNumMid * 10000 + phoneNumLast;
    }

    public int getPhoneNumMid(){
        return phoneNumMid;
    }

    public int getPhoneNumLast(){
        return phoneNumLast;
    }


    public String getPhoneNumMidByString(){
        return String.format("%04d",phoneNumMid);
    }

    public String getPhoneNumLastByString(){
        return String.format("%04d",phoneNumLast);
    }

    public String getBirthdayByString(){
        return String.format("%08d", birthday);
    }

    public int getBirthdayByInt(){
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

    public String getBirthYearByString(){ return String.format( "%04d", birthday / 10000 ); }

    public String getBirthMonthByString(){ return String.format( "%02d", birthday / 100 % 100);
    }

    public String getBirthDayOnlyByString(){ return String.format( "%02d", birthday % 100);
    }

    public Certificate getCerti(){
        return certi;
    }

    public String getCertiByString(){
        return certi.toString();
    }
}
