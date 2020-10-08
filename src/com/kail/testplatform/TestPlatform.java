package com.kail.testplatform;

import java.io.*;
import java.net.URI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.kail.testenvir.ParseEnvirConf;

@SuppressWarnings("all")
public class TestPlatform extends JFrame implements ActionListener,MouseListener,WindowListener
{
	JMenuBar menub=new JMenuBar();
	JTextArea text=new JTextArea();
	JMenu startUp=new JMenu("启动(S)");
	JMenu files=new JMenu("文件(F)");
	JMenu edit=new JMenu("编辑(E)");
	JMenu formats=new JMenu("格式(O)");
	JMenu script=new JMenu("脚本(T)");
	JMenu help=new JMenu("帮助(H)");
	JMenu tEnvir = new JMenu("测试环境");
	JRadioButtonMenuItem ie = new JRadioButtonMenuItem("IE");
	JRadioButtonMenuItem fireFox = new JRadioButtonMenuItem("FireFox");
	JRadioButtonMenuItem chrome = new JRadioButtonMenuItem("chrome");	
	ButtonGroup tEnvirGroup = new ButtonGroup();	
	JMenuItem compile=new JMenuItem("编译");
	JMenuItem testInfo=new JMenuItem("测试信息");
	JMenuItem testStart=new JMenuItem("测试执行");
	JMenuItem record=new JMenuItem("录制_C");
	JMenuItem play=new JMenuItem("回放_C");
	JMenuItem report=new JMenuItem("测试报告");
	JMenuItem newFile=new JMenuItem("新建(N)");
	JMenuItem open=new JMenuItem("打开(O)...");
	JMenuItem save=new JMenuItem("保存(S)");
	JMenuItem saveAs=new JMenuItem("另存为(A)...");
	JMenuItem exit=new JMenuItem("退出(X)");
	JMenuItem undo=new JMenuItem("撤销(U)");
	JMenuItem cut=new JMenuItem("剪切(T)");
	JMenuItem copy=new JMenuItem("复制(C)");
	JMenuItem paste=new JMenuItem("粘贴(P)");
	JMenuItem selectAll=new JMenuItem("全选(A)");
	JMenuItem timeDate=new JMenuItem("时间/日期(D)");
	JCheckBoxMenuItem lineWrap=new JCheckBoxMenuItem("自动换行(M)");
	JMenuItem encoded=new JMenuItem("GBK");
	JMenuItem fonts=new JMenuItem("字体...");
	JMenuItem about=new JMenuItem("关于自动化测试框架(A)");
	JMenuItem blog = new JMenuItem("进入小猪的主页");
	JFrame th=this;
	String name;
	String openedPath=null;
	boolean opened=false;
	boolean reworked=false;
	TestLogic tl=new TestLogic();
	TrayIcon trayIcon=null;
	Image image;
	SystemTray tray;
	ParseEnvirConf pec = new ParseEnvirConf();

	//初始化窗体
	TestPlatform(String name)
	{
		super(name);
		this.name=name;
		int x,y;
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		x=(size.width-600)/2;
		y=(size.height-400)/2;
		setSize(600,400);
		setLocation(x,y);
		//让程序界面显示在屏幕中央
		setMinimumSize(new Dimension(250,150));
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
	}
	
