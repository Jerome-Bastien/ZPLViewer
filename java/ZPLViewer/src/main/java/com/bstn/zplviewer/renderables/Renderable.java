package com.bstn.zplviewer.renderables;

import com.bstn.zplviewer.graphics.PDFRenderer;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public abstract class Renderable {
	private float x;
	private float y;
	private float width;
	private float height;
	private ZColor color;
	private ZJustification justification;
	private ZOrientation orientation;
	
	public Renderable(float x, float y, float width, float height, ZColor color, ZOrientation orientation, ZJustification justification) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.justification = justification;
		this.orientation = orientation;
	}
	
	public abstract void render(PDFRenderer renderer);

	public String getStringRepresentation() {
		return "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", color=" + color
				+ ", justification=" + justification + ", orientation=" + orientation + ", ";
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public ZColor getZColor() {
		return color;
	}

	public void setColor(ZColor color) {
		this.color = color;
	}

	public ZJustification getJustification() {
		return justification;
	}

	public void setJustification(ZJustification justification) {
		this.justification = justification;
	}

	public ZOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(ZOrientation orientation) {
		this.orientation = orientation;
	}
}
