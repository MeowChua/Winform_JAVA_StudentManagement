/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technical;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
public class CaptureScreen {
    int i =1;
     public CaptureScreen() throws Exception {

        try {
            System.out.println(" Doi 1 giay nao… ");
            Thread.sleep(1000);
        } catch(Exception e) {
            System.exit(1);
        }
        
        String outFileName = "screen"+i+".jpg";
        i++;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        Robot robot = new Robot();

        BufferedImage image = robot.createScreenCapture(screenRect);

        ImageIO.write(image, "jpg", new File(outFileName));
        System.out.println("Da luu vao file \"" + outFileName + "\".");
        
    }

}


