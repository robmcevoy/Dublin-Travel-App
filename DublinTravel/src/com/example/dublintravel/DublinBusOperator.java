package com.example.dublintravel;


public class DublinBusOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 5664488531244306417L;
	private final String OP_CODE="bac";
	private final int INDEX=0;
	
	public DublinBusOperator(){
		op_code=OP_CODE;
		index = INDEX;
	}

}
