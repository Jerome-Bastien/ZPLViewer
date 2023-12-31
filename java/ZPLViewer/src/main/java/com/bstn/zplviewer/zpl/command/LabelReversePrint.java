package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;

public class LabelReversePrint extends Command {
	
	/*
	 * Accepted Values:
	 * N = no
	 * Y = yes
	 * Initial Value at Power-up: N or last permanently saved value
	 */
	private boolean reversePrintAllFields;
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.setReversePrintAllFields(reversePrintAllFields);
	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 1, String.valueOf(ZPL.delimiter));
		
		this.reversePrintAllFields = Converter.parametersToBoolean(this, "reverse print all fields", parameterList.get(0), false);
	}

	@Override
	public String getShortName() {
		return "^LR";
	}

	@Override
	public String getLongName() {
		return "Label Reverse Print";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}

}
