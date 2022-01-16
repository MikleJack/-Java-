package com.example.back2.controller;


import com.example.back2.entity.ErrorInfo;
import com.example.back2.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 统一的异常类进行处理(把默认的异常返回信息改成自定义的异常返回信息)
// 当GlobalContrller抛出HospitalException异常时，将自动找到此类中对应的方法执行，并返回json数据给前台
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)    //异常处理器，处理HospitalException异常
    public ResponseEntity<?> hanlerException(HttpServletRequest request, GlobalException e){
        ErrorInfo<Object> error = new ErrorInfo<>();
        error.setCode(e.getTrace());
        error.setMessage(e.getMessage());
        error.setUrl(request.getRequestURI().toString());
        error.setData(e.getDate());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
