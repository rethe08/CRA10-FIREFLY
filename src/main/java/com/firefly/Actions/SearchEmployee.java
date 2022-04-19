package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.Database.IEmployeeDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchEmployee implements IActionEmployee {

    @Override
    public List<String> action(HashMap<String, String> inputCommand){

        IEmployeeDB db = new EmployeeDB(); //<- parameter 전달방식으로 변경 후 삭제해야 함. interface 변경 후 반영
        List<String> output = new ArrayList<>();

        if(inputCommand.get("OPTION1").equals("-p")) {
            db.searchEmployeeTop5(inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2"))
                    .forEach(emp -> output.add("SCH," +
                            emp.getEmployeeNumByString() + "," +
                            emp.getName() + "," +
                            emp.getCl().name() + "," +
                            emp.getPhoneNumByString() + "," +
                            emp.getBirthdayByString() + "," +
                            emp.getCerti().name()));
        }
        else {
            output.add("SCH," + db.searchEmployeeCnt(
                    inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2")));
        }

        if(output.size() == 0){
            output.add("SCH,NONE");
        }

        return output;
    }
}
