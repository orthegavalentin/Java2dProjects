package fr.um3.info;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FermeUtils {
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public static List<String> generateMapFromTextFile(String filepath)  {

        // list that holds strings of a file
        List<String> listOfStrings = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir"));
        sb.append("\\");
        sb.append(filepath);

       try{ BufferedReader bf = new BufferedReader(new FileReader(sb.toString()));

        // read entire line as string
        String line = bf.readLine();

        // checking for end of file
        while (line != null) {
            listOfStrings.add(line.replaceAll(",","").strip());
            System.out.println(line.replaceAll(",","").strip());
            line = bf.readLine();
        }

        // closing bufferreader object
        bf.close();}
       catch(IOException ex){
           System.out.println(ex.toString());
           System.out.println("****************************************");
           System.out.println(sb.toString());
       }

        return listOfStrings;
    }
}
