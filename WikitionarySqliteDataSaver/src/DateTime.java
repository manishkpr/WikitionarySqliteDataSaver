

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime {
    int time[]={}; 
    public static int getHM(String s,String format){
   	 String temp[]=s.split(":");
   	 int ret=0;
   	 if(format.equalsIgnoreCase("HH")){
   		 ret=Integer.parseInt(temp[0]);
   	 }else{
   		 ret=Integer.parseInt(temp[1]);
   	 }
   	 return ret;
    }
    public static int getDMY(String s,String format){
      	 String temp[]=s.split("-");
      	 int ret=0;
      	 if(format.equalsIgnoreCase("DD")){
      		 ret=Integer.parseInt(temp[2]);
      	 }else if(format.equalsIgnoreCase("MM")){
      		 ret=Integer.parseInt(temp[1]);
      	 }else if(format.equalsIgnoreCase("YY")){
     		 ret=Integer.parseInt(temp[0]);
     	 }
      	 return ret;
       }
	public static String getDate()
	 {
		  Calendar cal = new GregorianCalendar();	
		  int m=cal.get(Calendar.MONTH);m=(m+1);
		  String month = addZero(String.valueOf(m));		  
		  String year  = String.valueOf(cal.get(Calendar.YEAR));
		  String day   = addZero(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		  return year + "-" + (month) + "-" + day;
	 }
	public static String getMonth()
	 {
		Calendar cal   = new GregorianCalendar();	
		  int    m     =cal.get(Calendar.MONTH);m=(m+1);
		  String month = addZero(String.valueOf(m));	
		  return month;
	 }
	public static String getDay()
	 {
		  Calendar cal = new GregorianCalendar();	
		  String day   = addZero(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		  return day;
	 }
	public static String getYear()
	 {
		  Calendar cal = new GregorianCalendar();	
		  int year     = cal.get(Calendar.YEAR);
		  return String.valueOf(year);
	 }
	public static String getHour()
	{
		 Calendar cal  = new GregorianCalendar();      
		 String hour   = addZero(String.valueOf(cal.get(Calendar.HOUR)));
		 return hour;
         
	}
	public static String getMinute()
	{
		 Calendar cal  = new GregorianCalendar();      
		 String min  = addZero(String.valueOf(cal.get(Calendar.MINUTE)));
		 return min;
         
	}
    public static String getTime(String format)
    {
         String time;
         Calendar cal  = new GregorianCalendar();        
         String hour   = addZero(String.valueOf(cal.get(Calendar.HOUR)));
         String minute = addZero(String.valueOf(cal.get(Calendar.MINUTE)));
         String second = addZero(String.valueOf(cal.get(Calendar.SECOND)));
         int    ap     = cal.get(Calendar.AM_PM);
         String am_pm;
         
         if(ap==0){
        	 am_pm="AM";
         }else{
        	 am_pm="PM";
        	 hour=autoTimePM(hour);
         }
	         if(format.equalsIgnoreCase("HH:MM")){
	                 time=hour + ":"+ minute;
	         }else if(format.equalsIgnoreCase("HH")){
	        	 time=String.valueOf(hour); 
	         }else if(format.equalsIgnoreCase("MM")){
	        	 time=String.valueOf(minute); 
	         }else if(format.equalsIgnoreCase("AM:PM")){
	        	 time=am_pm; 
	         } else{
	                 time=hour + ":"+ minute + ":" + second;
	         }
         return time;
    }
    public static String addZero(String minute){
   	   switch(Integer.parseInt(minute)){
   	        case 0: minute="00"; break;
   	        case 1:	minute="01"; break;
   	        case 2:	minute="02"; break;
   	        case 3:	minute="03"; break;
   	        case 4: minute="04"; break;
   	        case 5:	minute="05"; break;
   	        case 6:	minute="06"; break;
   	        case 7:	minute="07"; break;
   	        case 8:	minute="08"; break;
   	        case 9:	minute="09"; break;
   	       default: minute=minute; break;
   	      }
   	return minute;
    }
    public static int getInt(String s)
    {
    	int a=Integer.parseInt(s);
    	return a;
    }
    public static boolean isCorrectDate(String s)
    {
   	 boolean isValid=false;
   	 String temp[]=s.split("-");
   	 int dd,mm,yy,cd,cm,cy;
   	 yy=DateTime.getInt(temp[0]);
   	 mm=DateTime.getInt(temp[1]);
   	 dd=DateTime.getInt(temp[2]);
   	 cy=Integer.parseInt(DateTime.getYear());
   	 cm=Integer.parseInt(DateTime.getMonth());
   	 cd=Integer.parseInt(DateTime.getDay());
   	 if(yy==cy){
   		if(mm==cm){
   		 if(mm>=cm && dd>=cd){
   		 isValid=true;
   		 }
   		}else if(mm>cm)
   		{
   			 isValid=true;
   		}
   	 }else if(yy>cy){
   		 isValid=true;
   	 }
   	 System.out.print(yy +" "+mm+" "+dd+" "+cy+" "+cm+" "+cd);
   	 
   		  
   	 return isValid;
    }
    public static boolean isCorrectTime(String t,String s)
    {
   	 boolean isValid=false;
   	 String temp2[]=s.split("-");
   	 int dd,mm,yy,cd,cm,cy;
   	 yy=DateTime.getInt(temp2[0]);
   	 mm=DateTime.getInt(temp2[1]);
   	 dd=DateTime.getInt(temp2[2]);
   	 String temp[]=t.split(":");
   	 int hr,min,ap,chr,cmin,cap;
   	 hr=Integer.parseInt(temp[0]);
   	 min=Integer.parseInt(temp[1]);
   	 String tim=DateTime.getTime("HH:MM");
   	 String time[]=tim.split(":");
   	 chr=Integer.parseInt(DateTime.getTime("HH"));
   	 cmin=Integer.parseInt(DateTime.getTime("MM"));
   	// cap=Integer.parseInt(DateTime.getTime("AM:PM"));
   	 System.out.println(DateTime.getDate()+" "+s);
   	 if(s.equalsIgnoreCase(DateTime.getDate()))
   	 {
   		 if(hr==chr)
   		 {
   			 if(min>cmin){
   			 isValid=true;
   			 }
   		 }else if(hr>chr)
   		 {
   			 isValid=true;
   		 }
   	 }else{
   		 isValid=true;
   	 }
   	 System.out.print(chr+" "+cmin+" "+hr +" "+min+"  ");
   	 return isValid;
    }
    public static String autoTimePM(String number)
    {
    	switch(Integer.parseInt(number)){
    	    case 0: number ="12"; break;
	        case 1:	number ="13"; break;
	        case 2:	number ="14"; break;
	        case 3:	number ="15"; break;
	        case 4: number ="16"; break;
	        case 5:	number ="17"; break;
	        case 6:	number ="18"; break;
	        case 7:	number ="19"; break;
	        case 8:	number ="20"; break;
	        case 9:	number ="21"; break;
	        case 10:number ="22"; break;
	        case 11:number ="23"; break;
	        default:number =number; break;
	      }
    	return number;
    }
    //# This Function convert date Format mm/dd/yyyy to yyyy-mm-dd
    public static String dateFormat(String date)
    {
   	 String temp[]=date.split("/");
   	 StringBuilder str=new StringBuilder();
   	 String dd = null,mm=null,yyyy=null;
   	for(int i=0;i<temp.length;i++)
   	 {
   		mm=temp[0];
   		dd=temp[1];
   		if(temp.length==3)
   		{
   			yyyy=temp[2];
   		}
   		  
   	 }
   	if(temp.length!=3)
   	{
   	 str=str.append("0000-"+mm+"-"+dd);
   	}else
   	{
   		str=str.append(yyyy+"-"+mm+"-"+dd);
   	}
   	 return str.toString();
    }
    public static String getMonthString(String m)
    {
    	 String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    	 return months[Integer.parseInt(m)-1];
    }
    public static String dateString(String str)
    {
   	 String temp[]=str.split("-");
   	 String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
   	 String m = null,d = null,r=null;
   	 if(!str.equalsIgnoreCase("0000-00-00")){
   	 for(int i=0;i<temp.length;i++)
   	 {
   		 if(temp.length==2){
   			m=months[Integer.parseInt(temp[0])-1];
   			d=temp[1];
   			r=m+"-"+d;
   		 }else if(temp.length==3){
   			 if(!temp[0].equals("0000")){
   			 m=months[Integer.parseInt(temp[1])-1];
   			 d=temp[2];
   			 r=temp[0]+"-"+m+"-"+d;
   			 }else{
   				 m=months[Integer.parseInt(temp[1])-1];
   	   			 d=temp[2];
   	   		     r=m+"-"+d;
   			 }
   		 }
   		 else
   		 {
   			 m=months[Integer.parseInt(temp[1])-1];
   			 d=temp[2];
   			 r=m+"-"+d;
   		 }
   	 }
   	  }else
   	  {
   		  r="Not Available";
   	  }
   	 return r;
    }
    //## Return Date, Month, Year 
    public static String getDateMonthYearFromString(String date,String format){
    	String temp[]=date.split("-");
    	String str=null;
    	if(temp.length==3){
	    	if(format.equalsIgnoreCase("DD")){
	    		str=temp[2];
	    	}else if(format.equalsIgnoreCase("MM")){
	    		str=temp[1];
	    	}else if(format.equalsIgnoreCase("YY")){
	    		str=temp[0];
    	}
    	}else{
    		if(format.equalsIgnoreCase("DD")){
	    		str=temp[1];
	    	}else if(format.equalsIgnoreCase("MM")){
	    		str=temp[0];
	    	}
	    	
    	}
    	return str;
    	
    }
    public static String getTimeFromString(String date,String format){
    	String temp[]=date.split(":");
    	String str=null;
    	if(format.equalsIgnoreCase("HH")){
    		str=temp[0];
    	}else if(format.equalsIgnoreCase("MM")){
    		str=temp[1];
    	}
    	return str;
    	
    }
    
    public static String substractHour(int hr,int sub){
		switch(hr){ case 0: hr=24; break;};
	hr=hr-sub;
	return String.valueOf(hr);
    }
}
