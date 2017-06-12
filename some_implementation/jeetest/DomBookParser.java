package algorithm.some_implementation.jeetest;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class DomBookParser {
 public static void main (String[] args){
	 try {
		 DocumentBuilderFactory fact1= DocumentBuilderFactory.newInstance();
		 fact1.setValidating(true);
		 fact1.setIgnoringElementContentWhitespace(true);
		 DocumentBuilder build1=fact1.newDocumentBuilder();
		 String book1="src\\jeetest\\book.xml";
		 Document bookDoc=build1.parse(new File(book1));
		 Element bookEle=bookDoc.getDocumentElement();
		 NodeList chapterNodes=bookEle.getChildNodes();
		 for(int i=0;i<chapterNodes.getLength();i++){
			 Element chapter=(Element) chapterNodes.item(i);
			 System.out.println("Value:"+chapter.getNodeName()+" ");
			 NodeList numberList=chapter.getElementsByTagName("chapNum");
			 Text number=(Text) numberList.item(0).getFirstChild();
			 System.out.println(number.getData()+" ");
			 NodeList titleList=chapter.getElementsByTagName("chapTitle");
			 Text title= (Text) titleList.item(0).getFirstChild();
			 System.out.println(title.getData());
		 }
	 }catch(Exception e)
	 {
		 System.err.print("Error parsing:"+ e.getMessage());
		 System.exit(1);
	 }
	 
 } 
}
