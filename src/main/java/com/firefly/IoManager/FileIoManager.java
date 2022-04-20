package com.firefly.IoManager;

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
            Throwable exceptionThrow = null;

            try {
                String str;
                try {
                    while((str = br.readLine()) != null) {
                        fileContents.add(str);
                    }
                } catch (Throwable fileReadingFailThrow) {
                    exceptionThrow = fileReadingFailThrow;
                    throw fileReadingFailThrow;
                }
            } finally {
                if (br != null) {
                    if (exceptionThrow != null) {
                        try {
                            br.close();
                        } catch (Throwable FileCloseFailThrow) {
                            exceptionThrow.addSuppressed(FileCloseFailThrow);
                        }
                    } else {
                        br.close();
                    }
                }
            }
        } catch (IOException IOFailThrow) {
            fileContents = new ArrayList();
        }

        return fileContents;
    }

    public void writeOutput(String fileName, List<String> fileLines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            Throwable exceptionThrow = null;

            try {
                Iterator iline = fileLines.iterator();

                while(iline.hasNext()) {
                    String line = (String)iline.next();
                    if (line.length() != 0) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (Throwable fileWritingFailThrow) {
                exceptionThrow = fileWritingFailThrow;
                throw fileWritingFailThrow;
            } finally {
                if (bw != null) {
                    if (exceptionThrow != null) {
                        try {
                            bw.close();
                        } catch (Throwable FileCloseFailThrow) {
                            exceptionThrow.addSuppressed(FileCloseFailThrow);
                        }
                    } else {
                        bw.close();
                    }
                }
            }
        } catch (IOException IOFailThrow) {
            IOFailThrow.printStackTrace();
        }
    }
}
