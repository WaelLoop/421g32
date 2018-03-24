import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import javax.swing.*;
import javax.swing.border.Border;

class test
{
    public static void main ( String [ ] args ) throws SQLException
    {
	// Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
	String tableName = "";
        int sqlCode=0;      // Variable to hold SQLCODE
        String sqlState="00000";  // Variable to hold SQLSTATE

	if ( args.length > 0 ){
	    tableName += args [ 0 ] ;
	}
	else {
	    tableName += "example3.tbl";
	}

	// Register the driver.  You must register the driver before you can use it.
        try {
	    DriverManager.registerDriver ( new com.ibm.db2.jcc.DB2Driver() ) ;
	} catch (Exception cnfe){
	    System.out.println("Class not found");
        }

	// This is the url you must use for DB2.
	//Note: This url may not valid now !
	String url = "jdbc:db2://comp421.cs.mcgill.ca:50000/cs421";
	Connection con = DriverManager.getConnection (url,"cs421g32","32FourTimes") ;
	Statement statement = con.createStatement ( );
	
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
						char[] ID = inputID.getText().toCharArray();
						for(char c : ID){
							if (!Character.isDigit(c)){
								
							}
						}
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
				
				//label telling the user what to do
				JLabel option5LabelFN = new JLabel("Please input your first name");
				option5LabelFN.setHorizontalAlignment(JLabel.LEFT);
				JLabel option5LabelLN = new JLabel("Please input your last name");
				option5LabelLN.setHorizontalAlignment(JLabel.LEFT);
				JLabel option5LabelPN = new JLabel("Please input your phone number");
				option5LabelPN.setHorizontalAlignment(JLabel.LEFT);
				JLabel option5LabelEA = new JLabel("Please input your email address");
				option5LabelEA.setHorizontalAlignment(JLabel.LEFT);
				JLabel option5LabelPA = new JLabel("Please input your physical address");
				option5LabelPA.setHorizontalAlignment(JLabel.LEFT);
				
				//text field and its boundaries
				JTextField option5FN = new JTextField();
				option5FN.setPreferredSize(new Dimension(50, 25));
				option5FN.setHorizontalAlignment(JTextField.RIGHT);
				JTextField option5LN = new JTextField();
				option5LN.setPreferredSize(new Dimension(50, 25));
				option5LN.setHorizontalAlignment(JTextField.RIGHT);
				JTextField option5PN = new JTextField();
				option5PN.setPreferredSize(new Dimension(50, 25));
				option5PN.setHorizontalAlignment(JTextField.RIGHT);
				JTextField option5EA = new JTextField();
				option5EA.setPreferredSize(new Dimension(50, 25));
				option5EA.setHorizontalAlignment(JTextField.RIGHT);
				JTextField option5PA = new JTextField();
				option5PA.setPreferredSize(new Dimension(50, 25));
				option5PA.setHorizontalAlignment(JTextField.RIGHT);
				
				//button for query selection
				JButton option5Button = new JButton("Submit query");
				
				//label panel telling the user what to do
				JPanel option5LabelPanel = new JPanel();
				option5LabelPanel.add(option5LabelFN);
				option5LabelPanel.add(option5LabelLN);
				option5LabelPanel.add(option5LabelPN);
				option5LabelPanel.add(option5LabelEA);
				option5LabelPanel.add(option5LabelPA);
				//input panel where we will get the input from the user
				JPanel option5InputPanel = new JPanel();
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
				option5MainPanel.setLayout(new GridLayout(3, 1));
				//adding all the panels to the main panel
				option5MainPanel.add(option5LabelPanel);
				option5MainPanel.add(option5InputPanel);
				option5MainPanel.add(option5ButtonPanel);
				
				//add the panel to the window frame
				option5Frame.getContentPane().add(option5MainPanel);
				
				//adjusting the size and setting it visible
				option5Frame.setSize(700, 250);
				option5Frame.setVisible(true);
				
				option5Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//hiding the previous frame
						option5Frame.setVisible(false);
						//the result of the query window frame
						JFrame option5ResultFrame = new JFrame("Result");
						option5ResultFrame.setLayout(new GridLayout(2, 1));
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						//get the input from the user
						//*********String ID = inputID.getText();
						//a label with records of the query
						JLabel option5ResultLabel = new JLabel("test");
						option5ResultLabel.setHorizontalAlignment(JLabel.CENTER);
						
						JButton option5ResultButton = new JButton("Finish");
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
								frame.setVisible(true);
							}
						});
						
						//adjusting the size and setting it visible
						option5ResultFrame.setSize(700, 250);
						option5ResultFrame.setVisible(true);
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

	
	// Finally but importantly close the statement and connection
	statement.close ( ) ;
	con.close ( ) ;
    }
}