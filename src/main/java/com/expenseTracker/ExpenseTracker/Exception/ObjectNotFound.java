package com.expenseTracker.ExpenseTracker.Exception;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String Message){
        super(Message);
    }
    public ObjectNotFound(){
        super("object is not present");
    }
}
