package com.fdmgroup.bullcow1.OOD_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SecretNumberGenerator {

	public String generate(int digits) {

		StringBuffer generator = new StringBuffer();
		ArrayList<String> num = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
		Collections.shuffle(num);
	
		for(int i=0; i< digits; i++)
		{
			generator.append(num.get(i));
		}
		return generator.toString();
	}

}
