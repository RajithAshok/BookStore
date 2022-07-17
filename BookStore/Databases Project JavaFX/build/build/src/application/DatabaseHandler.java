package application;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import javafx.collections.ObservableList;


@SuppressWarnings("unused")

//The databasehandler, it contains the functions to connect to the DB and the other functions sent
//to the server as well.
public class DatabaseHandler {

	//Function to register a new user!

	public boolean registerMember(String fname, String lname, String address, String city, String state, 
			String zip, String phone, String email, String userId, String password, String cardType, String cardNumber)
					throws IOException, SQLException{

		boolean registered=false;
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		//		System.out.println("Connected!");

		String query = " INSERT INTO members (fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedSt = conn.prepareStatement(query);
		preparedSt.setString(1, fname);
		preparedSt.setString(2, lname);
		preparedSt.setString(3, address);
		preparedSt.setString(4, city);
		preparedSt.setString(5, state);
		preparedSt.setString(6, zip);
		preparedSt.setString(7, phone);
		preparedSt.setString(8, email);
		preparedSt.setString(9, userId);
		preparedSt.setString(10, password);
		preparedSt.setString(11, cardType);
		preparedSt.setString(12, cardNumber);


		//Executing the prepared statement and sending it over, handling any errors along the way.
		try {
			preparedSt.execute();
			conn.close();
			registered=true;
			System.out.println("You have registered successfully!");
		} catch (Exception e) {
			System.err.println("Error registering user, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);
		}
		return registered;		
	}


	//This is the function that logs into the server. It's pretty much very similar to the previous
	//Register function.
	public boolean login(String userID, String password) throws IOException, SQLException{
		boolean exists=false;
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			System.out.println("Connected!");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		//Query in SQL to select the user from the database.
		String query=("SELECT fname, lname FROM members WHERE userid=? AND password=?");
		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);
			preparedSt.setString(1, userID);
			preparedSt.setString(2, password);

			//Result set to get the returned results from the server, then they're decoded
			//and assigned to strings.

			ResultSet r=preparedSt.executeQuery();
			if (r.next ()) {
				String firstname = r.getString(1);
				String lastname= r.getString(2);
				exists=true;
				//System.out.println(firstname + lastname +" is successfully logged.");
			}
			else{
				//System.out.println("User doesn't exist in the database.");
				preparedSt.close();
				conn.close();
				exists=false;
			}
		} catch (SQLException e) {
			System.err.println("Error registering user, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}
		return exists;
	}


/*	//Browse by subject. 
	//Based on user input for subject 'type'. Evaluates inside an array of strings of index [type-1]. Works similar to the previous functions.
	
	public ResultSet getBooksBySubject(String subject) {

		String title;
		String author;
		String isbn;
		String price;
		String returnedSubject;
		String selectedSubject=null;
		ResultSet r = null;


		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		String query=("SELECT isbn, author, title, price, subject FROM books WHERE subject="+selectedSubject+" ORDER BY subject ASC;");

		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);	
			//Result set to get the returned results from the server, then they're decoded
			//and assigned to strings.
			
			r=preparedSt.executeQuery();

			//Prints each tuple in the result set and consumes
			while(r.next()){
				isbn = r.getString("isbn");
				author = r.getString("author");
				title = r.getString("title");
				price = r.getString("price");
				returnedSubject=r.getString("subject");
			}
		} catch (SQLException e) {
			System.err.println("There was an error retrieving data, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);	
		}
		return r;
	}*/


	//Displays a list of orders by user.
	public void displayOrderList() {

		int orderno;
		Date shippedDate;
		Date receivedDate;

		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		//Query in SQL to select the user from the database.
		String query=("SELECT ono, shipped, received FROM orders o, members m WHERE m.userid=o.userid");
		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);

			//Result set to get the returned results from the server, then they're decoded
			//and assigned to strings.

			ResultSet r=preparedSt.executeQuery();
			System.out.println("--------------------------------------------\n");
			System.out.println("ORDER NO.\tRECEIVED DATE\tSHIPPED DATE");
			System.out.println("--------------------------------------------\n");
			while(r.next())
			{
				orderno = r.getInt("ono");
				receivedDate = r.getDate("received");
				shippedDate = r.getDate("shipped");
				System.out.println(orderno+"\t"+receivedDate+"\t"+shippedDate+"\n");
			}
			System.out.println("--------------------------------------------\n");

			preparedSt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error getting order list, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}
	}


	//When given a specific order number, displays the details of the order, including book title, quantity, total order value and more
	public void displayOrderDetails(int orderNum) {

		int isbn;
		String title;
		float price;
		int qty;
		int total;

		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		//Query in SQL to select the user from the database.
		String query=("SELECT od.isbn, title, od.price, qty FROM odetails od, books b WHERE b.isbn = od.isbn");
		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);

			//Result set to get the returned results from the server, then they're decoded
			//and assigned to strings.

			ResultSet r=preparedSt.executeQuery();
			System.out.println("--------------------------------------------\n");
			System.out.println("ISBN\tTITLE\t$\tQTY\tTOTAL");
			System.out.println("--------------------------------------------\n");
			while(r.next())
			{
				isbn = r.getInt("isbn");
				title = r.getString("title");
				price = r.getFloat("price");
				qty = r.getInt("qty");
				System.out.println(isbn+"\t\t"+title+"\t"+price+"\t"+qty+"\t"+qty*price+"\t");
			}
			System.out.println("--------------------------------------------\n");

			preparedSt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error getting order details, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}

	}

	public void oneClickCheckOut(){

		String userid=null;
		int isbn=0;
		int qty=0;
		String fname=null;
		String lname=null;
		String address=null;
		String city=null;
		String state=null;
		int zip=0;
		DatabaseHandler db = new DatabaseHandler();

		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		String query1="SELECT fname, lname, address, city, state, zip FROM members WHERE lname='ali' AND fname='korayem'";
		try{
			PreparedStatement preSt1=conn.prepareStatement(query1);
			ResultSet res=preSt1.executeQuery();
			while(res.next()){
				fname=res.getString("fname");
				lname=res.getString("lname");
				address=res.getString("address");
				city=res.getString("city");
				state=res.getString("state");
				zip=res.getInt("zip");
				preSt1.close();
			}
		}
		catch (SQLException e) {
			System.err.println("Error getting order details, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}
		System.out.println("                    Invoice for Order no.117       ");
		System.out.println("     Shipping Address                   Billing address     ");
		System.out.println("     Name:     "+fname+" "+lname+"           \tName:     "+fname+" "+lname+"           ");
		System.out.println("     Address:  "+address+"           \t\tAddress:  "+address+"     ");
		System.out.println("   \t\t"+city+"                              "+city);
		System.out.println("    \t           "+zip+"                            \t"+zip+"            ");
		db.displayOrderDetails(5566);
		String query2="INSERT INTO order (userid, ono, shipAddress, shipCity, shipState, shipZip)"+" VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement preSt=conn.prepareStatement(query2);
			preSt.setString(1, fname);
			preSt.setString(2, lname);
			preSt.setString(3, address);
			preSt.setString(4, city);
			preSt.setString(5, state);
			preSt.setInt(6, zip);
			preSt.close();
			conn.close();



		} 		catch (SQLException e) {
			System.err.println("Error getting order details, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}

	}
}


