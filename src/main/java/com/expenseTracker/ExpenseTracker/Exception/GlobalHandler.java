package com.expenseTracker.ExpenseTracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<ResponseModel> objectnotfound(Exception ex){
        ResponseModel resp=new ResponseModel();
        resp.setSuccess(false);
        resp.setMessage(ex.getMessage());
        resp.setStatus(HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectDoesntExist.class)
    public ResponseEntity<ResponseModel> objectdoesntexist(Exception ex){
        ResponseModel resp=new ResponseModel();
        resp.setSuccess(false);
        resp.setMessage(ex.getMessage());
        resp.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }
}
