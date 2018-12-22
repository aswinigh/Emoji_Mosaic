package JMiniProject;
import java.awt.Color;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class Learn extends Component {
    int Scale = 1;
    int w;
    BufferedImage img,image;
    int Rvalues[] = new int[850];
    int Gvalues[] = new int[850];
    int Bvalues[] = new int[850];


    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public Learn() {
        try {
            File folder = new File("/Users/aswinighosh/Documents/Affinity Designs");
            File[] listoffiles = folder.listFiles();
            img = ImageIO.read(listoffiles[1]);
            w = img.getWidth();
            makeRGBlist();
        } catch (IOException e) {
        }

    }


    public void makeRGBlist()
    {
        File file = new File("/Users/aswinighosh/Documents/Emojis");
        File[] files = new File[80];
          files= file.listFiles();
        int width=160,height=160,count=1;
        int avgR=0,avgG=0,avgB=0;
        for(File F: files){
            try {

                image = ImageIO.read(F);
               if(image!=null)
               { width=image.getWidth();
                 height=image.getHeight();

                Color c;
                for(int k=0; k<height; k++){

                    for(int j=0; j<width; j++){


                         c = new Color(image.getRGB(j,k));
                        avgR=avgR+c.getRed();
                        avgG=avgG+ c.getGreen();
                        avgB=avgB+c.getBlue();

                        // System.out.println("S.No+ " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                    }
                }

                Rvalues[count]=avgR/(width*height);
                Gvalues[count]=avgG/(width*height);
                Bvalues[count]=avgB/(width*height);

                count++;
            }

            }catch(IOException e) {
                System.out.println("Error"+e);
            }
        }
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(400,400);
        } else {
            return new Dimension(img.getWidth(null)/Scale, img.getHeight(null)/Scale);
        }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new Learn());
        f.pack();
        f.setVisible(true);
    }
}