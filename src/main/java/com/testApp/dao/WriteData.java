package com.testApp.dao;

import org.omg.CORBA.portable.OutputStream;

public class WriteData {
	private static String  id = "d://testData/";

	  public static void insert (org.omg.CORBA.Any a, String that)
	  {
	    OutputStream out = a.create_output_stream ();
	    a.type();
	    
	    }
}
