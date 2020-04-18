package main.java.com.timelessapps.javafxtemplate.helpers.OCR;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/** 
 * You will mainly be using getRSText(rectangle) to get text only sections and getRSNumber(rectangle) to get number only sections. 
 * The getRSText function uses Tesseract and has moderate error rates for RS font. You can pass an optional String parameter (whitelist) to narrow down results. 
 * The getRSNumber function uses custom image recognition for only numbers in RS font. It must be only 8 pixels high. 
 * @author MT
 *
 */
public class RSImageReader 

{
	String[] pixelToString = {"WBBBBBBWNBWWWWWWBNBWWWWWWBNBWWWWWWBNWBBBBBBWN", "WBWWWWWBNBBBBBBBBNWWWWWWWBN", "WBWWWWBBNBWWWWBWBNBWWWBWWBNBWWBWWWBNWBBWWWWBN", "WBWWWWBWNBWWBWWWBNBWWBWWWBNWBBWBBBWN", "BBBBBBWWNWWWWWBWWNWWWBBBBBNWWWWWBWWN", "BBBBWWBWNBWWBWWWBNBWWBWWWBNBWWWBBBWN", "WWBBBBBWNWBWWBWWBNBWWBWWWBNBWWBWWWBNWBWWBBBWN", "BWWWWWBBNBWWWBBWWNBWBBWWWWNBBWWWWWWN", "WBBWBBBWNBWWBBBBWNBWWBWWWBNBWWBWWWBNWBBWBBBWN", "WBBWWWWWNBWWBWWWWNBWWWBWWWNBWWWBWWWNWBBBBBBBN"};
	
	Rectangle goldNumber = new Rectangle(1260, 452, 60, 8);
	Rectangle firstRowStatus = new Rectangle(652, 95, 40, 15);
	Rectangle secondRowStatus = new Rectangle(652, 133, 90, 15);
	Rectangle firstRowItem = new Rectangle(738, 95, 90, 15);
	Rectangle secondRowItem = new Rectangle(738, 133, 90, 15);
	Rectangle firstRowPrice = new Rectangle(939, 98, 60, 8);
	Rectangle secondRowPrice = new Rectangle(939, 136, 60, 8);
	
	public RSImageReader()
	{
		
	}
    
    private String getImageTextUsingTess(BufferedImage image) 
    {
        ITesseract instance = new Tesseract();
        try 
        {
        	String imgText = instance.doOCR(image);
    		return imgText;
        } 

        catch (TesseractException e) 
        {
        	e.getMessage();
        	return "Error while reading image";
        }
	}
    
    private String getImageTextUsingTess(BufferedImage image, String whiteList) 
    {
        ITesseract instance = new Tesseract();
        try 
        {
        	instance.setTessVariable("tessedit_char_whitelist", whiteList); //whitelist example can be "coins0123456789"
        	String imgText = instance.doOCR(image);
    		return imgText;
        } 

        catch (TesseractException e) 
        {
        	e.getMessage();
        	return "Error while reading image";
        }
	}
    
