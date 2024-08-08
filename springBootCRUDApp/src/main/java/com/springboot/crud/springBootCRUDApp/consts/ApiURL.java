package com.springboot.crud.springBootCRUDApp.consts;

public class ApiURL {
    private ApiURL (){

    }

    public static final String VIEW_EMPLOYEES ="/viewEmployees";
    public static final String EDIT_SAVED_EMPLOYEES ="editSaveEmployee";
    public static final String DELETE_EMPLOYEE ="/deleteEmployee/{id}";
    public static final String EDIT_EMPLOYEE ="/editEmployee/{id}";
    public static final String SAVE_EMPLOYEE = "/saveEmployee";
    public static final String ADD_EMPLOYEE ="/addEmployee";
}
