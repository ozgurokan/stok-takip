package com.ozgurokanozdal.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageHelper {

    private static final String imageFolderPath = "C:\\Users\\Acer\\Desktop";
    private static final File imageFolder = new File(imageFolderPath);
    private static final JFileChooser chooser = new JFileChooser();


    private static final FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");




    public static File imageChooser(){

        File image = null;
        chooser.setCurrentDirectory(imageFolder);
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            image = chooser.getSelectedFile();
        }

        return image;

    }

    public static Image resizeImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        return bufferedImage.getScaledInstance(400,400,Image.SCALE_AREA_AVERAGING);
    }

    public static byte[] convertToBlob(ImageIcon icon){
        byte[] imageToBlob= null;

        // that's enough coding today...continue tomorrow. working with closed eyes is not a good way to improve yourself...
        return imageToBlob;

    }





}
