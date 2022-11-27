package fr.um3.info;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {

    public static void main(String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        File file= new File ("Boom Bap Flick - Quincas Moreira.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();


    }

}
