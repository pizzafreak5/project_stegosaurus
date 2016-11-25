package steganographyPackage;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Controller {
	
	private Model model;
    private View view;
    
 
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
                          
    }
    
    public void contol(){ 
    	
        view.lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.lineButton.isSelected())
				{
					view.hashButton.setSelected(false);
					view.ttbButton.setSelected(false);
					view.setEncryptMapIcon("linear");
				}
			}
		});
        view.hashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.hashButton.isSelected())
				{
					view.lineButton.setSelected(false);
					view.ttbButton.setSelected(false);
					view.setEncryptMapIcon("hash");
				}
			}
		});
        view.ttbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.ttbButton.isSelected())
				{
					view.lineButton.setSelected(false);
					view.hashButton.setSelected(false);
					view.setEncryptMapIcon("topdown");
				}
			}
		});
		
        view.lineDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.lineDecryptButton.isSelected())
				{
					view.hashDecryptButton.setSelected(false);
					view.ttbDecryptButton.setSelected(false);
					view.setDecryptMapIcon("linear");
				}
			}
		});
        view.hashDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.hashDecryptButton.isSelected())
				{
					view.lineDecryptButton.setSelected(false);
					view.ttbDecryptButton.setSelected(false);
					view.setDecryptMapIcon("hash");
				}
			}
		});
        view.ttbDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.ttbDecryptButton.isSelected())
				{
					view.lineDecryptButton.setSelected(false);
					view.hashDecryptButton.setSelected(false);
					view.setDecryptMapIcon("topdown");
				}
			}
		});
		
		
        view.rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.rButton.isSelected())
				{
					view.gButton.setSelected(false);
					view.bButton.setSelected(false);
					view.setColorIcon("red");
				}
			}
		});
        view.gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.gButton.isSelected())
				{
					view.rButton.setSelected(false);
					view.bButton.setSelected(false);
					view.setColorIcon("green");
				}
			}
		});
        view.bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.bButton.isSelected())
				{
					view.rButton.setSelected(false);
					view.gButton.setSelected(false);
					view.setColorIcon("blue");
				}
			}
		});
        
        view.uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				uploadEncryptFile();
				checkIfReadyEncrypt();
			}
		});
		view.encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				encryptFile();
				checkIfReadyEncrypt();
			}
		});
		view.destinationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				destinationFilePath();
				checkIfReadyEncrypt();
			}
		});
		
		view.goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				goEncrypt();
				
			}
		});
		
		 view.keyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					keyToDecrypt();
					checkIfReadyDecrypt();
				}
			});
		 view.encryptedButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fileToDecrypt();
					checkIfReadyDecrypt();
				}
			});
		view.goDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				goDecrypt();				
			}
		});
		
		view.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nextSlide();				
			}
		});
		
		view.previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				previousSlide();				
			}
		});
		
		
		
	}// end of control method
        
    private void checkIfReadyEncrypt(){
    	
    	if(model.getUploadedImg() != null && 
    			model.getImgToEncrypt() != null &&
    			model.getDirectoryChooser() != null)
    	{
    		view.goButton.setBackground(Color.GREEN);
    		view.goButton.setOpaque(true); 
    		view.goButton.setBorderPainted(true);
    		view.goButton.setEnabled(true);
    	}
    }
    private void checkIfReadyDecrypt(){
    	
    	if(model.getOriginalImg() != null && 
    			model.getImgToDecrypt() != null)
    	{
    		view.goDecryptButton.setBackground(Color.GREEN);
    		view.goDecryptButton.setOpaque(true); 
    		view.goDecryptButton.setBorderPainted(true);
    		view.goDecryptButton.setEnabled(true);
    	}
    }
    
    private void goDecrypt(){
    	model.goDecrypt();
    	
    	System.out.print("Mapping Function: ");
		{
			if(view.lineDecryptButton.isSelected())
			{
				System.out.println("Linear selected");
			}
			else if(view.hashDecryptButton.isSelected())
			{
				System.out.println("Hash selected");
			}
			else if(view.ttbDecryptButton.isSelected())
			{
				System.out.println("Top to Bottom selected");
			}
		}
    	view.showDecryptnComplDialog();
    }
    private void goEncrypt() {
		model.goEncrypt();	
		
		//Creation of String variables to be sent in to linear_map
		String colorString = null;
		String host_filename = model.getUploadedImg().toString(); //********************set correctly?
		String secret_filename = model.getImgToEncrypt().toString();
		String output_filename = null;    //model.destinationFilePath(view); ??????
		
		System.out.print("Color Plane: ");
		if(view.rButton.isSelected())
		{
			System.out.println("Red selected");
			colorString = "RED";
		}
		else if(view.gButton.isSelected())
		{
			System.out.println("Green selected");
			colorString = "GREEN";
		}
		else if(view.bButton.isSelected())
		{
			System.out.println("Blue selected");
			colorString = "BLUE";
		}
		else
		{
			System.out.println("Unable to get selected color plane");
		}
		
		System.out.print("Mapping Function: ");
		{
			if(view.lineButton.isSelected())
			{
				System.out.println("Linear selected");
			}
			else if(view.hashButton.isSelected())
			{
				System.out.println("Hash selected");
			}
			else if(view.ttbButton.isSelected())
			{
				System.out.println("Top to Bottom selected");
			}
		}
		
		//Call to encryption function 
		Image_encoder.linear_map(colorString, host_filename, secret_filename, output_filename);
		
		
		view.showEncryptnComplDialog();
	}
    private void uploadEncryptFile() {
    	model.goUploadEncrypt(this.view);
    	if(model.getUploadedImg() != null)
    	{
    		view.uploadButton.setBackground(Color.GREEN);
    		view.uploadButton.setOpaque(true); 
    		view.uploadButton.setBorderPainted(true);
    	}
    	
    	
    }
    private void encryptFile() {
		model.encryptFile(this.view);
		if(model.getImgToEncrypt() != null)
    	{
    		view.encryptButton.setBackground(Color.GREEN);
    		view.encryptButton.setOpaque(true); 
    		view.encryptButton.setBorderPainted(true);
    	}
		
	}
    private void destinationFilePath() {
		model.destinationFilePath(this.view);
		if(model.getDirectoryChooser() != null)
    	{
    		view.destinationButton.setBackground(Color.GREEN);
    		view.destinationButton.setOpaque(true); 
    		view.destinationButton.setBorderPainted(true);
    	}
	}
    private void keyToDecrypt() {
		model.keyToDecrypt(this.view);
		if(model.getOriginalImg() != null)
    	{
    		view.keyButton.setBackground(Color.GREEN);
    		view.keyButton.setOpaque(true); 
    		view.keyButton.setBorderPainted(true);
    	}
		
	}
    private void fileToDecrypt() {
		model.fileToDecrypt(this.view);
		if(model.getImgToDecrypt() != null)
    	{
    		view.encryptedButton.setBackground(Color.GREEN);
    		view.encryptedButton.setOpaque(true); 
    		view.encryptedButton.setBorderPainted(true);
    	}
	}
    private void nextSlide() {
		
		model.goToNextSlide();
		
		if(model.getSlideNumber() == view.slideArray.length-1)
    	{
    		view.nextButton.setEnabled(false);
    	}
		else
    	{
    		view.nextButton.setEnabled(true);
    	}
		if(model.getSlideNumber() == 0)
    	{
    		view.previousButton.setEnabled(false);
    	}
    	else
    	{
    		view.previousButton.setEnabled(true);
    	}
		view.setSlideNumber(model.getSlideNumber());
	}
    private void previousSlide() {
    	model.goToPreviousSlide();
    	
    	if(model.getSlideNumber() == view.slideArray.length)
    	{
    		view.nextButton.setEnabled(false);
    	}
		else
    	{
    		view.nextButton.setEnabled(true);
    	}
    	if(model.getSlideNumber() == 0)
    	{
    		view.previousButton.setEnabled(false);
    	}
    	else
    	{
    		view.previousButton.setEnabled(true);
    	}
    	view.setSlideNumber(model.getSlideNumber());
	}
}
