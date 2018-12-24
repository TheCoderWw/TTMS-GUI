package xupt.se.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	public static ImageIcon getScaledImage(String url,int width,int height) {
		ImageIcon source = new ImageIcon(url);
		Image scaled = source.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
	    ImageIcon icon   = new ImageIcon(scaled);
		return icon; 
	}
}
