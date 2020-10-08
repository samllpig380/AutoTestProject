package com.kail.robot.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.List;

import org.dom4j.Node;

import com.kail.robot.constant.Constant;
import com.kail.robot.core.KeyboardHK;
import com.kail.robot.core.MouseHK;
import com.kail.robot.xml.XmlUtil;



@SuppressWarnings("unused")
public class BusinessLogic {
       KeyboardHK keyObject;
       MouseHK mouseObject;
       Common robot;
       int count=0;
       public BusinessLogic(){
    	   mouseObject=new MouseHK();
    	   robot=new Common();
		}
       
       public void record(){
    	   Thread t1=new Thread(mouseObject);
           Thread t2=new Thread(new Runnable(){

 			@Override
 			public void run() {
 				// TODO �Զ���ɵķ������
 				KeyboardHK.doWithKbEvent();
 			}
         	  
           });

           t1.start();
           t2.start();
           try {
 			Thread.sleep(3000);
 		} catch (InterruptedException e) {
 			// TODO �Զ���ɵ� catch ��
 			e.printStackTrace();
 		}
       }
       
       public void playBack(){
    	   	XmlUtil x=new XmlUtil(Constant.actionData.getName());
    	   	long temp = 0,deplay=2000;
   			List<Node> list=x.getAllElements();
   	    	int[] key=new int[1];
   	    	for(Node n : list) {
   		    	/*if(n.getName()=="mouse"||n.valueOf("@click")=="L"){
   		    		System.out.println(n.valueOf("@x")+
   		    				"-"+n.valueOf("@y")+"-"
   		    				+n.valueOf("@click"));
   		    		Common.clickLMouse(r, Integer.parseInt(n.valueOf("@x")), 
   		    				Integer.parseInt(n.valueOf("@y")), 2000);
   		    	}*/
   	    		
   	    		
   	    		if(count>=1){
   	    			
   	    			deplay=Long.parseLong(n.valueOf("@time"))-temp;
   	    			try {
						Thread.sleep(deplay);
					} catch (InterruptedException e) {
						// TODO �Զ���ɵ� catch ��
						e.printStackTrace();
					}
   	    		}
   	    		if(n.getName()=="mouse"){
   	    			if(n.valueOf("@click").equals("L")){
   	    				if(n.valueOf("@dbclick").equals("1")){
   	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
   	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
   	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
   	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
   	    				}else{
   	    					System.out.println(n.valueOf("@x")+
   	   	   		    				"-"+n.valueOf("@y")+"-"
   	   	   		    				+n.valueOf("@click"));
   	   	   		    		robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
   	   	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
   	    				}
   	    				
   	    				
   	    			}else if(n.valueOf("@click").equals("R")){
   	    				System.out.println(n.valueOf("@x")+
   	   		    				"-"+n.valueOf("@y")+"-"
   	   		    				+n.valueOf("@click"));
   	    				robot.clickRMouse(Integer.parseInt(n.valueOf("@x")), 
   	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
   	    			}
   	    		}
   		    	if(n.getName()=="keyboard"){
   		    		key[0]=Integer.
   		    				parseInt(n.valueOf("@keyValue"));
   		    		robot.pressKeys(key, 1000);
   		    	}
   		    	temp=Long.parseLong(n.valueOf("@time"));
   		    	count++;
   		    	}
   		
       }
       
       public void playBackEx(){
    	   
    	   XmlUtil x=new XmlUtil(Constant.actionData.getName());
    	   robot=new CommonEx();
    	   
   	   	    long temp = 0,deplay=2000;
  			List<Node> list=x.getAllElements();
  	    	int[] key=new int[1];
  	    	for(Node n : list) {
  		    	/*if(n.getName()=="mouse"||n.valueOf("@click")=="L"){
  		    		System.out.println(n.valueOf("@x")+
  		    				"-"+n.valueOf("@y")+"-"
  		    				+n.valueOf("@click"));
  		    		Common.clickLMouse(r, Integer.parseInt(n.valueOf("@x")), 
  		    				Integer.parseInt(n.valueOf("@y")), 2000);
  		    	}*/
  	    		
  	    		
  	    		if(count>=1){
  	    			
  	    			deplay=Long.parseLong(n.valueOf("@time"))-temp;
  	    			try {
						Thread.sleep(deplay);
					} catch (InterruptedException e) {
						// TODO �Զ���ɵ� catch ��
						e.printStackTrace();
					}
  	    		}
  	    		if(n.getName()=="mouse"){
  	    			if(n.valueOf("@click").equals("L")){
  	    				if(n.valueOf("@dbclick").equals("1")){
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    				}else{
  	    					System.out.println(n.valueOf("@x")+
  	   	   		    				"-"+n.valueOf("@y")+"-"
  	   	   		    				+n.valueOf("@click"));
  	   	   		    		robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    				}
  	    				
  	    				
  	    			}else if(n.valueOf("@click").equals("R")){
  	    				System.out.println(n.valueOf("@x")+
  	   		    				"-"+n.valueOf("@y")+"-"
  	   		    				+n.valueOf("@click"));
  	    				robot.clickRMouse(Integer.parseInt(n.valueOf("@x")), 
  	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    			}
  	    		}
  		    	if(n.getName()=="keyboard"){
  		    		key[0]=Integer.
  		    				parseInt(n.valueOf("@keyValue"));
  		    		robot.pressKeys(key, 1000);
  		    	}
  		    	temp=Long.parseLong(n.valueOf("@time"));
  		    	count++;
  		    	}
       }
       
       public void playBack(File file){
    	   XmlUtil x=new XmlUtil(file);
    	   robot=new CommonEx();
    	   
   	   	    long temp = 0,deplay=2000;
  			List<Node> list=x.getAllElements();
  	    	int[] key=new int[1];
  	    	for(Node n : list) {
  		    	/*if(n.getName()=="mouse"||n.valueOf("@click")=="L"){
  		    		System.out.println(n.valueOf("@x")+
  		    				"-"+n.valueOf("@y")+"-"
  		    				+n.valueOf("@click"));
  		    		Common.clickLMouse(r, Integer.parseInt(n.valueOf("@x")), 
  		    				Integer.parseInt(n.valueOf("@y")), 2000);
  		    	}*/
  	    		
  	    		
  	    		if(count>=1){
  	    			
  	    			deplay=Long.parseLong(n.valueOf("@time"))-temp;
  	    			try {
						Thread.sleep(deplay);
					} catch (InterruptedException e) {
						// TODO �Զ���ɵ� catch ��
						e.printStackTrace();
					}
  	    		}
  	    		if(n.getName()=="mouse"){
  	    			if(n.valueOf("@click").equals("L")){
  	    				if(n.valueOf("@dbclick").equals("1")){
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    				}else{
  	    					System.out.println(n.valueOf("@x")+
  	   	   		    				"-"+n.valueOf("@y")+"-"
  	   	   		    				+n.valueOf("@click"));
  	   	   		    		robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    				}
  	    				
  	    				
  	    			}else if(n.valueOf("@click").equals("R")){
  	    				System.out.println(n.valueOf("@x")+
  	   		    				"-"+n.valueOf("@y")+"-"
  	   		    				+n.valueOf("@click"));
  	    				robot.clickRMouse(Integer.parseInt(n.valueOf("@x")), 
  	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    			}
  	    		}
  		    	if(n.getName()=="keyboard"){
  		    		key[0]=Integer.
  		    				parseInt(n.valueOf("@keyValue"));
  		    		robot.pressKeys(key, 1000);
  		    	}
  		    	temp=Long.parseLong(n.valueOf("@time"));
  		    	count++;
  		    	}
       }
       //用于外部脚本调用
       public void playBack(String filePath){
    	  
    	   XmlUtil x=new XmlUtil(filePath);
    	   robot=new CommonEx();
    	   
   	   	    long temp = 0,deplay=2000;
  			List<Node> list=x.getAllElements();
  	    	int[] key=new int[1];
  	    	for(Node n : list) {
  		    	/*if(n.getName()=="mouse"||n.valueOf("@click")=="L"){
  		    		System.out.println(n.valueOf("@x")+
  		    				"-"+n.valueOf("@y")+"-"
  		    				+n.valueOf("@click"));
  		    		Common.clickLMouse(r, Integer.parseInt(n.valueOf("@x")), 
  		    				Integer.parseInt(n.valueOf("@y")), 2000);
  		    	}*/
  	    		
  	    		
  	    		if(count>=1){
  	    			
  	    			deplay=Long.parseLong(n.valueOf("@time"))-temp;
  	    			try {
						Thread.sleep(deplay);
					} catch (InterruptedException e) {
						// TODO �Զ���ɵ� catch ��
						e.printStackTrace();
					}
  	    		}
  	    		if(n.getName()=="mouse"){
  	    			if(n.valueOf("@click").equals("L")){
  	    				if(n.valueOf("@dbclick").equals("1")){
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    					robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 0);
  	    				}else{
  	    					System.out.println(n.valueOf("@x")+
  	   	   		    				"-"+n.valueOf("@y")+"-"
  	   	   		    				+n.valueOf("@click"));
  	   	   		    		robot.clickLMouse(Integer.parseInt(n.valueOf("@x")), 
  	   	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    				}
  	    				
  	    				
  	    			}else if(n.valueOf("@click").equals("R")){
  	    				System.out.println(n.valueOf("@x")+
  	   		    				"-"+n.valueOf("@y")+"-"
  	   		    				+n.valueOf("@click"));
  	    				robot.clickRMouse(Integer.parseInt(n.valueOf("@x")), 
  	   		    				Integer.parseInt(n.valueOf("@y")), 2000);
  	    			}
  	    		}
  		    	if(n.getName()=="keyboard"){
  		    		key[0]=Integer.
  		    				parseInt(n.valueOf("@keyValue"));
  		    		robot.pressKeys(key, 1000);
  		    	}
  		    	temp=Long.parseLong(n.valueOf("@time"));
  		    	count++;
  		    	}
       }
       public static void main(String[] args) {
		  new BusinessLogic().playBack();
	}

}
