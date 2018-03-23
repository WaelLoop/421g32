import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import javax.swing.*;

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
	
	JFrame resultFrame ;
	//creating the GUI Window with our group name as a title
	JFrame frame = new JFrame("cs421g32");
	//close the frame when the the red x button (top left corner of the frame is pressed)
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//the first alternative option
	JRadioButton option1 = new JRadioButton("Select 1 from ...");
	
	//the second alternative option
	JRadioButton option2 = new JRadioButton("Select 2 from ...");
	
	//the third alternative option
	JRadioButton option3 = new JRadioButton("Select 3 from ...");
		
	//the fourth alternative option
	JRadioButton option4 = new JRadioButton("Select 4 from ...");
		
	//the fifth alternative option
	JRadioButton option5 = new JRadioButton("Select 5 from ...");
	
	//the quit alternative option
	JRadioButton quit = new JRadioButton("Quit");
	
	//The button in which a user will click upon selecting one of the alternatives
	JButton buttonSubmit = new JButton("Submit Query");
	
	buttonSubmit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//first alternative
			if(option1.isSelected()){
				
			}
			//second alternative
			else if(option2.isSelected()){

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
	frame.getContentPane().add(buttonPanel);

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
}