/**
 * Created by Tegan Straley on 11/18/2016.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.lang.Math.*;
import java.util.ArrayList;
public class Image_decoder {

    public static File linear_decode(String original_filename, String secret_filename) throws IOException {

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
        for(int x = 0; x < image.getWidth(); x++)
            for(int y = 0; y < image.getHeight(); y++) {
                color = Math.abs(image.getRGB(x, y) - secret_image.getRGB(x, y));                
                bits.add(color);
            }
        
        //First 2 delimiter notes end of filename
        if (bits.contains('2')){
        	firstDelimiter = bits.indexOf('2');
        }
        else{
        	System.out.println("Error: No filename found in encrypted file...");
        }
        
        //break up into 2 arraylists, filename, and rest of bits.
        ArrayList<Integer> decoded_filename = (ArrayList<Integer>) bits.subList(0, firstDelimiter - 1);
        ArrayList<Integer> rest_of_file = (ArrayList<Integer>) bits.subList(firstDelimiter + 1, bits.size());

        //Second 2 delimiter notes the end of the encrypted file
        if (rest_of_file.contains('2')){
        	secondDelimiter = bits.indexOf('2');
        }
        else{
        	System.out.println("Error: No end point found in encrypted file...");
        }
        
        //Create string out of decoded_filename
        StringBuilder fileString = new StringBuilder();
        for (Integer number : decoded_filename) {
          fileString.append(number != null ? number.toString() : "");
        }
        System.out.println("The fileString string = " + fileString.toString());
        
        //Create file 
        File output_file = null; 
        output_file = new File(fileString.toString());
        
        //Create file out of textFile ArrayList
        ArrayList<Integer> textFile =  (ArrayList<Integer>) bits.subList(0, secondDelimiter - 1);
        FileWriter writer = new FileWriter(output_file);
        for (Integer intBit : textFile){
        	writer.write(intBit);
        }
        writer.close();

        
        return output_file;
    }

}
