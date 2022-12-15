package DBMSProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLProj {
	public static void main (String[] args) throws SQLException {
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CS623Project","root","kapil");
		// For atomicity
				con.setAutoCommit(false);

				// For isolation
				con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		try {
		Statement stmt=con.createStatement();
		String sql1 = "CREATE TABLE Product " +
                "(prodId VARCHAR(255) not NULL, " +
                " prodName VARCHAR(255), " + 
                " prodPrice FLOAT, " + 
                " PRIMARY KEY ( prodId ))"; 

    stmt.executeUpdate(sql1);
      System.out.println("Created Product table in given database...");   
      
      String sql2 = "CREATE TABLE Depot " +
              "(depId VARCHAR(255) not NULL, " +
              " depAddr VARCHAR(255), " + 
              " depVolume FLOAT, " + 
              " PRIMARY KEY ( depId ))"; 

    stmt.executeUpdate(sql2);
    System.out.println("Created Depot table in given database...");
    
    String sql3 = "CREATE TABLE Stock " +
            "(prodId VARCHAR(255) not NULL, " +
            " depId VARCHAR(255), " + 
            " qty FLOAT, " + 
            " PRIMARY KEY ( prodId,depId ),"+
            "FOREIGN KEY (prodId) REFERENCES Product(prodId) ON UPDATE CASCADE,"+
            "FOREIGN KEY (depId) REFERENCES Depot(depId))";

  stmt.executeUpdate(sql3);
  System.out.println("Created Stock table in given database...");   
  
  String statement1 = "INSERT INTO Product VALUES ('p1', 'tape', 2.5),('p2', 'tv', 250),('p3', 'vcr', 80)";
  stmt.executeUpdate(statement1);
  System.out.println("Inserted values into Product table...");
  
  String statement2 = "INSERT INTO Depot VALUES ('d1', 'New York', 9000),('d2', 'Syracuse', 6000),('d4', 'New York', 2000)";
  stmt.executeUpdate(statement2);
  System.out.println("Inserted values into Depot table...");

  
  String statement3 = "INSERT INTO Stock VALUES ('p1', 'd1', 1000),('p1', 'd2', -100),('p1', 'd4', 1200),('p3', 'd1', 3000),('p3', 'd4', 2000),('p2', 'd4', 1500),('p2', 'd1', -400),('p2', 'd2', 2000)";
  stmt.executeUpdate(statement3);
  System.out.println("Inserted values into Stock table...");
  
  
  String statement4 = "UPDATE Product SET prodId='pp1' WHERE prodId='p1'";
  stmt.executeUpdate(statement4);
  System.out.println("Updated p1 to pp1....");
		}catch (SQLException e) {
			System.out.println("An exception was thrown");
			// For atomicity
			con.rollback();
			con.close();
			return;

  
	}
		con.commit();
		con.close();
	}
		

}
