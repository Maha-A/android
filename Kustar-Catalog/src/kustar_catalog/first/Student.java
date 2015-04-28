package kustar_catalog.first;

public class Student {
	String SFname;
	 String SLname;
	 String Major;
	 String Status;
	 String GPA;
	 String Gender;
	 String DOB;
	 String Campus;
	 String ID; 
	 String Pass; 
	
	 
	 public Student()
	 {
		  SFname=new String();
		  SLname=new String();
		  Major=new String();
		  Status=new String();
		  GPA=new String();
		  Gender=new String();
		  DOB=new String();
		  Campus=new String();
		  ID=new String();
		  Pass=new String();; 

	 }
	 
	 public Student(String student_ID, String fname,String lname,
				String major,String status,String gpa,String gender,String dob, String campus, String pass)
	 {
		  SFname=fname;;
		  SLname=lname;
		  Major=major;
		  Status=status;
		  GPA=gpa;
		  Gender=gender;
		  DOB=dob;
		  Campus=campus;
		  ID=student_ID;
		  Pass=pass;
		
	 }
	 
	 public void SetStudent(String student_ID, String fname,String lname,
				String major,String status,String gpa,String gender,String dob, String campus,String pass)
	 {
		  SFname=fname;;
		  SLname=lname;
		  Major=major;
		  Status=status;
		  GPA=gpa;
		  Gender=gender;
		  DOB=dob;
		  Campus=campus;
		  ID=student_ID; 
		  Pass=pass;
		
	 }
	 
}
