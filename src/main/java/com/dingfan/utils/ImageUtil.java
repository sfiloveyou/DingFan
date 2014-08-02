package com.dingfan.utils;

import java.awt.Dimension;
import java.awt.Rectangle;

import magick.CompositeOperator;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

public class ImageUtil {
	static {
		// 不能漏掉这个，不然jmagick.jar的路径找不到
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * 压缩图片
	 * 
	 * @param imgPath 源文件路径
	 * @param toPath 缩略 图路径
	 * @param width 设定宽
	 * @param height 设定长
	 * @throws MagickException 
	 */
	public static void changeSize(String imgPath, String toPath, int width,	int height) throws MagickException {
			ImageInfo info = new ImageInfo(imgPath);
			MagickImage image = new MagickImage(info);
			MagickImage scaled = image.scaleImage(width, height);
			writeImage(scaled, info, toPath);
	}
	/**
	 * 缩放图片
	 * 
	 * @param imgPath 源文件路径
	 * @param toPath 缩略 图路径
	 * @param rate 压缩倍率
	 * @throws MagickException 
	 */
	public static void changeSizeByRate(String imgPath, String toPath, int rate) throws MagickException {
			ImageInfo info = new ImageInfo(imgPath);
			MagickImage image = new MagickImage(info);
			Dimension imageDim = image.getDimension();
			int srcW = imageDim.width;
			int srcH = imageDim.height;	
			MagickImage scaled = null;
			if(rate>1)
				scaled = image.scaleImage(srcW/rate, srcH/rate);
			else if(rate<0)
				scaled = image.scaleImage(srcW*Math.abs(rate), srcH*Math.abs(rate));
			else
				scaled = image;
			writeImage(scaled, info, toPath);
	}
	/**
	* 水印(图片)
	* @param imgPath 源文件路径
	* @param toPath 修改图路径
	* @param logoPath 水印图路径
	* @throws MagickException
	*/
	public static void writeLogoToImg(String imgPath, String toPath, String logoPath,int rate) throws MagickException {
		ImageInfo info = new ImageInfo(imgPath);
		MagickImage image = new MagickImage(info);
		MagickImage logo = new MagickImage(new ImageInfo(logoPath));
		Dimension imageDim = image.getDimension();;
		Dimension logoDim = logo.getDimension();
		int width = imageDim.width;
		int lw = width / rate;
		int lh = logoDim.height * lw / logoDim.width;
		MagickImage sLogo = logo.scaleImage(lw, lh);
		 
		image.compositeImage(CompositeOperator.AtopCompositeOp, sLogo, 0, 0);
		writeImage(image, info, toPath);
	}

	/**
	 	* 水印(文字)
		* @param String imgPath 源文件路径
		* @param String toPath 生成文件路径
		* @param String text 显示文字
		* @param String color 文字颜色
		* @param String underColor 文字背景色
		* @param String fontPath 字体路径
		* @param int pointSize 字体大小
		* @param int opacity 透明度
		* @param String geometry 偏移度：+40+60
	 */
	public static void writeTextToImg(
				String imgPath,
				String toPath,
				String text,
				String color,
				String underColor,
				String fontPath,
				int pointSize,
				int opacity,
				String geometry
			)throws MagickException {
			ImageInfo info = new ImageInfo(imgPath);
			MagickImage image = new MagickImage(info);
			annotateImage(image, info, text, color, underColor, fontPath, pointSize, opacity, geometry);
			writeImage(image, info, toPath);
	}

	/**
	 * 切图
	 * 
	 * @param imgPath 源图路径
	 * @param toPath 修改图路径
	 * @param width 宽度
	 * @param height 高度
	 * @param x 左上角的 X 坐标
	 * @param y 左上角的 Y 坐标
	 * @throws MagickException
	 */
	public static void cutImg(String imgPath, String toPath, int width, int height,int x, int y) throws MagickException {
			ImageInfo info = new ImageInfo(imgPath);
			MagickImage image = new MagickImage(info);
			Rectangle rect = new Rectangle(x, y, width, height);
			MagickImage cropped = image.cropImage(rect);
			writeImage(cropped, info, toPath);
	}
	
	public static void writeImage(MagickImage image,ImageInfo info,String toPath){
		try{
			image.setFileName(toPath);
			image.writeImage(info);
		}catch (MagickException e) {  
			e.printStackTrace();  
		}finally {
			if (image != null) {
				image.destroyImages();
			}
		}
	}
	
	public static MagickImage annotateImage(
			MagickImage image,
			ImageInfo info,
			String text,
			String color,
			String underColor,
			String fontPath,
			int pointSize,
			int opacity,
			String geometry			
	)throws MagickException {
		DrawInfo aInfo = new DrawInfo(info);
		aInfo.setText(text);
		aInfo.setFill(PixelPacket.queryColorDatabase(color));
		aInfo.setUnderColor(PixelPacket.queryColorDatabase(underColor));
		aInfo.setFont(fontPath);
		aInfo.setPointsize(pointSize);
		aInfo.setTextAntialias(true);
		aInfo.setOpacity(opacity);
		aInfo.setGeometry(geometry);
		image.annotateImage(aInfo);
		return image;
	}
}
