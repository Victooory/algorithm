package algorithm.problems;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AboutDate {
	public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
	        SimpleDateFormat date1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        SimpleDateFormat date2=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date d1 = null,d2 = null;
	        try {
				d1 = (Date) date1.parse(in.nextLine());
				d2 = (Date) date1.parse(in.nextLine());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        //获取天数
	        System.out.println(getDays(d1, d2) + "," + getweeks(d1, d2));
	}
	public static int getDays(Date d1,Date d2){
		Calendar calst = Calendar.getInstance();   
		Calendar caled = Calendar.getInstance();   
		calst.setTime(d1);   
		caled.setTime(d2);    
//		int h1 = calst.get(Calendar.HOUR_OF_DAY);(h1==0&&m1==0&&s1==0)
//		int m1 = calst.get(Calendar.MINUTE);
//		int s1 = calst.get(Calendar.SECOND);
		int h2 = caled.get(Calendar.HOUR_OF_DAY);
		int m2 = caled.get(Calendar.MINUTE);
		int s2 = caled.get(Calendar.SECOND);
		//得到两个日期相差的天数   
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
            .getTime().getTime() / 1000)) / 3600 / 24;
		if(h2==0&&m2==0&&s2==0){
			days--;
		}
	    return days;     
	}
	public static int getweeks(Date d1,Date d2){
		Calendar calst = Calendar.getInstance();   
		Calendar caled = Calendar.getInstance();   
		calst.setTime(d1);   
		caled.setTime(d2);
		int i = calst.get(Calendar.DAY_OF_WEEK);
		int j =  caled.get(Calendar.DAY_OF_WEEK);
		int weeks = caled.get(Calendar.WEEK_OF_YEAR) - calst.get(Calendar.WEEK_OF_YEAR)-1;
		if(i>=3 && j<=3){
			weeks--;
		}
		return weeks;
	}
}
