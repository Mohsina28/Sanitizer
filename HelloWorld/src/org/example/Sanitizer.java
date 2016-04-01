package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sanitizer {
	
	/*
	 * This Method checks if argument string 'value' contains any date in it.
	 */
	/*private void checkForDate(String value) 
	{
        
            StringTokenizer st = new StringTokenizer(value);
            String substring = null;
            while (st.hasMoreTokens())
            {
            	substring = st.nextToken();
               if(isValidFormat("DD/MM/YYYY",substring) || isValidFormat("DD.MM.YYYY",substring) || isValidFormat("DD-MM-YYYY",substring))
               {
            	   System.out.println(substring + " is valid date");
      		   }
               else
               {
            	//   System.out.println(substring + " is invalid date");
               }
            }    
    }*/
	
	/*
	 * This method takes two parameters. PrintWriter parameter is the write stream to the file. String parameter is the string
	 * which can contain date.If it contains date this method will replace with ff/ff/ffff and using PrintWriter write into the
	 * file.
	 */
	private void checkForDate(PrintWriter writer,String value) 
	{
        
            StringTokenizer st = new StringTokenizer(value);
            String substring = null;
            
            String sanitisedLine = null;
            
            
            
            while (st.hasMoreTokens())
            {
            	substring = st.nextToken();
               if(isValidFormat("DD/MM/YYYY",substring) || isValidFormat("DD.MM.YYYY",substring) || isValidFormat("DD-MM-YYYY",substring)  )
               {
            	   System.out.println(substring + " is valid date");
            	  
            	   if(sanitisedLine == null)
            		   sanitisedLine = "dd/mm/yyyy" + " ";
            	   else
            		   sanitisedLine += "dd/mm/yyyy" + " ";
            		   
      		   }
               else
               {
            	   if(sanitisedLine == null)
            		   sanitisedLine = substring + " ";
            	   else
            		   sanitisedLine += substring + " ";;
            	   
            	//   System.out.println(substring + " is invalid date");
               }
            }
            
            writer.println(sanitisedLine);
            //writer.close();
    }
	
	private String checkForDate(String value) 
	{
        
            StringTokenizer st = new StringTokenizer(value);
            String substring = null;
            
            String sanitisedLine = null;
            
            
            
            while (st.hasMoreTokens())
            {
            	substring = st.nextToken();
               if(isValidFormat("DD/MM/YYYY",substring) || isValidFormat("DD.MM.YYYY",substring) || isValidFormat("DD-MM-YYYY",substring)  )
               {
            	   System.out.println(substring + " is valid date");
            	  
            	   if(sanitisedLine == null)
            		   sanitisedLine = "dd/mm/yyyy" + " ";
            	   else
            		   sanitisedLine += "dd/mm/yyyy" + " ";
            		   
      		   }
               else
               {
            	   if(sanitisedLine == null)
            		   sanitisedLine = substring + " ";
            	   else
            		   sanitisedLine += substring + " ";;
            	   
            	//   System.out.println(substring + " is invalid date");
               }
            }
            
            return sanitisedLine;
    }
	
	/*
	 * TODO:
	 */
	private String checkForNINO(String value) 
	{
			String regex = "[A-Za-z][A-Za-z]\\d{6}[A-Fa-f]";
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			String sanitizedStr = null;
			if(matcher.find())
			{
				
				sanitizedStr = matcher.replaceAll("AA999999A");
				System.out.println(sanitizedStr);
				return sanitizedStr;
				
			}
			return value;
			
			
	}
	
	
	/*
	 * This method is helper method used by CheckForDate. First parameter format is the format for date and second parameter
	 */
	
	private boolean isValidFormat(String format, String value) 
	{
					
			        Date date = null;
			        try {
			            SimpleDateFormat sdf = new SimpleDateFormat(format);
			            date = sdf.parse(value);
			            if (!value.equals(sdf.format(date))) 
			            	{
			                //date = null;
			            	return true;
			            	}
			        	}
			        catch (ParseException ex) {
			           // ex.printStackTrace();
			        }
			       // return date != null;
			        return false;
	  } 
	


/*	private boolean isWithinMonth(Date date)
		{
			Date date1 = null;
			Date presentDate = new Date();	
			date1.setTime(presentDate.getTime() - 1000L * 60L * 60L * 24L);
			if (date.before(presentDate) && date.after(date1))
				return true;
			else
				return false;
		}*/

	
	private void readFromFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{
		// ArrayList<String> lines = new ArrayList<String>();
	        String line = null;
	        
	        /*from fileName which is absolute path extract only filename and create file with 
            same filename for writing in another folder
            */
	        
	        /*TO-DO: For now '/' is used as separator but it could be different depending on the platform
	         * 
	         */
           // int index = fileName.lastIndexOf('/');
	        int index = fileName.lastIndexOf(File.separator);
            String destFileName = fileName.substring(index+1);
            String remainingPath= fileName.substring(0, index);
            
           // int newIndex = remainingPath.lastIndexOf('/');
            int newIndex = remainingPath.lastIndexOf(File.separator);
            String remainingFolderPath= remainingPath.substring(0, newIndex);
            
         //   String destFolder = remainingFolderPath + "/temp";  ///" + destFileName;
          //  String destFile = destFolder + "/" + destFileName;
            String destFolder = remainingFolderPath + File.separator + "temp";  ///" + destFileName;
            String destFile = destFolder + File.separator + destFileName;
            File folder = new File(destFolder);
            
            if(!folder.exists())
            	folder.mkdir();
            
            File file= new File(destFile);
            
            PrintWriter writer = new PrintWriter(file,"UTF-8");
            String sanitizedLine = null;
            String sanitizedLine1 = null;
	        try 
	        {
	            FileReader fr = new FileReader(fileName);
	            BufferedReader br = new BufferedReader(fr);
	           /* FileWriter fw = new FileWriter("Ticket/" + ticketIDNumber + ".dat");
	            BufferedWriter bw = new BufferedWriter(fw);*/
	            while ((line = br.readLine()) != null) 
	            {
	            	//TO-DO: checkForDate should be named as checkForSensitiveData and should act accordingly
	            //	checkForDate(writer,line);
	            	
	            	
	          //  	System.out.println(checkForNINO(checkForDate(line)));
	            	writer.println(checkForNINO(checkForDate(line)));
	            }    //end while
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        }
	     
	        writer.close();

	}
	
	public void readFilesFromFolder(String folderName) throws FileNotFoundException, UnsupportedEncodingException
	{
		
  		      File folder = null;
		      File[] files;
		      
		      try{      
			         // create new file
			         folder = new File(folderName);
			         if(folder.isDirectory())
			         {
				         // returns pathnames for files and directory
				         files = folder.listFiles();
				         
				         // for each pathname in pathname array
				         for(File file:files)
				         {
				            // prints file and directory paths
				            //System.out.println(file);
				        	 readFromFile(file.getPath());
				         }
			         }
		      	}
		      catch(Exception e)
		      {
		         // if any error occurs
		         e.printStackTrace();
		      }
		
	}

}


