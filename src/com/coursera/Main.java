package com.coursera;


import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.util.Scanner;

import java.io.File;
import java.util.Scanner;

class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
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
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }

    public void selectAndConvert(){
        DirectoryResource dir = new DirectoryResource();
        Scanner sc = new Scanner(System.in);
        for(File f : dir.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
            System.out.println("Would you like to save it?");
            System.out.println("1 - YES");
            System.out.println("2 - NO");
            String answer = sc.next();
            switch (answer){
                case("1"):
                    String fname = inImage.getFileName();
                    String newName = "GrayCopy-" + fname;
                    gray.setFileName(newName);
                    gray.save();
                    System.out.println("File name is " + newName);
                    break;
                case("2"):
                    break;
                default:
                    System.out.println("Invalid answer.");
                    break;
            }
        }
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
}

class Inversion {
    public ImageResource makeInvert(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int greenInvert = (255 - inPixel.getGreen());
            int redInvert = (255 - inPixel.getRed());
            int blueInver = (255 - inPixel.getBlue());
            pixel.setRed(redInvert);
            pixel.setGreen(greenInvert);
            pixel.setBlue(blueInver);
        }
        return outImage;
    }

    public void selectAndConvert(){
        Scanner sc = new Scanner(System.in);
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeInvert(inImage);
            gray.draw();
            System.out.println("Would you like to save it?");
            System.out.println("1 - YES");
            System.out.println("2 - NO");
            String answer = sc.next();
            switch (answer){
                case("1"):
                    String fname = inImage.getFileName();
                    String newName = "InvertCopy-" + fname;
                    gray.setFileName(newName);
                    gray.save();
                    System.out.println("File name is " + newName);
                    break;
                case("2"):
                    break;
                default:
                    System.out.println("Invalid answer.");
                    break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        while(true){
            System.out.println("Press 1 to convert photo into Black&White!");
            System.out.println("Press 2 to convert photo into Negtive!");
            System.out.println("Press 3 to Exit!");
            Scanner sc = new Scanner(System.in);
            String choise = sc.next();
            switch (choise){
                case ("1"):
                    GrayScaleConverter gray = new GrayScaleConverter();
                    gray.selectAndConvert();
                    break;
                case ("2"):
                    Inversion invert = new Inversion();
                    invert.selectAndConvert();
                    break;
                case ("3"):
                    System.exit(1);
                default:
                    System.out.println("Invalid value, tru again!");
            }
        }
    }
}
