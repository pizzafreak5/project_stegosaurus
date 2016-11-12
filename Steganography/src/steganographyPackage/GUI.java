package steganographyPackage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.applet.Applet;
import javax.swing.GrayFilter;

class GUIFrame extends JPanel 
{	
	private BufferedImage img;
    
	public GUIFrame() 
	{	
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
///////////// Page 1 ////////////////////////////////////////////	
		
		
		final ImageIcon imageIcon = new ImageIcon("src/stegosaurus_graphic.png");
		JTextArea aboutText = new JTextArea()
		{
		      Image image = imageIcon.getImage();

		      Image grayImage = GrayFilter.createDisabledImage(image);
		      {
		        setOpaque(false);
		      }

		      public void paint(Graphics g) {
		        g.drawImage(grayImage, 0, 0, this);
		        super.paint(g);
		      }
		};
		aboutText.setEditable(false);
		aboutText.setText("\tSteganography is the practice of concealing data within  \n"
					+ "another set of data. Steganography has been practiced since ancient  \n"
					+ "times as a way to keep secrets in politics, war, and as a hobby, among \n"
					+ "other situations. Steganography has seen a resurgence in use since \n"
					+ "the 1980’s and the proliferation of personal computers, with such \n"
					+ "possibilities arising as hiding an image within another without \n"
					+ "perceptible change to the human eye. Perhaps the greatest argument \n"
					+ "for the use of steganography rather than some other form if data \n"
					+ "hiding is that a person who is not privy to the data may not even \n"
					+ "know that they have captured a significant data transmission. For \n"
					+ "example, if the non­privy party captures an image accompanied by a \n"
					+ "message, they may not realize that important data in contained within \n"
					+ "the image.\n\n\n");

		
///////////// Page 2 ////////////////////////////////////////////	
		
		
		final JFileChooser fileChooser = new JFileChooser();
		
		
		
		JButton encryptButton = new JButton("Encrypt");
		JButton decryptButton = new JButton("Decrypt"); 
		JButton uploadButton = new JButton("Upload picture");
		JButton destinationButton = new JButton("Destination");
		
		JPanel colorPanel = new JPanel();
		TitledBorder colorTitle = BorderFactory.createTitledBorder("Color Plane");
		colorPanel.setBorder(colorTitle);
		
		JRadioButton rButton = new JRadioButton("Red");
		rButton.setSelected(true);
		JRadioButton gButton = new JRadioButton("Green");
		JRadioButton bButton = new JRadioButton("Blue");
		
		colorPanel.add(rButton);
		colorPanel.add(gButton);
		colorPanel.add(bButton);
		
//		String[] mappingStrings = {"Linear", "Hash", "Top to Bottom"};
//		JComboBox mapList = new JComboBox(mappingStrings);
//		mapList.setSelectedIndex(0);
		
		JRadioButton lineButton = new JRadioButton("Linear");
		lineButton.setSelected(true);
		JRadioButton hashButton = new JRadioButton("Hash");
		JRadioButton ttbButton = new JRadioButton("Top to Bottom");
		
		JPanel mapPanel = new JPanel();
		//mapPanel.add(mapList);
		mapPanel.add(lineButton);
		mapPanel.add(hashButton);
		mapPanel.add(ttbButton);
		TitledBorder maptitle = BorderFactory.createTitledBorder("Mapping Function");
		mapPanel.setBorder(maptitle);

		
		
		JPanel uploadPanel = new JPanel();
		TitledBorder uploadTitle = BorderFactory.createTitledBorder("Host Picture");
		uploadPanel.setBorder(uploadTitle);
		uploadPanel.add(uploadButton);
		
		JPanel encryptPanel = new JPanel();
		TitledBorder encryptTitle = BorderFactory.createTitledBorder("Secret File");
		encryptPanel.setBorder(encryptTitle);
		encryptPanel.add(encryptButton);
		
		JPanel destinationPanel = new JPanel();
		TitledBorder destinationTitle = BorderFactory.createTitledBorder("Encrypted File");
		destinationPanel.setBorder(destinationTitle);
		destinationPanel.add(destinationButton);
		
		
		JPanel filePanel = new JPanel();
		filePanel.add(uploadPanel);
		filePanel.add(encryptPanel);
		filePanel.add(destinationPanel);
		
		JPanel goButtonPanel = new JPanel();
		JButton goButton = new JButton("Go");
		goButton.setBackground(Color.GREEN);
		goButton.setOpaque(true); 
		goButton.setBorderPainted(true);
		goButton.setPreferredSize(new Dimension(475, 50));
		goButtonPanel.add(goButton);
		
///////////// Page 3 ////////////////////////////////////////////	
		
		
		
		
		JPanel decryptPanel = new JPanel();
		TitledBorder decryptTitle = BorderFactory.createTitledBorder("Decrypt your Photo");
		decryptPanel.setBorder(decryptTitle);
		decryptPanel.add(decryptButton);
		
		JRadioButton lineDecryptButton = new JRadioButton("Linear");
		lineDecryptButton.setSelected(true);
		JRadioButton hashDecryptButton = new JRadioButton("Hash");
		JRadioButton ttbDecryptButton = new JRadioButton("Top to Bottom");
		
		JPanel mapDecryptPanel = new JPanel();
		mapDecryptPanel.add(lineDecryptButton);
		mapDecryptPanel.add(hashDecryptButton);
		mapDecryptPanel.add(ttbDecryptButton);
		TitledBorder mapDecrypttitle = BorderFactory.createTitledBorder("Mapping Function");
		mapDecryptPanel.setBorder(mapDecrypttitle);
		
		
		JPanel keyPanel = new JPanel();
		JButton keyButton = new JButton("Original Image");
		TitledBorder keyTitle = BorderFactory.createTitledBorder("Original File");
		keyPanel.setBorder(keyTitle);
		keyPanel.add(keyButton);
		
		JPanel encryptedPanel = new JPanel();
		JButton encryptedButton = new JButton("Encypted Image"); 
		TitledBorder encryptedTitle = BorderFactory.createTitledBorder("Encrypted File");
		encryptedPanel.setBorder(encryptedTitle);
		encryptedPanel.add(encryptedButton);
		
		
		JPanel goDecryptButtonPanel = new JPanel();
		JButton goDecryptButton = new JButton("Go");
		goDecryptButton.setBackground(Color.GREEN);
		goDecryptButton.setOpaque(true); 
		goDecryptButton.setBorderPainted(true);
		goDecryptButton.setPreferredSize(new Dimension(475, 50));
		goDecryptButtonPanel.add(goDecryptButton);

		
////////////////////////////////////////////////////////////////
		JComponent page1 = makeTextPanel("Page 1");
		JComponent page2 = makeTextPanel("Page 2");
		JComponent page3 = makeTextPanel("Page 3");
		JComponent page4 = makeTextPanel("Page 4");
		tabbedPane.addTab("Encrypt", page2);
		tabbedPane.addTab("Decrypt", page3);
		tabbedPane.addTab("Tutorial", page4);
		tabbedPane.addTab("About",page1);
		
		page1.add(aboutText);
		
		page2.add(colorPanel);
		page2.add(mapPanel);
		page2.add(filePanel);
		page2.add(goButtonPanel);
		JPanel ePicturePanel = new JPanel();
		page2.add(ePicturePanel);
		page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));

		//page3.add(decryptPanel);
		page3.add(mapDecryptPanel);
		page3.add(keyPanel);
		page3.add(encryptedPanel);
		page3.add(goDecryptButtonPanel);
		JPanel dPicturePanel = new JPanel();
		page3.add(dPicturePanel);
		page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
		
		add(tabbedPane);
		
