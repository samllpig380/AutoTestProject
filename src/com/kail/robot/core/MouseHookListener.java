package com.kail.robot.core;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;

/**
 *������깳�ӣ����¼�����ص�
 * 
 *
 */
public abstract class MouseHookListener implements HOOKPROC {
        public User32 lib = null;//windowӦ�ó��򴰿�
        public HHOOK hhk;//���Ӿ��
        
    /**
     * �ص�
     * �������ֵ���е���һ�����ӳ��򣬷���ֵ�ú���ȡ���ڹ���
     * @param nCode
     * @param wParam
     * @param lParam
     * @return
     */
    public LRESULT callback(int nCode, WPARAM wParam, MouseHookStruct lParam){
        return null;
    }
}