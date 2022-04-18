package com.firefly.Database;

import java.util.ArrayList;
import java.util.List;

//class EmployeeTable<E> extends ArrayList<E> {
//
//}

class EmployeeTable<T> implements IEmployeeDAO<T> {

    static EmployeeTable employeeTable = new EmployeeTable();

    private EmployeeTable(){
    }

    static EmployeeTable getInstance(){
        return employeeTable;
    }

    List<T> empList = new ArrayList<>();


    public T get(int index){
        return empList.get(index);
    }

    public void clear() {
        empList.clear();
    }

    public int size() {
        return empList.size();
    }

//    public void add(EmployeeInfo e) {
//        empList.add(e);
//    }

    public void remove(T e) {
        empList.remove(e);
    }

    @Override
    public void add(T e) {
        empList.add(e);
    }

    @Override
    public List search(T e) {
        return null;
    }

    @Override
    public List delete(T e) {
        return null;
    }

    @Override
    public List modify(T e1, T e2) {
        return null;
    }
}
