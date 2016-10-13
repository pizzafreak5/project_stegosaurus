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

class GUIFrame extends JPanel 
{	
	private BufferedImage img;
    
	public GUIFrame() 
	{	
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
///////////// Page 1 ////////////////////////////////////////////	
		JComponent page1 = makeTextPanel("Page 1");
		tabbedPane.addTab("About",page1);
		
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
		String[] mappingStrings = {"Linear", "Hash", "Top to Bottom"};
		
		final JFileChooser fileChooser = new JFileChooser();
		JComponent page2 = makeTextPanel("Page 2");
		tabbedPane.addTab("Encrypt", page2);
		
		JButton encryptButton = new JButton("Encrypt");
		JButton decryptButton = new JButton("Decrypt"); 
		JButton uploadButton = new JButton("Upload picture");
		
		JPanel colorPanel = new JPanel();
		TitledBorder colorTitle = BorderFactory.createTitledBorder("Color Plane");
		colorPanel.setBorder(colorTitle);
		
		JRadioButton rButton = new JRadioButton("Red");
		JRadioButton gButton = new JRadioButton("Green");
		JRadioButton bButton = new JRadioButton("Blue");
		
		colorPanel.add(rButton);
		colorPanel.add(gButton);
		colorPanel.add(bButton);
		
		JComboBox mapList = new JComboBox(mappingStrings);
		JPanel mapPanel = new JPanel();
		mapPanel.add(mapList);
		TitledBorder maptitle = BorderFactory.createTitledBorder("Mapping Function");
		mapPanel.setBorder(maptitle);

		JPanel uploadPanel = new JPanel();
		TitledBorder uploadTitle = BorderFactory.createTitledBorder("Upload your Photo");
		uploadPanel.setBorder(uploadTitle);
		uploadPanel.add(uploadButton);
		
		JPanel encryptPanel = new JPanel();
		TitledBorder encryptTitle = BorderFactory.createTitledBorder("Encrypt your Photo");
		encryptPanel.setBorder(encryptTitle);
		encryptPanel.add(encryptButton);
		
		
///////////// Page 3 ////////////////////////////////////////////	
		
		JComponent page3 = makeTextPanel("Page 3");
		tabbedPane.addTab("Decrypt", page3);
		
		JPanel decryptPanel = new JPanel();
		TitledBorder decryptTitle = BorderFactory.createTitledBorder("Decrypt your Photo");
		decryptPanel.setBorder(decryptTitle);
		decryptPanel.add(decryptButton);
		
////////////////////////////////////////////////////////////////
		
		page1.add(aboutText);
		
		page2.add(colorPanel);
		page2.add(mapPanel);
		page2.add(encryptPanel);
		page2.add(uploadPanel);
		JPanel ePicturePanel = new JPanel();
		page2.add(ePicturePanel);
		page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));

		page3.add(decryptPanel);
		JPanel dPicturePanel = new JPanel();
		page3.add(dPicturePanel);
		page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
		
		add(tabbedPane);
		
/////////// Active Listeners ////////////////////////////////	
		
		mapList.setSelectedIndex(2);
		mapList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = mapList.getSelectedIndex();
				System.out.println(mappingStrings[index]+" is selected");
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
				System.out.println("encrypt was pressed");
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
	}
	protected JComponent makeTextPanel(String text)
	{
		JPanel panel = new JPanel(false);
		panel.setSize(500, 400);
		return panel;
	}

}