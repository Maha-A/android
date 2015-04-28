package kustar_catalog.first;

public class CourseTable {
	
	String Course_ID;
	String Name;
	String Description;
	String Pre_req;
	String Main_major;
	String Credits;
	String Free_elective;
	String Technical_elective;
	
	public CourseTable()
	{
		Course_ID = new String();
		Name = new String();
		Description = new String();
		Pre_req = new String();
		Main_major = new String();
		Credits = new String();
		Free_elective = new String();
		Technical_elective = new String();
		
	}
	
	public CourseTable(String course_ID, String name,String description,
			String pre_req,String main_major,String credits,String free_elective,String technical_elective)
	{
		Course_ID = course_ID;
		Name = name;
		Description = description;
		Pre_req = pre_req;
		Main_major = main_major;
		Credits = credits;
		Free_elective = free_elective;
		Technical_elective = technical_elective;
	}

	public void SetCourseTable(String course_ID, String name,String description,
			String pre_req,String main_major,String credits,String free_elective,String technical_elective)
	{
		Course_ID = course_ID;
		Name = name;
		Description = description;
		Pre_req = pre_req;
		Main_major = main_major;
		Credits = credits;
		Free_elective = free_elective;
		Technical_elective = technical_elective;
	}
	
}