/////////// Active Listeners ////////////////////////////////	
		
	
//		mapList.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				int index = mapList.getSelectedIndex();
//				System.out.println(mappingStrings[index]+" is selected");
//			}
//		});
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lineButton.isSelected())
				{
					hashButton.setSelected(false);
					ttbButton.setSelected(false);
				}
			}
		});
		hashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hashButton.isSelected())
				{
					lineButton.setSelected(false);
					ttbButton.setSelected(false);
				}
			}
		});
		ttbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ttbButton.isSelected())
				{
					lineButton.setSelected(false);
					hashButton.setSelected(false);
				}
			}
		});
		
		
		
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rButton.isSelected())
				{
					gButton.setSelected(false);
					bButton.setSelected(false);
				}
			}
		});
		gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gButton.isSelected())
				{
					rButton.setSelected(false);
					bButton.setSelected(false);
				}
			}
		});
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bButton.isSelected())
				{
					rButton.setSelected(false);
					gButton.setSelected(false);
				}
			}
		});
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Upload picture was pressed");
				if (e.getSource() == uploadButton) 
				{
					int returnVal = fileChooser.showOpenDialog(uploadButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						
						// This is where a real application would open the file.
						System.out.println("Opening: " + file.getName() + ".");
						
						img = null;
						System.out.println("Reading image from disk. ");
				        try {
				        	
							img = ImageIO.read(new File(file.getAbsolutePath()));
							
//							Image resizedImage;
//							resizedImage = img.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
//							JLabel label = new JLabel(new ImageIcon(resizedImage));
//							ePicturePanel.add(label);
//							page2.revalidate();
							
						} catch (IOException e1) {
							System.out.println("Exception: " + e1.getMessage());
						}
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("decrypt was pressed");
				if (e.getSource() == decryptButton) 
				{
					int returnVal = fileChooser.showOpenDialog(decryptButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						
						// This is where a real application would open the file.
						System.out.println("Opening: " + file.getName() + ".");
						
						img = null;
						System.out.println("Reading image from disk. ");
				        try {
				        	
							img = ImageIO.read(new File(file.getAbsolutePath()));
							
							Image resizedImage;
							resizedImage = img.getScaledInstance(275, 275, Image.SCALE_DEFAULT);
							JLabel label = new JLabel(new ImageIcon(resizedImage));
							dPicturePanel.add(label);
							page3.revalidate();
			
						} catch (IOException e1) {
							System.out.println("Exception: " + e1.getMessage());
						}
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Upload picture was pressed");
				if (e.getSource() == uploadButton) 
				{
					int returnVal = fileChooser.showOpenDialog(uploadButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						
						// This is where a real application would open the file.
						System.out.println("Opening: " + file.getName() + ".");
						
						img = null;
						System.out.println("Reading image from disk. ");
				        try {
				        	
							img = ImageIO.read(new File(file.getAbsolutePath()));
							
							Image resizedImage;
							resizedImage = img.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
							JLabel label = new JLabel(new ImageIcon(resizedImage));
							ePicturePanel.add(label);
							page2.revalidate();
							
						} catch (IOException e1) {
							System.out.println("Exception: " + e1.getMessage());
						}
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Application launch");
				System.out.print("Color Plane: ");
				if(rButton.isSelected())
				{
					System.out.println("Red selected");
				}
				else if(gButton.isSelected())
				{
					System.out.println("Green selected");
				}
				else if(bButton.isSelected())
				{
					System.out.println("Blue selected");
				}
				else
				{
					System.out.println("Unable to get selected color plane");
				}
				
				System.out.print("Mapping Function: ");
				//int index = mapList.getSelectedIndex();
				{
					if(lineButton.isSelected())
					{
						System.out.println("Linear selected");
					}
					else if(hashButton.isSelected())
					{
						System.out.println("Hash selected");
					}
					else if(ttbButton.isSelected())
					{
						System.out.println("Top to Bottom selected");
					}
				}

			}
		});
	}
	protected JComponent makeTextPanel(String text)
	{
		JPanel panel = new JPanel(false);
		//panel.setSize(500, 400);
		return panel;
	}

}