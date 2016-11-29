# project_stegosaurus
created by Garret Schwartz, Tegan Straley, & Justin Hockenberry for CSCI 4800 at UCDenver (2016).

**1. Purpose**
  To be used as an instructional tool about steganography. Tool can encode or decode a given in photo. Encryption needs an additional photo or file to encrypt into the original photo. Decoding needs the color(which color layer to decrypt from), pixel count, or any other specific way the photo was encrypted (key pattern for randomized encryption).

**2. Build instructions**
  Download zip. If you use Eclipse, set your working directory to where you extracted the zip. In the run configurations, set the Main class to 'steganographyPackage.Main'.
  
**3. Run instructions**
  Make sure JVM is on computer. Either...
      --Download zip and execute jar. 
      --If you completed the build instructions, press Run. 

  
 **4. Program restrictions**
   1. Program can only encode and decode .png images!
   2. Encoded/output image is saved as "encoded_image_<number>.png" in the destination folder set by the user. 
   3. Currently, the program is only working with the Linear encoding and decoding.
  

