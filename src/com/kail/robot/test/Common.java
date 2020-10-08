package com.kail.robot.test;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @description Robot�����࣬ʵ�ֻ�Ĺ���
 *
 *
 */
/**
 * 
 *
 */
@SuppressWarnings("all")
public class Common {

	private Robot r;
	public Common(){
		try {
			this.r=new Robot();
		} catch (AWTException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
	}

    /**
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
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.delay(10);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(delay);

    }

    public Robot getR() {
		return r;
	}

	/**
     * ����һ�,Ҫ˫�����������
     * 
     * @param r
     * @param x
     *            x���λ��
     * @param y
     *            y���λ��
     * @param delay
     *            �ò�������ӳ�ʱ��
     */
    public void clickRMouse( int x, int y, int delay) {
        r.mouseMove(x, y);
        System.out.println("r");
        r.mousePress(InputEvent.BUTTON3_MASK);
        r.delay(10);
        r.mouseRelease(InputEvent.BUTTON3_MASK);
        r.delay(delay);

    }

    /**
     * �������루һ��ֻ������һ���ַ�
     * 
     * @param r
     * @param ks
     *            ����������ַ�����
     * @param delay
     *            ����һ�������ӳ�ʱ��
     */
    public void pressKeys( int[] ks, int delay) {
        for (int i = 0; i < ks.length; i++) {
        	if(ks[i]==13||ks[i]==221){
        		r.keyPress(KeyEvent.VK_ENTER);
        		  r.delay(10);
        		r.keyRelease(KeyEvent.VK_ENTER);
        		 r.delay(delay);
        	}
        	else{
        		 r.keyPress(ks[i]);
                 r.delay(10);
                 r.keyRelease(ks[i]);
                 r.delay(delay);
        	}
           
        }
    }

    /**
     * ����
     * 
     * @param r
     * @throws InterruptedException
     */
    void doCopy() throws InterruptedException {
        Thread.sleep(3000);
        r.setAutoDelay(200);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_C);
    }

    /**
     * ճ��
     * 
     * @param r
     * @throws InterruptedException
     */
    void doParse() throws InterruptedException {
        r.setAutoDelay(500);
        Thread.sleep(2000);
        r.mouseMove(300, 300);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
    }

    /**
     * ��׽ȫ��Ľ
     * 
     * @param r
     * @return
     */
    public Icon captureFullScreen() {
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(
                Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        return icon;
    }
    /**
     * ��׽ȫ��Ľ 
     * BufferedImage
     * 
     * @param r
     * @return 
     */
    public BufferedImage captureFullScreenBI() {
        return r.createScreenCapture(new Rectangle(
                Toolkit.getDefaultToolkit().getScreenSize()));
    }
    /**
     * ��׽��Ļ��һ����������
     * 
     * @param r
     * @param x
     *            x���λ��
     * @param y
     *            y���λ��
     * @param width
     *            ���εĿ�
     * @param height
     *            ���εĸ�
     * @return
     */
    public Icon capturePartScreen(int x, int y, int width, int height) {
        r.mouseMove(x, y);
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(
                width, height));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        return icon;
    }
    public BufferedImage capturePartScreenBuff(int x, int y, int width, int height) {
        r.mouseMove(x, y);
        BufferedImage image = r.createScreenCapture(new Rectangle(
                width, height));
        
        return image;
    }
    /**
     * ��׽ȫ��Ľ 
     * BufferedImage
     * 
     * @param r
     * @return 
     */
    public BufferedImage capturePartScreenBI( int x, int y, int width, int height) {
        r.mouseMove(x, y);
        return r.createScreenCapture(new Rectangle(
                width, height));
    }
    
    public static void main(String[] args) {
    	Common c=new Common();
    	BufferedImage bi=null;
    
    	Robot r;
		try {
			r = new Robot();
			/*bi=c.captureFullScreenBI();
			ImageIO.write(bi, "jpeg", new File("d:\\1.jpg"));*/
			
			File fs=new File("d:/1");
			if(!fs.exists()){
				fs.mkdir();
			}
			
            bi=c.capturePartScreenBuff(589,368,100,200);
            ImageIO.write(bi, "jpeg", new File(fs,"5.jpg"));
			//���
			//Common.clickLMouse(r, 12, 29, 100);
			/*Runtime.getRuntime().exec("notepad");
			int[] ks={81,82};
			Common.pressKeys(r, ks, 2000);*/
			
		} catch (AWTException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
    	
    	
	}

}