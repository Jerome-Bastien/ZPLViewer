package com.bstn.zplviewer;

import java.io.File;
import java.util.Scanner;

import com.bstn.zplviewer.graphics.DPI;
import com.bstn.zplviewer.graphics.PDFRenderer;
import com.bstn.zplviewer.graphics.RenderQueue;
import com.bstn.zplviewer.interpreter.Interpreter;
import com.bstn.zplviewer.interpreter.Parser;
import com.bstn.zplviewer.util.Logger;
import com.bstn.zplviewer.util.TextFile;
import com.bstn.zplviewer.util.UnitConverter;
import com.bstn.zplviewer.util.Validator;
import com.bstn.zplviewer.zpl.ZPLDocument;

/*
 * Code 39
 * Code 128
 * QR Codes
 */
public class ZPLViewer {

	public static final String version = "0.0.3";
	
	private File input;
	private File output;
	
	private int labelIndex;
	
	public static void main(String[] args) {
		ZPLViewer zplViewer = new ZPLViewer();

		if(zplViewer.parseArguments(args)) {
			zplViewer.execute();
		}
	}

	private boolean parseArguments(String[] args) {
		if (args.length <= 2) {
	        System.out.println("Usage: zplviewer [input file] [output file], [label index]");
	        return false;
		}

	    input = new File(args[0]);
	    output = new File(args[1]);

	    if (!input.isFile()) {
	        Logger.warning("Unable to read the input file.");
	        return false;
	    }

	    if (output.exists()) {
	        System.out.print("File already exists. Do you want to overwrite it? (y/n)\n > ");
	        try (Scanner scanner = new Scanner(System.in)) {
	            String response = scanner.next();
	            if (!response.equalsIgnoreCase("Y")) {
	                System.out.println("Operation canceled.");
	                return false;
	            }
	            if (!output.delete()) {
	    	        Logger.error("Unable to delete the existing file.");
	                return false;
	            }
	        }
	    }
	    
	    if(args.length > 2) {
		    String tempLabelIndex = args[2];
		    
		    if(!Validator.validateInteger(tempLabelIndex)) {
    	        Logger.error("Label index '" + args[2] + "' should be numeric.");
		    	return false;
		    }
		    
		    labelIndex = Integer.parseInt(tempLabelIndex);
	    }
	    return true;
	}

	private void execute() {
		try (Scanner scanner = new Scanner(System.in)) {
			TextFile textFile = new TextFile(input);
						
			Parser parser = new Parser(textFile.read());
			ZPLDocument zplDocument = parser.parse();

			UnitConverter unitConverter = new UnitConverter(DPI.DPI203);
			
			Interpreter interpreter = new Interpreter(zplDocument);
			RenderQueue renderQueue = interpreter.interpret(unitConverter, labelIndex, output);
			System.out.println(renderQueue);
			
			PDFRenderer pdfRenderer = new PDFRenderer();
			renderQueue.render(pdfRenderer, output);
		}
	}
}
