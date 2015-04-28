package kustar_catalog.first;

public class InstructorTable {

	String Instrutor_ID;
	String Fname;
	String Lname;
	String Department;
	
	public InstructorTable()
	{	
		Instrutor_ID = new String();
		Fname = new String();
		Lname = new String();
		Department = new String();
	}
	
	public InstructorTable(String instrutor_ID, String fname, String lname, String department)
	{	
		Instrutor_ID = instrutor_ID;
		Fname = fname;
		Lname = lname;
		Department = department;
	}
	
	
	public void SetInstructorTable(String instrutor_ID, String fname, String lname, String department)
	{	
		Instrutor_ID = instrutor_ID;
		Fname = fname;
		Lname = lname;
		Department = department;
	}
}
