package steganographyPackage;


import java.awt.BorderLayout;
import javax.swing.JFrame;


public class mainPopUp
{
    
	public void init()
	{
		// Create and set up the window
		JFrame frame = new JFrame("Steganography Instructional Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
		// Add content to the window
		frame.add(new GUIFrame(), BorderLayout.CENTER);
		
		// Display the window
		frame.pack();
		frame.setSize(500, 500);
		frame.setVisible(true);
	}


	public static void main(String[] args) 
	{
		mainPopUp window = new mainPopUp();
		window.init();
	}

}
