package com.expenseTracker.ExpenseTracker.Exception;

public class ObjectDoesntExist extends RuntimeException{
    public ObjectDoesntExist(String message){
        super(message);
    }
    public ObjectDoesntExist(){
        super("Object is not found");
    }

}
