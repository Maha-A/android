package kustar_catalog.first;

import android.content.ContentValues;
import android.content.Context;
//import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Database  extends SQLiteOpenHelper{
		
		
			static final String dbName="catalog";

		public Database(Context context) {
			super(context, dbName, null,1);
			// TODO Auto-generated constructor stub
		}

		public static final int dbVersion = 2;
		
		//table Course_table 
		//want
		static final String Course_table ="Course_table";
		static final String Course_ID="Course_ID";
		static final String Name="Name";
		static final String Description="Description";
		static final String Pre_req="Pre_req";
		static final String Credits="Credits";
		static final String Main_major="Main_major";
		static final String Free_elective="Free_elective";
		static final String Technical_elective="Technical_elective";
		
	   //table Pre_req
		//want
		static final String Pre_req_table="Pre_req";
		static final String Course_ID_Pre="Course_ID";
		static final String Pre_req_Pre="Pre_req";
		
		//table Instructor_table
		//want
		static final String Instructor_table="Instructor_table";
		static final String Instructor_ID="Instructor_ID";
		static final String Fname="Fname";
		static final String Lname="Lname";
		static final String Department="Department";

		//table Course_schedule
		//want
		static final String Course_schedule="Course_schedule";
		static final String CRN="CRN";
		static final String Course_ID_Sch="Course_ID";
		static final String Term="Term";
		static final String Days="Days";
		static final String Campus="Campus";
		static final String Instructor_ID_Sch="Instructor_ID";
		static final String Time="Time";
		static final String Instructor_comment="Instructor_comment";

		//table Student_table
		static final String Student_table="Student_table";
		static final String Student_ID="Student_ID";		
		static final String SFname="Fname";
		static final String SLname="Lname";
		static final String Major="Major";
		static final String Status="Status";
		static final String GPA="GPA";
		static final String Gender="Gender";
		static final String DOB="DOB";
		static final String Campus_student="Campus";
		static final String Password="Password";

		
		//table Student_course
		//want
		static final String Student_courses="Student_courses";
		static final String Student_ID_courses="Student_ID";
		static final String CRN_Student="CRN";
		static final String Grade="Grade";
		static final String Rating="Rating";
		static final String Student_comment="Student_comment";
		static final String Course_ID_courses="Course_ID";
		
		//Technical_elective table 
		static final String Technical_elective_table="Technical_elective";
		static final String Course_ID_elective="Course_ID";
		static final String Major_tech="Major";

		//Major_course table 
		
		
		static final String Mojor_course="Major_course";
		static final String Course_ID_course="Course_ID";
		static final String Major_course="Major";
		
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
		//	db.setVersion(4);
			
			db.execSQL("CREATE TABLE "+Course_table+" ("+Course_ID+" TEXT,"
	                +Name+ " TEXT, "+Description+ " TEXT,"+
	                Pre_req+" INTEGER,"+Credits+" INTEGER,"+Main_major+
	                " TEXT,"+Free_elective+" INTEGER,"+Technical_elective+" INTEGER)");
			
			
			db.execSQL("CREATE TABLE "+Pre_req+" ("+Course_ID+" TEXT,"
	                +Pre_req+ " TEXT)");
			
			db.execSQL("CREATE TABLE "+Instructor_table+" ("+Instructor_ID+" INTEGER, "
	                +Fname+ " TEXT, "+Lname+ " TEXT, "+Department +" TEXT)");
			
			db.execSQL("CREATE TABLE "+Course_schedule+" ("+CRN+" INTEGER,"+ Course_ID+" TEXT,"+Term +" TEXT," +
			Days +" TEXT," +Campus+" TEXT,"+Instructor_ID+" TEXT,"+Time+" TEXT, "+ Instructor_comment+" TEXT)");
			
			db.execSQL("CREATE TABLE "+Student_table+" ("+Student_ID+" INTEGER,"+ Fname+" TEXT,"+Lname +" TEXT," +
					Major +" TEXT," +Status+" TEXT,"+GPA+" FLOAT,"+Gender+" TEXT, "+ DOB+" DATE,"+Campus+" TEXT,"+Password +" TEXT)");
			
			
			db.execSQL("CREATE TABLE "+Student_courses+" ("+Student_ID_courses+" INTEGER,"+CRN_Student +" INTEGER," +
					Grade +" TEXT," +Rating+" INTEGER,"+Student_comment+" TEXT, "+ Course_ID_courses+" TEXT)");
			
			db.execSQL("CREATE TABLE "+Technical_elective_table+" ("+Course_ID_elective+" TEXT,"+ Major_tech+" TEXT)");
			
			db.execSQL("CREATE TABLE "+Mojor_course+" ("+Course_ID_elective+" TEXT,"+ Major_tech+" TEXT)");

		}
		
		
		
		
		
