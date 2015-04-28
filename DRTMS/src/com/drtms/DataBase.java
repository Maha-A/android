package com.drtms;

import android.content.ContentValues;
import android.content.Context;
//import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase  extends SQLiteOpenHelper{
	
	
		static final String dbName="diabetes";

	public DataBase(Context context) {
		super(context, dbName, null,1);
		// TODO Auto-generated constructor stub
	}

	public static final int dbVersion = 1;
	
	//table patients 
	static final String Patients_Table="patients";
	static final String ColProfileNumber="profileNumber";
	static final String ColFirstName="firstname";
	static final String ColLastName="lastname";
	//static final String ColPassword="password";
	static final String ColPhoneNumber="phoneNumber";
	static final String ColMobileNumber="mobileNumber";
	static final String ColGender="gender";
	static final String ColDOB="DOB";
	static final String ColDoctorID="doctorID";
	
   //table Meds
	static final String Medications_Table="Medications";
	static final String ColMedicationID="medicationID";
	static final String ColMedName="name";
	static final String Coldescription="description";
	static final String ColDosage="dosage";
	static final String ColDosageUnit="dosageUnit";
	static final String ColMedStartDate="medStartDate";
	static final String ColMedEndDate="medEndDate";
	static final String ColMedAddedDate="medAddedDate";
   
	//table glucose tests
	static final String GlucoseTests_Table="Medication";
	static final String ColResult="result";
	static final String ColTestTime="time";
	static final String Collevel="level";
	
	//table appointments
	static final String appointments_Table="appointments";
	static final String ColAppointmentTime="time";

	//table advices
	static final String advices_Table="advices";
	static final String ColAdviceTime="time";
	static final String ColAdvice="advice";
	
	
	
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		
		db.execSQL("CREATE TABLE "+Patients_Table+" ("+ColProfileNumber+" INTEGER,"
                +ColFirstName+ " TEXT, "+ColLastName+ " TEXT,"+
                ColPhoneNumber+" INTEGER,"+ColMobileNumber+" INTEGER,"+ColDOB+
                " TEXT,"+ColGender+" TEXT,"+ColDoctorID+" INTEGER)");
		
		db.execSQL("CREATE TABLE "+Medications_Table+" ("+ColMedicationID+" INTEGER,"
                +ColMedName+ " TEXT, "+Coldescription+ " TEXT,"+
                ColDosage+" INTEGER,"+ColDosageUnit+" TEXT,"+ColMedStartDate+
                " INTEGER,"+ColMedEndDate+" INTEGER,"+ColMedAddedDate+" INTEGER)");
		
		db.execSQL("CREATE TABLE "+GlucoseTests_Table+" ("+ColResult+" INTEGER,"
                +ColTestTime+ " INTEGER, "+Collevel+ " INTEGER)");
		
		db.execSQL("CREATE TABLE "+appointments_Table+" ("+ColAppointmentTime+" INTEGER)");
		
		db.execSQL("CREATE TABLE "+advices_Table+" ("+ColAdviceTime+" INTEGER,"
                +ColAdvice+ " TEXT)"); 
		
		
	}
	
	
	
	public void insertPatient(String profileNumber, String firstName, String LastName, String PhoneNumber
			, String MobileNumber, String DOB, String Gender, String DoctorID)
	
    {
        
					SQLiteDatabase DB=this.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    
                    cv.put(ColProfileNumber, profileNumber);
                    cv.put(ColFirstName, firstName);
                    cv.put(ColLastName, LastName);
                    cv.put(ColPhoneNumber,PhoneNumber);
                    cv.put(ColMobileNumber, MobileNumber);
                    cv.put(ColDOB, DOB);
                    cv.put(ColGender,Gender);
                    cv.put(ColDoctorID,DoctorID);
                    
                    
                    DB.insert(Patients_Table, null, cv);
                    DB.close();
    }
	
	public void insertMedication(String MedicationId, String MedName, String Description, String Dosage
			, String DosageUnit, String MedStartDate, String MedEndDate, String MedAddedDate)
	
    {
        
					SQLiteDatabase DB=this.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    
                    cv.put(ColMedicationID, MedicationId);
                    cv.put(ColMedName, MedName);
                    cv.put(Coldescription, Description);
                    cv.put(ColDosage,Dosage);
                    cv.put(ColDosageUnit, DosageUnit);
                    cv.put(ColMedStartDate, MedStartDate);
                    cv.put(ColMedEndDate,MedEndDate);
                    cv.put(ColMedAddedDate,MedAddedDate);
                    
                    
                    DB.insert(Medications_Table, null, cv);
                    DB.close();
    }
	
	public void insertAppointment(String time)
	
    {
        
					SQLiteDatabase DB=this.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    
                    cv.put(ColAppointmentTime, time);
                    
                    
                    DB.insert(appointments_Table, null, cv);
                    DB.close();
    }
	
	public void insertGlucoseTests(String Result, String Testtime, String level)
	
    {
        
					SQLiteDatabase DB=this.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    
                    cv.put(ColResult, Result);
                    cv.put(ColTestTime, Testtime);
                    cv.put(Collevel, level);
                    
                    DB.insert(GlucoseTests_Table, null, cv);
                    DB.close();
    }
	
	
	public void insertAdvice(String time, String advice )
	
    {
        
					SQLiteDatabase DB=this.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    
                    cv.put(ColAdviceTime, time);
                    cv.put(ColAdvice, advice);
                    
                    
                    DB.insert(advices_Table, null, cv);
                    DB.close();
    }
	
	
	
	
	
	
	
	
	
	
	
	 public Cursor getPatient()
     {
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cur=db.rawQuery("SELECT *  from "+Patients_Table,new String [] {});
     
      return cur;
     }
	
	 public Cursor getMedication()
     {
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cur=db.rawQuery("SELECT *  from "+Medications_Table,new String [] {});
     
      return cur;
     }
	 
	 public Cursor getAppointment()
     {
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cur=db.rawQuery("SELECT *  from "+appointments_Table,new String [] {});
     
      return cur;
     }
	 
	 public Cursor getGlucoseTest()
     {
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cur=db.rawQuery("SELECT *  from "+GlucoseTests_Table,new String [] {});
     
      return cur;
     }
	 
	 
	 
	 public Cursor getAdvices()
     {
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cur=db.rawQuery("SELECT *  from "+advices_Table,new String [] {});
     
      return cur;
     } 
	 
	 
	 
	 
	 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	 public boolean isProfileExsists()
     {
     	SQLiteDatabase DB=this.getReadableDatabase();
     	Cursor cur=DB.rawQuery("SELECT COUNT(*) FROM "+Patients_Table,null);
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