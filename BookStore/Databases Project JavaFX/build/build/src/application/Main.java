package application;

import javafx.application.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javafx.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;



@SuppressWarnings("unused")
public class Main extends Application {
	DatabaseHandler dbmanager=new DatabaseHandler();

	/*
	 * main function
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}


	String userlogged;
	/*
	 * 
	 * 
	 *This function handles the whole login
	 * 
	 * 
	 */
	@Override
	
	
	public void start(Stage primaryStage) {
		
		try {
			primaryStage.setTitle("Online Bookstore Application");
			GridPane grid = new GridPane();
			Scene scene = new Scene(grid, 800, 640);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Text scenetitle = new Text("Enter your login credentials \n or register as a new user!");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Label userName = new Label("User Name:");
			grid.add(userName, 0, 1);

			TextField userTextField = new TextField();
			grid.add(userTextField, 1, 1);
			
			
			Label pw = new Label("Password:");
			grid.add(pw, 0, 2);

			PasswordField pwBox = new PasswordField();
			grid.add(pwBox, 1, 2);

			Button logBtn = new Button("Sign in");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_LEFT);
			hbBtn.getChildren().add(logBtn);
			grid.add(hbBtn, 0, 4);
			
			Button regBtn = new Button("Register");
			HBox hbBtn2 = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(regBtn);
			grid.add(hbBtn2, 0, 5);

			final Text actiontarget = new Text();
			grid.add(actiontarget, 0, 6);

			/*
			 * 
			 * Login Button Handler here
			 * 
			 * 
			 */
			logBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					 userlogged = userTextField.getText();
					boolean exists=false;
					try {
						exists=dbmanager.login(userTextField.getText(), pwBox.getText());
					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					}
					actiontarget.setFill(Color.FIREBRICK);
					if(exists==true){
						//actiontarget.setText("User successfully logged in!");
						userTextField.clear();
						pwBox.clear();
						secondView(primaryStage);
						primaryStage.close();
					}
					else
						actiontarget.setText("The user doesn't exist in the database, please register.");
				}
			});


			/*
			 * 
			 * Register Button Handler here
			 *     
			 */
			regBtn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					startRegister(primaryStage);
					primaryStage.close();
				}
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This function is responsible for the register scene.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void startRegister(Stage stage){
		Stage regStage = new Stage();
		regStage.setTitle("Book Store");
		GridPane grid = new GridPane();
		Scene regScene=new Scene(grid, 800, 640);
		regScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Enter your login credentials \n or register as a new user!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label firstName=new Label("First name:");
		grid.add(firstName, 0, 1);
		TextField firstNameField=new TextField();
		grid.add(firstNameField, 1, 1);

		Label lastName=new Label("Last name:");
		grid.add(lastName, 0, 2);
		TextField lastNameField=new TextField();
		grid.add(lastNameField, 1, 2);

		Label address=new Label("Street Address:");
		grid.add(address, 0, 3);
		TextField addressField=new TextField();
		grid.add(addressField, 1, 3);

		Label city=new Label("City:");
		grid.add(city, 0, 4);
		TextField cityField=new TextField();
		grid.add(cityField, 1, 4);

		Label state=new Label("State:");
		grid.add(state, 0, 5);
		TextField stateField=new TextField();
		grid.add(stateField, 1, 5);

		Label zip=new Label("ZIP code:");
		grid.add(zip, 0, 6);
		TextField zipField=new TextField();
		grid.add(zipField, 1, 6);

		Label phone=new Label("Phone No.:");
		grid.add(phone, 0, 7);
		TextField phoneField=new TextField();
		grid.add(phoneField, 1, 7);

		Label email=new Label("E-mail: ");
		grid.add(email, 0, 8);
		TextField emailField=new TextField();
		grid.add(emailField, 1, 8);

		Label userName = new Label("Username:");
		grid.add(userName, 0, 9);
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 9);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 10);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 10);



		Label cChoice = new Label("Do you wish to enter a credit card?");
		grid.add(cChoice, 0, 11);


		ObservableList<String> cResponse = 
				FXCollections.observableArrayList(
						"Yes.",
						"No."
						);
		final ComboBox comboBox = new ComboBox(cResponse);
		grid.add(comboBox, 0, 12);

		ObservableList<String> cType=
				FXCollections.observableArrayList(
						"RuPay",
						"Visa"
						);
		final ComboBox comboBox2=new ComboBox(cType);
		grid.add(comboBox2, 0, 13);

		Label cNumber=new Label("Enter your credit card number:");
		grid.add(cNumber, 0, 14);
		TextField cNumberField=new TextField();
		grid.add(cNumberField, 1, 14);

		Button regBtn = new Button("Register");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(regBtn);
		grid.add(hbBtn, 0, 15);

		Button backBtn=new Button("Back");
		hbBtn.getChildren().add(backBtn);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 16);

		regBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				boolean registered=false;
				boolean card=false;
				String cardResponse=comboBox.getValue().toString();
				String cardName=comboBox2.getValue().toString();
				String cNumberString=cNumberField.getText();

				if(cardResponse=="No."){
					cardName=null;
					cNumberString=null;
					try {
						registered=dbmanager.registerMember(firstNameField.getText(), lastNameField.getText()
								, addressField.getText(), cityField.getText(), stateField.getText(),
								zipField.getText(), phoneField.getText(), emailField.getText(), userTextField.getText(), 
								pwBox.getText(),cardName, cNumberString);
						actiontarget.setFill(Color.FIREBRICK);
						actiontarget.setText("User successfully registered, Press the back button to go back to the login screen.");

					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				else				
					try {
						if(cardResponse=="Yes."){
							if(cNumberString.length()!=16){
								actiontarget.setFill(Color.FIREBRICK);
								actiontarget.setText("Enter a valid credit card number of 16 digits.");

							}
							else{
								registered=dbmanager.registerMember(firstNameField.getText(), lastNameField.getText()
										, addressField.getText(), cityField.getText(), stateField.getText(),
										zipField.getText(), phoneField.getText(), emailField.getText(), userTextField.getText(), 
										pwBox.getText(),cardName="non", cNumberString);
								actiontarget.setFill(Color.FIREBRICK);
								actiontarget.setText("User successfully registered, Press the back button to go back to the login screen.");
							}
						}
					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					}

			}
		});

		backBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				regStage.close();
				stage.show();
			}
		});

		regStage.setScene(regScene);
		regStage.show();
	}

	public void secondView(Stage stage){
		Stage secondStage = new Stage();
		secondStage.setTitle("Book Store");
		GridPane grid = new GridPane();
		Scene secondScene=new Scene(grid, 800, 640);
		secondScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Welcome! Please choose what you'd like to do today: ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);



		Button oneBtn=new Button("Browse by subject");
		Button twoBtn= new Button("Search by Author/Title");
		Button threeBtn=new Button("View/Edit shopping Cart");
		Button fourBtn=new Button("View check out Status");
		Button sixBtn=new Button("One click check out");
		Button eightBtn=new Button("Logout");



		BorderPane border = new BorderPane();
		border.setPadding(new Insets(20, 0, 20, 20));

		oneBtn.setMaxWidth(Double.MAX_VALUE);
		twoBtn.setMaxWidth(Double.MAX_VALUE);
		threeBtn.setMaxWidth(Double.MAX_VALUE);
		fourBtn.setMaxWidth(Double.MAX_VALUE);
		sixBtn.setMaxWidth(Double.MAX_VALUE);
		eightBtn.setMaxWidth(Double.MAX_VALUE);

		VBox vbButtons = new VBox();
		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20)); 
		vbButtons.getChildren().addAll(oneBtn, twoBtn, threeBtn, fourBtn, sixBtn, eightBtn);
		grid.addColumn(1, vbButtons);


		/*
		 * Those are the collection of functions that handle all the eight buttons.
		 */

		//The browse by subject button

		oneBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				browseBySubject(secondStage);
				secondStage.close();
			}
		});

		//The search button.

		twoBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				searchBy(secondStage);
				secondStage.close();
			}
		});

		//The view/edit shopping cart button.

		threeBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				editCart(secondStage);
				secondStage.close();

			}
		});

		//The check out status button.

		fourBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				checkStatus(secondStage);
				secondStage.close();

			}
		});

		//The one click check out button.

		sixBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				oneClickCheck(secondStage);
				secondStage.close();
			}
		});

		//The logout Button

		eightBtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				secondStage.close();
				stage.show();
			}
		});

		secondStage.setScene(secondScene);
		secondStage.show();

	}



	/*
	 * This function handles the browse by subject option.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void browseBySubject(Stage stage){

		Stage browseStage = new Stage();
		browseStage.setTitle("Browse by subject");
		GridPane grid = new GridPane();
		Scene browseScene=new Scene(grid, 800, 800);
		browseScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Choose the subject you'd like to browse using the drop down menu\n and then click on the browse button: ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);


		ObservableList<String> subjects = 
				FXCollections.observableArrayList(
						"Comedy",
						"Cooking",
						"Sports",
						"Autobiography"
						);
		final ComboBox subjectBox = new ComboBox(subjects);
		grid.add(subjectBox, 0, 1);

		Text isbnNotification=new Text("Enter the ISBN of the book here you'd like to buy here: ");
		grid.add(isbnNotification, 0, 2);

		TextField isbnSlot=new TextField();
		grid.add(isbnSlot, 0, 3);

		Text quantityNotification=new Text("Enter the quantity here and press add to cart: ");
		grid.add(quantityNotification, 0, 4);
		TextField quantity=new TextField();
		grid.add(quantity, 0, 5);

		Button browse=new Button("Browse");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(browse);

		Button goBack=new Button("Back");
		hbBtn.getChildren().add(goBack);

		Button addCart=new Button("Add to cart");
		hbBtn.getChildren().add(addCart);
		grid.add(hbBtn, 0, 6);


		Text addedToCart=new Text();
		grid.add(addedToCart, 1, 6);





		//the tableview
		TableView table = new TableView();
		table.setEditable(true);



		TableColumn<rowQuery, String> isbnCol = new TableColumn("ISBN");
		TableColumn<rowQuery, String> authorCol = new TableColumn("Author");
		TableColumn<rowQuery, String> titleCol = new TableColumn("Title");
		TableColumn<rowQuery, String> priceCol = new TableColumn("Price");
		TableColumn<rowQuery, String> subjectCol = new TableColumn("Subject");



		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		grid.add(vbox, 0, 7);
		isbnCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("isbn"));
		authorCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("author"));
		titleCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("title"));
		priceCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("price"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("subject"));
		table.getColumns().addAll(isbnCol, authorCol, titleCol, priceCol, subjectCol);

		/*
		 *temp fix, resort to if all fails.
		 * 
  		final ListView<String> isbnView = new ListView();
		final ListView<String> authorView = new ListView();
		final ListView<String> titleView = new ListView();
		final ListView<String> priceView = new ListView();
	    final ListView<String> subjectView = new ListView();
	    final VBox vbox=new VBox();
		vbox.setSpacing(5);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(isbnView, authorView, titleView, priceView, subjectView);
		grid.add(vbox, 0, 5);
		 */




		browse.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				String subjectChoice=subjectBox.getValue().toString();
				table.getItems().clear();
				/*			
				 * 
				 * temp fix, resort to if all fails
  				ObservableList<String> isbns = FXCollections.observableArrayList();
				ObservableList<String> authors = FXCollections.observableArrayList();
				ObservableList<String> titles = FXCollections.observableArrayList();
				ObservableList<String> prices = FXCollections.observableArrayList();
			    ObservableList<String> subjects = FXCollections.observableArrayList();*/

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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

				String query=("SELECT isbn, author, title, price, subject FROM books b WHERE b.subject=\""+subjectChoice+"\" ORDER BY subject ASC;");

				try {
					PreparedStatement preparedSt=conn.prepareStatement(query);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						rowQuery rowdata=new rowQuery(r.getString("isbn"), r.getString("author"), r.getString("title"), r.getString("price"), r.getString("subject"));	
						table.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
					System.exit(0);	
				}


				/*	
				 * 
				 * 
				 * Temp Fix, resort to if all fails			
				try{
					PreparedStatement st=conn.prepareStatement(query);
					ResultSet rs = st.executeQuery();
					 while (rs.next()) {
					      isbns.add(rs.getString("isbn"));
					      authors.add(rs.getString("author"));
					      titles.add(rs.getString("title"));
					      prices.add(rs.getString("price"));
					      subjects.add(rs.getString("subject"));
					    }

				}
				catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
					System.exit(0);	
				}
				isbnView.setItems(isbns);
				authorView.setItems(authors);
				titleView.setItems(titles);
				priceView.setItems(prices);
				subjectView.setItems(subjects);
				 */
			}
		});

		goBack.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.show();
				browseStage.close();
			}
		});
		
		addCart.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				String isbn = isbnSlot.getText();
				int qty = Integer.parseInt(quantity.getText());

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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

				String query=("INSERT INTO cart(userid, isbn, qty) VALUES (?,?,?)");

				try{
					PreparedStatement preparedSt = conn.prepareStatement(query);
					preparedSt.setString(1, userlogged);
					preparedSt.setString(2, isbnSlot.getText());
					preparedSt.setInt(3, qty);

					preparedSt.execute();
					addedToCart.setText("Added to cart successfully!");
					preparedSt.close();
					conn.close();					
				}catch (SQLException ex) {
					addedToCart.setText("You have run a duplicate entry, please select a different\n"
							+ "book/quantity");
					System.err.println("There was an error retrieving data, system exiting.");
					System.out.println(ex.getMessage());
				}


			}

		});

		browseStage.setScene(browseScene);
		browseStage.show();

	}

	/*
	 * this function handles the search by author/title option.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void searchBy(Stage stage){
		Stage searchStage=new Stage();
		searchStage.setTitle("Search by author/title");
		GridPane grid = new GridPane();
		Scene searchScene=new Scene(grid, 800, 800);
		searchScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Write the substring you'd like to search and press the button corresponding\n"
				+"to the search method you'd like to follow:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		TextField author=new TextField();
		grid.add(author, 0, 1);

		Button back=new Button("Back");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(back);
		grid.add(hbBtn, 0, 2);
		Button searchAuthor=new Button("Search By Author");
		hbBtn.getChildren().add(searchAuthor);
		Button searchTitle=new Button("Search by Title");
		hbBtn.getChildren().add(searchTitle);




		Text isbnNotification=new Text("Enter the ISBN number here \n"
				+ "and press add to cart to add the book:");
		grid.add(isbnNotification, 0, 3);
		TextField isbnSlot=new TextField();
		grid.add(isbnSlot, 0, 4);
		
		Text qtyNotification=new Text("Quantity Here:");
		grid.add(qtyNotification, 0, 5);
		TextField quantity=new TextField();
		grid.add(quantity, 0, 6);


		Button addCart=new Button("Add to cart");
		grid.add(addCart, 0, 7);
		Text addedToCart=new Text();
		grid.add(addedToCart, 1, 7);

		//the tableview
		TableView table = new TableView();
		table.setEditable(true);

		TableColumn<rowQuery, String> isbnCol = new TableColumn("ISBN");
		TableColumn<rowQuery, String> authorCol = new TableColumn("Author");
		TableColumn<rowQuery, String> titleCol = new TableColumn("Title");
		TableColumn<rowQuery, String> priceCol = new TableColumn("Price");
		TableColumn<rowQuery, String> subjectCol = new TableColumn("Subject");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		grid.add(vbox, 0, 8);
		isbnCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("isbn"));
		authorCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("author"));
		titleCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("title"));
		priceCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("price"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<rowQuery, String>("subject"));
		table.getColumns().addAll(isbnCol, authorCol, titleCol, priceCol, subjectCol);

		//search by author
		searchAuthor.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ev){
				String authorName=author.getText().toString();
				table.getItems().clear();

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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


				//Searches the books table for an author whose name matches the input string
				try {
					String query=("SELECT isbn, author, title, price, subject FROM books b WHERE author LIKE \"%"+authorName+"%"+"\" ORDER BY author ASC;");
					PreparedStatement preparedSt=conn.prepareStatement(query);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						rowQuery rowdata=new rowQuery(r.getString("isbn"), r.getString("author"), r.getString("title"), r.getString("price"), r.getString("subject"));	
						table.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
					System.exit(0);	
				}
			}
		});

		//search by title
		searchTitle.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent en){
				String titleName=author.getText().toString();
				table.getItems().clear();

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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


				//Searches the books table for an title whose name matches the input string
				try {
					String query=("SELECT isbn, author, title, price, subject FROM books b WHERE title LIKE \"%"+titleName+"%"+"\" ORDER BY title ASC;");
					PreparedStatement preparedState=conn.prepareStatement(query);
					ResultSet rs=preparedState.executeQuery();

					while(rs.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						rowQuery rowdata=new rowQuery(rs.getString("isbn"), rs.getString("author"), rs.getString("title"), rs.getString("price"), rs.getString("subject"));	
						table.getItems().addAll(rowdata);
					}
					preparedState.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
					System.exit(0);	
				}
			}
		});
		

		//Add to cart Button.
		addCart.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				String isbn = isbnSlot.getText();
				int qty=Integer.parseInt(quantity.getText());

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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

				String query=("INSERT INTO cart(userid, isbn, qty) VALUES (?,?,?)");

				try{
					PreparedStatement preparedSt = conn.prepareStatement(query);
					preparedSt.setString(1, userlogged);
					preparedSt.setString(2, isbn);
					preparedSt.setInt(3, qty);

					preparedSt.execute();
					addedToCart.setText("Added to cart successfully!");
					preparedSt.close();
					conn.close();					
				}catch (SQLException ex) {
					addedToCart.setText("You have run a duplicate entry, please select a different\n"
							+ "book/quantity");
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}


			}

		});

		//Back button.
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.show();
				searchStage.close();
			}
		});

		searchStage.setScene(searchScene);
		searchStage.show();
	}
	
	// View/Edit shopping Cart
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void editCart(Stage stage){
		Stage editCartStage=new Stage();
		editCartStage.setTitle("Edit cart");
		GridPane grid = new GridPane();
		Scene editCartScene=new Scene(grid, 800, 800);
		editCartScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Press on the show cart Button to show your current cart content\n"
				+ "or enter the ISBN of the book and press delete to remove the entry\n"
				+ "or enter the ISBN & The new quantity of the book & press update \n"
				+ "to edit your cart's quantity:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Text enterIsbn=new Text("Enter the ISBN here:");
		grid.add(enterIsbn, 0, 1);
		TextField isbnField=new TextField();
		grid.add(isbnField, 0, 2);

		Text enterQty=new Text("Enter the quantity here:");
		grid.add(enterQty, 0, 3);
		TextField qtyField=new TextField();
		grid.add(qtyField, 0, 4);

		Button showCart=new Button("View Cart");
		Button deleteCart=new Button("Delete Entry from Cart");
		Button back=new Button("Back");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().addAll(back, showCart, deleteCart);
		grid.add(hbBtn, 0, 5);

		//table view.
		//the tableview
		TableView table = new TableView();
		table.setEditable(true);

		TableColumn<cartQuery, String> isbnCol = new TableColumn("ISBN");
		TableColumn<cartQuery, String> titleCol = new TableColumn("Title");
		TableColumn<cartQuery, Integer> qtyCol = new TableColumn("Quantity");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		grid.add(vbox, 0, 6);
		isbnCol.setCellValueFactory(new PropertyValueFactory<cartQuery, String>("isbn"));
		titleCol.setCellValueFactory(new PropertyValueFactory<cartQuery, String>("Title"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<cartQuery, Integer>("Quantity"));
		table.getColumns().addAll(isbnCol, titleCol, qtyCol);

		//The delete Button
		deleteCart.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ev){
				table.getItems().clear();
				String isbn=isbnField.getText();

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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

				try {
					String query=("DELETE FROM cart WHERE isbn = "+isbn);
					PreparedStatement preparedSt=conn.prepareStatement(query);
					preparedSt.execute();
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
			}
		});

		//The show cart button
		showCart.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ev){
				table.getItems().clear();

				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException exp) {
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



				try {
					String query=("SELECT books.isbn, title, c.qty FROM cart c, books WHERE books.isbn=c.isbn;");
					PreparedStatement preparedSt=conn.prepareStatement(query);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						cartQuery rowdata=new cartQuery(r.getString("isbn"), r.getString("title"), r.getInt("qty"));	
						table.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
			}
		});

		//Back button.
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.show();
				editCartStage.close();
			}
		});

		editCartStage.setScene(editCartScene);
		editCartStage.show();
	}
	
	//View check out Status
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkStatus(Stage stage){
		Stage checkStage=new Stage();
		checkStage.setTitle("Check Status");
		GridPane grid = new GridPane();
		Scene checkScene=new Scene(grid, 800, 800);
		checkScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Press on orders orders to display your orders or Type in\n"
				+ " an order number and press get details to get its details:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 3, 1);

		Text enterOno=new Text("Enter the order number here:");
		grid.add(enterOno, 1, 1);
		TextField onoField=new TextField();
		grid.add(onoField, 1, 2);

		Button getOrders=new Button("View Orders");
		Button back=new Button("Back");
		Button getDetails=new Button("Get details");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().addAll(back, getOrders, getDetails);
		grid.add(hbBtn, 0, 2);

		//table view.
		//the tableview on the left.
		TableView table = new TableView();
		table.setEditable(true);

		TableColumn<orderDetails, Integer> onoCol = new TableColumn("Order Number");
		TableColumn<orderDetails, String> shippCol = new TableColumn("Shipped");
		TableColumn<orderDetails, String> reciCol = new TableColumn("Received");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		grid.add(vbox, 0, 4);
		onoCol.setCellValueFactory(new PropertyValueFactory<orderDetails, Integer>("ono"));
		shippCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("shipped"));
		reciCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("received"));
		table.getColumns().addAll(onoCol, shippCol, reciCol);


		//the table view on the right
		TableView table2=new TableView();
		table2.setEditable(true);

		TableColumn<orderDetails, String> fnameCol = new TableColumn("First Name");
		TableColumn<orderDetails, String> lnameCol = new TableColumn("Last Name");
		TableColumn<orderDetails, String> addCol = new TableColumn("Address");
		TableColumn<orderDetails, String> cityCol = new TableColumn("City");
		TableColumn<orderDetails, String> stateCol = new TableColumn("State");
		TableColumn<orderDetails, String> zipCol = new TableColumn("Zip");

		final VBox vbox2 = new VBox();
		vbox2.setSpacing(5);
		vbox2.setPadding(new Insets(10, 0, 0, 10));
		vbox2.setAlignment(Pos.TOP_RIGHT);
		vbox2.getChildren().addAll(table2);
		grid.add(vbox2, 2, 4);

		fnameCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("lname"));
		addCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("address"));
		cityCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("city"));
		stateCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("state"));
		zipCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("zip"));

		table2.getColumns().addAll(fnameCol, lnameCol, addCol, cityCol, stateCol, zipCol);


		getOrders.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				table.getItems().clear();
				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
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
				
				String query=("SELECT ono, shipped, received FROM orders o WHERE o.userid= ? ");
				try {
					PreparedStatement preparedSt=conn.prepareStatement(query);
					preparedSt.setString(1, userlogged);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						orderDetails rowdata=new orderDetails(r.getInt("ono"), r.getString("shipped"), r.getString("received"));	
						table.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
			}
		});
		
		getDetails.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				table2.getItems().clear();
				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
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
				
				String query=("SELECT fname, lname, address, city, state, zip FROM members WHERE userid= ? ");
				try {
					PreparedStatement preparedSt=conn.prepareStatement(query);
					preparedSt.setString(1, userlogged);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						orderDetails rowdata=new orderDetails(r.getString("fname"), r.getString("lname"),r.getString("address"),r.getString("city"),
								r.getString("state"),r.getString("zip"));
						table2.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
			}
		});


		//Back button.
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.show();
				checkStage.close();
			}
		});

		checkStage.setScene(checkScene);
		checkStage.show();
	}

	// One click check out
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void oneClickCheck(Stage stage){
		Stage ocStage=new Stage();
		ocStage.setTitle("One Click Checkout");
		GridPane grid = new GridPane();
		Scene checkScene=new Scene(grid, 800, 800);
		checkScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Congratulations on your purchase!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 3, 1);
		
		Button oneClick=new Button("One Click Checkout");
		Button back=new Button("Back");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().addAll(oneClick, back);
		grid.add(hbBtn, 0, 1);
		
		Text gratz=new Text();
		grid.add(gratz, 0, 2);
		Text totalText=new Text();
		grid.add(totalText, 0, 3);
		
		TableView table=new TableView();
		table.setEditable(true);
		
		TableColumn<cartQuery, String> isbnCol = new TableColumn("ISBN");
		TableColumn<cartQuery, String> titleCol = new TableColumn("Title");
		TableColumn<cartQuery, Integer> qtyCol = new TableColumn("Quantity");
		TableColumn<cartQuery, Float> priceCol=new TableColumn("Price");

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
		grid.add(vbox, 0, 4);
		isbnCol.setCellValueFactory(new PropertyValueFactory<cartQuery, String>("isbn"));
		titleCol.setCellValueFactory(new PropertyValueFactory<cartQuery, String>("Title"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<cartQuery, Integer>("Quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<cartQuery, Float>("Price"));
		table.getColumns().addAll(isbnCol, titleCol, qtyCol, priceCol);
		

		//the table view on the right
		TableView table2=new TableView();
		table2.setEditable(true);

		TableColumn<orderDetails, String> fnameCol = new TableColumn("First Name");
		TableColumn<orderDetails, String> lnameCol = new TableColumn("Last Name");
		TableColumn<orderDetails, String> addCol = new TableColumn("Address");
		TableColumn<orderDetails, String> cityCol = new TableColumn("City");
		TableColumn<orderDetails, String> stateCol = new TableColumn("State");
		TableColumn<orderDetails, String> zipCol = new TableColumn("Zip");

		final VBox vbox2 = new VBox();
		vbox2.setSpacing(5);
		vbox2.setPadding(new Insets(10, 0, 0, 10));
		vbox2.setAlignment(Pos.TOP_RIGHT);
		vbox2.getChildren().addAll(table2);
		grid.add(vbox2, 2, 4);

		fnameCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("fname"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("lname"));
		addCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("address"));
		cityCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("city"));
		stateCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("state"));
		zipCol.setCellValueFactory(new PropertyValueFactory<orderDetails, String>("zip"));

		table2.getColumns().addAll(fnameCol, lnameCol, addCol, cityCol, stateCol, zipCol);
		
		oneClick.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				table2.getItems().clear();
				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
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
				
				String query=("SELECT fname, lname, address, city, state, zip FROM members WHERE userid= ? ");

				try {
					PreparedStatement preparedSt=conn.prepareStatement(query);
					preparedSt.setString(1, userlogged);
					ResultSet r=preparedSt.executeQuery();

					while(r.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						orderDetails rowdata=new orderDetails(r.getString("fname"), r.getString("lname"),r.getString("address"),r.getString("city"),
								r.getString("state"),r.getString("zip"));
						table2.getItems().addAll(rowdata);
					}
					preparedSt.close();
					conn.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
				
				
				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
					System.out.println("Cannot find JDBC Driver");
				}
				//Connect to the database
				Connection conn2 = null;
				try {
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
				String query2=("SELECT books.isbn, title, o.price, o.qty FROM odetails o, books WHERE books.isbn=o.isbn");
				try {
					PreparedStatement preparedStat=conn2.prepareStatement(query2);
					ResultSet rs=preparedStat.executeQuery();

					while(rs.next()){
						ObservableList<String> data=FXCollections.observableArrayList();
						cartQuery rowdata=new cartQuery(rs.getString("isbn"), rs.getString("title"), rs.getFloat("price"), rs.getInt("qty"));
						table.getItems().addAll(rowdata);
					}
					preparedStat.close();
					conn2.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
				
				// Load JDBC Driver
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException ex) {
					System.out.println("Cannot find JDBC Driver");
				}
				//Connect to the database
				Connection conn3 = null;
				try {
					conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","12345678");
					// Do something with the Connection
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
				String query3=("SELECT price FROM odetails");
				try {
					PreparedStatement preparedStat2=conn3.prepareStatement(query2);
					ResultSet rs1=preparedStat2.executeQuery();
					float total=0;
					while(rs1.next()){
						total+=rs1.getFloat("price");
					}
					String totalMoney="The total amount due is: "+total+"$";
					totalText.setText(totalMoney);
					preparedStat2.close();
					conn3.close();
				} catch (SQLException ex) {
					System.err.println("There was an error retrieving data, system exiting.");
					System.err.println(ex.getMessage());
				}
				
			}
		});
		
		
		//Back button.
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.show();
				ocStage.close();
			}
		});
		ocStage.setScene(checkScene);
		ocStage.show();
	}
	public class orderDetails{
		private Integer ono;
		private String received;
		private String shipped;
		private String fname;
		private String lname;
		private String address;
		private String city;
		private String state;
		private Integer shipZip;
		private String zip;

		public orderDetails(Integer ono, String recieved, String shipped,
				String fname, String lname, String address, String city,
				String state, Integer shipZip, String zip) {
			this.ono = ono;
			this.received = recieved;
			this.shipped = shipped;
			this.fname = fname;
			this.lname = lname;
			this.address = address;
			this.city = city;
			this.state = state;
			this.shipZip = shipZip;
			this.zip = zip;
		}

		public orderDetails(Integer ono, String shipped, String received) {
			this.ono = ono;
			this.shipped = shipped;
			this.received = received;
		}

		public orderDetails(String fname, String lname, String address,
				String city, String state, String zip) {
			super();
			this.fname = fname;
			this.lname = lname;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}

		public Integer getOno() {
			return ono;
		}

		public void setOno(Integer ono) {
			this.ono = ono;
		}

		public String getReceived() {
			return received;
		}

		public void setReceived(String received) {
			this.received = received;
		}

		public String getShipped() {
			return shipped;
		}

		public void setShipped(String shipped) {
			this.shipped = shipped;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public Integer getShipZip() {
			return shipZip;
		}

		public void setShipZip(Integer shipZip) {
			this.shipZip = shipZip;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

	}

	public class cartQuery{
		private String isbn;
		private String title;
		private int	quantity;
		private float price;

		//@SuppressWarnings("removal")
		private cartQuery(String isbn, String title, int quantity){
			this.isbn=new String(isbn);
			this.title=new String(title);
			this.quantity= quantity;
		}

		public cartQuery(String isbn, String title, float price, int qty) {
			this.isbn = isbn;
			this.title = title;
			this.price = price;
			this.quantity = qty;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}


	}


	public class rowQuery{

		private String isbn;
		private String author;
		private String title;
		private String price;
		private String subject;

		private rowQuery(String isbn, String author, String title, String price, String subject) {
			this.isbn = new String(isbn);
			this.author = new String(author);
			this.title = new String(title);
			this.price = new String(price);
			this.subject = new String(subject);
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

	}

}


