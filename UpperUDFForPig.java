package com.mjyutika;
import java.io.IOException; 
import org.apache.pig.EvalFunc; 
import org.apache.pig.data.Tuple;

public class UpperUDFForPig extends EvalFunc{ 

	   public String exec(Tuple input) throws IOException {   
	      if (input == null || ((Tuple) input).size() == 0)      
	      return null;      
	      String str = (String)input.get(0);      
	      return str.toUpperCase();  
	   } 
	}

