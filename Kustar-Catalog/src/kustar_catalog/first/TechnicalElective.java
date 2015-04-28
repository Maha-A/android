package kustar_catalog.first;

public class TechnicalElective {

	 String Course_ID_course;
	 String Major_course;
		
		 
		 public TechnicalElective()
		 {
			 
			
			  Course_ID_course=new String();
			  Major_course=new String();
				
			 

		 }
		 
		 public TechnicalElective(String major_course, String course_id)
		 {
			 Course_ID_course=major_course;
			  Major_course=course_id;
			
		 }
		 
		 public void SetTechnicalElective(String major_course, String course_id)
		 {
			 Course_ID_course=major_course;
			  Major_course=course_id;
			
		 } 
		 
	}
