package com.bstn.zplviewer.renderables;

import java.awt.image.BufferedImage;

import com.bstn.zplviewer.graphics.PDFRenderer;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class Graphic extends Renderable {
	private String data;
	private float bytesPerRow;
	private float scaleX;
	private float scaleY;
	private BufferedImage bufferedImage;
	
	public Graphic(float x, float y, float width, float height, ZColor color, ZOrientation orientation,
			ZJustification justification, String data, float bytesPerRow, float scaleX, float scaleY,
			BufferedImage bufferedImage) {
		super(x, y, width, height, color, orientation, justification);
		this.data = data;
		this.bytesPerRow = bytesPerRow;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.bufferedImage = bufferedImage;
	}
	
	@Override
	public void render(PDFRenderer renderer) {
		renderer.drawGraphic(this);
	}
	
	@Override
	public String toString() {
		return "Graphic [" + getStringRepresentation() + "data=" + data + ", bytesPerRow=" + bytesPerRow + ", scaleX=" + scaleX + ", scaleY=" + scaleY
				+ ", bufferedImage=" + bufferedImage + "]";
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public float getBytesPerRow() {
		return bytesPerRow;
	}
	
	public void setBytesPerRow(float bytesPerRow) {
		this.bytesPerRow = bytesPerRow;
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}
	
	public float getScaleY() {
		return scaleY;
	}
	
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}
