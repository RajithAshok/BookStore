package com.databases.project;
import com.databases.project.DatabaseHandler;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;


public class UserBookstore {
	public static Scanner scan=new Scanner(System.in);
	static boolean exists=false;
	final static String welcome="**********************************************************************\n"+
			"***                                                                ***\n"+
			"***             Welcome to the Online Book Store                   ***\n"+
			"***                                                                ***\n"+ 
			"**********************************************************************\n";
	final static String firstPage="                         1. Member Login\n"+
			"                         2. New Member Registration\n"+
			"                         q. Quit\n";
	final static String secondPage="                     1. Browse by Subject\n"+
			"                     2. Search by Author/Title/Subject\n"+
			"                     3. View/Edit Shopping Cart\n"+
			"                     4. Check Order Status\n"+
			"                     5. Check Out\n"+
			"                     6. One Click Check Out\n"+
			"                     7. View/Edit Personal Information\n"+
			"				     8. Logout\n";




	public static void main(String [] args) throws IOException, SQLException{

		DatabaseHandler dbMan = new DatabaseHandler();
		String y = welcomeScreen();
		System.out.println("Welcome to our online bookstore!");
		System.out.print("You've selected: ");


		switch (y){
		case "1":
			System.out.println(" Member Login.\n"+ "Please enter your credentials: ");
			System.out.println("Username: ");
			scan.nextLine();
			String userID=scan.nextLine();
			System.out.println("Password: ");
			String password=scan.nextLine();
			exists=dbMan.login(userID, password);
			if(exists==true){
				System.out.println(welcome);
				System.out.println(secondPage);
				System.out.println("Type in your option:");
				Scanner logOp=new Scanner(System.in);
				char op=logOp.next().charAt(0);
				logOp.close();
				if(op=='8')
					System.out.println("You have successfully logged out!");
				welcomeScreen();
			}
			else
				welcomeScreen();
			break;


		case"2":
			System.out.println(" New Member Registeration:");
			System.out.println("Please enter the registeration info, each in its corresponding field.");
			dbMan.registerMember();
			
			//to be implemented in the next phase.
			/*System.out.println("Do you wish to perform another operation?(y/n)");
			Scanner in=new Scanner(System.in);
			char input;
			input=in.next().charAt(0);
			in.close();
			switch (input){
			case 'y':
				welcomeScreen();
			case 'n':
				System.out.println("Program terminated, Thanks for visiting!");
				System.exit(0);
			}*/
			break;


		case"q":
			System.out.println("Quit.\n"+"Program terminated, Thanks for visiting!");
			System.exit(0);
			break;
		}

	}

	public static String welcomeScreen(){
		System.out.println(welcome);
		System.out.println(firstPage+"\n");
		System.out.println("Type in your option: ");
		String x;		
		x=scan.nextLine();
		return x;		
	}


}
