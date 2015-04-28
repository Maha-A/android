package kustar_catalog.first;

public class Pre_req {

	 String Course_ID_Pre;
	 String Pre_req_Pre;
			
			 
			 public Pre_req()
			 {
				  Course_ID_Pre=new String();
				  Pre_req_Pre=new String();
				
				 
				 

			 }
			 
			 public Pre_req(String coureid, String prereqid)
			 {
				  Course_ID_Pre=coureid;
				  Pre_req_Pre=prereqid;
				
			 }
			 
			 public void SetPre_req(String coureid, String prereqid)
			 {
				  Course_ID_Pre=coureid;
				  Pre_req_Pre=prereqid;
				
			 }
			 
		}
