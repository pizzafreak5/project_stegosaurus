package steganographyPackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import javax.swing.JLabel;

public class Model {

private int x;
private BufferedImage uplaodedImg = null;
private BufferedImage originalImg = null;
private BufferedImage imgToEncrypt = null;
private BufferedImage imgToDecrypt = null;

private JFileChooser fileChooser = new JFileChooser();
private JFileChooser directoryChooser = null;
    
    public Model(){
        x = 0;
        
    }
    
    public Model(int x){
        this.x = x;
    }
    

	public void goDecrypt() {
		System.out.println("Application launch");
	
	}

	public void goEncrypt() {
		System.out.println("Application launch");
		
		
	}

	public void goUploadEncrypt(View view) {
		
		System.out.println("Upload picture was pressed");
		
			int returnVal = fileChooser.showOpenDialog(view.uploadButton);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				File file = fileChooser.getSelectedFile();
				
				// This is where a real application would open the file.
				System.out.println("Opening: " + file.getName() + ".");
				
				
				System.out.println("Reading image from disk. ");
		        try {
		        	
		        	uplaodedImg = ImageIO.read(new File(file.getAbsolutePath()));
					
				} catch (IOException e1) {
					System.out.println("Exception: " + e1.getMessage());
				}
			} else {
				System.out.println("Open command cancelled by user.");
			}
		
		
	}
	public void encryptFile(View view) {
		System.out.println("Encrypt picture was pressed");
		
		
			int returnVal = fileChooser.showOpenDialog(view.encryptButton);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				
				// This is where a real application would open the file.
				System.out.println("Opening: " + file.getName() + ".");
				
				
				System.out.println("Reading image from disk. ");
		        try {
		        	
		        	imgToEncrypt = ImageIO.read(new File(file.getAbsolutePath()));
					
				} catch (IOException e1) {
					System.out.println("Exception: " + e1.getMessage());
				}
			} else {
				System.out.println("Open command cancelled by user.");
			}
		
	}
	public void destinationFilePath(View view) {
		// TODO Auto-generated method stub
		directoryChooser = new JFileChooser();
		directoryChooser.setCurrentDirectory(new java.io.File("."));
		directoryChooser.setDialogTitle("choosertitle");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setAcceptAllFileFilterUsed(false);

		if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + directoryChooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + directoryChooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
			directoryChooser = null;
			view.destinationButton.setBackground(Color.RED);
			view.destinationButton.setOpaque(true); 
			view.destinationButton.setBorderPainted(true);
		}

	}
	public void keyToDecrypt(View view) {
		System.out.println("Key picture was pressed");
		
		
		int returnVal = fileChooser.showOpenDialog(view.keyButton);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
			
			
			System.out.println("Reading image from disk. ");
	        try {
	        	
	        	originalImg = ImageIO.read(new File(file.getAbsolutePath()));
				
			} catch (IOException e1) {
				System.out.println("Exception: " + e1.getMessage());
			}
		} else {
			System.out.println("Open command cancelled by user.");
		}
		
	}

	public void fileToDecrypt(View view) {
		System.out.println("Encrypted picture was pressed");
		
		
		int returnVal = fileChooser.showOpenDialog(view.encryptedButton);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
			
			
			System.out.println("Reading image from disk. ");
	        try {
	        	
	        	imgToDecrypt = ImageIO.read(new File(file.getAbsolutePath()));
				
			} catch (IOException e1) {
				System.out.println("Exception: " + e1.getMessage());
			}
		} else {
			System.out.println("Open command cancelled by user.");
		}
		
	}
	public BufferedImage getUplaodedImg()
	{
		return this.uplaodedImg;
	}
	public BufferedImage getImgToEncrypt()
	{ 
		return this.imgToEncrypt;
	}
	
	public BufferedImage getOriginalImg()
	{
		return this.originalImg;
	}
	public BufferedImage getImgToDecrypt()
	{
		return this.imgToDecrypt;
	}
	public JFileChooser getDirectoryChooser()
	{
		return this.directoryChooser;
	}

	public void goToNextSlide() {
		
		x++;
	}

	public void goToPreviousSlide() {
		
		x--;
	}
	public int getSlideNumber() {
		
		return this.x;
	}
	

	
}