	//初始化布局
	void init()
	{		//为控件添加助记符
		this.addWindowListener(this);
		startUp.setMnemonic('S');
		files.setMnemonic('F');
		edit.setMnemonic('E');
		formats.setMnemonic('O');
		help.setMnemonic('H');
		newFile.setMnemonic('N');
		open.setMnemonic('O');
		save.setMnemonic('S');
		saveAs.setMnemonic('A');
		exit.setMnemonic('X');
		undo.setMnemonic('U');
		cut.setMnemonic('T');
		copy.setMnemonic('C');
		paste.setMnemonic('P');
		selectAll.setMnemonic('A');
		timeDate.setMnemonic('D');
		lineWrap.setMnemonic('M');
		about.setMnemonic('A');


		//为控件添加快捷键
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
		timeDate.setAccelerator(KeyStroke.getKeyStroke("F5"));
		
		startUp.add(tEnvir);
		tEnvir.add(ie);
		tEnvir.add(fireFox);
		tEnvir.add(chrome);
		tEnvirGroup.add(ie);
		tEnvirGroup.add(fireFox);
		tEnvirGroup.add(chrome);
		String teConf = pec.GetTestEnvir();
		if(teConf.equals("IE")){
			ie.setSelected(true);
		}else if(teConf.equals("FireFox")){
			fireFox.setSelected(true);
		}else if(teConf.equals("Chrome")){
			chrome.setSelected(true);
		}
		startUp.add(testInfo);
		startUp.add(this.testStart);
		startUp.add(this.play);
		startUp.add(this.record);
		startUp.add(report);
		script.add(compile);
		files.add(newFile);
		files.add(open);
		files.add(save);
		files.add(saveAs);
		files.addSeparator();
		files.add(exit);
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(selectAll);
		edit.add(timeDate);
		formats.add(lineWrap);
		formats.add(fonts);
		formats.add(encoded);
		help.add(about);
		help.add(blog);
		
		menub.add(startUp);
		menub.add(script);
		menub.add(files);
		menub.add(edit);
		menub.add(formats);
		menub.add(help);
		setJMenuBar(menub); 
		getContentPane().add(new JScrollPane(text));
		
		Listen listen=new Listen();
		Listen1 listen1=new Listen1();
		RobotListen robotListen=new RobotListen();
		
		newFile.addActionListener(listen);
		open.addActionListener(listen);
		save.addActionListener(listen);
		saveAs.addActionListener(listen);
		exit.addActionListener(listen);
		cut.addActionListener(listen);
		copy.addActionListener(listen);
		paste.addActionListener(listen);
		selectAll.addActionListener(listen);
		timeDate.addActionListener(listen);
		lineWrap.addActionListener(listen);
		encoded.addActionListener(listen);
		about.addActionListener(listen);
		blog.addActionListener(listen);
		open.addActionListener(listen1);
		save.addActionListener(listen1);
		saveAs.addActionListener(listen1);
		testInfo.addActionListener(robotListen);
		testStart.addActionListener(robotListen);
		record.addActionListener(robotListen);
		report.addActionListener(robotListen);
		play.addActionListener(robotListen);
		compile.addActionListener(robotListen);
		ie.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO 自动生成的方法存根
				if(ie.isSelected()){
					pec.SetTestEnvir("IE");
				}
			}

		});
		fireFox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO 自动生成的方法存根
				if(fireFox.isSelected()){
					pec.SetTestEnvir("FireFox");
				}
				
			}
			
		});
		
		chrome.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO 自动生成的方法存根
				if(chrome.isSelected()){
					pec.SetTestEnvir("Chrome");
				}
				
			}
			
		});
		//为控件添加事件侦听器
		
		undo.setEnabled(false);
		fonts.setEnabled(false); 
		//暂时没有实现的功能
	}
	
	//实现托盘程序
	public void rliTray(){
		this.setVisible(false);
		if(tray!=null){
			
		}else{
			
			if (SystemTray.isSupported()) {
				   tray = SystemTray.getSystemTray();
				      image = Toolkit.getDefaultToolkit().getImage("Image/showPannel.png");
				   PopupMenu popup = new PopupMenu();
				   //popup.addActionListener(this);
				   MenuItem defaultItem = new MenuItem("Open");
				   defaultItem.addActionListener(this);
				   popup.add(defaultItem);
				   defaultItem = new MenuItem("Exit");
				   defaultItem.addActionListener(this);
				   popup.add(defaultItem);
				   trayIcon = new TrayIcon(image, "RLI1.0", popup);
				   trayIcon.addMouseListener(this);
				     try {
				    tray.add(trayIcon);
				    } catch (AWTException e) {
				    System.err.println(e);
				             }
				  }
		}
	
	}
	
	class RobotListen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			Object source=e.getSource();
			if(source==testInfo){
				text.setText(tl.testInfo());
			}else if(source==testStart){
				rliTray();
				tl.startUp();
				
			}else if(source==play){
				rliTray();
				tl.play();
				
			}else if(source==record){
				rliTray();
				tl.record();
				
			}else if(source==report){
				tl.report();
			}else if(source==compile){
				compile();
				
			}
				
			
		}
		
	}

	class Listen implements ActionListener
	{
		//实现用于一般操作的事件侦听器
		public void actionPerformed(ActionEvent e)
		{
			Object source=e.getSource();

			if(source==newFile)
			{
				text.setText("");
				th.setTitle(name);
				openedPath=null;
				opened=false;
				
			}
			else if(source==exit)
				System.exit(0);
			else if(source==selectAll)
				text.selectAll();
			else if(source==cut)
				text.cut();
			else if(source==copy)
				text.copy();
			else if(source==paste)
				text.paste();
			else if(source==lineWrap)
				text.setLineWrap(!text.getLineWrap());
			else if(source==about)
			{
				String message="                   ----RLI----\n版本：1.0\n作者：kail\nhttp://quan.51testing.com/pcQuan/owner/482?name=小猪\n感谢您的使用";
				JOptionPane.showMessageDialog(th,message,"关于",JOptionPane.PLAIN_MESSAGE);
			}else if(source==blog){
				URI uri = URI.create("http://quan.51testing.com/pcQuan/owner/482?name=小猪");
				try {
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}else if(source==timeDate)
			{
				Date nowTime=new Date();
				SimpleDateFormat times=new SimpleDateFormat("HH:mm yyyy-MM-dd");
				text.insert(times.format(nowTime),text.getCaretPosition());
			}else if(source==encoded){
				
				if(openedPath!=null){
					FileInputStream fis;
					try {
						fis = new FileInputStream(openedPath);
						char [] content=new char[fis.available()];
						BufferedReader in = new BufferedReader
								(new InputStreamReader(fis,"GBK"));
						
						in.read(content);
						text.setText(new String(content));
						text.setCaretPosition(0);
						in.close();
						fis.close();
					} catch (Exception ex) {
						// TODO 自动生成的 catch 块
						ex.printStackTrace();
					}
					
					
				}
			}
		}
	}

	class Listen1 implements ActionListener
	{
		//实现用于对文件进行操作的事件侦听器
		public void actionPerformed(ActionEvent e)
		{
			Object source=e.getSource();
			//打开文件事件
			if(source==open)
			{
				FileDialog openFile=new FileDialog(th,"打开文件...",FileDialog.LOAD);
				openFile.setVisible(true);
				String filePath=openFile.getDirectory()+openFile.getFile();
				try
				{
					FileInputStream fis=new FileInputStream(filePath);
					byte [] content=new byte[fis.available()];
					fis.read(content);
					text.setText(new String(content));
					text.setCaretPosition(0);
					if(openFile.getFile()!=null)
					{
						th.setTitle(openFile.getFile()+"__"+name);
						openedPath=filePath;
						opened=true;
					}
					fis.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				opened=true;
			}
			//保存及另存为事件
			else if(source==save||source==saveAs)
			{
				String savePath=openedPath;
				if(savePath==null||source==saveAs)
				{
					FileDialog saveFile=new FileDialog(th,"保存文件...",FileDialog.SAVE);
					saveFile.setVisible(true);
					savePath=saveFile.getDirectory()+saveFile.getFile();
				}
				try
				{
					FileOutputStream fos=new FileOutputStream(savePath);
					fos.write(text.getText().getBytes());
					fos.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				if(source==save)
					openedPath=savePath;
			}
		}
	}
	
	public void compile(){
		
		String savePath=openedPath;
		System.out.println(openedPath);
		Process process = null;
		String[] cmdArray = new String[6];
		cmdArray[0]="javac";
		cmdArray[1]="-d";
		cmdArray[2]="./code";
		cmdArray[3]="-encoding";
		cmdArray[4]="UTF-8";
		if(savePath==null){
			FileDialog saveFile=new FileDialog(th,"保存文件...",FileDialog.SAVE);
			saveFile.setVisible(true);
			savePath=saveFile.getDirectory()+saveFile.getFile();
			try
			{
				FileOutputStream fos=new FileOutputStream(savePath);
				fos.write(text.getText().getBytes());
				fos.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		
		cmdArray[5]=savePath;
		if(cmdArray[5]!=null){
			try {
				
				process=Runtime.getRuntime().exec(cmdArray);

				/*BufferedReader br = new BufferedReader(new InputStreamReader(process
	                    .getInputStream()));
	            String inline;
	            while ((inline = br.readLine()) != null) {
	                System.out.println(inline);
	                
	            }
	            br.close();*/
			}
			catch(Exception e) {
				System.err.println(e);
				System.exit(1);
			}
		}
		System.out.println(cmdArray[3]);
	}
	
	public static void main(String[] args)
	{
		try {
			//BeautyEyeLNFHelper.launchBeautyEyeLNF();
			//UIManager.put("RootPane.setupButtonVisible",false);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String name=("RLI--V1.0");
		//让界面呈现跟系统一致的外观
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		TestPlatform note=new TestPlatform(name);		
		note.init();
		note.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		 this.rliTray();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		 if(e.getClickCount()==2)
		  {
		   this.setVisible(true);
		   this.setExtendedState(JFrame.NORMAL);
		   this.repaint();
		  }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		  if(e.getActionCommand().equals("Exit"))
			   System.exit(0);
			  else if(e.getActionCommand().equals("Open"))
			   this.setVisible(true);
	}
}