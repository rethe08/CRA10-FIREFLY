package com.firefly.Actions;

import java.util.HashMap;
import java.util.List;

public class ActionManager {
    public List<String> action(String inputCommand) {

        HashMap<String, String> inputValue = new HashMap<>();

        String[] inputSplit = inputCommand.split(",");
        String inputAction = inputSplit[0];

        if(inputSplit.length != 6 && inputSplit.length != 8 && inputSplit.length != 10) return null;
        inputValue.put("COMMAND",inputSplit[0]);
        inputValue.put("OPTION1",inputSplit[1]);
        inputValue.put("OPTION2",inputSplit[2]);
        inputValue.put("OPTION3",inputSplit[3]);
        inputValue.put("VALUE1",inputSplit[4]);
        inputValue.put("VALUE2",inputSplit[5]);

        if((inputValue.get("COMMAND").equals("MOD")&& inputSplit.length==8 || inputValue.get("COMMAND").equals("ADD"))  && inputSplit.length==10){
            inputValue.put("VALUE3",inputSplit[6]);
            inputValue.put("VALUE4",inputSplit[7]);
        }
        if(inputValue.get("COMMAND").equals("ADD") && inputSplit.length==10){
            inputValue.put("VALUE5",inputSplit[8]);
            inputValue.put("VALUE6",inputSplit[9]);
        }

        List<String> output = null;

        switch (inputAction){
            case "ADD":
                output=new AddEmployee().action(inputValue);
                break;
            case "DEL":
                output=new DelEmployee().action(inputValue);
                break;
            case "SCH":
                output=new SearchEmployee().action(inputValue);
                break;
            case "MOD":
                output=new ModEmployee().action(inputValue);
                break;
            default:
        }

        return output;
    }
}
