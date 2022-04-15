
package com.firefly.EmpManager;

import com.firefly.Actions.ActionManager;
import com.firefly.IoManager.FileIoManager;
import com.firefly.IoManager.IoManager;
import java.util.*;

public class EmpManager {

    public void doProcess(String inputFileName, String outputFileName) {

        IoManager fileIOManager = new FileIoManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);

        ArrayList<String> resultLines = new ArrayList<>();
        ActionManager actionManager = new ActionManager();
        for(String input : inputLines){
            resultLines.add(actionManager.action(input));
        }
        //input 부분 : inputLines 활용
        //output 부분 : 현재는 input 그대로 출력하도록 되어있으므로 outputLines outputLines 반환하도록 개선 필요
        List<String> outputLines = resultLines;

        fileIOManager.writeOutput(outputFileName, outputLines);

    }

}