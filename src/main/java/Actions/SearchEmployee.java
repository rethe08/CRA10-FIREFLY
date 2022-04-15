package Actions;

import java.util.HashMap;

public class SearchEmployee {


    public void search(String input){

        HashMap<String, String> parameters = new HashMap<>();

        //입력값을 ,로 쪼개서 저장
        String[] inputSplit = input.split(",");
        if(inputSplit.length != 6) return;
        parameters.put("COMMAND",inputSplit[0]);
        parameters.put("OPTION1",inputSplit[1]);
        parameters.put("OPTION2",inputSplit[2]);
        parameters.put("OPTION3",inputSplit[3]);
        parameters.put("INPUTCOLUMN",inputSplit[4]);
        parameters.put("INPUTVALUE",inputSplit[5]);


        //실제 실행부분
        //-p 옵션 있을경우 record를 출력(max5개)
        if(parameters.get("OPTION1").equals("-p")){

        }
        //-p 옵션 없을경우 record 수를 출력
        else{
            //System.out.println("SCH,");
        }



    }

}
