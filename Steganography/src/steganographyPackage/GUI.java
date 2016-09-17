package steganographyPackage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GUIFrame extends JFrame {
	JButton bChange; // reference to the button object

	// constructor for ButtonFrame
	GUIFrame(String title) {
		super(title); // invoke the JFrame constructor
		setLayout(new FlowLayout()); // set the layout manager

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JButton encryptB = new JButton("Encrypt"); // construct a JButton
		JButton decryptB = new JButton("Decrypt"); // construct a JButton
		JButton uploadB = new JButton("Upload picture"); // construct a JButton
		final JFileChooser fileChooser = new JFileChooser();
		add(panel1);
		add(panel2);
		add(panel3);
		panel1.add(encryptB);
		panel1.add(decryptB); // add the button to the JFrame
		panel2.add(uploadB);
		encryptB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("encrypt was pressed");
			}
		});
		decryptB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("decrypt was pressed");
			}
		});
		uploadB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Upload picture was pressed");
				if (e.getSource() == uploadB) {
					int returnVal = fileChooser.showOpenDialog(uploadB);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						// This is where a real application would open the file.
						System.out.println("Opening: " + file.getName() + ".");
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

public class GUI {
	public static void main(String[] args) {
		GUIFrame frm = new GUIFrame("Steganography instructional tool");

		frm.setSize(400, 400);
		frm.setVisible(true);
	}
}