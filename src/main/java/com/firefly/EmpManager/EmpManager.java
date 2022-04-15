
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
            resultLines.addAll(actionManager.action(input));
        }

        fileIOManager.writeOutput(outputFileName, resultLines);

    }

}