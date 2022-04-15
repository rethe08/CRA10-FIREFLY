package com.firefly.IoManager;
import java.util.List;

public interface IoManager<T> {

    List<T> readInput(String var1);
    void writeOutput(String var1, List<T> var2);

}
