
package com.firefly.EmpManager;

import com.firefly.Actions.*;
import com.firefly.IoManager.FileIoManager;
import com.firefly.IoManager.IoManager;
import java.util.*;

public class EmpManager {

    private Map<String, IActionEmployee> actionMap = new HashMap<String, IActionEmployee>() {{
        put("ADD", new AddEmployee());
        put("DEL", new DelEmployee());
        put("SCH", new SearchEmployee());
        put("MOD", new ModEmployee());
    }};

    public void doProcess(String inputFileName, String outputFileName) {

        IoManager fileIOManager = new FileIoManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);

        ArrayList<String> resultLines = new ArrayList<>();
        ActionManager actionManager = new ActionManager();

        for(String input : inputLines){
            resultLines.addAll(actionManager.action(input, actionMap.get(input.split(",")[0])));
        }

        fileIOManager.writeOutput(outputFileName, resultLines);
    }
}