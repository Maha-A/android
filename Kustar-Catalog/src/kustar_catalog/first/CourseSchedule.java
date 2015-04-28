package kustar_catalog.first;

public class CourseSchedule {
	String CRN;
	String Course_ID;
	String Term;
	String Days;
	String Campus;
	String Instrutor_ID;
	String Class_time;
	String Instructor_comment;
	String Time; 
	

	public CourseSchedule()
	{
		CRN = new String();
	    Course_ID = new String();
		Term= new String();
		Days= new String();
		Campus= new String();
		Instrutor_ID= new String();
		Class_time= new String();
		Instructor_comment= new String();
		Time = new String();
	}
	
	public CourseSchedule(String crn, String course_ID,String term,
			String days,String campus,String instructor_ID,String class_time,String instructor_comment, String time)
	{
		CRN = crn;
		Course_ID = course_ID;
		Term = term;
		Days = days;
		Campus = campus;
		Instrutor_ID= instructor_ID;
		Class_time= class_time;
		Instructor_comment= instructor_comment;
		Time=time;
	}
	
	public void SetCourseSchedule(String crn, String course_ID,String term,
			String days,String campus,String instructor_ID,String class_time,String instructor_comment, String time)
	{
		CRN = crn;
		Course_ID = course_ID;
		Term = term;
		Days = days;
		Campus = campus;
		Instrutor_ID= instructor_ID;
		Class_time= class_time;
		Instructor_comment= instructor_comment;
		Time =time;
	}
}
