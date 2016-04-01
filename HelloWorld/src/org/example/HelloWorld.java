package org.example;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class HelloWorld 
{
	
	public static void main(String args[])
	{
		
		Sanitizer snt= new Sanitizer();
		try
		{
			snt.readFilesFromFolder("/Users/mohsinakara/Documents/workspace/HelloWorld/src/Files");
		}
		catch(FileNotFoundException fe)
		{
			
		}
		catch (UnsupportedEncodingException e)
		{
			
		}
		//String str = "this is a trial stringAA123456b for testing purpose";
		//checkForNINO(str);	
	
	}
	
/*	public static void checkForNINO(String str)
	{
		String regex = "[A-Za-z][A-Za-z]\\d{6}[A-Fa-f]";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find())
		{
			System.out.println("found NINO");
			String sanitizedStr = matcher.replaceAll("AA999999A");
			System.out.println(sanitizedStr);
		}
		else
		{
			System.out.println("not found NINO");
		}
		
	}*/
	
}
	
