package algorithm.some_implementation.jeetest;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class UseDomEditElement {
     public static void main(String para[]){
    	 Text textMsg;
    	 try{
    		 DocumentBuilderFactory favtory=DocumentBuilderFactory.newInstance();
    		 favtory.setValidating(true);
    		 favtory.setIgnoringElementContentWhitespace(true);
    		 DocumentBuilder builder=favtory.newDocumentBuilder();
    		 Document document=builder.parse(new File("src\\jeetest\\publication.xml"));
    		 Element root=document.getDocumentElement();
    		 Element book=document.createElement("book");
    		 Element title=document.createElement("Title");
    		 textMsg=document.createTextNode("Applied Cryptography new");
    		 title.appendChild(textMsg);
    		 book.appendChild(title);
    		 Element autor=document.createElement("Writer");
    		 textMsg=document.createTextNode("Tom Brooks Son");
    		 autor.appendChild(textMsg);
    		 book.appendChild(autor);
    		 Element date=document.createElement("PublishDate");
    		 textMsg=document.createTextNode("1994-09-08");
    		 date.appendChild(textMsg);
    		 book.appendChild(date);
    		 root.appendChild(book);
    		 
    		 TransformerFactory tfactory=TransformerFactory.newInstance();
    		 Transformer transformer=tfactory.newTransformer();
    		 DOMSource source=new DOMSource(document);
    		 StreamResult result=new StreamResult(new File("src\\jeetest\\publication.xml"));
    		 transformer.transform(source, result);
    		 }catch(Exception e){
    			 e.printStackTrace();
    		 }
    	 }
}
