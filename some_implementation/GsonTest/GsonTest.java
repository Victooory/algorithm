package algorithm.some_implementation.GsonTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * gson-2.8.0.jar
 * @author dong
 *
 */
public class GsonTest {
	
	//序列化反序列化
	public static void ReadAndWrite() {
		Gson gson =new Gson();
		Collection<Integer> ints = new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			ints.add(i);
		}
		//(序列化Serialization)
		String json = gson.toJson(ints); // ==> json is [1,2,3,4,5]
		// (反序列化Deserialization)
		Type collectionType =new TypeToken<Collection<Integer>>(){}.getType();
		Collection<Integer> ints2 = gson.fromJson(json, collectionType);//ints,ints2是一样的
		Iterator<Integer> iterator = ints2.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	/**
	 * resultcode:200
	 * reason:successed!
	 * temperature:16℃~27℃
	 * weather:阴转多云
	 * @throws FileNotFoundException 
	 * @throws JsonSyntaxException 
	 * @throws JsonIOException 
	 */
	public static void Read() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		//File file = new File("F:"+File.separator+"javatest");
		//File file = new File("src/algorithm/some_implementation/weather.json");
		JsonParser parse =new JsonParser();  //创建json解析器
		Gson gson = new Gson();
        JsonObject json=(JsonObject) parse.parse(new FileReader("F:/exercise/test/src/algorithm/some_implementation/GsonTest/weather.json"));  //创建jsonObject对象
        System.out.println("resultcode:"+json.get("resultcode").getAsInt());  //将json数据转为为int型的数据
        System.out.println("reason:"+json.get("reason").getAsString());     //将json数据转为为String型的数据
        JsonObject result=json.get("result").getAsJsonObject();
        JsonObject today=result.get("today").getAsJsonObject();
        System.out.println("temperature:"+today.get("temperature").getAsString());
        System.out.println("weather:"+today.get("weather").getAsString());

	}
	/**
	 * 数组读取
	 * @param args
	 * @throws JsonIOException
	 * @throws JsonSyntaxException
	 * @throws FileNotFoundException
	 */
	public static void Read2() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		 JsonParser parser=new JsonParser();  //创建JSON解析器
         JsonObject object=(JsonObject) parser.parse(new FileReader("F:/exercise/test/src/algorithm/some_implementation/GsonTest/weather.json"));  //创建JsonObject对象
         System.out.println("cat="+object.get("cat").getAsString()); //将json数据转为为String型的数据
         System.out.println("pop="+object.get("pop").getAsBoolean()); //将json数据转为为boolean型的数据
          
         JsonArray array=object.get("language").getAsJsonArray();    //得到为json的数组
         for(int i=0;i<array.size();i++){
             System.out.println("---------------");
             JsonObject subObject=array.get(i).getAsJsonObject();
             System.out.println("id="+subObject.get("id").getAsInt());
             System.out.println("name="+subObject.get("name").getAsString());
             System.out.println("ide="+subObject.get("ide").getAsString());
         }
	}
	
	public static void fileio() throws IOException {
		List<Student> arrayList=new ArrayList<Student>();
		Gson gson=new Gson();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("algorithm/some_implementation/GsonTest/applicationContext.xml");
		Student student = (Student) ctx.getBean("student");
		Student student2 = (Student) ctx.getBean("student2");
		System.out.println(student);
		System.out.println(student2);
		arrayList.add(student);
		arrayList.add(student2);
		
		
		/*
		 * 文件io*/
		//对单个实例操作
		//String json = gson.toJson(student);
		//将对象的集合转换成json字符串
//		String json=gson.toJson(arrayList);
//		System.out.println(json);
		//FileReader fileReader = new FileReader("student.json");
		File file = new File("src/algorithm/some_implementation/GsonTest/student.json");
		// if file doesnt exists, then create it
		if (!file.exists()) {
		    file.createNewFile();
		}
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
//		bw.write(json);
//		bw.flush();
//		bw.close();
		//对单个实例操作
		//gson.fromJson(json,Sutdent.Class);//json为要解析的字符串，Sutdent.Class为对应的对象
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		StringBuffer content = new StringBuffer();
		int ch;
		while ((ch = br.read()) != -1) {
		    content.append((char) ch);
		}
		br.close();
		List<Student> arrayList2 = gson.fromJson(content.toString(), new TypeToken<List<Student>>(){}.getType());
		for(Student stu:arrayList2)
		{
			System.out.println(stu);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		JsonObject jsonObject = new JsonObject();
		
		String json = "{ \"name\":\"java书籍\", \"authors\":[\"Jerry\",\"Tom\"]}";
        JsonParser parser = new JsonParser();
        JsonElement ele = parser.parse(json);
        System.out.println(ele);
        System.out.println(ele.getAsJsonObject().get("authors"));
        System.out.println(ele.getAsJsonObject().get("authors").getAsString());
        
	}
}
