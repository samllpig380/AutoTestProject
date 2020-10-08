package com.kail.robot.core;
import com.kail.robot.bean.MouseBean;
import com.kail.robot.constant.Constant;
import com.kail.robot.test.Common;
import com.kail.robot.xml.XmlUtil;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;


public class MouseHK extends MouseHookListener implements Runnable {
	//����¼�����
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    //public User32 lib;
  
    private static HHOOK hhk;
    //private MouseHookListener mouseHook;
    private HMODULE hMod;
    private boolean isWindows = false;
    private boolean flag=true;
    private XmlUtil xml;
    private MouseBean mouse;
    public MouseHK() {
        isWindows = Platform.isWindows();
        if (isWindows) {
            //lib = User32.INSTANCE;
            hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        	this.lib=User32.INSTANCE;
            xml=new XmlUtil();
            new Common();
        }

    }
  /*  //��ӹ��Ӽ���
    public void addMouseHookListener(MouseHookListener mouseHook) {
        this.mouseHook = mouseHook;
        //this.mouseHook.lib = User32.INSTANCE;
    }*/
   //��װ ����
    public void setHook() {
        if (isWindows) {

        	 hhk=User32.INSTANCE.SetWindowsHookEx(WinUser.WH_MOUSE_LL, this, hMod, 0);
         	System.out.println(hhk.toString());
            int result;
            MSG msg = new MSG();
            while ((result =  User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
                if (result == -1) {
                    System.err.println("error in get message");
                    break;
                } else {
                    System.err.println("got message");
                    User32.INSTANCE.TranslateMessage(msg);
                    User32.INSTANCE.DispatchMessage(msg);
                }
            }
        }

    }
    //ֹͣ    ж��
    public  void unHook() {
        if (isWindows) {
        	 User32.INSTANCE.UnhookWindowsHookEx(hhk);
        	 //System.out.println(hhk.toString());
        	 
        }

    }
    /* ʵ�ֹ��Ӻ���
     	��������Ϣ���ݵ���ǰ�������е���һ���ӳ���һ�����ӳ�����Ե����������֮ǰ��֮���?����Ϣ
    	hhk--��ǰ���Ӿ��
    	nCode--���Ӵ��룻������һ������Ҫ����ģ������ݸ�ǰHook��̵Ĵ��롣��һ�����ӳ���ʹ�ô˴���
   		��ȷ����δ��?����Ϣ
    	wParam--Ҫ���ݵĲ����ɹ������;�����ʲô����˲���ĺ���ȡ���ڵ�ǰ�����빳�����͡�
   		lParam--Param��ֵ���ݸ�ǰHook��̡��˲���ĺ���ȡ���ڵ�ǰ�Ĺ����빳�����͡�
    */   
    public LRESULT callback(int nCode, WPARAM wParam, MouseHookStruct lParam){
    	
    	mouse=new MouseBean();
    	MouseBean t=null;
    	
    	if (nCode >= 0) {
            switch (wParam.intValue()) {
            case MouseHook.WM_MOUSEMOVE:
            	
                break;
            case MouseHook.WM_LBUTTONDOWN:
                System.err.println("mouse left button down, x="
                        + lParam.pt.x + " y=" + lParam.pt.y);
                //��ͼ
                /*n++;
                BufferedImage bi=c.captureFullScreenBI();
                try {
					ImageIO.write(bi, "jpeg",new File(Constant.dir+"/"+Constant.actionData.getName()+"/record/"+n+".jpg"));
				} catch (IOException e1) {
					// TODO �Զ���ɵ� catch ��
					e1.printStackTrace();
				}*/

                mouse.setX(lParam.pt.x);
                mouse.setY(lParam.pt.y);
                mouse.setClick("L");
                mouse.setDbClick("0");
                mouse.setTime(System.currentTimeMillis());
                int size=Constant.aList.size();
                if((size>0)&&(Constant.aList.get(size-1).getName()=="mouse")){
                	t=(MouseBean)Constant.aList.get(size-1);
                }
                
                if((t!=null)&&
                		(lParam.pt.x==t.getX())&&
                		(lParam.pt.y==t.getY())){
                	t.setDbClick("1");
                	Constant.aList.set(size-1, t);	
                }else{
                	 Constant.aList.add(mouse);
                }
               
               
                break;
            case MouseHook.WM_LBUTTONUP:
                System.err.println("mouse left button up, x="
                        + lParam.pt.x + " y=" + lParam.pt.y);
                
                break;
            case MouseHook.WM_RBUTTONDOWN:
                System.err.println("mouse R button down, x="
                        + lParam.pt.x + " y=" + lParam.pt.y);
                
              
                break;
            case MouseHook.WM_RBUTTONUP:
                System.err.println("mouse R button up, x="
                        + lParam.pt.x + " y=" + lParam.pt.y);
                mouse.setX(lParam.pt.x);
                mouse.setY(lParam.pt.y);
                mouse.setClick("R");
                mouse.setDbClick("0");
                mouse.setTime(System.currentTimeMillis());
                Constant.aList.add(mouse);
                break;
            case MouseHook.WM_MBUTTONDOWN:
            	System.out.println("program terminated.");
                this.unHook();
                this.stop();
                Constant.actionData.setActionList(Constant.aList);
                	try {
    					xml.createDocument(Constant.actionData);

    				} catch (Exception e) {
    					// TODO �Զ���ɵ� catch ��
    					e.printStackTrace();
    				}
            	return null;
			case MouseHook.WM_MBUTTONUP:
                break;
            }
        }
    	   return lib.CallNextHookEx(hhk, nCode, wParam,
                   lParam.getPointer());
    	
    }
    //��������¼�
    public void doWithMouseEven(){

    	this.setHook();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	this.unHook();
    }

    public static void main(String[] args) {
        try {
        	
        	 MouseHK mk=new MouseHK();
             Thread t1=new Thread(mk);
             t1.start();
  
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	@Override
	public void run() {
		// TODO �Զ���ɵķ������
		while(flag){
			this.doWithMouseEven();
		}
		
	}
	public void stop(){
		this.flag=false;
	}
}
