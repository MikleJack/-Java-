package com.example.back2.exception;


public class GlobalException extends Exception {

    private Object date;
    private int trace;

    /**
     *  自定义项目内异常
     */
    public GlobalException(String message,Object date){
        super(message);
        this.date = date;

        //记录发生异常的代码行数
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        this.trace = stackTraceElements[1].getLineNumber();
    }

    public Object getDate(){
        return  date;
    }

    public int getTrace() {return this.trace;}

}
