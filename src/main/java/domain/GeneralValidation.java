package domain;

public class GeneralValidation {
	
	public static String validateProjectName(String projectName)
	{
		String errors=null;
		if(projectName!=null && projectName.length()>0)
		 {
		      
			 if (!Character.isWhitespace(projectName.charAt(0)) && !Character.isDigit(projectName.charAt(0)))
			 {
			 		boolean invalid = false;
		            
		                  for(int i=0; i<projectName.length() && !invalid; i++) {
		                	  if (!Character.isAlphabetic(projectName.charAt(i)) && !Character.isDigit(projectName.charAt(i)) && !Character.isWhitespace(projectName.charAt(i)))
	                    		  
		                      {
		                          invalid = true;
		                          errors= "project name field contains invalid characters";
		                         
		                      }
		                  }
		               
			 }
			 else 
			 {
				 errors="project name must start with a letter e.g. 'A', 'a'";
			 }
	       }
		   else
		   {
			   errors="project name field required";
		   }
return errors;
	}

}
