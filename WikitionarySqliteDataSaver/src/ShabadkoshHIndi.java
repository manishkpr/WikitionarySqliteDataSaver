import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ShabadkoshHIndi {
	
	public static void main(String[] args) throws Exception {
	
	String dataFileName = "/Users/munish/Desktop/hindi-csv/hindi_w.csv";
	//Properties prop;
	Reader reader = new InputStreamReader(new FileInputStream(dataFileName),"UTF-8");
	/*Scanner scan= new Scanner(new File(dataFileName));
	while(scan.hasNext()){
		System.out.print(scan.nextLine());
		
	}*/
	BufferedReader bReader = new BufferedReader(reader);
	String line;
	while ((line = bReader.readLine()) != null) {
	    String datavalue[] = line.split("\t");
		    for(int i=0;i<datavalue.length;i++){
		    	if(!datavalue[i].equalsIgnoreCase("<br>")){
		    		if(datavalue[i].startsWith("\"")){
		    	       //System.out.print(removeData(datavalue[i])+"\t");
		    			removeData(datavalue[i]);
		    	    }
		    	}
		    }
		    System.out.println();
	    }
	 initData(dicData);
	}
	public static String removeData(String data){
		data=data.replace("\"", "");
		data=data.replace("<br>", "");
		data=data.replace("1.", "");
		SplitingWord(data);
		return data;
	}
	static List<DicGetSet> dicData=new ArrayList<DicGetSet>();
	public static void SplitingWord(String data){
		
		String val[]=data.split(",");
		System.out.print(val[0]+" : "+replaceWord(val[1])+" : "+val[2]);
		dicData.add(new DicGetSet(val[0],replaceWord(val[1]),val[2]));
	}
	public static void initData(List<DicGetSet> d) throws ClassNotFoundException{
   	 Class.forName("org.sqlite.JDBC");
   	    Connection connection = null;
   	    try
   	    {
   	      // create a database connection
   	      connection = DriverManager.getConnection("jdbc:sqlite:/Users/munish/Desktop/eng-hin.db");
   	      Statement statement = connection.createStatement();
   	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
   	      String mean,word;
   	     for(int i=0;i<d.size();i++){
   	    	    mean=d.get(i).getMean();
   	    	    word=d.get(i).getWord();
   	    	    mean=mean.replaceAll("'", "\''");
   	    	    word=word.replaceAll("'", "\''");
   				System.out.println(i+" "+d.get(i).getWord()+" "+d.get(i).getType()+" "+d.get(i).getMean());
   	            statement.executeUpdate("insert into wiki_dic(word,type,meaning) values('"+word+"','"+d.get(i).getType()+"','"+mean+"')");
   	      }
   	    }
   	    catch(SQLException e)
   	    {
   	      // if the error message is "out of memory", 
   	      // it probably means no database file is found
   	      System.err.println(e.getMessage());
   	    }
   	    finally
   	    {
   	      try
   	      {
   	        if(connection != null)
   	          connection.close();
   	      }
   	      catch(SQLException e)
   	      {
   	        // connection close failed.
   	        System.err.println(e);
   	      }
   	    }
   	  
    }
	public static String replaceWord(String data){
		data=data.replace("N", "Noun");
		data=data.replace("Adj", "Adjective");
		data=data.replace("Adv", "Adverb");
		return data;
	}
}
