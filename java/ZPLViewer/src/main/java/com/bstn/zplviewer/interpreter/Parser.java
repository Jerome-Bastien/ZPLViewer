package com.bstn.zplviewer.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bstn.zplviewer.util.Logger;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.ZPLDocument;
import com.bstn.zplviewer.zpl.command.BarcodeDefault;
import com.bstn.zplviewer.zpl.command.ChangeAlphanumericDefaultFont;
import com.bstn.zplviewer.zpl.command.Code128Barcode;
import com.bstn.zplviewer.zpl.command.Command;
import com.bstn.zplviewer.zpl.command.DownloadGraphics;
import com.bstn.zplviewer.zpl.command.FieldData;
import com.bstn.zplviewer.zpl.command.FieldOrigin;
import com.bstn.zplviewer.zpl.command.FieldReversePrint;
import com.bstn.zplviewer.zpl.command.FieldTypeset;
import com.bstn.zplviewer.zpl.command.GraphicBox;
import com.bstn.zplviewer.zpl.command.GraphicField;
import com.bstn.zplviewer.zpl.command.LabelReversePrint;
import com.bstn.zplviewer.zpl.command.RecallGraphic;
import com.bstn.zplviewer.zpl.command.ScalableBitmappedFont;

public class Parser {

	private String text;
	private Map<String, Class<?>> commands;

	public Parser(String text) {
		this.text = text;
		
		init();
	}
	
	private void init() {
		commands = new HashMap<>();
		
		commands.put("^A", ScalableBitmappedFont.class);
		commands.put("^FO", FieldOrigin.class);
		commands.put("^FT", FieldTypeset.class);
		commands.put("^CF", ChangeAlphanumericDefaultFont.class);
		commands.put("^FD", FieldData.class);
		commands.put("^GB", GraphicBox.class);
		commands.put("^FR", FieldReversePrint.class);
		commands.put("^BY", BarcodeDefault.class);
		commands.put("^BC", Code128Barcode.class);
		commands.put("~DG", DownloadGraphics.class);
		commands.put("^XG", RecallGraphic.class);
		commands.put("^GF", GraphicField.class);
		commands.put("^LR", LabelReversePrint.class);
	}

	public ZPLDocument parse() {
		ParserEnvironment env = new ParserEnvironment(text);

		Pattern commandPattern = Pattern.compile("[\\^~][A-Za-z]{1,2}");
		Matcher matcher = commandPattern.matcher(text);
	
		while (matcher.find()) {
			String match = text.substring(matcher.start(), matcher.end());
			
			int start = matcher.start();
			int parameterStart = matcher.end();
			
			int nextCommandPrefix = text.indexOf(ZPL.commandPrefix, parameterStart);
			int nextFormatPrefix = text.indexOf(ZPL.commandPrefix, parameterStart);

			int end = (nextCommandPrefix > nextFormatPrefix) ? nextCommandPrefix : nextFormatPrefix;
			
            int lineNumber = text.substring(0, start).split("\n").length;
			int startIndex = start - (text.lastIndexOf('\n', start) + 1);
            			
			String parameters = "";
			if (end > parameterStart) {
				parameters = text.substring(parameterStart, end);
			}

			switch (match) {
				case "^XA" -> {
					env.startLabel();
				}
	
				case "^FS" -> {
					env.endField();
				}
	
				case "^XZ" -> {
					env.endLabel();
				}
	
				case "^FX" -> {
					continue;
				}
	
				default -> {
					Class<?> clazz = null;
	
					if ((clazz = commands.get(match)) != null) {
						try {
							Command command = (Command) clazz.getConstructor().newInstance();
							
							command.setLineNumber(lineNumber);
							command.setStartIndex(startIndex);
							
							env.addCommand(command, parameters);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					} else {
		    	        Logger.warning("Command '" + match + "' in line " + lineNumber + " at index " + startIndex + " is unavailable.");
					}
				}
			}
		}

		return env.getZplDocument();
	}
}
