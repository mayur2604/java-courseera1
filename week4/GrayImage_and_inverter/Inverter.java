package GrayImage_and_inverter;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class Inverter {
    public static ImageResource invert(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixel's red to average
            pixel.setRed(255-inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(255-inPixel.getGreen());
            //set pixel's blue to average
            pixel.setBlue(255-inPixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }

    public static void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = invert(inImage);
            gray.setFileName("inverted-"+inImage.getFileName());
            gray.save();
        }
    }


    public static void main(String[] args){
        selectAndConvert();
    }
}
