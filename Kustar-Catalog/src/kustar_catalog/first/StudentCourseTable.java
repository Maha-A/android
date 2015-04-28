package kustar_catalog.first;

public class StudentCourseTable {

	 String Student_ID_courses;
	 String CRN_Student;
	 String Grade;
	 String Rating;
	 String Student_comment;
	 String Course_ID_courses;
	
				
				 
				 public StudentCourseTable()
				 {
					  Student_ID_courses=new String();
					  CRN_Student=new String();
					  Grade=new String();
					  Rating=new String();
					  Student_comment=new String();
					  Course_ID_courses=new String();
					
				 }
				 
				 public StudentCourseTable(String student_id, String CRN, String grade, String rating, String student_comment, String Course_ID)
				 {
					  Student_ID_courses=student_id;
					  CRN_Student=CRN;
					  Grade=grade;
					  Rating=rating;
					  Student_comment=student_comment;
					  Course_ID_courses=Course_ID;
					
				 }
				 
				
					 public void SetStudentCourseTable(String student_id, String CRN, String grade, String rating, String student_comment)
					 {
						  Student_ID_courses=student_id;
						  CRN_Student=CRN;
						  Grade=grade;
						  Rating=rating;
						  Student_comment=student_comment;
						
					 }
					 
				 
				 
			}

