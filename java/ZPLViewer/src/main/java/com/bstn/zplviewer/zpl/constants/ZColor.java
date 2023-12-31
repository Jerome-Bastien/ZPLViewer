package com.bstn.zplviewer.zpl.constants;

import java.awt.Color;

public enum ZColor {
	BLACK("B", Color.BLACK),
	WHITE("W", Color.WHITE);

	private final String representation;
	private final Color color;
	
	ZColor(String representation, Color color) {
		this.representation = representation;
		this.color = color;
	}

	public String getRepresentation() {
		return representation;
	}

	public Color getColor() {
		return color;
	}
}
