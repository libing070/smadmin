package com.aspire.wifi.manage.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.StringUtils;

public class PictureUtil {
	public static void main(String[] args)
    {
		int index = "abc.jpg".lastIndexOf('.');
		String postFix = "abc.jpg".substring(index+1);
		System.out.println(postFix);
		//File dy = new File("D:/aspire/document/IMG_0068.JPG");
		File dy = new File("D:/aspire/document/IMG_0068.JPG");
    	byte[] bytes = compressPicByByte(ReadFile.getBytesFromFile(dy),"jpeg");
        
    	byte2image(bytes, "D:/aspire/document/IMG_0068_1.JPG");
    	
    	/*if(compressPic("D:/aspire/document/Desert.jpg", "D:/aspire/document/aaa1.jpg",(float)0.5))
        {
            System.out.println("压缩成功！"); 
        }
        else
        {
            System.out.println("压缩失败！"); 
        }*/
    }
    
	/**
	 * 通过图片路径压缩图片
	 * @param srcFilePath
	 * @param descFilePath
	 * @param quality 压缩比 0~1范围,越大压缩质量越高
	 * @return
	 */
    public static boolean compressPic(String srcFilePath, String descFilePath, float quality)
    {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(quality);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try
        {
            if(StringUtils.isBlank(srcFilePath))
            {
                return false;
            }
            else
            {
                file = new File(srcFilePath);
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);

                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 通过byte数组压缩图片
     * @param data 图片byte数组
     * @param imagetype 图片格式，jpg、jpeg、pgn、gif等
     * @return 压缩后的byte数组
     */
    public static byte[] compressPicByByte(byte[] data,String imagetype){
    	if(data.length == 0)
    		return data;
    	
    	int basicSize = 0;
    	String basicUploadPicSize = "300000";
		try {
			basicUploadPicSize = GetConfigFile.getInstance().getProperties("ImageUploadSize");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		basicSize = Integer.parseInt(basicUploadPicSize);
    	
		if(data.length <= basicSize){
			return data;
		}
		
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        
        BufferedImage src = null;
        ByteArrayOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        if(imagetype == null || "".equals(imagetype)){
        	imagetype = "jpg";
        }
        imgWrier = ImageIO.getImageWritersByFormatName(imagetype).next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(getQuality(data.length));
                         
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try
        {
            src = ImageIO.read(is);
            out = new ByteArrayOutputStream(data.length);

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            
            out.flush();
            out.close();
            is.close();
            data = out.toByteArray();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private static float getQuality(int length) {
		System.out.println("length:"+length);
		String basicUploadPicSize = "300000";
		int basicSize = 0;
		try {
			basicUploadPicSize = GetConfigFile.getInstance().getProperties("ImageUploadSize");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		basicSize = Integer.parseInt(basicUploadPicSize);
		BigDecimal b1 = new BigDecimal(basicSize);
	    BigDecimal b2 = new BigDecimal(length);
	    String b3 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).toString();
		return Float.parseFloat(b3);
	}

	//byte数组到图片
    private static void byte2image(byte[] data,String path){
      if(data.length<3||path.equals("")) return;
      try{
      FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
      imageOutput.write(data, 0, data.length);
      imageOutput.close();
      System.out.println("Make Picture success,Please find image in " + path);
      } catch(Exception ex) {
        System.out.println("Exception: " + ex);
        ex.printStackTrace();
      }
    }
}
