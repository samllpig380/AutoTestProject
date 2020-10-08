package com.kail.robot.core;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.POINT;

/**
 * ������깳�ӽṹ��
 * 
 *
 */
public class MouseHookStruct extends Structure
{
    public static class ByReference extends MouseHookStruct implements Structure.ByReference {};
    public POINT pt;//�����
    public HWND hwnd;//���ھ��
    public int wHitTestCode;
    public ULONG_PTR dwExtraInfo;//��չ��Ϣ
}