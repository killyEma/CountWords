package com.wikse.app;

public enum InvalidInput {
	BACKSLASH("\"", "\""),
	COMMA(", ", ", "),
	A(" a ", " A "), 
	AN(" An ", " an "), 
	THE(" the ", " The "), 
	AND( " and ", "And"),
	OF(" of ", " Of "),
	BE(" be ", " Be "),
	AS(" as ", " As ");
	
	String first;
	String second;
	
	private InvalidInput( String first, String second) {
		this.first = first;
		this.second = second;
	}

	public static InvalidInput[] list() {
		return InvalidInput.values();
	}
	
	
}
