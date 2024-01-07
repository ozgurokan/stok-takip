package com.ozgurokanozdal.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

public class ImageHelper {

    private static final String imageFolderPath = "C:\\Users\\Acer\\Desktop";
    private static final File imageFolder = new File(imageFolderPath);
    private static final JFileChooser chooser = new JFileChooser();


    private static final FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");



    public static String imageChooser(){

        String image = null;
        chooser.setCurrentDirectory(imageFolder);
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            image = chooser.getSelectedFile().getAbsolutePath();
        }

        return image;

    }

    public static Image resizeImage(String filePath) throws IOException {
        File image = new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(image);
        return bufferedImage.getScaledInstance(300,400,Image.SCALE_AREA_AVERAGING);
    }






}
