package com.firefly.Actions;

import java.util.HashMap;

public class ActionManager {
    public String action(String inputCommand) {

        HashMap<String, String> inputValue = new HashMap<>();

        String[] inputSplit = inputCommand.split(",");
        String inputAction = inputSplit[0];

        if(inputSplit.length != 6 && inputSplit.length != 8) return null;
        inputValue.put("COMMAND",inputSplit[0]);
        inputValue.put("OPTION1",inputSplit[1]);
        inputValue.put("OPTION2",inputSplit[2]);
        inputValue.put("OPTION3",inputSplit[3]);
        inputValue.put("COLUMN",inputSplit[4]);
        inputValue.put("VALUE",inputSplit[5]);
        inputValue.put("TobeCOLUMN",inputSplit[6]);
        inputValue.put("TobeVALUE",inputSplit[7]);


        String output = "";

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
