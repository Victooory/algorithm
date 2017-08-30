package algorithm.some_implementation.api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


public class GetUrl {

	private String send(String urlStr,String valStr) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String JsonStr = "";
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(10000);

		conn.setDoOutput(true);
		conn.setDoInput(true);
		out = new PrintWriter(conn.getOutputStream());
		out.print(valStr);
		out.flush();
		//END
		in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		String rl;
		while((rl = in.readLine())!=null){
			JsonStr += rl;
		}
		if(out!=null){out.close();}
		if(in!=null){in.close();}
		
			return JsonStr;
	}
	
	public String sendForGet(String urlStr) throws Exception {
	
		return this.send(urlStr, "");
	}
	public String sendForPost(String urlStr,String valStr) throws Exception {
		return this.send(urlStr, valStr);
	}
	public static void main(String[] args) {
		GetUrl g = new GetUrl();
		try {
			//System.out.println(g.sendForGet("http://v.juhe.cn/calendar/day?date=2015-1-1&key=d7235a9c10f256e1bebec66876323679"));
			//System.out.println(g.sendForPost("http://v.juhe.cn/calendar/day","date=2015-1-1&key=d7235a9c10f256e1bebec66876323679"));
			System.out.println(g.sendForPost("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName","theCityName=齐齐哈尔"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
