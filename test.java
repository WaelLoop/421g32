import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

class test
{
	private static String tableName;
	private static int sqlCode;
	private static String sqlState;

	// This is the url you must use for DB2.
	//Note: This url may not valid now !
	private static String url;
	private static Connection con;
	private static Statement statement;

    public static void main ( String [ ] args ) throws SQLException
    {
	// Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
	tableName = "";
	sqlCode=0;
	sqlState="00000";

	//TODO: i dont know if we need this
//	if ( args.length > 0 ){
//	    tableName += args [ 0 ] ;
//	}
//	else {
//	    tableName += "example3.tbl";
//	}

	// Register the driver.  You must register the driver before you can use it.
        try {
	    DriverManager.registerDriver ( new com.ibm.db2.jcc.DB2Driver() ) ;
	} catch (Exception cnfe){
	    System.out.println("Class not found: " + cnfe);
        }

	// This is the url you must use for DB2.
	//Note: This url may not valid now !
	url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
	con = DriverManager.getConnection (url,"cs421g32","32FourTimes") ;
	statement = con.createStatement ( );
	
	//creating the GUI Window with our group name as a title
	JFrame frame = new JFrame("cs421g32");
	//close the frame when the the red x button (top left corner of the frame is pressed)
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//the first alternative option
	JRadioButton option1 = new JRadioButton("Find the most popular book");
	
	//the second alternative option
	JRadioButton option2 = new JRadioButton("Look up books with a certain genre");
	
	//the third alternative option
	JRadioButton option3 = new JRadioButton("Select 3 from ...");
		
	//the fourth alternative option
	JRadioButton option4 = new JRadioButton("Select 4 from ...");
		
	//the fifth alternative option
	JRadioButton option5 = new JRadioButton("Select 5 from ...");
	
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
				option1ResultFrame.setSize(500, 250);
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
				option2Frame.setSize(500, 250);
				option2Frame.setVisible(true);
				
				option2Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//hiding the previous frame
						option2Frame.setVisible(false);
						//the result of the query window frame
						JFrame option2ResultFrame = new JFrame("Result");
						option2ResultFrame.setLayout(new GridLayout(2, 1));
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						//get the input from the user
						String genre = inputGenre.getText();
						//a label with records of the query
						JLabel option2ResultLabel = new JLabel(genre);
						option2ResultLabel.setHorizontalAlignment(JLabel.CENTER);
						
						JButton option2ResultButton = new JButton("Finish");
						JPanel option2ResultPanel = new JPanel();
						option2ResultPanel.add(option2ResultButton);
						//add the label to the window frame
						option2ResultFrame.getContentPane().add(option2ResultLabel);
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
						option2ResultFrame.setSize(500, 250);
						option2ResultFrame.setVisible(true);
					}
				});
				
			}
			//third alternative
			else if(option3.isSelected()){

			}
			//fourth alternative
			else if(option4.isSelected()){

			}
			//fifth alternative
			else if(option5.isSelected()){

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
	frame.setSize(500, 250);
	//show the frame
	frame.setVisible(true);

	
	// Finally but importantly close the statement and connection
	statement.close ( ) ;
	con.close ( ) ;
    }

    ArrayList<ArrayList<String>> findBookName(String input) throws SQLException {
		// Querying the book table to find the book with the name = input

		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

		try {
			String querySQL = "SELECT BOOKS.ISBN,\n" +
					"BOOKS.BOOK_NAME,\n" +
					"\tBOOKS.RATING\n" +
					"FROM BOOKS\n" +
					"LEFT JOIN BOOKSGENRE ON BOOKS.ISBN=BOOKGENRES.ISBN\n" +
					"WHERE BOOKGENRES.GENRE_NAME = " + input + " \n" +
					"ORDER BY BOOKS.ISBN";

			System.out.println (querySQL) ;
			java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

			while ( rs.next ( ) ) {
				ArrayList<String> temp = new ArrayList<String>();

				String id = Integer.toString(rs.getInt ( 1 )) ;
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

			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}

		return results;
	}
}