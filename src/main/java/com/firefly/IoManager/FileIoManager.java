

package com.firefly.IoManager;

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

public class FileIoManager implements IoManager<String> {

    public List<String> readInput(String fileName) {
        ArrayList fileContents = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Throwable throw1 = null;

            try {
                String str;
                try {
                    while((str = br.readLine()) != null) {
                        fileContents.add(str);
                    }
                } catch (Throwable throw2) {
                    throw1 = throw2;
                    throw throw2;
                }
            } finally {
                if (br != null) {
                    if (throw1 != null) {
                        try {
                            br.close();
                        } catch (Throwable var13) {
                            throw1.addSuppressed(var13);
                        }
                    } else {
                        br.close();
                    }
                }

            }
        } catch (IOException var16) {
            fileContents = new ArrayList();
        }

        return fileContents;
    }

    public void writeOutput(String fileName, List<String> fileLines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            Throwable throw1 = null;

            try {
                Iterator var5 = fileLines.iterator();

                while(var5.hasNext()) {
                    String line = (String)var5.next();
                    if (line.length() != 0) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (Throwable throw2) {
                throw1 = throw2;
                throw throw2;
            } finally {
                if (bw != null) {
                    if (throw1 != null) {
                        try {
                            bw.close();
                        } catch (Throwable var14) {
                            throw1.addSuppressed(var14);
                        }
                    } else {
                        bw.close();
                    }
                }

            }
        } catch (IOException var17) {
            var17.printStackTrace();
        }
    }
}