//		public Cursor GetCRN(String course_id,String id)
//	     {
//	      SQLiteDatabase db=this.getReadableDatabase();
//	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
//	      		" WHERE Course_ID="+course_id,new String [] {});*/
//	     
//	   Cursor cur=db.rawQuery("SELECT CRN from Course_schedule where Course_ID = ?",new String [] {course_id});
//		 // Cursor cur=db.rawQuery("SELECT Instructor_ID from Course_schedule where Course_ID = ?",new String [] {"course_id"});
//
//	      return cur;
//	     }
//		 
		
		public Cursor getrating (String course_id)
	     {
		      SQLiteDatabase db=this.getReadableDatabase();
		      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
		      		" WHERE Course_ID="+course_id,new String [] {});*/
		     
		   Cursor cur=db.rawQuery("SELECT Rating from Student_courses where Course_ID = ?",new String [] {course_id});
			 // Cursor cur=db.rawQuery("SELECT Instructor_ID from Course_schedule where Course_ID = ?",new String [] {"course_id"});

		      return cur;
		     }
		
		
		public Cursor GetComments(String course_id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     
	   Cursor cur=db.rawQuery("SELECT Instructor_comment, Instructor_ID from Course_schedule where Course_ID = ?",new String [] {course_id});
		 // Cursor cur=db.rawQuery("SELECT Instructor_ID from Course_schedule where Course_ID = ?",new String [] {"course_id"});

	      return cur;
	     }
		 
		
		public boolean ifStudentTookCourse(String course_id, String id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     Cursor cur=db.rawQuery("SELECT COUNT(*) FROM Student_courses where Course_ID = ? AND Student_ID = ?",new String [] {course_id,id});
		 // Cursor cur=db.rawQuery("SELECT Instructor_ID from Course_schedule where Course_ID = ?",new String [] {course_id});

	     
	     boolean result=true;
	     	if (cur == null){
		     Log.d("in db ","am null");
	     	return false;

	     	}
	     	else{
	     	    cur.moveToFirst();
	     	    if (cur.getInt(0) == 0) 
	     	    {
	   		     Log.d("in db ","am 0");
	     	    	return false;	

	     	    }
	     	}
		return true;
		
	     }
		public Cursor getname(String id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     
	      Cursor cur=db.rawQuery("SELECT Fname,Lname from Instructor_table where Instructor_ID = ?",new String [] {id});
	      return cur;
	     }
		 
		public Cursor GetCommentsStudents(String course_id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     
	      Cursor cur=db.rawQuery("SELECT Student_comment from Student_courses where Course_ID = ?",new String [] {course_id});
	      return cur;
	     }
		 
		
		public Cursor GetDescription(String course_id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     
	      Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table where Course_ID = ?",new String [] {course_id});
	      return cur;
	     }
		 
		public Cursor GetPreReq(String course_id)
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      /*Cursor cur=db.rawQuery("SELECT Name, Course_ID, Description from Course_table" +
	      		" WHERE Course_ID="+course_id,new String [] {});*/
	     
	      Cursor cur=db.rawQuery("SELECT Pre_req from Pre_req where Course_ID = ?",new String [] {course_id});
	     /* Cursor cur=db.rawQuery("SELECT Pre_req.Pre_req from Pre_req where Course_ID = ?",new String [] {course_id});*/
	      return cur;
	     }
		 
		
		public int updateComment(String comment,String ID, String course)
		{	
			ContentValues cv=new ContentValues();
			SQLiteDatabase DB=this.getWritableDatabase();
            cv.put(Student_comment, comment);
         return   DB.update(Student_courses, cv, "Student_ID = "+ID+" AND Course_ID ='"+course+"'", null);

         //return   DB.update(Student_courses, cv, "Course_ID = "+course.trim(), null);
			
		}
		
		
		
		
		public int addrating(int foo,String extra, String studentid)
		{
			ContentValues cv=new ContentValues();
			SQLiteDatabase DB=this.getWritableDatabase();
            cv.put(Rating, foo);
         return   DB.update(Student_courses, cv, "Student_ID = "+studentid+" AND Course_ID ='"+extra+"'", null);
	
		}
		
		public void insertCourse_table(String course_ID, String name, String description, String pre_req
				, String credits, String main_major, String free_elective, String technical_elective)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Name, name);
	                    cv.put(Description, description);
	                    cv.put(Pre_req,pre_req);
	                    cv.put(Credits, credits);
	                    cv.put(Main_major, main_major);
	                    cv.put(Free_elective,free_elective);
	                    cv.put(Technical_elective,technical_elective);
	                    
	                    
	                    DB.insert(Course_table, null, cv);
	                    DB.close();
	    }
		
		
		public void insertCourse_table(CourseTable t)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, t.Course_ID);
	                    cv.put(Name, t.Name);
	                    cv.put(Description, t.Description);
	                    cv.put(Pre_req,t.Pre_req);
	                    cv.put(Credits, t.Credits);
	                    cv.put(Main_major, t.Main_major);
	                    cv.put(Free_elective,t.Free_elective);
	                    cv.put(Technical_elective,t.Technical_elective);
	                    
	                    
	                    DB.insert(Course_table, null, cv);
	                    DB.close();
	    }
		
		public void insertPre_req(String course_ID, String pre_req)		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Pre_req, pre_req);
	                    
	                    
	                    DB.insert(Pre_req, null, cv);
	                    DB.close();
	    }
		
		public void insertPre_req(Pre_req p)		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, p.Course_ID_Pre);
	                    cv.put(Pre_req, p.Pre_req_Pre);
	                    
	                    
	                    DB.insert(Pre_req, null, cv);
	                    DB.close();
	    }
		
		
		public void insertIntstructor_table(String instructor_ID,String fname,String lname,String department)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Instructor_ID, instructor_ID);
	                    cv.put(Fname, fname);
	                    cv.put(Lname, lname);
	                    cv.put(Department, department);

	                    
	                    DB.insert(Instructor_table, null, cv);
	                    DB.close();
	    }
		
