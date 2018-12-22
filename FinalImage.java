package JMiniProject;

import java.io.File;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class FinalImage {
    public static void main(String[] args) {


        File folder = new File("/Users/aswinighosh/Documents/Emojis");
        File[] listoffiles = folder.listFiles();

        String emojifolder = "/Users/aswinighosh/Documents/Emojis";
      //  String ii = listoffiles[1].getPath();
        String ii = "/Users/aswinighosh/Documents/affinity-designer.jpg";
        EmojiMosaic e = new EmojiMosaic(emojifolder);

        ManipulatedImage iimag = new ManipulatedImage(ii);
        iimag.DividePic();
        iimag.makeRGBMatrix();
        e.makeRGBlist();


        BufferedImage finalimg = new BufferedImage(iimag.Iwidth,iimag.Iheight,BufferedImage.TYPE_INT_RGB);
        Graphics2D gd= finalimg.createGraphics();
        BufferedImage timage=null;
        int heightcurrent=0;
        try {for(int i=0;i<iimag.ScaleFactor;i++)
        {
            int widthcurrent=0;
            for(int j=0;j<iimag.ScaleFactor;j++)
            {
                int index=iimag.FindStandardDeviation(i,j,e);

                timage=iimag.resize(e.files[index].getPath(),iimag.w,iimag.h);
                gd.drawImage(timage,widthcurrent,heightcurrent,null);
                widthcurrent+=timage.getWidth();

            }
            heightcurrent+=timage.getHeight();
        }
            gd.dispose();


            File of = new File("/Users/aswinighosh/Documents/Saved.jpg");
            ImageIO.write(finalimg,"jpg",of);

        }catch(IOException p) {
            System.out.println("error:"+p);
        }
    }

}
