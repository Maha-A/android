package kustar_catalog.first;

public class Major_course {

 String Course_ID_course;
 String Major_course;
	
	 
	 public Major_course()
	 {
		 
		
		  Course_ID_course=new String();
		  Major_course=new String();
			
		 

	 }
	 
	 public Major_course(String major_course, String course_id)
	 {
		 Course_ID_course=major_course;
		  Major_course=course_id;
		
	 }
	 
	 public void SetMajor_course(String major_course, String course_id)
	 {
		 Course_ID_course=major_course;
		  Major_course=course_id;
		
	 }
	 
}