public void insertIntstructor_table(InstructorTable m)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Instructor_ID, m.Instrutor_ID);
	                    cv.put(Fname, m.Fname);
	                    cv.put(Lname, m.Lname);
	                    cv.put(Department, m.Department);

	                    
	                    DB.insert(Instructor_table, null, cv);
	                    DB.close();
	    }
		
		public void insertCourse_schedule(String crn, String course_ID, String term, String days, String campus, 
				String instructor_ID, String time, String instructor_comment )
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(CRN, crn);
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Term, term);
	                    cv.put(Days, days);
	                    cv.put(Campus, campus);
	                    cv.put(Instructor_ID, instructor_ID);
	                    cv.put(Time, time);
	                    cv.put(Instructor_comment, instructor_comment);
	                    
	                    
	                    DB.insert(Course_schedule, null, cv);
	                    DB.close();
	    }
		

		
		
		public void insertCourse_schedule(CourseSchedule s)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(CRN, s.CRN);
	                    cv.put(Course_ID, s.Course_ID);
	                    cv.put(Term, s.Term);
	                    cv.put(Days, s.Days);
	                    cv.put(Campus, s.Campus);
	                    cv.put(Instructor_ID, s.Instrutor_ID);
	                    cv.put(Time, s.Time);
	                    cv.put(Instructor_comment, s.Instructor_comment);
	                    
	                    
	                    DB.insert(Course_schedule, null, cv);
	                    DB.close();
	    }
		

		
		public void insertStudent_table(String student_ID, String fname,String lname,
				String major,String status,String gpa,String gender,String dob, String campus,String pass)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Student_ID, student_ID);
	                    cv.put(Fname, fname);
	                    cv.put(Lname, lname);
	                    cv.put(Major, major);
	                    cv.put(Status, status);
	                    cv.put(GPA, gpa);
	                    cv.put(Gender, gender);
	                    cv.put(DOB, dob);
	                    cv.put(Campus, campus);
	                    cv.put(Password, pass);
	                    
	                    DB.insert(Student_table, null, cv);
	                    DB.close();
	    }
		
		
		public void insertStudent(Student student)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Student_ID, student.ID);
	                    cv.put(Fname, student.SFname);
	                    cv.put(Lname, student.SLname);
	                    cv.put(Major, student.Major);
	                    cv.put(Status, student.Status);
	                    cv.put(GPA, student.GPA);
	                    cv.put(Gender, student.Gender);
	                    cv.put(DOB, student.DOB);
	                    cv.put(Campus, student.Campus);
	                    cv.put(Password, student.Pass);
	                    
	                    DB.insert(Student_table, null, cv);
	                    DB.close();
	    }
		
		public void insertStudent_courses(String student_ID, String crn,String course_ID,
				String grade,String rating,String student_comment)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Student_ID, student_ID);
	                    cv.put(CRN, crn);
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Grade, grade);
	                    cv.put(Rating, rating);
	                   // cv.put(Password, password);
	                    cv.put(Student_comment, student_comment);
	                    
	                    DB.insert(Student_courses, null, cv);
	                    DB.close();
	    }
		
		public void insertStudentCourses(StudentCourseTable s)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Student_ID, s.Student_ID_courses);
	                    cv.put(CRN, s.CRN_Student);
	                    cv.put(Course_ID, s.Course_ID_courses);
	                    cv.put(Grade, s.Grade);
	                    cv.put(Rating, s.Rating);
	                   // cv.put(Password, s.);
	                    cv.put(Student_comment, s.Student_comment);
	                    
	                    DB.insert(Student_courses, null, cv);
	                    DB.close();
	    }
		
		
		
		
		public void insertTechnicalElective(String course_ID, String major)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Major, major);
	         
	                    DB.insert(Technical_elective, null, cv);
	                    DB.close();
	    }

		public void insertTechnicalElective(TechnicalElective t)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, t.Course_ID_course);
	                    cv.put(Major, t.Major_course);
	         
	                    DB.insert(Technical_elective, null, cv);
	                    DB.close();
	    }
		
		public void insertCourseMajor(String course_ID, String major)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, course_ID);
	                    cv.put(Major, major);
	         
	                    DB.insert(Major_course, null, cv);
	                    DB.close();
	    }
		
