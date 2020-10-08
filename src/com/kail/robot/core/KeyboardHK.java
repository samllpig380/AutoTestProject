package com.kail.robot.core;




import com.kail.robot.bean.KeyBoardBean;
import com.kail.robot.constant.Constant;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinUser.*;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
public class KeyboardHK {
	static HHOOK keyboardHHK;//���̹��ӵľ��
    static LowLevelKeyboardProc keyboardHook;//���̹��Ӻ���
    static boolean flag=false;
    static KeyBoardBean k;
    // ��װ����
    private static void setHook() {
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        keyboardHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);       
    }
     
    //ж�ع���
	private static void unHook() {
       boolean f= User32.INSTANCE.UnhookWindowsHookEx(keyboardHHK);   
       System.out.println(f);
    }
    //��������¼�
    public static void doWithKbEvent(){
    	keyboardHook = new LowLevelKeyboardProc() {
            @Override
            //�ú���������˼�ο���http://msdn.microsoft.com/en-us/library/windows/desktop/ms644985(v=vs.85).aspx
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam) {
            	k=new KeyBoardBean();
                int w = wParam.intValue();
                //����alt��ʱw=.WM_SYSKEYDOWN; ��������󲿷ּ�ʱw=WinUser.WM_KEYDOWN
                if(w==WinUser.WM_KEYDOWN || w==WinUser.WM_SYSKEYDOWN){
                	 /*System.out.println("key down: vkCode = "+lParam.vkCode);
                     String i=""+lParam.vkCode;
                     System.err.println(i);*/
                	k.setKeyValue(lParam.vkCode);
                	k.setTime(System.currentTimeMillis());
                	/*Constant.list.add("keyboard");
                	Constant.list.add(""+lParam.vkCode);
                	Constant.list.add(""+System.currentTimeMillis());*/
                	Constant.aList.add(k);
                }else if(w==WinUser.WM_KEYUP || w==WinUser.WM_SYSKEYUP)
                    System.out.println("key up: vkCode = "+lParam.vkCode);
                 
                // ]
                if(lParam.vkCode==221) { 
                    unHook();
                    System.err.println("program terminated.");
                    //System.exit(0);
                    return null;
                }
                return User32.INSTANCE.CallNextHookEx(keyboardHHK, nCode, wParam, lParam.getPointer());
            }
            
        };
        
        setHook();      
                 
        int result;
        MSG msg = new MSG();
        // ��Ϣѭ��
        // ʵ����whileѭ��һ�ζ���ִ�У���Щ�����������������ó�����GetMessage������������Ȼ����ͽ����ˡ�
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {           
            if (result == -1) {
                System.err.println("error in GetMessage");
                unHook();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);                
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
        unHook();
	}

    public static void main(String[] args) {
    	/*keyboardHook = new LowLevelKeyboardProc() {
            
            @Override
            //�ú���������˼�ο���http://msdn.microsoft.com/en-us/library/windows/desktop/ms644985(v=vs.85).aspx
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam) {
                int w = wParam.intValue();
                //����alt��ʱw=.WM_SYSKEYDOWN; ��������󲿷ּ�ʱw=WinUser.WM_KEYDOWN
                if(w==WinUser.WM_KEYDOWN || w==WinUser.WM_SYSKEYDOWN)
                    System.out.println("key down: vkCode = "+lParam.vkCode);
                else if(w==WinUser.WM_KEYUP || w==WinUser.WM_SYSKEYUP)
                    System.out.println("key up: vkCode = "+lParam.vkCode);
                 
                // �����'q'�˳�����'q'��vkCode��81
                if(lParam.vkCode==81) { 
                    unhook();
                    System.err.println("program terminated.");
                    System.exit(0);
                }
                return User32.INSTANCE.CallNextHookEx(keyboardHHK, nCode, wParam, lParam.getPointer());
            }
            
        };
        System.out.println("press 'q' to quit.");
        setHook();      
                 
        int result;
        MSG msg = new MSG();
        // ��Ϣѭ��
        // ʵ����whileѭ��һ�ζ���ִ�У���Щ�����������������ó�����GetMessage������������Ȼ����ͽ����ˡ�
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {           
            if (result == -1) {
                System.err.println("error in GetMessage");
                unhook();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);                
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
        unhook();*/
    	KeyboardHK.doWithKbEvent();
	}


	

}
