package EmpManager;

import IoManager.FileIoManager;

import java.util.*;

public class EmpManager {

    public void doProcess(String inputFileName, String outputFileName) {

        FileIoManager fileIOManager = new FileIoManager();

        List<String> inputLines = fileIOManager.readInput(inputFileName);

        /********** input print for test **********/
        for(String str : inputLines){
            System.out.println(str);
        }

        //List<Command> commandList = this.getCommandObjectList(inputLines);
        //List<String> outputLines = this.getOutputLines(commandList);

        /********** output print for test **********/
        String output_test = "C:\\output_test.txt";
        List<String> outputLines = fileIOManager.readInput(output_test);

        fileIOManager.writeOutput(outputFileName, outputLines);

    }

}
