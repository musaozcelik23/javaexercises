package demo;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


/**
 * @author 
 */
public class JDBCExample {
	//deneme
  public static void main(String[] args) {

    String jdbcUrl = "jdbc:postgresql://localhost:5432/aritma";
    String username = "postgres";
    String password = "230711";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    
    try {
      conn = DriverManager.getConnection(jdbcUrl, username, password);
      stmt = conn.createStatement();
      
      Scanner girdi = new Scanner(System.in);
      
      String a="1",b="2",c="3",d="4";
      String secim=secimekranı().toString();
      
      if(secim.equals(a)) {
      System.out.println("1-İstasyon listele");
	  System.out.println("2-Kanal listele");
	  System.out.println("3-Ölçüm değeri listele");
	  String e="1",f="2",g="3";
	  String secimno = girdi.next();
	  if(secimno.equals(e)) {
		  clearScreen();
		  
		  rs = stmt.executeQuery("SELECT * from station");
		  while(rs.next()) {
	    	  System.out.print(rs.getString(1));
	    	  System.out.println(".istasyon : "+rs.getString(2));
	      }
		  System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
		  String Ana = girdi.next();
		  String X="X";
		  
		  if (Ana.equals(X)) {
			  clearScreen();
			  secimekranı();
		  }
		  
	  }
	  else if(secimno.equals(f)) {
		  clearScreen();
		  
		  
		  while(rs.next()) {
		  rs = stmt.executeQuery("SELECT c.id,c.name,s.name from channel c inner join station s on s.id=c.station_id ");
		  
		  
			  System.out.print(rs.getString(3)+" istasyonu - ");
			  System.out.println(rs.getString(2)+" kanalı");
			  
		  
	  };
		  
		  System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
		  String Ana = girdi.next();
		  String X="X";
		  
		  if (Ana.equals(X)) {
			  clearScreen();
			  secimekranı();
		  }
		  
	  }
	  else if(secimno.equals(g)) {
		  clearScreen();
		  
		  rs = stmt.executeQuery("SELECT c.name,s.name,m.value,m.measure_date from channel c inner join station s on s.id=c.station_id inner join measure m on m.channel_id=c.id ");
		  while(rs.next()) {
			  
			  
				  System.out.print(rs.getString(2)+" istasyonu - ");
				  System.out.print(rs.getString(1)+" kanalının ");
				  System.out.print(rs.getString(4)+" tarihli degeri ");
				  System.out.println(rs.getString(3));

				  
			  
		  };
		  System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
		  String Ana = girdi.next();
		  String X="X";
		  
		  if (Ana.equals(X)) {
			  clearScreen();
			  secimekranı();
		  }
		  
	  }
	  
      }
      else if (secim.equals(b)) {
    	  clearScreen();
		  System.out.print("istasyon adını giriniz	  : ");
	      String adi = girdi.next();
		  stmt.executeUpdate("INSERT INTO station (name) VALUES ('"+adi+"')");
	      rs = stmt.executeQuery("SELECT * from station where name='"+adi+"'");
	      System.out.println("kayıt edildi");
	      System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
	      String Ana = girdi.next();
	      String X="X";

	      if (Ana.equals(X)) {
	    	  secimekranı();
	      }
	      else
	      {
			  System.exit(0);
	      }
	      
	        }
	          
	  
        

     else if (secim.equals(c)) {
    	 clearScreen();
		  System.out.println("Hangi İstasyona Eklemek İstiyorsunuz.Lütfen Numarasını giriniz.");
		  while(rs.next()) {
	    	  rs = stmt.executeQuery("SELECT * from station ");
	    	  
	    	  System.out.print(rs.getString(1));
	    	  System.out.println(".istasyon : "+rs.getString(2));
	    	  
	      };
	      int istasyon = girdi.nextInt();
	      System.out.print("Kanal adını giriniz	  : ");
	      String adi = girdi.next();
		  stmt.executeUpdate("INSERT INTO channel (name,station_id) VALUES ('"+adi+"',"+istasyon+")");
	      System.out.println("kayıt edildi");

		  System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
	      String Ana = girdi.next();
	      String X="X";

	      if (Ana.equals(X)) {
	    	  secimekranı();
	      }
	      else
	      {
			  System.exit(0);
	      }
	      
	        }
	          
	  
      

	  else if (secim.equals(d)) {
		  clearScreen();
		  System.out.println("Hangi İstasyona Ölçüm Eklemek İstiyorsunuz.Lütfen Numarasını giriniz.");
		  while(rs.next()) {
	    	  rs = stmt.executeQuery("SELECT * from station ");
	    	 
	    	  System.out.print(rs.getString(1));
	    	  System.out.println(".istasyon : "+rs.getString(2));
	    	  
	      };
	      int istasyon = girdi.nextInt();
	      System.out.println("Lütfen Ölçüm Girilecek Kanal Numarasını Seçiniz	  : ");
	      while(rs.next()) {
			  rs = stmt.executeQuery("SELECT c.name,s.name,c.id from channel c inner join station s on s.id=c.station_id ");
	    	  
	    		  System.out.print(rs.getString(3)+"-");
		    	  System.out.print(rs.getString(2)+" istayonu ");
		    	  System.out.println(rs.getString(1)+" kanalı");

	    	  
	    	  
	      };
	      int kanal = girdi.nextInt();
	      System.out.print("Lütfen ölçüm değerini giriniz	  : ");
	      double deger = girdi.nextDouble();
		  stmt.executeUpdate("INSERT INTO measure (measure_date,value,channel_id) VALUES ( now(),"+deger+","+kanal+")");
	      System.out.println("kayıt edildi");

		  System.out.println("Ana Menüye Dönmek İçin X Tuşuna Basınız");
	      String Ana = girdi.next();
	      String X="X";

	      if (Ana.equals(X)) {
	    	  secimekranı();
	      }
	      else
	      {
			  System.exit(0);
	      }
        }
          
  
    

}
   catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      
    } finally {
      try {

        if (stmt != null) {
          stmt.close();
        }
        if (rs != null) {
          rs.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
  public static String secimekranı() {
	  System.out.println("Ana Menü");
	    System.out.println("1-Listele");
	    System.out.println("2-İstasyon Ekle");
	    System.out.println("3-Kanal Ekle");
	    System.out.println("4-Ölçüm Ekle");
	    System.out.println("Lütfen Seçim Yapınız");
	    Scanner girdi = new Scanner(System.in);
	      String secim = girdi.next();
	      
	    return secim;
	    
  }
  public static void clearScreen() {  
	    //System.out.print("\033[H\033[2J");  
	    System.out.flush(); 
	    }
}