    private BufferedImage getSnapShot(Rectangle rectangle) throws AWTException 
    {
    	BufferedImage capture = new Robot().createScreenCapture(rectangle);
    	try {
			ImageIO.write(capture, "tif", new File("C:\\Users\\Max\\Desktop\\tempimage\\images\\test2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return capture;
    }
    
    /** For orange text sections **/
    private BufferedImage convertOrangeTextToBlackWhite(BufferedImage input) 
    {
    	BufferedImage image = input;
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++) 
			{
				Color color = new Color(image.getRGB(x, y));
				if (color.getRed()== 255 && color.getGreen() == 152 && color.getBlue() == 31) //If color does equals to the proper shade of orange that the text uses. 
				{
					Color black = new Color(0, 0, 0);
					image.setRGB(x, y, black.getRGB());
				}
				else if (color.getRed()== 255 && color.getGreen() == 184 && color.getBlue() == 63) //If color does equals to the proper shade of orange that the text uses. 
				{
					Color black = new Color(0, 0, 0);
					image.setRGB(x, y, black.getRGB());
				}
				else if (color.getRed()== 255 && color.getGreen() == 255 && color.getBlue() == 0) //If color does equals to the proper shade of yellow that the text uses. 
				{
					Color black = new Color(0, 0, 0);
					image.setRGB(x, y, black.getRGB());
				}
				else
				{
					Color white = new Color(255, 255, 255);
					image.setRGB(x, y, white.getRGB());
				}
			}
		}
		try 
		{
			ImageIO.write(image, "tif", new File("C:\\Users\\Max\\Desktop\\tempimage\\images\\test.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
    }
    
    /** For white text on login screen **/
    private BufferedImage convertWhiteTextToBlackWhite(BufferedImage input) 
    {
    	BufferedImage image = input;
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++) 
			{
				Color color = new Color(image.getRGB(x, y));
				if (color.getRed()== 255 && color.getGreen() == 255 && color.getBlue() == 255) //If color does equals to white
				{
					Color black = new Color(0, 0, 0);
					image.setRGB(x, y, black.getRGB());
				}
				else
				{
					Color white = new Color(255, 255, 255);
					image.setRGB(x, y, white.getRGB());
				}
			}
		}
		try 
		{
			ImageIO.write(image, "tif", new File("C:\\Users\\Max\\Desktop\\tempimage\\images\\test.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
    }
    
    /** For black text on GE search area **/
    private BufferedImage convertBlackTextToBlackWhite(BufferedImage input) 
    {
    	BufferedImage image = input;
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++) 
			{
				Color color = new Color(image.getRGB(x, y));
				if (color.getRed()== 0 && color.getGreen() == 0 && color.getBlue() == 0) //If color does equals to black
				{
					Color black = new Color(0, 0, 0);
					image.setRGB(x, y, black.getRGB());
				}
				else
				{
					Color white = new Color(255, 255, 255);
					image.setRGB(x, y, white.getRGB());
				}
			}
		}
		try 
		{
			ImageIO.write(image, "tif", new File("C:\\Users\\Max\\Desktop\\tempimage\\images\\test.png"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
    }
    
    private BufferedImage getResizedImage(BufferedImage input, double percent) throws IOException 
    {
        BufferedImage inputImage = input;
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        return getRescaledImage(input, scaledWidth, scaledHeight);
    }
    
    private BufferedImage getRescaledImage(BufferedImage inputImage, int scaledWidth, int scaledHeight) throws IOException 
    {
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }
    /** End of orange text section. **/
    
    //Input should be from convertOrangeTextToBlackWhite(). Converts black and white image into a string. It goes top to bottom, then left to right on the image and appends B for black and W for white, then N for new line
    private String getImagePixelString(BufferedImage image)
    {
		StringBuilder sb = new StringBuilder("");
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++)
			{
				Color color = new Color(image.getRGB(x, y));
				if (color.getRed()== 255 && color.getGreen() == 255 && color.getBlue() == 255) //If color is white
				{
					sb.append("W");
				}
				else if (color.getRed()== 0 && color.getGreen() == 0 && color.getBlue() == 0) //If color is black
				{
					sb.append("B");
				}
			}
			sb.append("N");
		}
		
    	return sb.toString();
    }
    
    private Integer getRSNumberFromBlackAndWhiteImage(BufferedImage image)
    {
    	String convertedImage = getImagePixelString(image);
    	String[] convertedImageSectioned = convertedImage.split("WWWWWWWWN");
    	StringBuilder numbers = new StringBuilder("");
    	
    	for (int i = 0; i < convertedImageSectioned.length; i++)
    	{
    		Integer number = getPixelStringValue(convertedImageSectioned[i]);
    		if (number != null)
    		{
    			numbers.append(number.toString());
    		}
    	}
    	
    	int result; 
    	
    	try 
    	{
    		result = Integer.parseInt(numbers.toString());
    	}
    	catch (Exception e)
    	{
    		return null;
    	}
    	
		return result;
    }
    
    /**
     * Returns a 0-9 integer value depending on the pixelToString values. 
     * 
     * @param pixelString
     * @return 0-9 value based on the string. Does not return letters/symbols. 
     */
    private Integer getPixelStringValue(String pixelString)
    {
    	int result = Arrays.asList(pixelToString).indexOf(pixelString);
    	if (result == -1)
    	{
    		return null;
    	}
    	else
    	{
    		return result;
    	}
    }
    
    public BufferedImage convertFileToBufferedImage(File input) throws IOException
    {
		ImageInputStream iis = ImageIO.createImageInputStream(input);
		BufferedImage image = ImageIO.read(iis);
		return image;
    }
    
    //This this uses Tesseract-OCR to get text, it is not reliable for getting numbers in RS font. 
    public String getRSText(Rectangle rectangle) throws Exception
    {
    	double percent = 4.0; // Needs to be 400% larger for better results. 
    	BufferedImage originalSnapShot = getSnapShot(rectangle);
    	BufferedImage filteredImage = convertOrangeTextToBlackWhite(originalSnapShot);
    	BufferedImage resizedImage = getResizedImage(filteredImage, percent);
    	
    	String imageText = getImageTextUsingTess(resizedImage);
    	return imageText;
    }
    
    /**
     * This function takes a set of white-listed letters. They will be the only letters that will be returned when scanning the section. 
     * @param rectangle
     * @param whitelist
     * @return Text from the rectangular section, using only white-listed letters. 
     * @throws Exception
     */
    public String getRSText(Rectangle rectangle, String whitelist) throws Exception
    {
    	double percent = 4.0; // Needs to be 400% larger for better results. 
    	BufferedImage originalSnapShot = getSnapShot(rectangle);
    	BufferedImage filteredImage = convertOrangeTextToBlackWhite(originalSnapShot);
    	BufferedImage resizedImage = getResizedImage(filteredImage, percent);
    	
    	String imageText = getImageTextUsingTess(resizedImage, whitelist);
    	return imageText;
    }
    
    public String getRSLoginText(Rectangle rectangle) throws Exception
    {
    	double percent = 4.0; // Needs to be 400% larger for better results. 
    	BufferedImage originalSnapShot = getSnapShot(rectangle);
    	BufferedImage filteredImage = convertWhiteTextToBlackWhite(originalSnapShot);
    	BufferedImage resizedImage = getResizedImage(filteredImage, percent);
    	
    	String imageText = getImageTextUsingTess(resizedImage);
    	return imageText;
    }
    
    public String getGESearchAreaText(Rectangle rectangle) throws Exception
    {
    	double percent = 4.0; // Needs to be 400% larger for better results. 
    	BufferedImage originalSnapShot = getSnapShot(rectangle);
    	BufferedImage filteredImage = convertBlackTextToBlackWhite(originalSnapShot);
    	BufferedImage resizedImage = getResizedImage(filteredImage, percent);
    	
    	String imageText = getImageTextUsingTess(resizedImage);
    	return imageText;
    }
    
    public Integer getRSNumber(Rectangle rectangle) throws Exception
    { 
    	BufferedImage originalSnapShot = getSnapShot(rectangle);
    	BufferedImage blackAndWhiteImage = convertOrangeTextToBlackWhite(originalSnapShot);
    	Integer number = getRSNumberFromBlackAndWhiteImage(blackAndWhiteImage);
    	return number;
    }
    
	public static boolean isSameColor(Color firstColor, Color secondColor)
	{
		if (firstColor.getRed() == secondColor.getRed())
		{
			if  (firstColor.getGreen() == secondColor.getGreen())
			{
				if (firstColor.getBlue() == secondColor.getBlue())
				{
					return true;
				}
			}
		} 
		else 
		{
			return false;
		}
		return false;
	}
}
