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
private BufferedImage uploadedImg = null;
private BufferedImage originalImg = null;
private BufferedImage imgToEncrypt = null;
private BufferedImage imgToDecrypt = null;

private JFileChooser fileChooser = new JFileChooser();
private JFileChooser directoryChooser = null;
private String uploadFilePath;
private String secretFilePath;
private String selectedFilePath;

private String keyFilePath;
private String encryptedFilePath;
private String selectedDecryptFilePath;
    
    public Model(){
        x = 0;
        
    }
    
    public Model(int x){
        this.x = x;
    }
    

	public void goDecrypt() throws IOException {
		System.out.println("Application launch");
		String temp_string = Image_decoder.linear_decode(keyFilePath, encryptedFilePath);
		System.out.println("temp_string/decoder result: "+ temp_string);
	}

	public void goEncrypt(String colorString) {
		System.out.println("Application launch");
		String temp_string = Image_encoder.linear_map(colorString, uploadFilePath, secretFilePath, selectedFilePath);
		System.out.println("temp_string/encloder result: "+ temp_string);
	}

	public void goUploadEncrypt(View view) {
		
		System.out.println("Upload picture was pressed");
		
			int returnVal = fileChooser.showOpenDialog(view.uploadButton);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				File file = fileChooser.getSelectedFile();
				
				// This is where a real application would open the file.
				System.out.println("Opening: " + file.getName() + ".");
				
				uploadFilePath = file.getAbsolutePath();
				
				/*
				System.out.println("Reading image from disk. ");
		        try {
		        	
		        	uploadedImg = ImageIO.read(new File(file.getAbsolutePath()));
					
				} catch (IOException e1) {
					System.out.println("Exception: " + e1.getMessage());
				}
				*/
			} else {
				System.out.println("Open command cancelled by user.");
			}
		
		
	}
	public void encryptFile(View view) 	// Secret file
	{
		System.out.println("Encrypt picture was pressed");
		
		
			int returnVal = fileChooser.showOpenDialog(view.encryptButton);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				
				// This is where a real application would open the file.
				System.out.println("Opening: " + file.getName() + ".");
				
				
				secretFilePath = file.getAbsolutePath();
				
				
				
				/*
				System.out.println("Reading image from disk. ");
		        try {
		        	
		        	imgToEncrypt = ImageIO.read(new File(file.getAbsolutePath()));
					
				} catch (IOException e1) {
					System.out.println("Exception: " + e1.getMessage());
				}
				*/
			} else {
				System.out.println("Open command cancelled by user.");
			}
				 
	}
	public void destinationFilePath(View view) {
		
		directoryChooser = new JFileChooser();
		directoryChooser.setCurrentDirectory(new java.io.File("."));
		directoryChooser.setDialogTitle("choosertitle");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setAcceptAllFileFilterUsed(false);

		if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + directoryChooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + directoryChooser.getSelectedFile());
			
			String temp_path = directoryChooser.getCurrentDirectory().getAbsolutePath();
			//temp_path = temp_path.substring(0, temp_path.length() - 1);
			this.selectedFilePath = temp_path.trim() + "/";
			
			System.out.println(selectedFilePath);
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
		
		
		int returnVal = fileChooser.showOpenDialog(view.encryptButton);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
			
			
			setKeyFilePath(file.getAbsolutePath());
			
		} else {
			System.out.println("Open command cancelled by user.");
		}
		
	}

	public void fileToDecrypt(View view) {
		System.out.println("Encrypted picture was pressed");
		
	
		
		
		int returnVal = fileChooser.showOpenDialog(view.encryptButton);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
			
			
			setEncryptedFilePath(file.getAbsolutePath());
			
			
		
			
		} else {
			System.out.println("Open command cancelled by user.");
		}
		
	}
	public void destinationDecryptFilePath(View view) {

		directoryChooser = new JFileChooser();
		directoryChooser.setCurrentDirectory(new java.io.File("."));
		directoryChooser.setDialogTitle("choosertitle");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setAcceptAllFileFilterUsed(false);

		if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): " + directoryChooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + directoryChooser.getSelectedFile());
			
			String temp_path = directoryChooser.getCurrentDirectory().getAbsolutePath();
			//temp_path = temp_path.substring(0, temp_path.length() - 1);
			this.selectedDecryptFilePath = temp_path.trim() + "/";
			
			System.out.println(selectedDecryptFilePath);
		} else {
			System.out.println("No Selection ");
			directoryChooser = null;
			view.destinationDecryptButton.setBackground(Color.RED);
			view.destinationDecryptButton.setOpaque(true); 
			view.destinationDecryptButton.setBorderPainted(true);
		}

		
	}
	public BufferedImage getUploadedImg()
	{
		return this.uploadedImg;
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

	public String getSelectedFilePath() {
		return selectedFilePath;
	}

	public void setSelectedFilePath(String selectedFilePath) {
		this.selectedFilePath = selectedFilePath;
	}

	public String getSecretFilePath() {
		return secretFilePath;
	}

	public void setSecretFilePath(String secretFilePath) {
		this.secretFilePath = secretFilePath;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	

	public String getKeyFilePath() {
		return keyFilePath;
	}

	public void setKeyFilePath(String keyFilePath) {
		this.keyFilePath = keyFilePath;
	}

	public String getEncryptedFilePath() {
		return encryptedFilePath;
	}

	public void setEncryptedFilePath(String encryptedFilePath) {
		this.encryptedFilePath = encryptedFilePath;
	}
	public String getSelectedDecryptFilePath()
	{
		return selectedDecryptFilePath;
	}
	

	
}
