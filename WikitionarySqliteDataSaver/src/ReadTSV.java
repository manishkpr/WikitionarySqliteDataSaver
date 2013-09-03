import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ReadTSV {

	/**
	 * @param args
	 * @throws Exception 
	 */
	static List<DicGetSet> data=new ArrayList<DicGetSet>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String word="",type="",mean="";
		try {
			String dataFileName = "/Users/munish/Desktop/demo.tsv";
			Reader reader = new InputStreamReader(new FileInputStream(dataFileName),"UTF-8");
			BufferedReader bReader = new BufferedReader(reader);
			String line;
			while ((line = bReader.readLine()) != null) {
			    String datavalue[] = line.split("\t");
			    for(int i=0;i<datavalue.length;i++){
			    	 if(datavalue[i].equalsIgnoreCase("Noun") || 
			    			 datavalue[i].equalsIgnoreCase("Proper noun")||
			    			 datavalue[i].equalsIgnoreCase("Adjective")||
			    			 datavalue[i].equalsIgnoreCase("Interjection")||
			    			 datavalue[i].equalsIgnoreCase("Initialism")||
			    			 datavalue[i].equalsIgnoreCase("Abbreviation")||
			    			 datavalue[i].equalsIgnoreCase("Adverb")||
			    			 datavalue[i].equalsIgnoreCase("Prefix")||
			    			 datavalue[i].equalsIgnoreCase("Phrase")||
			    			 datavalue[i].equalsIgnoreCase("Conjunction")||
			    			 datavalue[i].equalsIgnoreCase("Preposition")||
			    			 datavalue[i].equalsIgnoreCase("Suffix")||
			    			 datavalue[i].equalsIgnoreCase("Contraction")||
			    			 datavalue[i].equalsIgnoreCase("Particle")||
			    			 datavalue[i].equalsIgnoreCase("Infix")||			    			 
			    			 datavalue[i].equalsIgnoreCase("Verb")){
			    		     type="("+datavalue[i]+")";
			    		     //System.out.print("("+datavalue[i]+")\t");
			    	  }else{
			    		  if(!datavalue[i].equalsIgnoreCase("English")){
			    			if(datavalue[i].startsWith("#")){
			    			 String temp=datavalue[i]; 
			    			 temp=temp.replace("#", "");
			    			 mean=temp;
			    		     //System.out.print(""+temp+"\t");
			    			}else{
			    			 word=datavalue[i];
			    		     //System.out.print(""+datavalue[i]+"\t");
			    			}
			    		  }
			    	  }
			    }
			    if(word!="" && type!="" && mean!=""){
			      data.add(new DicGetSet(word,type,mean));
			    }
			    //System.out.println();
			}
			bReader.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initData(data);
	}
	public static void printData(List<DicGetSet> d) throws Exception{
		for(int i=0;i<d.size();i++){
			System.out.println(d.get(i).getWord()+" "+d.get(i).getType()+" "+d.get(i).getMean());
			//initData(d.get(i).getWord(),d.get(i).getType(),d.get(i).getMean());
		}
	}
     public static void initData(List<DicGetSet> d) throws ClassNotFoundException{
    	 Class.forName("org.sqlite.JDBC");
    	    Connection connection = null;
    	    try
    	    {
    	      // create a database connection
    	      connection = DriverManager.getConnection("jdbc:sqlite:/Users/munish/Desktop/MyDictDB.db");
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
}
