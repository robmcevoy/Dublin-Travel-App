package com.example.dublintravel;


public class LuasOperator extends RtpiJsonOperator {

	private static final long serialVersionUID = 7363410576114813569L;
	private final String OP_CODE = "luas";
	private final int INDEX=2;
	
	public LuasOperator(){
		op_code=OP_CODE;
		index = INDEX;
	}

}
