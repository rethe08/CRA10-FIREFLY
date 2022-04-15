package com.firefly.Actions;

public class ActionManager {
    public String action(String inputCommand) {

        //입력값을 ,로 쪼개서 저장
        String[] inputSplit = inputCommand.split(",");
        String inputAction = inputSplit[0];
        String output = "";

        switch (inputAction){
            case "ADD":
                output=new AddEmployee().action(inputCommand);
                break;
            case "DEL":
                output=new DelEmployee().action(inputCommand);
                break;
            case "SCH":
                output=new SearchEmployee().action(inputCommand);
                break;
            case "MOD":
                output=new ModEmployee().action(inputCommand);
                break;
            default:
        }

        return output;

    }
}
