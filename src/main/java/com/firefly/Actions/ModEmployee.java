package com.firefly.Actions;

import com.firefly.Database.EmployeeDB;
import com.firefly.Database.IEmployeeDB;
import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModEmployee implements IActionEmployee {


    @Override
    public List<String> action(HashMap<String, String> inputCommand) {

        IEmployeeDB db = new EmployeeDB();

        List<String> ret=new ArrayList<>();
        if( inputCommand.get("OPTION1").equals("-p")) {
            for(EmployeeInfo temp : db.modEmployeeRetToTop5(inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2"),inputCommand.get("VALUE3"),inputCommand.get("VALUE4"))){
                ret.add("MOD," + temp.getEmployeeNumByString() + "," + temp.getName() + "," + temp.getCl() + "," + temp.getPhoneNumByString() + "," + temp.getBirthdayByString() + "," + temp.getCerti());
            }
        } else {
            int count = db.modEmployeeRetToCnt(inputCommand.get("VALUE1"), inputCommand.get("VALUE2"), inputCommand.get("OPTION2"),inputCommand.get("VALUE3"),inputCommand.get("VALUE4"));
            if(count != 0)
                ret.add("MOD," + count);
            }

        if(ret.isEmpty()){
            ret.add("MOD,NONE");
        }
        return ret;


    }
}
