package steganographyPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class View {

	private JFrame frame;
    
    private JTabbedPane tabbedPane;
    private ImageIcon dinoIcon;
    public JTextArea aboutText;

    public JButton uploadButton;
    public JButton encryptButton;
    public JButton destinationButton;
    
    public ImageIcon colorIcon;
    public ImageIcon mapEncryptIcon;
    public ImageIcon mapDecryptIcon;
    public ImageIcon slideShowIcon;
    public BufferedImage colorRedImg;
    public BufferedImage colorGreenImg;
    public BufferedImage colorBlueImg;
    public BufferedImage mapLinearImg;
    public BufferedImage mapHashImg;
    public BufferedImage mapTopDownImg;
    private JLabel mapEncryptLabel;
    private JLabel mapDecryptLabel;
    private JLabel colorLabel;
    private JLabel slideShowLabel;
    private JPanel slideShowPanel;
    private JPanel colorPanel;
    
    public JRadioButton rButton;
    public JRadioButton gButton;
    public JRadioButton bButton;
    
    public JRadioButton lineButton;
    public JRadioButton hashButton;
    public JRadioButton ttbButton;
    
    private JPanel mapPanel;
    private JPanel uploadPanel;
    private JPanel encryptPanel;
    private JPanel destinationPanel;
	
    private JPanel filePanel;
	
    private JPanel goButtonPanel;
    public JButton goButton;
    
    public JPanel ePicturePanel;
    public JPanel dPicturePanel;
    
    public JComponent page1;
    public JComponent page2;
    public JComponent page3;
    public JComponent page4;
    
	
    public JRadioButton lineDecryptButton;
    public JRadioButton hashDecryptButton;
    public JRadioButton ttbDecryptButton;
	
    private JPanel mapDecryptPanel;
	
    private JPanel keyPanel;
    public JButton keyButton;
	
    private JPanel encryptedPanel;
    public JButton encryptedButton;
    
    private JPanel destinationDecryptPanel;
    public JButton destinationDecryptButton;
    
    private JPanel decryptPanel;
	
    private JPanel goDecryptButtonPanel;
    public JButton goDecryptButton;
    
    public Icon nextIcon;
    public JButton nextButton;
	
    public Icon previousIcon; 
    public JButton previousButton;
    
    public BufferedImage slide0;
    public BufferedImage slide1;
    public BufferedImage slide2;
    public BufferedImage slide3;
    public BufferedImage slide4;
    public BufferedImage slide5;
    public BufferedImage slide6;
    public BufferedImage slideArray[];

    

    
    public View(String text){
    	frame = new JFrame("Steganography Instructional Tool");                                   
        frame.getContentPane().setLayout(new BorderLayout());                                          
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        frame.setSize(525,420);        
        frame.setVisible(true);
        frame.setResizable(false);
        
///////////// Page 1 - About ///////////////        
        tabbedPane = new JTabbedPane();
        dinoIcon = new ImageIcon("src/stegosaurus_graphic.png");
        aboutText = new JTextArea()
        {
		      Image image = dinoIcon.getImage();
		      {
		        setOpaque(false);
		      }
		   
		      public void paint(Graphics g) {
		        g.drawImage(image, 0, 0, this);
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
///////////// Page 2 - Encrypt //////////////    
		
		
		uploadButton = new JButton("Upload picture");
		uploadButton.setBackground(Color.RED);
    	uploadButton.setOpaque(true); 
    	uploadButton.setBorderPainted(true);
		encryptButton = new JButton("Encrypt");
		encryptButton.setBackground(Color.RED);
		encryptButton.setOpaque(true); 
		encryptButton.setBorderPainted(true);
		destinationButton = new JButton("Destination");
		destinationButton.setBackground(Color.RED);
		destinationButton.setOpaque(true); 
		destinationButton.setBorderPainted(true);
		
		colorPanel = new JPanel();
		TitledBorder colorTitle = BorderFactory.createTitledBorder("Color Plane");
		colorPanel.setBorder(colorTitle);
		
		rButton = new JRadioButton("Red");
		rButton.setSelected(true);
		gButton = new JRadioButton("Green");
		bButton = new JRadioButton("Blue");

	    colorIcon = new ImageIcon();
		try {
		    colorRedImg = ImageIO.read(new File("src/PaintBucketRed-26.png"));
		    colorGreenImg = ImageIO.read(new File("src/PaintBucketGreen-26.png"));
		    colorBlueImg = ImageIO.read(new File("src/PaintBucketBlue-26.png"));
		    
		    colorIcon.setImage(colorRedImg);
		    colorLabel = new JLabel();//("", colorIcon, JLabel.CENTER);
		    colorLabel.setIcon(colorIcon);
		} catch (IOException e) {
			System.out.println("Can't paint the bucket");
		}
		
		
		colorPanel.add(rButton);
		colorPanel.add(gButton);
		colorPanel.add(bButton);
		colorPanel.add(colorLabel, BorderLayout.CENTER);
		
		lineButton = new JRadioButton("Linear");
		lineButton.setSelected(true);
		hashButton = new JRadioButton("Hash");
		hashButton.setEnabled(false);
		ttbButton = new JRadioButton("Top to Bottom");
		ttbButton.setEnabled(false);
		
		mapPanel = new JPanel();
		TitledBorder maptitle = BorderFactory.createTitledBorder("Mapping Function");
		mapPanel.setBorder(maptitle);
		
		mapEncryptIcon = new ImageIcon();
		try {
			mapLinearImg = ImageIO.read(new File("src/Linear-24.png"));
			mapHashImg = ImageIO.read(new File("src/Hash-24.png"));
			mapTopDownImg = ImageIO.read(new File("src/TopDown-24.png"));

		    mapEncryptIcon.setImage(mapLinearImg);
		    mapEncryptLabel = new JLabel();
		    mapEncryptLabel.setIcon(mapEncryptIcon);
		} catch (IOException e) {
			System.out.println("Can't paint the encrypt map");
		}
		
		mapPanel.add(lineButton);
		mapPanel.add(hashButton);
		mapPanel.add(ttbButton);
		mapPanel.add(mapEncryptLabel, BorderLayout.CENTER);
		
		uploadPanel = new JPanel();
		TitledBorder uploadTitle = BorderFactory.createTitledBorder("Host Picture");
		uploadPanel.setBorder(uploadTitle);
		uploadPanel.add(uploadButton);
		
		encryptPanel = new JPanel();
		TitledBorder encryptTitle = BorderFactory.createTitledBorder("Secret File");
		encryptPanel.setBorder(encryptTitle);
		encryptPanel.add(encryptButton);
		
		destinationPanel = new JPanel();
		TitledBorder destinationTitle = BorderFactory.createTitledBorder("Encrypted File");
		destinationPanel.setBorder(destinationTitle);
		destinationPanel.add(destinationButton);
		
		filePanel = new JPanel();
		filePanel.add(uploadPanel);
		filePanel.add(encryptPanel);
		filePanel.add(destinationPanel);
		
		goButtonPanel = new JPanel();
		goButton = new JButton("Go");
		goButton.setFont(new Font("Arial", Font.PLAIN, 40));
		goButton.setBackground(Color.GRAY);
		goButton.setOpaque(true); 
		goButton.setBorderPainted(true);
		goButton.setPreferredSize(new Dimension(475, 50));
		
		goButton.setEnabled(false);
		goButtonPanel.add(goButton);
		
///////////// Page 3 Decrypt ////////////
		
		lineDecryptButton = new JRadioButton("Linear");
		lineDecryptButton.setSelected(true);
		hashDecryptButton = new JRadioButton("Hash");
		hashDecryptButton.setEnabled(false);
		ttbDecryptButton = new JRadioButton("Top to Bottom");
		ttbDecryptButton.setEnabled(false);

		mapDecryptIcon = new ImageIcon();
		mapDecryptIcon.setImage(mapLinearImg);
		mapDecryptLabel = new JLabel();
		mapDecryptLabel.setIcon(mapDecryptIcon);

		mapDecryptPanel = new JPanel();
		mapDecryptPanel.add(lineDecryptButton);
		mapDecryptPanel.add(hashDecryptButton);
		mapDecryptPanel.add(ttbDecryptButton);
		mapDecryptPanel.add(mapDecryptLabel, BorderLayout.CENTER);
		
		TitledBorder mapDecrypttitle = BorderFactory.createTitledBorder("Mapping Function");
		mapDecryptPanel.setBorder(mapDecrypttitle);
		
		keyPanel = new JPanel();
		keyButton = new JButton("Key Image");
		keyButton.setBackground(Color.RED);
		keyButton.setOpaque(true); 
		keyButton.setBorderPainted(true);
		TitledBorder keyTitle = BorderFactory.createTitledBorder("Original File");
		keyPanel.setBorder(keyTitle);
		keyPanel.add(keyButton);
		
		encryptedPanel = new JPanel();
		encryptedButton = new JButton("Encypted Image");
		encryptedButton.setBackground(Color.RED);
		encryptedButton.setOpaque(true); 
		encryptedButton.setBorderPainted(true);
		TitledBorder encryptedTitle = BorderFactory.createTitledBorder("Encrypted File");
		encryptedPanel.setBorder(encryptedTitle);
		encryptedPanel.add(encryptedButton);
		
		destinationDecryptButton = new JButton("Destination");
		destinationDecryptButton.setBackground(Color.RED);
		destinationDecryptButton.setOpaque(true); 
		destinationDecryptButton.setBorderPainted(true);
		destinationDecryptPanel = new JPanel();
		TitledBorder destinationDecryptTitle = BorderFactory.createTitledBorder("Decrypted File");
		destinationDecryptPanel.setBorder(destinationDecryptTitle);
		destinationDecryptPanel.add(destinationDecryptButton);
	
		
		decryptPanel = new JPanel();
		decryptPanel.add(keyPanel);
		decryptPanel.add(encryptedPanel);
		decryptPanel.add(destinationDecryptPanel);
		
		
		goDecryptButtonPanel = new JPanel();
		goDecryptButton = new JButton("Go");
		goDecryptButton.setFont(new Font("Arial", Font.PLAIN, 40));
		goDecryptButton.setBackground(Color.GRAY);
		goDecryptButton.setOpaque(true); 
		goDecryptButton.setBorderPainted(true);
		goDecryptButton.setPreferredSize(new Dimension(475, 50));
		
		goDecryptButton.setEnabled(false);
		goDecryptButtonPanel.add(goDecryptButton);
///////////// Page 4 - Tutorial /////////////		
		
		
	    JPanel tutorialPanel = new JPanel(new BorderLayout());
		slideShowPanel = new JPanel();
		
		try {
			slide0 = ImageIO.read(new File("src/tutorialSlide_0 small.png"));
			slide1 = ImageIO.read(new File("src/tutorialSlide_1 small.png"));
			slide2 = ImageIO.read(new File("src/tutorialSlide_2 small.png"));
			slide3 = ImageIO.read(new File("src/tutorialSlide_3 small.png"));
			slide4 = ImageIO.read(new File("src/tutorialSlide_4 small.png"));
			slide5 = ImageIO.read(new File("src/tutorialSlide_5 small.png"));
			slide6 = ImageIO.read(new File("src/tutorialSlide_6 small.png"));
		
			slideArray = new BufferedImage[]{slide0, slide1, slide2, slide3, slide4, slide5, slide6};
			
			slideShowIcon = new ImageIcon();
		 	slideShowIcon.setImage(slide0);
		    slideShowLabel = new JLabel();
		    slideShowLabel.setIcon(slideShowIcon);

		    
		} catch (IOException e) {
			System.out.println("Can't paint the encrypt map");
		}
		
		slideShowPanel.add(slideShowLabel, BorderLayout.CENTER);

		JPanel tutorialButtonPanel = new JPanel();
		
		nextIcon = new ImageIcon("src/Right-48.png");
		nextButton = new JButton(nextIcon);
		
		previousIcon = new ImageIcon("src/Left-48.png");
		previousButton = new JButton(previousIcon);
		previousButton.setEnabled(false);
		
		tutorialButtonPanel.add(previousButton);
		tutorialButtonPanel.add(nextButton);
		
		tutorialPanel.add(slideShowPanel, BorderLayout.CENTER);
		tutorialPanel.add(tutorialButtonPanel, BorderLayout.SOUTH);
		
///////////// GUI structure /////////////		
		page1 = makeTextPanel("Page 1");
		page2 = makeTextPanel("Page 2");
		page3 = makeTextPanel("Page 3");
		page4 = makeTextPanel("Page 4");
		
		tabbedPane.addTab("Encrypt", page2);
		tabbedPane.addTab("Decrypt", page3);
		tabbedPane.addTab("Tutorial", page4);
		tabbedPane.addTab("About",page1);
		
		page1.add(aboutText);
		
		page2.add(colorPanel);
		page2.add(mapPanel);
		page2.add(filePanel);
		page2.add(goButtonPanel);
		
		ePicturePanel = new JPanel();
		page2.add(ePicturePanel);
		page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));

		page3.add(mapDecryptPanel);
		page3.add(decryptPanel);
		page3.add(goDecryptButtonPanel);
		
		dPicturePanel = new JPanel();
		page3.add(dPicturePanel);
		page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
		
		page4.add(tutorialPanel);
		
		frame.add(tabbedPane);
		
    }
        
   
    protected JComponent makeTextPanel(String text)
	{
		JPanel panel = new JPanel(false);
		return panel;
	}
    public void setColorIcon(String text){
    	if(text == "green")
    	{
    		colorIcon.setImage(colorGreenImg);
    		colorLabel.setIcon(colorIcon);
    		
    	}
    	else if(text == "red")
    	{
    		colorIcon.setImage(colorRedImg);
    		colorLabel.setIcon(colorIcon);
    		
    	}
    	else if(text == "blue")
    	{
    		colorIcon.setImage(colorBlueImg);
    		colorLabel.setIcon(colorIcon);
    		
    	}
    	page2.repaint();
    }
    public void setEncryptMapIcon(String text){
    	if(text == "linear")
    	{
    		mapEncryptIcon.setImage(mapLinearImg);
    		mapEncryptLabel.setIcon(mapEncryptIcon);
    		
    	}
    	else if(text == "hash")
    	{
    		mapEncryptIcon.setImage(mapHashImg);
    		mapEncryptLabel.setIcon(mapEncryptIcon);
    		
    	}
    	else if(text == "topdown")
    	{
    		mapEncryptIcon.setImage(mapTopDownImg);
    		mapEncryptLabel.setIcon(mapEncryptIcon);
    		
    	}
    	page2.repaint();
    }
    public void setDecryptMapIcon(String text){
    	if(text == "linear")
    	{
    		mapDecryptIcon.setImage(mapLinearImg);
    		mapDecryptLabel.setIcon(mapDecryptIcon);
    		
    	}
    	else if(text == "hash")
    	{
    		mapDecryptIcon.setImage(mapHashImg);
    		mapDecryptLabel.setIcon(mapDecryptIcon);
    		
    	}
    	else if(text == "topdown")
    	{
    		mapDecryptIcon.setImage(mapTopDownImg);
    		mapDecryptLabel.setIcon(mapDecryptIcon);
    		
    	}
    	page3.repaint();
    }
    public void setSlideNumber(int number){
        
    	for(int i=0; i < slideArray.length; i++)
    	{
    		if(i == number)
    		{
    			slideShowIcon.setImage(slideArray[i]);
    			slideShowLabel.setIcon(slideShowIcon);
    			
    		}
    	}
    	page4.repaint();

    }
    public void showEncryptnComplDialog()
    {
    	Icon dinoIcon = new ImageIcon("src/Stegosaurus150x75.png");
    	
    	// Custom title, custom icon
    	JOptionPane.showMessageDialog(frame,
    	    "Encryption Complete",
    	    "Steganography Instructional Tool",
    	    JOptionPane.INFORMATION_MESSAGE,
    	    dinoIcon);
    }
    public void showDecryptnComplDialog()
    {
    	Icon dinoIcon = new ImageIcon("src/Stegosaurus150x75.png");
    	
    	// Custom title, custom icon
    	JOptionPane.showMessageDialog(frame,
    	    "Decryption Complete",
    	    "Steganography Instructional Tool",
    	    JOptionPane.INFORMATION_MESSAGE,
    	    dinoIcon);
    }
}

