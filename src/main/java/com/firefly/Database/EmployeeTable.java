package com.firefly.Database;

import com.firefly.EmployeeInfo.EmployeeInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

//class EmployeeTable<E> extends ArrayList<E> {
//
//}

class EmployeeTable {

    static List<EmployeeInfo> empList = new ArrayList<>();


    public EmployeeInfo get(int index){
        return empList.get(index);
    }

    public void clear() {
        empList.clear();
    }

    public int size() {
        return empList.size();
    }

    public void add(EmployeeInfo e) {
        empList.add(e);
    }

    public void remove(EmployeeInfo e) {
        empList.remove(e);
    }

}
