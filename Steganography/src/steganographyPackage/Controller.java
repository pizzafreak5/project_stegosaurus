package steganographyPackage;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class Controller {
	
	private Model model;
    private View view;
    
    private String colorString;
    
 
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
				
				goEncrypt(view);
				
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
		 view.destinationDecryptButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					destinationDecryptFilePath();
					checkIfReadyDecrypt();
				}
			});
		view.goDecryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					goDecrypt();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
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
    	
    	if(model.getUploadFilePath() != null && 
    			model.getSecretFilePath() != null &&
    			model.getSelectedFilePath() != null)
    	{
    		view.goButton.setBackground(Color.GREEN);
    		view.goButton.setOpaque(true); 
    		view.goButton.setBorderPainted(true);
    		view.goButton.setEnabled(true);
    	}
    }
    private void checkIfReadyDecrypt(){
    	
    	if(model.getKeyFilePath() != null && 
    			model.getEncryptedFilePath() != null &&
    			model.getSelectedDecryptFilePath() != null)
    	{
    		view.goDecryptButton.setBackground(Color.GREEN);
    		view.goDecryptButton.setOpaque(true); 
    		view.goDecryptButton.setBorderPainted(true);
    		view.goDecryptButton.setEnabled(true);
    	}
    }
    
    private void goDecrypt() throws IOException{
    	model.goDecrypt();
    	
    	//System.out.print("Mapping Function: ");
		{
			if(view.lineDecryptButton.isSelected())
			{
				//System.out.println("Linear selected");
			}
			else if(view.hashDecryptButton.isSelected())
			{
				//System.out.println("Hash selected");
			}
			else if(view.ttbDecryptButton.isSelected())
			{
				//System.out.println("Top to Bottom selected");
			}
		}
    	view.showDecryptnComplDialog();
    }
    private void goEncrypt(View view) {	// Go button -> to encrypt
		
		
		//Creation of colorString to be sent in to linear_map
		colorString = null; 
		
		//System.out.print("Color Plane: ");
		if(view.rButton.isSelected())
		{
			//System.out.println("Red selected");
			colorString = "RED";
		}
		else if(view.gButton.isSelected())
		{
			//System.out.println("Green selected");
			colorString = "GREEN";
		}
		else if(view.bButton.isSelected())
		{
			//System.out.println("Blue selected");
			colorString = "BLUE";
		}
		else
		{
			//System.out.println("Unable to get selected color plane");
		}
		
		//System.out.print("Mapping Function: ");
		{
			if(view.lineButton.isSelected())
			{
				//System.out.println("Linear selected");
			}
			else if(view.hashButton.isSelected())
			{
				//System.out.println("Hash selected");
			}
			else if(view.ttbButton.isSelected())
			{
				//System.out.println("Top to Bottom selected");
			}
		}
		
		//Call to encryption function 
		model.goEncrypt(colorString);
		
		view.showEncryptnComplDialog();	
	}
    private void uploadEncryptFile() {
    	model.goUploadEncrypt(this.view);
    	if(model.getUploadFilePath() != null)
    	{
    		view.uploadButton.setBackground(Color.GREEN);
    		view.uploadButton.setOpaque(true); 
    		view.uploadButton.setBorderPainted(true);
    	}
    	
    	
    }
    private void encryptFile() {	// secret file
		model.encryptFile(this.view);
		if(model.getSecretFilePath() != null)
    	{
			
    		view.encryptButton.setBackground(Color.GREEN);
    		view.encryptButton.setOpaque(true); 
    		view.encryptButton.setBorderPainted(true);
    	}
		
	}
    private void destinationFilePath() {
		model.destinationFilePath(this.view);
		if(model.getSelectedFilePath() != null)
    	{
    		view.destinationButton.setBackground(Color.GREEN);
    		view.destinationButton.setOpaque(true); 
    		view.destinationButton.setBorderPainted(true);
    	}
	}
    private void keyToDecrypt() {
		model.keyToDecrypt(this.view);
		if(model.getKeyFilePath() != null)
    	{
    		view.keyButton.setBackground(Color.GREEN);
    		view.keyButton.setOpaque(true); 
    		view.keyButton.setBorderPainted(true);
    	}
		
	}
    private void fileToDecrypt() {
		model.fileToDecrypt(this.view);
		if(model.getEncryptedFilePath() != null)
    	{
    		view.encryptedButton.setBackground(Color.GREEN);
    		view.encryptedButton.setOpaque(true); 
    		view.encryptedButton.setBorderPainted(true);
    	}
	}
    private void destinationDecryptFilePath() {
		model.destinationDecryptFilePath(this.view);
		if(model.getSelectedDecryptFilePath() != null)
    	{
    		view.destinationDecryptButton.setBackground(Color.GREEN);
    		view.destinationDecryptButton.setOpaque(true); 
    		view.destinationDecryptButton.setBorderPainted(true);
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
    public String getColorString() {
		return colorString;
	}

	public void setColorString(String colorString) {
		this.colorString = colorString;
	}
}
