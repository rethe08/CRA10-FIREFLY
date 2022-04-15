package com.firefly.Actions;

import java.util.HashMap;

public class SearchEmployee implements IActionEmployee {


    @Override
    public String action(HashMap<String, String> inputCommand){



        //실제 실행부분
        //-p 옵션 있을경우 record를 출력(max5개)
        if(inputCommand.get("OPTION1").equals("-p")){

        }
        //-p 옵션 없을경우 record 수를 출력
        else{
            //System.out.println("SCH,");
        }


        return null;
    }

    @Override
    public void printResult() {

    }
}
