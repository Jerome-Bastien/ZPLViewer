package com.bstn.zplviewer.renderables;

import com.bstn.zplviewer.graphics.PDFRenderer;
import com.bstn.zplviewer.zpl.constants.ZColor;
import com.bstn.zplviewer.zpl.constants.ZFont;
import com.bstn.zplviewer.zpl.constants.ZJustification;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class Text extends Renderable {
	private String data;
	private ZFont font;

	public Text(float x, float y, float width, float height, ZColor color, ZOrientation orientation,
			ZJustification justification, String data, ZFont font) {
		super(x, y, width, height, color, orientation, justification);
		this.data = data;
		this.font = font;
	}

	@Override
	public void render(PDFRenderer renderer) {
		renderer.drawText(this);
	}

	@Override
	public String toString() {
		return "Text [" + getStringRepresentation() + "data=" + data + ", font=" + font + "]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ZFont getFont() {
		return font;
	}

	public void setFont(ZFont font) {
		this.font = font;
	}
}
