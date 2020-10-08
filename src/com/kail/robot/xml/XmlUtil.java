package com.kail.robot.xml;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




















//import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.kail.robot.bean.ActionBean;
import com.kail.robot.bean.KeyBoardBean;
import com.kail.robot.bean.MouseBean;
import com.kail.robot.constant.Constant;
import com.kail.robot.iterface.IActionData;



/**
 * @author kail
 *
 */
public class XmlUtil {
	private SAXReader reader;
    private Document document;
    public XmlUtil(){
    	reader = new SAXReader();
    	/*try {
			document = reader.read(new File("User1.xml"));
		} catch (DocumentException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}*/
    }
    public XmlUtil(String dataName){
    	reader = new SAXReader();
    	try {
			//document = reader.read(new File(Constant.dir+"/"+dataName+"/data/"+dataName+".xml"));
			//document = reader.read(new File(Constant.dir+"/"+dataName+".xml"));
    		document = reader.read(new File(dataName));
		} catch (DocumentException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
    }
    
    public XmlUtil(File fileName){
    	reader = new SAXReader();
    	try {
			//document = reader.read(new File(Constant.dir+"/"+dataName+"/data/"+dataName+".xml"));
			document = reader.read(fileName);
		} catch (DocumentException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		XmlUtil x=new XmlUtil();
		/*String[][] str=x.getKeyBoardElements(1);
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				System.out.println(str[i][j]);
			}
		}
	    Document document = x.getDocument();
	    Element rootElement = document.getRootElement();
	    System.out.println(rootElement.getName());
	    
	    for ( Iterator<?> i = rootElement.elementIterator(); i.hasNext(); ) {
	        Element element = (Element) i.next();
	       System.out.println(element.getName());
	        
	        for ( Iterator<?> j = element.attributeIterator(); j.hasNext(); ) {
	            Attribute attribute = (Attribute) j.next();
	            System.out.println(attribute.getName() + "-" + attribute.getValue());
	         }
	     }
		Document document = x.getDocument();
		@SuppressWarnings("unchecked")
		List<Node> list = document.selectNodes("//Test/Action/*");
		System.out.println(list.size());
		for(Node n : list) {
	    	if(n.getName()=="mouse"){
	    		System.out.println(n.valueOf("@x")+
	    				"-"+n.valueOf("@y")+"-"
	    				+n.valueOf("@click"));
	    	}
	    	if(n.getName()=="keyboard"){
	    		System.out.println(n.valueOf("@keyValue"));
	    	}
	    }*/
		ArrayList<String> list = new ArrayList<String>();
		list.add("mouse");
		list.add("123");
		list.add("124");
		list.add("R");
		list.add("keyboard");
		list.add("aaa");
		list.add("keyboard");
		list.add("bbb");
		list.add("keyboard");
		list.add("ccc");
		list.add("mouse");
		list.add("123");
		list.add("124");
		list.add("L");
		try {
			x.createDocument(list);
		} catch (Exception e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
	  
	}
	/**
	 * ����ȫ�������
	 * 
	 * 
	 */
	public List<Node> getAllElements(){
		@SuppressWarnings("unchecked")
		List<Node> list = document.selectNodes("//Test/Action/*");
		return list;
		
	}
	
	/**
	 * ȡ������ֵ
	 * @param id
	 * @return String[][]
	 */
	public  String[][] getMouseElements(int id){
		
		String [][] str;
		int i=0;
		if(id==0){
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Node> list = document.selectNodes("//Test/Action["+id+"]/mouse");
		System.out.println(list.size());
		str=new String[list.size()][3];
	    for(Node n : list) {
	    	str[i][0]=n.valueOf("@x");
	    	str[i][1]=n.valueOf("@y");
	    	str[i][2]=n.valueOf("@click");	
	    	i++;
	    }
	    return str;
		
	}
	/**
	 * ȡ�ü��̵�ֵ
	 * @param id//�ڼ���Action����
	 * @return String[][]
	 */
	public  String[][] getKeyBoardElements(int id){
		
		String [][] str;
		int i=0;
		if(id==0){
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Node> list = document.selectNodes("//Test/Action["+id+"]/keyboard");
		System.out.println(list.size());
		str=new String[list.size()][3];
	    for(Node n : list) {
	    	str[i][0]=n.valueOf("@keyValue");
	    	str[i][1]="1";
	    	str[i][2]="1";
	    	i++;
	    }
	    return str;
		
	}
    @SuppressWarnings("rawtypes")
	public Document createDocument( ArrayList<String> list) throws Exception {
	       Document document = DocumentHelper.createDocument();
	       
	       //System.out.println(list.size());
	       Element root = document.addElement("Test");
	       Element classElement = root.addElement("Action")
	       	   .addAttribute("id", "1");
	      Iterator i=list.iterator();
	      while(i.hasNext()){
	    	  String temp=(String) i.next();
	    	  if(temp=="mouse"){
	    		  classElement.addElement("mouse")
	    		  				.addAttribute("x", (String) i.next())
	    		  				.addAttribute("y", (String) i.next())
	    		  				.addAttribute("click", (String) i.next())
	    		  				.addAttribute("dbclick", (String) i.next())
	    		  				.addAttribute("time", (String) i.next());
	    		  
	    	  }
	    	  if(temp=="keyboard"){
	    		  classElement.addElement("keyboard")
	  				.addAttribute("keyValue", (String) i.next())
	  				.addAttribute("time", (String) i.next());
	    	  }
	      }
	       
	       OutputFormat format = OutputFormat.createPrettyPrint();
	       XMLWriter writer = new XMLWriter(
	               new FileWriter( "User1.xml" ), format
	           );

	           writer.write( document );
	           writer.close();
	       
	       return document;
	    }
    
    @SuppressWarnings("rawtypes")
	public Document createDocument( ActionBean actionData) throws Exception {
	       Document document = DocumentHelper.createDocument();
	       MouseBean m=null;
	       KeyBoardBean k=null;
	       //System.out.println(list.size());
	       Element root = document.addElement("Test");
	       Element classElement = root.addElement("Action")
	       	   .addAttribute("id", ""+actionData.getId());
	      Iterator i=actionData.getActionList().iterator();
	      while(i.hasNext()){
	    	  IActionData temp=(IActionData) i.next();
	    	  if(temp.getName()=="mouse"){
	    		  m=(MouseBean)temp;
	    		  classElement.addElement("mouse")
	    		  				.addAttribute("x", ""+m.getX())
	    		  				.addAttribute("y", ""+m.getY())
	    		  				.addAttribute("click", m.getClick())
	    		  				.addAttribute("dbclick", m.getDbClick())
	    		  				.addAttribute("time", ""+m.getTime());
	    		  
	    	  }
	    	  if(temp.getName()=="keyboard"){
	    		  k=(KeyBoardBean)temp;
	    		  classElement.addElement("keyboard")
	  				.addAttribute("keyValue", ""+k.getKeyValue())
	  				.addAttribute("time", ""+k.getTime());
	    	  }
	      }
	       
	       OutputFormat format = OutputFormat.createPrettyPrint();
	       //File file=new File(Constant.dir+"/"+actionData.getName()+"/data");
	       File file=new File(Constant.dir);
	       if(!file.exists()){
	    	   file.mkdir();
	       }
	       File fs=new File(file,actionData.getName()+".xml");
	       XMLWriter writer = new XMLWriter(
	               new FileWriter( fs,true ), format
	           );

	           writer.write( document );
	           writer.close();
	       
	       return document;
	    }
	public SAXReader getReader() {
		return reader;
	}

	public void setReader(SAXReader reader) {
		this.reader = reader;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
