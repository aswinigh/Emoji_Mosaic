package JMiniProject;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class ManipulatedImage {



    BufferedImage InputImage=null;
    int ScaleFactor=10,Iwidth,Iheight,w,h;
    int [][]Rvalue,Gvalue,Bvalue;
    File inf ;
    ManipulatedImage(String IImageloc)
    {
        try{inf=new File(IImageloc);
            InputImage = ImageIO.read(inf);
        }catch(IOException e) {
            System.out.println("error"+e);
        }
    }


    void DividePic()
    {

        Iwidth = InputImage.getWidth();
        Iheight = InputImage.getHeight();
        System.out.println(Iwidth);
        System.out.println(Iheight);
        w = Iwidth/ScaleFactor;
        h=Iheight/ScaleFactor;

    }
    void makeRGBMatrix()
    {
        Rvalue = new int[ScaleFactor][ScaleFactor];
        Gvalue = new int[ScaleFactor][ScaleFactor];
        Bvalue = new int[ScaleFactor][ScaleFactor];
        int avg_R,avg_B,avg_G;

        Color cr;

        System.out.println(h);
        System.out.println(w);

        for(int x=0;x<ScaleFactor-1;x++)
        {
            for(int y=0;y<ScaleFactor-1;y++)
            {  avg_B =avg_G=avg_R=0;
                for(int i=x*w;i<x*w+w;i++)
                {
                    for(int j=y*h;j<y*h+h;j++)
                    {  // System.out.println(i+" "+j);
                        cr = new Color(InputImage.getRGB(i,j));
                        avg_B+=cr.getBlue();
                        avg_G+=cr.getGreen();
                        avg_R+=cr.getRed();
                    }
                }

                Rvalue[x][y]=avg_R/(w*h);
                Gvalue[x][y]=avg_G/(w*h);
                Bvalue[x][y]=avg_B/(w*h);
            }
        }
    }


    int FindStandardDeviation(int x,int y,EmojiMosaic obj)
    {
        int index=0;
        double sd,leastsd=1000;
        for(int i=0;i<61;i++)
        {
            sd = Math.sqrt(Math.pow(obj.Rvalues[i]-Rvalue[x][y],2)+Math.pow(obj.Gvalues[i]-Gvalue[x][y],2)+Math.pow(obj.Bvalues[i]-Bvalue[x][y],2));
            if(sd<leastsd)
            {
                leastsd = sd;
                index = i;
            }
        }

        return index;
    }



    BufferedImage resize(String inputImagePath,int scaledWidth,int scaledHeight)
            throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }


}
