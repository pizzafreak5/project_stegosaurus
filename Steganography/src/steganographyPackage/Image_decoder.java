/**
 * Created by Tegan Straley on 11/18/2016.
 */

package steganographyPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
public class Image_decoder {

    public static String linear_decode(String original_filename, String secret_filename) throws IOException {

        /*
        Description of linear decoding function:
        

         */

        BufferedImage image = null; //This is initially set to the original image
        BufferedImage secret_image = null; //This is initially set to the original image
        File original_file = null;  //This is the original unmodified image file
        File secret_file = null; //This is the secret file that has the encrypted image in it


        //Attempt to read file and import image
        try{
            //Open original file and read into image
            original_file = new File(original_filename);
            image = ImageIO.read(original_file);
            
        } catch(IOException e){
            System.out.println("You blew it up! Damn you! Damn you all to hell! " + e);
        }
        
        //Open secret encrypted image file
        try{
            secret_file = new File(secret_filename);
            secret_image = ImageIO.read(secret_file);

        } catch(IOException e){
            System.out.println("And it blew up again. " + e);
        }


        //Creation of variables
        int color;
        int firstDelimiter = 0;
        int secondDelimiter = 0;
        ArrayList<Integer> bits = new ArrayList<>();
        
        
        //Subtract every original pixel from encrypted image pixel to get either a 0, 1, or 2.  
        for(int y = 0; y < image.getWidth(); y++)
            for(int x = 0; x < image.getHeight(); x++) {
                color = Math.abs(image.getRGB(x, y) - secret_image.getRGB(x, y));                
                bits.add(color);
            }
        
        //if difference is 1, 256, 65536 == 1
        
        //0 keep it
        
        
        //2
        
        
        
        //First 2 delimiter notes end of filename
        if (bits.contains(2)){
        	firstDelimiter = bits.indexOf(2);
        	secondDelimiter = bits.lastIndexOf(2);
        }
        else{
        	System.out.println("Error: No filename delimiter found in encrypted file...");
        }
        
        System.out.println("first is " + firstDelimiter);
        System.out.println("bits.size() is " + bits.size());
        System.out.println("second is " + secondDelimiter);

        //break up into 2 lists, filename, and rest of bits.
        List<Integer> decoded_filename = bits.subList(0, firstDelimiter);

        //Create string out of decoded_filename
        StringBuilder fileString = new StringBuilder();
        for (Integer number : decoded_filename) {
          fileString.append(number != null ? number.toString() : "");
        }
        System.out.println("The fileString string = " + fileString.toString());
        
        
        //break up into last arraylist which is the secret message
        List<Integer> secret_message = bits.subList(firstDelimiter + 1, secondDelimiter );

        
        //Create string out of decoded_filename
        StringBuilder messageString = new StringBuilder();
        for (Integer number : secret_message) {
        	messageString.append(number != null ? number.toString() : "");
        }
        System.out.println("The messageString string = " + messageString.toString());
        
        
        
        
        FileOutputStream outputfile = new FileOutputStream("Default_out.txt");
        
        //
        int out_byte = 0;
        int out_byte_count = 0;
        for ( int i : secret_message){
        	if (i < 2){
        		out_byte += i;
            	if (out_byte_count < 7){
            		out_byte = out_byte << 1;
            	}
            	
            	out_byte_count++;
            	
            	if(out_byte_count == 8){
            		out_byte_count = 0;
            		try{
            			outputfile.write(out_byte);
            		}
            		catch (IOException e){
            			System.out.println("Can't write" + e);
            		}
            		
            		out_byte = 0;
            	}
        	}
        	else{
        		outputfile.write(out_byte);
        	}
        	
        }
        
        outputfile.close();

        
        return "Default_out.txt";
    }

}
