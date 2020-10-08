package com.kail.util.log;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class LogUtil {
	
	private PrintWriter logPrint;
    private String logFile = "";
    private String logName = "";

    /**
     * 配置没有头的log
    */
    public LogUtil(){
        checkDate();
    }

    /**
     * 配置log文件名的头
    */
    public LogUtil(String logName){
        this.logName = logName;
        checkDate();
    }

    /**
     * 得到log文件名
    */
    private String getLogFile(){
        String date = "";
        Calendar cd = Calendar.getInstance();
        int y = cd.get(Calendar.YEAR);
        int m = cd.get(Calendar.MONTH) + 1;
        int d = cd.get(Calendar.DAY_OF_MONTH);

        date = "./log/" + logName + y + "-";

        if(m < 10)
            date += 0;

        date += m + "-";

        if(d < 10)
            date += 0;

        date += d + ".log";

        return date;
    }

    /**
     * 配置log属性，如果没有新建log文件
    */
    private void newLog(){
        logFile = getLogFile();

        try{
            logPrint = new PrintWriter(new FileWriter(logFile, true), true);
        }catch(IOException e){
            (new File("./log")).mkdir();

            try{
                logPrint = new PrintWriter(new FileWriter(logFile, true), true);

 }catch(IOException ex){
                System.err.println("无法打开日志文件：" + logFile);
                logPrint = new PrintWriter(System.err);
            }
        }
    }

    /**
     * 检查日期如果改变新建log文件
    */
    private void checkDate(){
        if(logFile == null || logFile.trim().equals("") || !logFile.equals(getLogFile())){
            newLog();
        }
    }

    /**
     * 将文本信息写入日志文件，缺省为userdir/log
    */
    public void log(String msg) {
        checkDate();
        logPrint.println(new Date() + ": " + msg);
    }

    /**
     * 将文本信息与异常写入日志文件
    */
    public void log(Throwable e, String msg) {
        checkDate();
        logPrint.println(new Date() + ": " + msg);
        e.printStackTrace(logPrint);
    }
    
 /*   public static void main(String[] args){
    	LogUtil lg=new LogUtil("err");
    	lg.log("测试");
    	
    	
    }*/
}
