package IoManager;

import java.io.*;
import java.util.*;
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileIoManager {

    public List<String> readInput(String fileName) {
        ArrayList fileContents = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String str;
            while((str = br.readLine()) != null) {
                fileContents.add(str);
            }
        } catch (IOException a) {
            fileContents = new ArrayList();
        }

        return fileContents;
    }

    public void writeOutput(String fileName, List<String> fileLines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));


                Iterator var5 = fileLines.iterator();

                while(var5.hasNext()) {
                    String line = (String)var5.next();
                    if (line.length() != 0) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            bw.close();
        } catch (IOException var17) {
            var17.printStackTrace();
        }
    }
}
