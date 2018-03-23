import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.* ;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	JRadioButton option1 = new JRadioButton("Select 1 from ...");

	//the second alternative option
	JRadioButton option2 = new JRadioButton("Select 2 from ...");
	
	//the second alternative option
	JRadioButton option3 = new JRadioButton("Select 3 from ...");
		
	//the second alternative option
	JRadioButton option4 = new JRadioButton("Select 4 from ...");
		
	//the second alternative option
	JRadioButton option5 = new JRadioButton("Select 5 from ...");
	
	//the second alternative option
	JRadioButton quit = new JRadioButton("Quit");
	
	//The button in which a user will click upon selecting one of the alternatives
	JButton buttonSubmit = new JButton("Submit Query");	
	
	ButtonGroup group = new ButtonGroup();
	group.add(option1);
	group.add(option2);
	group.add(option3);
	group.add(option4);
	group.add(option5);
	group.add(buttonSubmit);
	
	
	
	//this will display the result of queries
	JLabel labelResult = new JLabel("TEST RESULT");
	labelResult.setHorizontalAlignment(JLabel.CENTER);
	labelResult.setVerticalAlignment(JLabel.BOTTOM);
	frame.getContentPane().add(labelResult);
	
	/*to modify the size of a button, label,etc. you have to create a panel
	and add button,label,etc. to the panel.
	*/
	JPanel buttonPanel = new JPanel();
	buttonPanel.add(option1);
	buttonPanel.add(option2);
	buttonPanel.add(option3);
	buttonPanel.add(option4);
	buttonPanel.add(option5);
	buttonPanel.add(quit);

	buttonPanel.add(buttonSubmit);
	//positioning the submit button to be at the very buttom
	frame.getContentPane().add(buttonPanel);
	
	//the size of the frame window
	frame.setSize(500, 200);
	//show the frame
	frame.setVisible(true);

	
	
	// Finally but importantly close the statement and connection
	statement.close ( ) ;
	con.close ( ) ;
    }
}