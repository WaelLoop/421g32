import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;

class test
{
    //primary key start for adding new customers; will be incremented of course.
    static int uniqueID = 200;
    public static void main ( String [ ] args ) throws SQLException
    {
        int sqlCode=0;      // Variable to hold SQLCODE
        String sqlState="00000";  // Variable to hold SQLSTATE

        // Register the driver.  You must register the driver before you can use it.
        try {
            DriverManager.registerDriver ( new com.ibm.db2.jcc.DB2Driver() ) ;
        } catch (Exception cnfe){
            System.out.println("Class not found");
        }

        //creating the GUI Window with our group name as a title
        JFrame frame = new JFrame("cs421g32");
        //close the frame when the the red x button (top left corner of the frame is pressed)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //the first alternative option
        JRadioButton option1 = new JRadioButton("Find the most popular book");

        //the second alternative option
        JRadioButton option2 = new JRadioButton("Look up books with a certain genre");

        //the third alternative option
        JRadioButton option3 = new JRadioButton("Verify if a copy is IN or OUT, return copy info. If out, update to IN and return copy info");

        //the fourth alternative option
        JRadioButton option4 = new JRadioButton("");

        //the fifth alternative option
        JRadioButton option5 = new JRadioButton("Add a new Customer");

        //the quit alternative option
        JRadioButton quit = new JRadioButton("Quit");

        //The button in which a user will click upon selecting one of the alternatives
        JButton buttonSubmit = new JButton("Submit");

        buttonSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //first alternative
                if(option1.isSelected()){
                    //hide the alternative options frame first
                    frame.setVisible(false);
                    //creating a new frame to display the result of the query
                    JFrame option1ResultFrame = new JFrame("Find the most popular book");
                    option1ResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    option1ResultFrame.setLayout(new GridLayout(2, 1));
                    String option1Result = "test";
                    //label informing the user with the output
                    JLabel option1ResultLabel = new JLabel(option1Result);

                    option1ResultLabel.setHorizontalAlignment(JLabel.CENTER);
                    //button to go back to the window frame with alternatives
                    JButton option1ResultButton = new JButton("Finish");

                    JPanel option1ResultPanel = new JPanel();
                    option1ResultPanel.add(option1ResultButton);

                    option1ResultFrame.getContentPane().add(option1ResultLabel);
                    option1ResultFrame.getContentPane().add(option1ResultPanel);

                    //upon clicking finish
                    option1ResultButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //hiding the result frame window and showing the frame window with the alternatives
                            option1ResultFrame.setVisible(false);
                            frame.setVisible(true);
                        }
                    });

                    //adjusting the size and setting it visible
                    option1ResultFrame.setSize(700, 250);
                    option1ResultFrame.setVisible(true);


                }
                //second alternative
                else if(option2.isSelected()){
                    //hide the alternative options frame first
                    frame.setVisible(false);
                    //creating a new frame for prompting the user to input a genre
                    JFrame option2Frame = new JFrame("Look up books with a certain genre");
                    option2Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    //label telling the user what to do
                    JLabel option2Label = new JLabel("Please input the genre");
                    option2Label.setHorizontalAlignment(JLabel.CENTER);

                    //text field and its boundaries
                    JTextField inputGenre = new JTextField();
                    inputGenre.setPreferredSize(new Dimension(100, 25));

                    //button for query selection
                    JButton option2Button = new JButton("Submit query");

                    //label panel telling the user what to do
                    JPanel option2LabelPanel = new JPanel();
                    option2LabelPanel.add(option2Label);
                    //input panel where we will get the input from the user
                    JPanel option2InputPanel = new JPanel();
                    option2InputPanel.add(inputGenre);
                    //submit panel where the user will click to initiate the query
                    JPanel option2ButtonPanel = new JPanel();
                    option2ButtonPanel.add(option2Button);

                    //Main panel that will hold all the panels created
                    JPanel option2MainPanel = new JPanel();
                    option2MainPanel.setLayout(new GridLayout(3, 1));
                    //adding all the panels to the main panel
                    option2MainPanel.add(option2LabelPanel);
                    option2MainPanel.add(option2InputPanel);
                    option2MainPanel.add(option2ButtonPanel);

                    //add the panel to the window frame
                    option2Frame.getContentPane().add(option2MainPanel);

                    //adjusting the size and setting it visible
                    option2Frame.setSize(700, 250);
                    option2Frame.setVisible(true);

                    option2Button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
                            //hiding the previous frame
                            option2Frame.setVisible(false);
                            //the result of the query window frame
                            JFrame option2ResultFrame = new JFrame("Result");
                            option2ResultFrame.setLayout(new GridLayout(0, 1));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            //get the input from the user
                            String genre = inputGenre.getText();
                            try{
                                results = findByGenre(genre);
                            } catch (java.sql.SQLException sqle) {
                                System.out.println("SQLEXCEPTION: " + sqle);
                            }

                            String[][] resultsNames = new String[results.size()][1];
                            String[] col = {"Book Name"};

                            for(int i=0;i<results.size();i++){
                                //storing the records into an array
                                resultsNames[i][0] = (results.get(i).get(1));
                            }

                            //create a table with all the results and make it scrollable
                            JTable tableResults = new JTable(resultsNames,col);
                            JScrollPane scrollPane = new JScrollPane(tableResults);
                            tableResults.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


                            JButton option2ResultButton = new JButton("Finish");
                            JPanel option2ResultPanel = new JPanel();
                            option2ResultPanel.add(option2ResultButton);
                            //add the panel and the scroll pane to the window frame
                            option2ResultFrame.getContentPane().add(scrollPane);
                            option2ResultFrame.getContentPane().add(option2ResultPanel);
                            option2ResultButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //hiding the result frame window and showing the frame window with the alternatives
                                    option2ResultFrame.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });

                            //adjusting the size and setting it visible
                            option2ResultFrame.setSize(700, 250);
                            option2ResultFrame.setVisible(true);
                        }
                    });

                }
                //third alternative
                else if(option3.isSelected()){
                    //hide the alternative options frame first
                    frame.setVisible(false);
                    //creating a new frame for prompting the user to input a genre
                    JFrame option3Frame = new JFrame("Verify if a copy is IN or OUT, return copy info. If out, update to IN and return copy info");
                    option3Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    //label telling the user what to do
                    JLabel option3Label = new JLabel("Please input reservation id");
                    option3Label.setHorizontalAlignment(JLabel.CENTER);

                    //text field and its boundaries
                    JTextField inputID = new JTextField();
                    inputID.setPreferredSize(new Dimension(50, 25));

                    //button for query selection
                    JButton option3Button = new JButton("Submit query");

                    //label panel telling the user what to do
                    JPanel option3LabelPanel = new JPanel();
                    option3LabelPanel.add(option3Label);
                    //input panel where we will get the input from the user
                    JPanel option3InputPanel = new JPanel();
                    option3InputPanel.add(inputID);
                    //submit panel where the user will click to initiate the query
                    JPanel option3ButtonPanel = new JPanel();
                    option3ButtonPanel.add(option3Button);

                    //Main panel that will hold all the panels created
                    JPanel option3MainPanel = new JPanel();
                    option3MainPanel.setLayout(new GridLayout(3, 1));
                    //adding all the panels to the main panel
                    option3MainPanel.add(option3LabelPanel);
                    option3MainPanel.add(option3InputPanel);
                    option3MainPanel.add(option3ButtonPanel);

                    //add the panel to the window frame
                    option3Frame.getContentPane().add(option3MainPanel);

                    //adjusting the size and setting it visible
                    option3Frame.setSize(700, 250);
                    option3Frame.setVisible(true);

                    option3Button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //hiding the previous frame
                            option3Frame.setVisible(false);
                            //the result of the query window frame
                            JFrame option3ResultFrame = new JFrame("Result");
                            option3ResultFrame.setLayout(new GridLayout(2, 1));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            //get the input from the user
                            String ID = inputID.getText();



                            //a label with records of the query
                            JLabel option3ResultLabel = new JLabel("");
                            option3ResultLabel.setHorizontalAlignment(JLabel.CENTER);

                            JButton option3ResultButton = new JButton("Finish");
                            JPanel option3ResultPanel = new JPanel();
                            option3ResultPanel.add(option3ResultButton);
                            //add the label to the window frame
                            option3ResultFrame.getContentPane().add(option3ResultLabel);
                            option3ResultFrame.getContentPane().add(option3ResultPanel);

                            option3ResultButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //hiding the result frame window and showing the frame window with the alternatives
                                    option3ResultFrame.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });

                            //adjusting the size and setting it visible
                            option3ResultFrame.setSize(700, 250);
                            option3ResultFrame.setVisible(true);
                        }
                    });
                }
                //fourth alternative
                else if(option4.isSelected()){
                    //hide the alternative options frame first
                    frame.setVisible(false);
                    //creating a new frame for prompting the user to input a genre
                    JFrame option4Frame = new JFrame("Verify if a copy is IN or OUT, return copy info. If out, update to IN and return copy info");
                    option4Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    //label telling the user what to do
                    JLabel option4Label = new JLabel("Please input reservation id");
                    option4Label.setHorizontalAlignment(JLabel.CENTER);

                    //text field and its boundaries
                    JTextField inputID = new JTextField();
                    inputID.setPreferredSize(new Dimension(50, 25));

                    //button for query selection
                    JButton option4Button = new JButton("Submit query");

                    //label panel telling the user what to do
                    JPanel option4LabelPanel = new JPanel();
                    option4LabelPanel.add(option4Label);
                    //input panel where we will get the input from the user
                    JPanel option4InputPanel = new JPanel();
                    option4InputPanel.add(inputID);
                    //submit panel where the user will click to initiate the query
                    JPanel option4ButtonPanel = new JPanel();
                    option4ButtonPanel.add(option4Button);

                    //Main panel that will hold all the panels created
                    JPanel option4MainPanel = new JPanel();
                    option4MainPanel.setLayout(new GridLayout(3, 1));
                    //adding all the panels to the main panel
                    option4MainPanel.add(option4LabelPanel);
                    option4MainPanel.add(option4InputPanel);
                    option4MainPanel.add(option4ButtonPanel);

                    //add the panel to the window frame
                    option4Frame.getContentPane().add(option4MainPanel);

                    //adjusting the size and setting it visible
                    option4Frame.setSize(700, 250);
                    option4Frame.setVisible(true);

                    option4Button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //hiding the previous frame
                            option4Frame.setVisible(false);
                            //the result of the query window frame
                            JFrame option4ResultFrame = new JFrame("Result");
                            option4ResultFrame.setLayout(new GridLayout(2, 1));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            //get the input from the user
                            String ID = inputID.getText();

							try {
								ArrayList<String> result = returnBook(ID);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							//a label with records of the query
                            JLabel option4ResultLabel = new JLabel(ID);
                            option4ResultLabel.setHorizontalAlignment(JLabel.CENTER);

                            JButton option4ResultButton = new JButton("Finish");
                            JPanel option4ResultPanel = new JPanel();
                            option4ResultPanel.add(option4ResultButton);
                            //add the label to the window frame
                            option4ResultFrame.getContentPane().add(option4ResultLabel);
                            option4ResultFrame.getContentPane().add(option4ResultPanel);

                            option4ResultButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //hiding the result frame window and showing the frame window with the alternatives
                                    option4ResultFrame.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });

                            //adjusting the size and setting it visible
                            option4ResultFrame.setSize(700, 250);
                            option4ResultFrame.setVisible(true);
                        }
                    });
                }
                //fifth alternative
                else if(option5.isSelected()){
                    //hide the alternative options frame first
                    frame.setVisible(false);
                    //creating a new frame for prompting the user to input a genre
                    JFrame option5Frame = new JFrame("Add a new customer");
                    option5Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame's layout will have as many rows but only 1 column
                    option5Frame.setLayout(new GridLayout(0,1));

                    //label telling the user what to do
                    JLabel option5LabelFN = new JLabel("Please input your first name");
                    JLabel option5LabelLN = new JLabel("Please input your last name");
                    JLabel option5LabelPN = new JLabel("Please input your phone number");
                    JLabel option5LabelEA = new JLabel("Please input your email address");
                    JLabel option5LabelPA = new JLabel("Please input your physical address");

                    //text fields and their boundaries
                    JTextField option5FN = new JTextField();
                    option5FN.setPreferredSize(new Dimension(50, 25));
                    JTextField option5LN = new JTextField();
                    option5LN.setPreferredSize(new Dimension(50, 25));
                    JTextField option5PN = new JTextField();
                    option5PN.setPreferredSize(new Dimension(50, 25));
                    JTextField option5EA = new JTextField();
                    option5EA.setPreferredSize(new Dimension(50, 25));
                    JTextField option5PA = new JTextField();
                    option5PA.setPreferredSize(new Dimension(50, 25));

                    //button for query selection
                    JButton option5Button = new JButton("Submit query");

                    //label panel telling the user what to do
                    JPanel option5LabelPanel = new JPanel();
                    //have as many rows as we want but only 1 column
                    option5LabelPanel.setLayout(new GridLayout(0,1));
                    option5LabelPanel.add(option5LabelFN);
                    option5LabelPanel.add(option5LabelLN);
                    option5LabelPanel.add(option5LabelPN);
                    option5LabelPanel.add(option5LabelEA);
                    option5LabelPanel.add(option5LabelPA);
                    //input panel where we will get the input from the user
                    JPanel option5InputPanel = new JPanel();
                    //have as many rows as we want but only 1 column
                    option5InputPanel.setLayout(new GridLayout(0,1));
                    option5InputPanel.add(option5FN);
                    option5InputPanel.add(option5LN);
                    option5InputPanel.add(option5PN);
                    option5InputPanel.add(option5EA);
                    option5InputPanel.add(option5PA);
                    //submit panel where the user will click to initiate the query
                    JPanel option5ButtonPanel = new JPanel();
                    option5ButtonPanel.add(option5Button);

                    //Main panel that will hold all the panels created
                    JPanel option5MainPanel = new JPanel();
                    option5MainPanel.setLayout(new GridLayout(0,2));
                    //adding all the panels to the main panel
                    option5MainPanel.add(option5LabelPanel);
                    option5MainPanel.add(option5InputPanel);

                    //add the panels to the window frame
                    option5Frame.getContentPane().add(option5MainPanel);
                    option5Frame.getContentPane().add(option5ButtonPanel);

                    //adjusting the size and setting it visible
                    option5Frame.setSize(500, 250);
                    option5Frame.setVisible(true);

                    option5Button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e){

                            //hiding the previous frame
                            option5Frame.setVisible(false);
                            //the result of the query window frame
                            JFrame option5ResultFrame = new JFrame("Result");
                            option5ResultFrame.setLayout(new GridLayout(2, 1));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            //getting the input from the user
                            String FN = option5FN.getText();
                            String LN = option5LN.getText();
                            String PN = option5PN.getText();
                            String EA = option5EA.getText();
                            String PA = option5PA.getText();

                            //the primary key that will be incremented as we add customers to the database

                            //if all the inputs are valid, insert in the database
                            if(validateName(FN) && validateName(LN) && validatePN(PN) && validateEA(EA) && validateName(PA)){

                                try {
                                    //Note: This url may not valid now !
                                    String url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
                                    Connection con = DriverManager.getConnection(url, "cs421g32", "32FourTimes");
                                    Statement statement = con.createStatement();
                                    String insertSQL = "INSERT INTO customers VALUES ("+uniqueID+","+ "\'"+  FN  + "\'," +
                                            "\'"+ LN +"\'," + "\'" + PN +"\'," + "\'"+  EA +"\'," + "\'"+ PA +"\', NULL)";
                                    statement.executeUpdate(insertSQL);
                                    statement.close ();
                                    con.close ();
                                    uniqueID++;
                                }
                                catch(Exception cone) {
                                    System.out.println("Error has occured: " + cone.getMessage());
                                }
                                //a label with records of the query
                                JLabel option5ResultLabel = new JLabel("Customer has been added to the database!");
                                option5ResultLabel.setHorizontalAlignment(JLabel.CENTER);
                                //button to go back to alternatives frame
                                JButton option5ResultButton = new JButton("Finish");
                                JPanel option5ResultPanel = new JPanel();
                                option5ResultPanel.add(option5ResultButton);

                                //add the label to the window frame
                                option5ResultFrame.getContentPane().add(option5ResultLabel);
                                option5ResultFrame.getContentPane().add(option5ResultPanel);

                                //return the alternative options frame window
                                option5ResultButton.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //hiding the result frame window and showing the frame window with the alternatives
                                        option5ResultFrame.setVisible(false);
                                        frame.setVisible(true);
                                    }
                                });

                                //adjusting the size and setting it visible
                                option5ResultFrame.setSize(700, 250);
                                option5ResultFrame.setVisible(true);
                            }
                            else {
                                JLabel option5ResultLabel = new JLabel("An Error has occured. Make sure you " +
                                        "input a 10 digit phone number and a email address with an @");
                                //centering the label
                                option5ResultLabel.setHorizontalAlignment(JLabel.CENTER);
                                //button to return to the main input frame
                                JButton option5ResultButton = new JButton("OK");
                                JPanel option5ResultPanel = new JPanel();
                                option5ResultPanel.add(option5ResultButton);
                                //add the label to the window frame
                                option5ResultFrame.getContentPane().add(option5ResultLabel);
                                option5ResultFrame.getContentPane().add(option5ResultPanel);

                                option5ResultButton.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //hiding the result frame window and showing the frame window with the alternatives
                                        option5ResultFrame.setVisible(false);
                                        option5Frame.setVisible(true);
                                    }
                                });

                                //adjusting the size and setting it visible
                                option5ResultFrame.setSize(700, 250);
                                option5ResultFrame.setVisible(true);
                            }
                        }
                    });
                }
                //quit
                else if(quit.isSelected()){
                    frame.setVisible(false);
                    System.exit(0);
                }

            }
        });

        //grouping the radio buttons to prevent the user from selecting more than one radio box
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);
        group.add(option5);
        group.add(quit);
        //group.add(buttonSubmit);

        //all the alternative options goes into a single panel with 6 rows and 1 column
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
        buttonPanel.add(option1);
        buttonPanel.add(option2);
        buttonPanel.add(option3);
        buttonPanel.add(option4);
        buttonPanel.add(option5);
        buttonPanel.add(quit);
        //adding the panel to the window frame
        frame.getContentPane().add(BorderLayout.WEST,buttonPanel);

        //the button has a different panel
        JPanel submitPanel = new JPanel();
        submitPanel.add(buttonSubmit);
        //positioning the submit button to be at the very bottom
        frame.getContentPane().add(BorderLayout.PAGE_END,submitPanel);

        //the size of the frame window
        frame.setSize(700, 250);
        //show the frame
        frame.setVisible(true);

    }

    static ArrayList<ArrayList<String>> updateCopyStatus(String res_id) throws SQLException {
        // Querying the book table to find the book with the genrename = input

        // This is the url you must use for DB2.
        //Note: This url may not valid now !
        String url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
        Connection con = DriverManager.getConnection (url,"cs421g32","32FourTimes") ;
        Statement statement = con.createStatement ( );

        // This is the url you must use for DB2.
        //Note: This url may not valid now !

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

        try {
            String querySQL = "SELECT BOOKS.ISBN,\n" +
                    "BOOKS.BOOK_NAME,\n" +
                    "\tBOOKS.RATING\n" +
                    "FROM BOOKS\n" +
                    "LEFT JOIN BOOKSGENRES ON BOOKS.ISBN=BOOKSGENRES.ISBN\n" +
                    "WHERE BOOKSGENRES.GENRE_NAME = \'" + res_id + "\'\n" +
                    "ORDER BY BOOKS.ISBN";

//			System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while ( rs.next ( ) ) {
                ArrayList<String> temp = new ArrayList<String>();

                String id = rs.getString (1);
                String name = rs.getString (2);
                String rating = Integer.toString(rs.getInt ( 3 )) ;

                temp.add(id);
                temp.add(name);
                temp.add(rating);

//				System.out.println ("id:  " + id);
//				System.out.println ("name:  " + name);
//				System.out.println("rating: " + rating);

                results.add(temp);
            }

            System.out.println ("DONE");
        } catch (SQLException e) {

            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println(e);
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        } finally {
            // Finally but importantly close the statement and connection
            statement.close ( ) ;
            con.close ( ) ;
        }

        return results;
    }
    static ArrayList<ArrayList<String>> findByGenre(String input) throws SQLException {
        // Querying the book table to find the book with the genrename = input

        // This is the url you must use for DB2.
        //Note: This url may not valid now !
        String url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
        Connection con = DriverManager.getConnection (url,"cs421g32","32FourTimes") ;
        Statement statement = con.createStatement ( );

        // This is the url you must use for DB2.
        //Note: This url may not valid now !

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

        try {
            String querySQL = "SELECT BOOKS.ISBN,\n" +
                    "BOOKS.BOOK_NAME,\n" +
                    "\tBOOKS.RATING\n" +
                    "FROM BOOKS\n" +
                    "LEFT JOIN BOOKSGENRES ON BOOKS.ISBN=BOOKSGENRES.ISBN\n" +
                    "WHERE BOOKSGENRES.GENRE_NAME = \'" + input + "\'\n" +
                    "ORDER BY BOOKS.ISBN";

//			System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while ( rs.next ( ) ) {
                ArrayList<String> temp = new ArrayList<String>();

                String id = rs.getString (1);
                String name = rs.getString (2);
                String rating = Integer.toString(rs.getInt ( 3 )) ;

                temp.add(id);
                temp.add(name);
                temp.add(rating);

				System.out.println ("id:  " + id);
				System.out.println ("name:  " + name);
				System.out.println("rating: " + rating);

                results.add(temp);
            }

            System.out.println ("DONE");
        } catch (SQLException e) {

            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println(e);
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        } finally {
            // Finally but importantly close the statement and connection
            statement.close ( ) ;
            con.close ( ) ;
        }

        return results;
    }


	static ArrayList<String> returnBook(String input) throws SQLException {
		// Querying the book table to find the book with the genrename = input

        // This is the url you must use for DB2.
        //Note: This url may not valid now !
        String url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
        Connection con = DriverManager.getConnection (url,"cs421g32","32FourTimes") ;
        Statement statement = con.createStatement ( );

        // This is the url you must use for DB2.
        //Note: This url may not valid now !

		ArrayList<String> temp = new ArrayList<String>();
		java.sql.ResultSet rs;

		try {
			String querySQL = "SELECT COPIES.ISBN,\n" +
					"\tCOPIES.COPY_NO,\n" +
					"\tCOPIES.LIBRARY_ID,\n" +
					"\tCOPIES.STATUS\n" +
					"FROM COPIES\n" +
					"LEFT JOIN RESERVATIONS ON RESERVATIONS.ISBN=COPIES.ISBN AND RESERVATIONS.COPY_NO=COPIES.COPY_NO\n" +
					"WHERE RESERVATIONS.RESERVATION_ID = " + input;

//			System.out.println (querySQL) ;
			rs = statement.executeQuery ( querySQL ) ;

			String isbn = "", copy_no = "", library_id = "", status = "";


            while ( rs.next ( ) ) {
				isbn = rs.getString (1);
				copy_no = Integer.toString(rs.getInt ( 2 )) ;
				library_id = Integer.toString(rs.getInt ( 3 )) ;
				status = rs.getString (4);

				temp.add(isbn);
				temp.add(copy_no);
				temp.add(library_id);
				temp.add(status);

				System.out.println ("isbn:  " + isbn);
				System.out.println ("copy_no:  " + copy_no);
				System.out.println("library_id: " + library_id);
				System.out.println("status: " + status);
			}

			statement.close();


			if (status.equals("OUT")) {

				statement = con.createStatement ( );

				querySQL = "UPDATE COPIES\n" +
						"SET STATUS= \'IN\' \n" +
						"WHERE COPY_ISBN = \'" + isbn + "\' AND COPY_NO = " + copy_no;

				int done = statement.executeUpdate(querySQL);
				if (done == 1) {
					System.out.println ("CHANGED TO IN");
				}

				statement.close ( ) ;
			}

            System.out.println ("DONE");
        } catch (SQLException e) {

            int sqlCode = e.getErrorCode(); // Get SQLCODE
            String sqlState = e.getSQLState(); // Get SQLSTATE

            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println(e);
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		} finally {
			// Finally but importantly close the statement and connection

			con.close ( ) ;
		}

		return temp;
	}

    //validate the user's input names
    public static boolean validateName(String name){
        char [] arr = name.toCharArray();
        for(char c : arr){
            if(Character.isDigit(c)) return false;
        }
        return true;
    }
    //check if the phone number has 10 digits
    public static boolean validatePN(String phoneNumber){
        if(phoneNumber.length() != 10) return false;
        else {
            char[] arr = phoneNumber.toCharArray();
            for (char c : arr) {
                if (Character.isAlphabetic(c)) return false;
            }
            return true;
        }
    }
    //check if an email address input has @
    public static boolean validateEA(String email){
        char[] arr = email.toCharArray();
        for(char c : arr){
            if( c == '@') return true;
        }
        return false;
    }
    //check if an reservation_id is only int
    public static boolean validateResID(String res_id){
        char[] arr = res_id.toCharArray();
        for(char c : arr){
            if( Character.isAlphabetic(c)) return false;
        }
        return true;
    }
}