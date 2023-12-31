package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.ZFont;

public class ChangeAlphanumericDefaultFont extends Command {
	
	/*
	 * Accepted Values: A through Z and 0 to 9
	 * Default Value: A
	 */
	private ZFont specifiedDefaultFont;
	
	/*
	 * Accepted Values: 0 to 32000
	 * Default Value: 9
	 */
	private int individualCharacterHeight;
	
	/*
	 * Accepted Values: 0 to 32000
	 * Default Value: 0
	 */
	private int individualCharacterWidth;
	
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.setFont(specifiedDefaultFont);
		env.setFontHeight(individualCharacterHeight);
		env.setFontWidth(individualCharacterWidth);

	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 3, String.valueOf(ZPL.delimiter));
		
		this.specifiedDefaultFont = ZFont.getByCharacter(this, Converter.parametersToFontName(this, "Specified Default Font", parameterList.get(0), '0'));
		this.individualCharacterHeight = Converter.parameterToIntWithRange(this, "Individual Character Height", parameterList.get(1), 0, 32000, 9);
		this.individualCharacterWidth = Converter.parameterToIntWithRange(this, "Individual Character Width", parameterList.get(2), 0, 32000, 0);
	}
	
	@Override
	public String getShortName() {
		return "^CF";
	}

	@Override
	public String getLongName() {
		return "Change Alphanumeric Default Font";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}
	
	@Override
	public String toString() {
		return "ChangeAlphanumericDefaultFont [specifiedDefaultFont=" + specifiedDefaultFont
				+ ", individualCharacterHeight=" + individualCharacterHeight + ", individualCharacterWidth="
				+ individualCharacterWidth + "]";
	}

	public ZFont getSpecifiedDefaultFont() {
		return specifiedDefaultFont;
	}

	public void setSpecifiedDefaultFont(ZFont specifiedDefaultFont) {
		this.specifiedDefaultFont = specifiedDefaultFont;
	}

	public int getIndividualCharacterHeight() {
		return individualCharacterHeight;
	}

	public void setIndividualCharacterHeight(int individualCharacterHeight) {
		this.individualCharacterHeight = individualCharacterHeight;
	}

	public int getIndividualCharacterWidth() {
		return individualCharacterWidth;
	}

	public void setIndividualCharacterWidth(int individualCharacterWidth) {
		this.individualCharacterWidth = individualCharacterWidth;
	}
}
