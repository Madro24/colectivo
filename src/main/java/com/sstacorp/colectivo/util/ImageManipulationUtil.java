package com.sstacorp.colectivo.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageManipulationUtil {

	/*
	 * For local testing only
	 */
	public static void main(String[] args) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("picture.jpg"));
		} catch (IOException e) {
		}
		
		BufferedImage croppedImage = null;
		try {
			croppedImage = crop(img, 0, 272, 2448, 2448);
		    File outputfile = new File("cropped.png");
		    ImageIO.write(croppedImage, "png", outputfile);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		BufferedImage defaultImage = null;
		try {
		    defaultImage = resize(croppedImage, 400, 400);
		    File outputfile = new File("default.png");
		    ImageIO.write(defaultImage, "png", outputfile);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		BufferedImage circleImage = null;
		try {
			circleImage = circleCrop(defaultImage);
		    File outputfile = new File("circle.png");
		    ImageIO.write(circleImage, "png", outputfile);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		BufferedImage tinyImage = null;
		try {
			tinyImage = resize(circleImage, 60, 60);
		    File outputfile = new File("tiny.png");
		    ImageIO.write(tinyImage, "png", outputfile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height) throws RasterFormatException{
		return image.getSubimage(x, y, width, height);
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height){
		int finalWidth = width;
		int finalHeight = height;
		double factor = 1.0d; // factor for resize
		if(image.getWidth() > image.getHeight()){ // Scale
			factor = (double) image.getHeight()/(double)image.getWidth();
			finalHeight = (int) (finalWidth * factor);
		}
		else{
			factor = (double) image.getWidth()/(double) image.getHeight();
			finalWidth = (int) (finalHeight * factor);
		}
		
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resized.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.drawImage(image, (width - finalWidth)/2, (height - finalHeight)/2, finalWidth, finalHeight, null);
	    g2.dispose();
		
		return resized;
	}
	
	public static BufferedImage circleCrop(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();

		
		BufferedImage circleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = circleImage.createGraphics();
		
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, width, height, width, height));
		
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);
		
		g2.dispose();
		
		return circleImage;
	}

}