public void insertCourseMajor(Major_course m)
		
	    {
	        
						SQLiteDatabase DB=this.getWritableDatabase();
	                    ContentValues cv=new ContentValues();
	                    
	                    cv.put(Course_ID, m.Course_ID_course);
	                    cv.put(Major, m.Major_course);
	         
	                    DB.insert(Major_course, null, cv);
	                    DB.close();
	    }
		
		
		
		 public Cursor getCourse_table()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Course_table,new String [] {});
	     
	      return cur;
	     }
		
		 public Cursor getCourse_major()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Mojor_course,new String [] {});
	     
	      return cur;
	     }
		 
		 
		 public Cursor getTechnical_elective()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Technical_elective,new String [] {});
	     
	      return cur;
	     }
		 
		 
		 
		 public Cursor getPre_req()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Pre_req,new String [] {});
	     
	      return cur;
	     }
		 
		 public Cursor getInstructor_table()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Instructor_table,new String [] {});
	     
	      return cur;
	     }
		 
		 public Cursor getCourse_schedule()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Course_schedule,new String [] {});
	     
	      return cur;
	     }
		 
		 
		 
		 public Cursor getStudent_table()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Student_table,new String [] {});
	     
	      return cur;
	     } 
		 
		 public Cursor getStudent_courses()
	     {
	      SQLiteDatabase db=this.getReadableDatabase();
	      Cursor cur=db.rawQuery("SELECT *  from "+Student_courses,new String [] {});
	     
	      return cur;
	     } 
		 

		 
		 
		 
		 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		 public boolean isProfileExsists()
	     {
	     	SQLiteDatabase DB=this.getReadableDatabase();
	     	Cursor cur=DB.rawQuery("SELECT COUNT(*) FROM "+Student_table,null);
	     	boolean result=true;
	     	if (cur != null){
	     	    cur.moveToFirst();
	     	    if (cur.getInt(0) == 0) {
	     	    	result=false;
	     	    	return result;
	     	    }

	     	}
	     	return result;
	     }
		
		

		
}

