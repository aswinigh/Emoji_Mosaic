package JMiniProject;

import java.awt.Color;


import java.io.IOException;

import java.awt.Graphics2D;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



/**
 * This class makes a list of average RGB values of all emojis
 */
public class EmojiMosaic{

    BufferedImage image;
    public File file;

    EmojiMosaic(String emojifolder)
    {

        file = new File(emojifolder);
        NFiles = file.list().length;
        files  = file.listFiles();
        Rvalues = new int[NFiles];
        Gvalues = new int[NFiles];
        Bvalues = new int[NFiles];
    }

    int	NFiles;
    public  File[] files;



   // files = file.listFiles();

    int Rvalues[];
    int Gvalues[];
    int Bvalues[];


    public void makeRGBlist()
    {

        int width=1,height=1,count=0;

        for(File F: files){
            try {
                int avgB,avgG,avgR;
                avgR=avgB=avgG=0;
                image = ImageIO.read(F);
                if(image!=null){
                    width=image.getWidth();
                    height=image.getHeight();

                    Color c;
                    for(int l=0; l<height; l++){

                        for(int m=0; m<width; m++){


                            c = new Color(image.getRGB(m,l));
                            avgR=avgR+c.getRed();
                            avgG=avgG+ c.getGreen();
                            avgB=avgB+c.getBlue();


                        }
                    }

                    Rvalues[count]=avgR/(width*height);
                    Gvalues[count]=avgG/(width*height);
                    Bvalues[count]=avgB/(width*height);

                    count++;
                }

            }catch(IOException e) {
                System.out.println(width+" "+height);
                System.out.println("Error"+e);
            }
        }
    }
}