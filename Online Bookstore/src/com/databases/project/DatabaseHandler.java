package com.databases.project;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.io.*;


@SuppressWarnings("unused")
public class DatabaseHandler {
	public	Scanner inBuff=new Scanner(System.in);



	//Function to register a new user!

	public void registerMember() throws IOException, SQLException{
		String fname="", lname="", address="", city="", state="", email="", userid="", password="", ccType="", ccNum="";
		int zip=0;
		String phone="";	
		String cardConf="";
		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		//Connect to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=12341234");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		//		System.out.println("Connected!");

		//This is where the input is processed.
		System.out.println("Enter your First Name:");
		fname = inBuff.nextLine();
		System.out.println("Enter your Last Name: ");
		lname = inBuff.nextLine();
		System.out.println("Enter your street address: ");
		address = inBuff.nextLine();
		System.out.println("Enter your city: ");
		city = inBuff.nextLine();
		System.out.println("Enter your state: ");
		state = inBuff.nextLine();
		System.out.println("Enter your zip: ");
		zip = inBuff.nextInt();
		inBuff.nextLine();
		System.out.println("Enter your phone number: ");
		phone = inBuff.nextLine();
		System.out.println("Enter your email address: ");
		email = inBuff.nextLine();
		System.out.println("Enter your userID: ");
		userid = inBuff.nextLine();
		System.out.println("Enter your password: ");
		password = inBuff.nextLine();
		System.out.println("Do you wish to store credit card information(yes/no): ");
		cardConf = inBuff.nextLine();

/*		if(cardConf=="yes\n"){
			System.out.println("Enter type of Credit Card(amex/visa): ");
			ccType = inBuff.nextLine();
			System.out.println("Enter Credit Card Number: ");
			ccNum = inBuff.nextLine();
			while(ccNum.length()!=16){
				System.out.println("Invalid Entry! please enter a valid number of 16 digits.");
				ccNum = inBuff.nextLine();
			}
		}
	*/
		inBuff.close();

		String query = " INSERT INTO members (fname, lname, address, city, state, zip, phone, email, userid, password, creditcardtype, creditcardnumber)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedSt = conn.prepareStatement(query);
		preparedSt.setString(1, fname);
		preparedSt.setString(2, lname);
		preparedSt.setString(3, address);
		preparedSt.setString(4, city);
		preparedSt.setString(5, state);
		preparedSt.setInt(6, zip);
		preparedSt.setString(7, phone);
		preparedSt.setString(8, email);
		preparedSt.setString(9, userid);
		preparedSt.setString(10, password);
		preparedSt.setString(11, ccType);
		preparedSt.setString(12, ccNum);

		try {
			preparedSt.execute();
			conn.close();
			System.out.println("You have registered successfully!");
		} catch (Exception e) {
			System.err.println("Error registering user, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}



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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=12341234");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		String query=("SELECT fname, password FROM members WHERE userid=? AND password=?");
		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);
			preparedSt.setString(1, userID);
			preparedSt.setString(2, password);
			ResultSet r=preparedSt.executeQuery();
			if (r.next ()) {
				String firstname = r.getString(1);
				String lastname= r.getString(2);
				exists=true;
				System.out.println(firstname + lastname +" is successfully logged.");
			}
			else
				System.out.println("User doesn't exist in the database.");
			preparedSt.close();
			conn.close();
			exists=false;
		} catch (SQLException e) {
			System.err.println("Error registering user, system exiting.");
			System.err.println(e.getMessage());
			System.exit(0);		
		}
		return exists;
	}

}

