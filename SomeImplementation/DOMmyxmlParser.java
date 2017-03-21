package algorithm.SomeImplementation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.SSLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 对xml的解析读取
 * @author dong
 * 可能会测试写入，注释来自文档
 */
public class DOMmyxmlParser {
	public static void main(String[] args) {
		read();
	}
	public static void read(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();				//使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器
			//InputStream in = DOMmyxmlParser.class.getClassLoader().getResourceAsStream("myxml.xml");   //new File("myxml.xml")
			Document doc = builder.parse(new File("src\\algorithm\\SomeImplementation\\myxml.xml"));				//它是文档树的根，并提供对文档数据的基本访问
			Element root = doc.getDocumentElement();		//这是一种便捷属性，该属性允许直接访问文档的文档元素的子节点。
			System.out.println("root:"+root.getTextContent());			
			NodeList nodes = root.getChildNodes();			//NodeList 中的项可以通过从 0 开始的整数索引进行访问
			for(int i = 0; i < nodes.getLength(); i++) {  
				Node el = nodes.item(i);
				if(el != null && el.getNodeType() == Node.ELEMENT_NODE){
					System.out.println("nodename: "+el.getNodeName()+" id: "+el.getAttributes().getNamedItem("id").getNodeValue()+" text:"+el.getTextContent());	//输出结点的值
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
