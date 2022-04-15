package com.firefly.Actions;

import java.util.HashMap;
import java.util.List;

public interface IActionEmployee {

    void printResult();
    List<String> action(HashMap<String, String> inputCommand);
}
