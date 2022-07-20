package application;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import javafx.collections.ObservableList;


@SuppressWarnings("unused")
public class DatabaseHandler {

	//Function to register a new user!

	public boolean registerMember(String fname, String lname, String address, String city, String state, 
			String zip, String phone, String email, String userId, String password, String cardType, String cardNumber)
					throws IOException, SQLException{

		boolean registered=false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
		
		} catch (SQLException ex) {
		
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

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


	//Login Function.
	public boolean login(String userID, String password) throws IOException, SQLException{
		boolean exists=false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find JDBC Driver");
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
			System.out.println("Connected!");
		
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		String query=("SELECT fname, lname FROM members WHERE userid=? AND password=?");
		try {
			PreparedStatement preparedSt=conn.prepareStatement(query);
			preparedSt.setString(1, userID);
			preparedSt.setString(2, password);

			ResultSet r=preparedSt.executeQuery();
			if (r.next ()) {
				String firstname = r.getString(1);
				String lastname= r.getString(2);
				exists=true;
			}
			else{
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

}


