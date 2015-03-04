package com.example.dublintravel;


public class IrishRailOperator extends RtpiXmlOperator {

	private static final long serialVersionUID = 4362786347952215799L;
	private final String OP_CODE="ir";
	private final int INDEX=3;
	
	public IrishRailOperator(){
		op_code=OP_CODE;
		index = INDEX;
	}
}
