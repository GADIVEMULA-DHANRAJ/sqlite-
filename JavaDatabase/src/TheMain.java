import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TheMain {
	 public static void main(String [] args) {
	 	   //insert value here ::::        insert();
	     //retriving all values 	:::::: readAllData();
		 
		   readSpecificRow();
		   
}
	 private static void insert(String MovieName, String Actor, String Actress, String ReleaseDate ,String Director) {
		  Connection con = DbConnection.connect();
		  PreparedStatement ps = null; 
		  try {
		    String sql = "INSERT INTO users(MovieName,Actor, Actress, ReleaseDate,Director) VALUES(?,?,?,?,?) ";
		    ps = con.prepareStatement(sql);
		    ps.setString(1, MovieName);
		    ps.setString(2, Actor);
		    ps.setString(3,  Actress);
		    ps.setString(4,ReleaseDate);
		    ps.setString(5,Director);
		    ps.execute();
		    System.out.println("Data has been inserted!");
		  } catch(SQLException e) {
		    System.out.println(e.toString());
		    // always remember to close database connections
		  } finally {
		    try{
		      ps.close();
		      con.close();
		    } catch(SQLException e) {
		      System.out.println(e.toString());
		    }
		    
		  }
	 }
		  //retriving data from database
		  
	 private static void readAllData() {
		    Connection con = DbConnection.connect(); 
		    PreparedStatement ps = null; 
		    ResultSet rs = null; 
		    
		    try {
		      String sql = "SELECT * FROM users";
		      ps = con.prepareStatement(sql); 
		      rs = ps.executeQuery();
		      System.out.println("ALL Movie details\n");
		      while(rs.next()) {
		        String MovieName = rs.getString("MovieName"); 
		        String Actor = rs.getString("Actor"); 
		        String Actress = rs.getString("Actress"); 
		        String ReleaseDate = rs.getString("ReleaseDate"); 
		        String Director = rs.getString("Director"); 
		        
		        
		        
		        System.out.println("MovieName: "+MovieName);
		        System.out.println("Actor: "+Actor);
		        System.out.println("Actress: "+Actress);
		        System.out.println("ReleaseDate: "+ReleaseDate);
		        System.out.println("Director: "+Director+"\n\n");
		        
		      }
		    } catch(SQLException e) {
		      System.out.println(e.toString());
		    } finally {
		      try {
		        rs.close();
		        ps.close();
		        con.close(); 
		      } catch(SQLException e) {
		        System.out.println(e.toString());
		      }
		    }
		    
		    
		  }
	 private static void readSpecificRow() {
		    
		    Connection con = DbConnection.connect(); 
		    PreparedStatement ps = null; 
		    ResultSet rs = null; 
		    try {
		      String sql = "Select MovieName from users where Actor = ? "; 
		      ps = con.prepareStatement(sql); 
		      ps.setString(1, "dhanraj");
		      rs = ps.executeQuery(); 
		      
		     
		      String MovieName = rs.getString(1); 
		      System.out.println(MovieName);
		      
		    } catch(SQLException e) {
		      System.out.println(e.toString());
		    } finally {
		      
		      try{
		        rs.close(); 
		        ps.close();
		        con.close(); 
		      } catch (SQLException e) {
		       
		        System.out.println(e.toString());
		      }
		      
		    }
		  }
		
}
