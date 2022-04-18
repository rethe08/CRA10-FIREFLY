package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.Database.IEmployeeDB;
import com.firefly.EmployeeInfo.EmployeeInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DelEmployee implements IActionEmployee {



    @Override
    public List<String> action(HashMap<String, String> inputCommand) {

        IEmployeeDB db = new EmployeeDB();

        List<String> ret = new ArrayList();
        if( inputCommand.get("OPTION1").equals("-p")) {
            for(EmployeeInfo temp : db.delEmployeeRetToTop5(inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2"))){
                ret.add("DEL," + temp.getEmployeeNumByString() + "," + temp.getName() + "," + temp.getCl() + "," + temp.getPhoneNumByString() + "," + temp.getBirthdayByString() + "," + temp.getCerti());
            }
        } else {
            ret.add("DEL,"+ db.delEmployeeRetToCnt(inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2")));
        }
        return ret;
    }
}
