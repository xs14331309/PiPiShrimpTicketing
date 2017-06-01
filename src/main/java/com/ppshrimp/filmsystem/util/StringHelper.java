package com.ppshrimp.filmsystem.util;

public class StringHelper {
	
    public StringHelper() {
    	super();
    }
	
	
	public boolean posFilter(String str) {
	    int len = str.length();
	    for (int i = 0; i < len; i++) {
	    	if (str.charAt(i) != '0' || str.charAt(i) != '1')
	    		return false;
	    }
	    return true;
	}
}
