package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class SearchEmployee implements IActionEmployee {

    //DB전달 방법을 정하지 않은 상태라서 임시로 만들어서 사용하였습니다.
    //전달 방식 확정 후 삭제
    ArrayList<EmployeeInfo> empDb = new ArrayList<>();

    //DB전달 방법을 정하지 않은 상태라서 임시로 만들어서 사용하였습니다.
    //전달 방식 확정 후 삭제
    public void setEmployeeDb(ArrayList<EmployeeInfo> empDb){
        this.empDb = empDb;
    }

    @Override
    public List<String> action(HashMap<String, String> inputCommand){
        List<String> output = new ArrayList<>();
        String column = inputCommand.get("VALUE1");
        String keyword = inputCommand.get("VALUE2");
        String option1 = inputCommand.get("OPTION1");
        String option2 = inputCommand.get("OPTION2");
        ArrayList<EmployeeInfo> filtered = new ArrayList<>();

        if(column.equals("employeeNum")){
            int employeeNum = Integer.parseInt(keyword);
            filtered.add(empDb.stream().filter(emp ->
                    emp.getEmployeeNumByInt() == employeeNum).findFirst().get());
        }
        else if(column.equals("name")) {
            if(option2.equals("-f")){
                filtered = empDb.stream().filter(emp ->
                        emp.getFirstName().equals(keyword)).collect(Collectors.toCollection(ArrayList::new));
            }
            else if(option2.equals("-l")){
                filtered = empDb.stream().filter(emp ->
                        emp.getLastName().equals(keyword)).collect(Collectors.toCollection(ArrayList::new));
            }
            else {
                filtered = empDb.stream().filter(emp ->
                        emp.getName().equals(keyword)).collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if(column.equals("cl")){
            filtered = empDb.stream().filter(emp ->
                    emp.getCl().name().equals(keyword)).collect(Collectors.toCollection(ArrayList::new));
        }
        else if(column.equals("phoneNum")) {
            int phoneNum;
            if(option2.equals("-m")) {
                phoneNum = Integer.parseInt(keyword.split("-")[1]);
                filtered = empDb.stream().filter(emp ->
                        emp.getPhoneNumMid() == phoneNum).collect(Collectors.toCollection(ArrayList::new));
            }
            else if(option2.equals("-l")){
                phoneNum = Integer.parseInt(keyword.split("-")[2]);
                filtered = empDb.stream().filter(emp ->
                        emp.getPhoneNumLast() == phoneNum).collect(Collectors.toCollection(ArrayList::new));
            }
            else {
                phoneNum = Integer.parseInt(keyword.split("-")[1] + keyword.split("-")[2]);
                filtered = empDb.stream().filter(emp ->
                        emp.getPhoneNum8digitsInt() == phoneNum).collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if(column.equals("birthday")) {
            int birthday;
            if (option2.equals("-y")) {
                birthday = Integer.parseInt(keyword.substring(0, 4));
                filtered = empDb.stream().filter(emp ->
                        emp.getBirthYear() == birthday).collect(Collectors.toCollection(ArrayList::new));
            }
            else if (option2.equals("-m")) {
                birthday = Integer.parseInt(keyword.substring(4, 6));
                filtered = empDb.stream().filter(emp ->
                        emp.getBirthMonth() == birthday).collect(Collectors.toCollection(ArrayList::new));
            }
            else if (option2.equals("-d")) {
                birthday = Integer.parseInt(keyword.substring(6, 8));
                filtered = empDb.stream().filter(emp ->
                        emp.getBirthDayOnly() == birthday).collect(Collectors.toCollection(ArrayList::new));
            }
            else {
                birthday = Integer.parseInt(keyword);
                filtered = empDb.stream().filter(emp ->
                        emp.getBirthdayByInt() == birthday).collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if(column.equals("certi")){
            filtered = empDb.stream().filter(emp ->
                    emp.getCerti().name().equals(keyword)).collect(Collectors.toCollection(ArrayList::new));
        }
        else {
            //input error
        }

        if(filtered.size() == 0){
            output.add("SCH,NONE");
            return output;
        }

        if(option1.equals("-p")) {
            filtered.stream()
                    .sorted(Comparator.comparing(EmployeeInfo::getEmployeeNumYear)
                            .thenComparing(EmployeeInfo::getEmployeeNumExceptYear)).limit(5)
                    .forEach(emp -> output.add("SCH," +
                            emp.getEmployeeNumByString() + "," +
                            emp.getName() + "," +
                            emp.getCl().name() + "," +
                            emp.getPhoneNumByString() + "," +
                            emp.getBirthdayByString() + "," +
                            emp.getCerti().name()));
        }
        else {
            output.add("SCH," + filtered.size());
        }

        return output;
    }
}
