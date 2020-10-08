package com.kail.robot.test;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kail.robot.constant.Constant;



public class CommonEx extends Common {
	private int n=0,c=0;
	private int x=0;
	private int y=0;
	public CommonEx(){
		super();
	}
	
	/**
	 *(��д����ķ�����ʵ�ֽ�ͼ����)
     * ��굥���������,Ҫ˫�����������
     * 
     * @param r
     * @param x
     *            x���λ��
     * @param y
     *            y���λ��
     * @param delay
     *            �ò�������ӳ�ʱ��
     */
    public void clickLMouse( int x, int y, int delay) {
    	if(this.c==0){
    		this.x=x;
        	this.y=y;
        	this.c++;
    	}else{
    		this.c=0;
    	}
    	
    	Robot r=super.getR();
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON1_MASK);
        
        //��ͼ
        if(this.n>0&&this.x==x&&this.y==y){
        	
        }else{
        	// capture();
        }
       
        r.delay(100);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(delay);

    }
    //��ͼ
    public void capture(){
    	 n++;
         BufferedImage bi=super.captureFullScreenBI();
         try {
 			ImageIO.write(bi, "jpeg",new File(Constant.dir+"/"+Constant.actionData.getName()+"/play/"+n+".jpg"));
 		} catch (IOException e1) {
 			// TODO �Զ���ɵ� catch ��
 			e1.printStackTrace();
 		}
    }
}
