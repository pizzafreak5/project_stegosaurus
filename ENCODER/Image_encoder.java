/**
 * Created by Garrett on 10/18/2016.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.lang.Math.*;
public class Image_encoder {

    /*
    public static void main(String [] args){
        linear_map("BLUE", "black_blank.png", "default_secret.txt","default_out.png");
    }
    */

    public static String linear_map(String color_field, String host_filename, String secret_filename, String output_filename) {

        /*
        Description of linear mapping function:
        Linear map reads the secret file byte-by-byte and for every bit in the byte,
        increases the relevant colorfield in a host file pixel by one if the bit is 1.
        For the special case that the host pixel color field is 0xFF, the maximum value for
        a byte, the color field value is decreased by one, to make 0xFE.
        The host file pixel is then advanced to the next pixel.

        Some pixels in the modified image will have their color field augmented by 2 values, rather
        than one, Ex. 0xA2 -> 0xA4, 0xFE -> 0xFC or 0xFF -> 0x FD. This is a special symbol, delimiting data that
        is meant for the decoder to use. For example, the modified image will include a run of data
        which represents the secret file filename, then a delimiter pixel, and then a run of data
        representing the contents of the secret file, and then a delimiter to let the decoder know
        that the data set has ended.

        Obviously, this scheme with a linear encoding is terribly easy to discover, but considering
        this encoding scheme is for educational purposes, it doesn't matter.

        For the return, if there was an error, this function will return an error code. If there wasn't,
        this function will return the filename of the logging file
         */

        /*
        Now for some notes about data structures and what-not:
        This algorithm works under the assumption that the secret file will be encoded into an image,
        in other words, that the host file is an image. The host file is read in with java's
        java.awt.image class, and represented as a java.awt.image.BufferedImage

        This is cool because the BufferedImage datastructure is essentialy a matrix of pixels,
        each pixel represented by an int, or 4 bytes, such as 0x00112233.
        The fist byte, 0x00 in the above example, represents the alpha channel. In a jpg, this is
        always 0x00. The next byte, 0x11, is red, and 0x22 and 0x33 are green and blue respectively.

        The secret file is a FileInputStream so that the file can be read byte-by-byte. When a byt is
        read from the stream, it is read into an integer, and only the last byte in the integer will
        be filled with data for the stream. So, the integer will take the form:
            0x 00 00 00 xx
        where xx is the byte from the stream.

        Important for this encoder is a knowledge of bit-wise operators, such as:
        | bit-wise OR
        ^ bit-wise XOR
        & bit-wise AND
        >> bit-shift right
        << bit-shift left
        ~ bit-wise compliment

        About some future changes:
        Some actions are repeated enough to warrant they're

         */

        BufferedImage image = null; //This is initialy set to the host image, but will become
                                    // the modified image.
        File host_file = null;      //This is the host image file
        FileInputStream secret_file = null; //This is the secret file, and it can be any file-type.


        //Attempt to read file and import image
        try{
            //Open host file and read into image
            host_file = new File(host_filename);
            image = ImageIO.read(host_file);

            //Open secret file
        } catch(IOException e){
            System.out.println("You blew it up! Damn you! Damn you all to hell! " + e);
        }

        try{
            secret_file = new FileInputStream(secret_filename);

            //Open secret file
        } catch(IOException e){
            System.out.println("And it blew up again. " + e);
        }

        //Get width and height of image.
        int width = image.getWidth();
        int height = image.getHeight();
        //Get color model, probably not gonna use this, but this tells us if there is an alpha channel
         ColorModel color_model = image.getColorModel(); //Might not use this, but this tells us if there is an
                                                        //alpha channel

        int field_number = 0;
        int field_mask = 0x00000000;


        //Setup the relevant color field information:
        if (color_field.equals("RED")){
            field_number = 2;
            field_mask = 0x00ff0000;
        }

        else if (color_field.equals("GREEN")){
            field_number = 1;
            field_mask = 0x0000ff00;
        }

        else if (color_field.equals("BLUE")){
            field_number = 0;
            field_mask = 0x000000ff;
        }

        else{
            //This is a bad place to be
            return("ERROR: Invalid color field");
        }
        //For this encoder to work, the host file must have more pixels than bits in the secret file,
        //specificaly, it must contain at least
        // (number_of_bits_in_secret + number_of_delimiters + bits_in_filename_and_other_decoder_data)
        // pixels.
        //In this case, there are only 2 delimiters and the decoder data consists only of the filename,
        //so we must only check that the host file contains
        //(number_of_bits_in_secret + 2 + bits_in_filename)
        //pixels.

        int total_pixels = width * height;
        long secret_length_in_bytes;

        try {
            secret_length_in_bytes = secret_file.getChannel().size();
        } catch (IOException e) {
            e.printStackTrace();
            secret_length_in_bytes = 0;
            return ("Error: Could not read secret file filesize");
        }

        //Note that in java, a character is 2 bytes long, hence the (secret_filename.length() * 16)
        long total_data_size = (secret_length_in_bytes * 8) + 2 + (secret_filename.length() * 16);
        //While this may seem like it could grow quickly, an fair bit of information can be held in
        //3kb of text, which would only require 24,000 pixels, or a 160 x 150. A full HR photograph from
        //a DSLR at 6000x4000 pixels could hold 24,000,000 bits. For reference, the completed works of
        //William Shakespeare come in at 28,000,000 bits. If we modified the algorithm to use all three color
        //fields, thus holding three bits per pixel, we could hold 72,000,000 bits.


        if (!(total_pixels > total_data_size)){
            //The image is not big enough
            return("Error: Host file too small for secret file!");
        }

        //Encode the data into the modified image file
        int pixel_x_counter = 0;
        int pixel_y_counter = 0;

        //These are used when modifing pixels
        int current_pixel = 0;
        int new_pixel = 0;
        int modify_pixel = 0;
        int[] next_pixel;


        //Encode the filename
        for(char c : secret_filename.toCharArray()){
            //this for loop moves through every bit in the char c and encodes it into
            //the image
            for (int i = 0; i < 16; i++){
                //shift the bits in the char such that
                // the correct bit read from L -> R is in
                // the rightmost spot to be
                int target_bit = c >> (15 - i);
                //turn all bits to 0 with mask
                target_bit = target_bit & 0x00000001;

                current_pixel = image.getRGB(pixel_x_counter,pixel_y_counter);


                //check if bit is 1
                if (target_bit > 0){

                    new_pixel = modify_pixel(current_pixel, color_field, false);

                    //Finaly, we will write the pixel to the image
                    image.setRGB(pixel_x_counter,pixel_y_counter,new_pixel);
                    System.out.println("At " + pixel_x_counter + " , " + pixel_y_counter);
                    //Easy, right?
                }

                //To figure out the appropriate pixel, we must check if
                //x is in the bounds of the image. if x is equal to the
                //width, set x to 0 (like a carrage return) and increment y
                // (like a newline)

                next_pixel = advance_pixel(height, width, pixel_x_counter, pixel_y_counter);
                pixel_x_counter = next_pixel[0];
                pixel_y_counter = next_pixel[1];


            }
        }

        //Now we've written the filename to the host file.
        //Next, we need to put a special marker to denote the end of the filename.

        current_pixel = image.getRGB(pixel_x_counter, pixel_y_counter);

        new_pixel = modify_pixel(current_pixel, color_field, true);

        //And now write the new pixel
        image.setRGB(pixel_x_counter,pixel_y_counter,new_pixel);
        System.out.println("At " + pixel_x_counter + " , " + pixel_y_counter);

        //And advance the pixel
        next_pixel = advance_pixel(height, width, pixel_x_counter, pixel_y_counter);
        pixel_x_counter = next_pixel[0];
        pixel_y_counter = next_pixel[1];

        //Now that we have the filename and the delimiter, we have to read the actual image into the file
        int input_byte;
        int temporary_byte;

        try {
            while ((input_byte = secret_file.read()) != -1){

                //When the input byte is read, it is stored in the last byte of the integer input_byte
                //Because it reads a byte at a time, we have check every read 8 times to check
                //the bits individualy
                for (int i = 0; i < 8; i++){
                    temporary_byte = input_byte;

                    //Since i ranges from 0 to 7 inclusively, an AND with a power of two
                    // will check each bit-space from the least significant bit to the end
                    //of the byte.
                    temporary_byte = temporary_byte & ((int)(Math.pow(2.0,(double) i)));

                    //Now to check if the bit is high
                    if (temporary_byte > 0)
                    {
                        //Of course if it is, check if the color field of the current pixel is at max (FF)
                        current_pixel = image.getRGB(pixel_x_counter, pixel_y_counter);

                        new_pixel = modify_pixel(current_pixel, color_field, false);

                        image.setRGB(pixel_x_counter,pixel_y_counter, new_pixel);
                        System.out.println("At " + pixel_x_counter + " , " + pixel_y_counter);


                    }

                    //And advance the pixel
                    next_pixel = advance_pixel(height, width, pixel_x_counter, pixel_y_counter);
                    pixel_x_counter = next_pixel[0];
                    pixel_y_counter = next_pixel[1];

                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Blew up when reading secret file: " + e);
        }

        //Now all that is left is to place the final delimiter to let the decoder know that the
        //information has ended.

        current_pixel = image.getRGB(pixel_x_counter, pixel_y_counter);

        new_pixel = modify_pixel(current_pixel, color_field, true);

        //And now write the new pixel
        image.setRGB(pixel_x_counter,pixel_y_counter,new_pixel);
        System.out.println("At " + pixel_x_counter + " , " + pixel_y_counter);

        //No need to advance the pixel, because now we are done

        try{
            File outputfile = new File(output_filename);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("FUCK" + e);
        }

        return "Encoded Secret File Successfully";


    }

    //return the next position of x and y
    public static int [] advance_pixel(int image_height, int image_width, int x, int y){
        int[] result;
        result = new int[2];

        if (x == (image_width - 1)){
            result[0] = 0;
            result[1] = (y+1);
        }
        else{
            result[0] = (x + 1);
            result[1] = y;
        }

        return result;
    }

    //This function takes the current pixel color, and ticks the appropriate color field up or down
    //one or two, depending on delimiter's value. it Returns the new pixel
    // color field
    public static int modify_pixel (int current_pixel, String colorfield, boolean delimiter){

        int shift_multiplier = 0;
        int field_mask = 0x0000FF;

        if (colorfield.equals("RED")){
            shift_multiplier = 2;
        }
        else if (colorfield.equals("GREEN")){
            shift_multiplier = 1;
        }

        int work_pixel = current_pixel;
        int result_pixel = 0;

        work_pixel = work_pixel >> (8 * shift_multiplier);

        if (delimiter){
            if (work_pixel == 0x000000FF || work_pixel == 0x000000FE){
                System.out.println("Found a pixel w/ delim at limit");

                result_pixel = current_pixel - (0x00000002 << (8 * shift_multiplier));
            }
            else{
                result_pixel = current_pixel + (0x00000002 << (8 * shift_multiplier));
            }
        }
        else{
            if (work_pixel == 0x000000FF){
                result_pixel = current_pixel - (0x00000001 << (8 * shift_multiplier));
            }
            else{
                result_pixel = current_pixel + (0x00000001 << (8 * shift_multiplier));
            }
        }

        System.out.println("Converted pixel " + Integer.toHexString(current_pixel) + " to " + Integer.toHexString(result_pixel));
        return result_pixel;
    }

}
