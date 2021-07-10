package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DbConnection {
	Connection conn;
	Statement stm;
	public DbConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded Success");	
				System.out.println("Connection Success");		
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3308/fconn","root","");
				stm=conn.createStatement();			
			} catch (Exception e) {
	     System.out.println("Driver not loaded");
	     System.out.println("Connection not Success");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//Constructor close 
		  public int Ins (String name,String  dob,String gender ,String reg_no,String email,String pass) { 
			  int row=0; 
		     try {
	String sql="insert into ftable (name,birthday,gender,reg_no,email,password) values ('"+name+"','"+dob+" ','"+gender+" ','"+reg_no+"','"+email+"','"+pass+"')"; 
		  ////System.out.println(sql); 
		  stm.executeUpdate(sql); }		 
		  catch (SQLException e) {
		  e.printStackTrace(); 
		 }
		  return row;
		  }///method closed 
	//	  public List  Login( String email ,String pass){
		  public LoginModel  Login( String email ,String pass){
			  //List list=new ArrayList();
			  LoginModel loginModel=new LoginModel();  
			  try {
			  String  sql="select *  from ftable  where   Email='"+email+"' and  password='"+pass+"' ";
			  System.out.println(sql);
			
			  ResultSet rs=stm.executeQuery(sql);
				while(rs.next()){
					loginModel.setId(rs.getInt("id"));
					loginModel.setName(rs.getString("name"));
				     loginModel.setBirthday(rs.getString("birthday"));
					loginModel.setGender(rs.getString("gender"));	
					loginModel.setRegNo(rs.getString("reg_no"));
					loginModel.setEmail(rs.getString("email"));
                    loginModel.setPassword(rs.getString("password"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return loginModel;
		  }
		  public  LoginModel   Show_Record (){  
			  LoginModel loginModel=new LoginModel();
			      try {					   
			             String sql="select * from ftable ";
				        ResultSet rs=stm.executeQuery(sql);	
			     	while(rs.next()){
				        loginModel.setId(rs.getInt("id"));
						loginModel.setName(rs.getString("name"));
						loginModel.setBirthday(rs.getString("birthday"));
						loginModel.setGender(rs.getString("gender"));
						loginModel.setRegNo(rs.getString("reg_no"));
						loginModel.setEmail(rs.getString("email"));
	                    loginModel.setPassword(rs.getString("password"));    	 
			    	 System.out.println( rs);
						}
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return  loginModel;			
		  }///method close
		  public  List   ShowdataforInsert(){
		      List list=new ArrayList();		  
			  try {
			    String sql="select  * from   ftable ";
		     	ResultSet rs=stm.executeQuery(sql);	
	         	while(rs.next()){
	       	         	 list.add(rs.getInt("id"));
				     	 list.add(rs.getString("name"));
			            list.add(rs.getString("birthday"));
			            list.add(rs.getString("gender"));
				    	 list.add( rs.getString("reg_no"));
				    	 list.add(rs.getString("email"));
				    	list.add(rs.getString("password"));
				System.out.println("Id fatching Success"+list);
		
	         	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			  return  list;
			  
		  }
		  
   public int Update(String id[],String name,String birthday,String gender,String reg_no,String email, String password){
	   int row=0;
	   try {
			for(Object ob:id){
	   String  sql="update ftable  set name='"+name+"',  birthday='"+birthday+ "',  gender='"+gender+"' ,   reg_no='"+reg_no+"' ,  email='"+email+"', password='"+password+"'   where id='"+ob+"'  ";
	stm.executeUpdate(sql);
	////System.out.println("update Success"+sql);
			
			}
} catch (SQLException e) {
	System.out.println("update not success");
	e.printStackTrace();
}	 
return row;	   
   }///method close
		  public int del(String id[]){
			  int row=0;
			  try {
			  for(Object obj:id){
				  String sql="delete   from ftable  where  id='"+obj+"'";
			  stm.executeUpdate(sql);
			////System.out.println("Delete Success"+sql);
			  }
			  } catch (SQLException e) {
				System.out.println("Delete not Success");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return row;
		  }////method close
		  
	/*	  public  List   Next(String Id[]){
				  
			  List list=new ArrayList();		  
			  try {
				  for(Object ob:Id){	 
			    String sql="select  * from   ftable where id='"+ob+"' ";
		     	ResultSet rs=stm.executeQuery(sql);	
	         	if(rs.next()){
	       	         	 list.add(rs.getInt("id"));
				    	 list.add(rs.getString("name"));
				    	list.add( rs.getString("reg_no"));
				    	 list.add(rs.getString("email"));
				    	list.add(rs.getString("password"));
				System.out.println("Id fatching Success"+list);
			}
			  }  
				  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
				  return  list;
		  }
	*/
		  public  LoginModel   Next (String []id){  
			  LoginModel loginModel=new LoginModel();
			      try {					   
			    	  for(Object ob:id){
			             String sql="select * from ftable where id='"+ob+"' ";
				        ResultSet rs=stm.executeQuery(sql);	
		    		while(rs.next()){
				        loginModel.setId(rs.getInt("id"));
						loginModel.setName(rs.getString("name"));
						loginModel.setRegNo(rs.getString("reg_no"));
						loginModel.setEmail(rs.getString("email"));
	                    loginModel.setPassword(rs.getString("password"));    	 
			    	 System.out.println( rs);
						}
			    	  }
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return  loginModel;			
		  }///method close

		  public LoginModel  admin(int  id){
			  //List list=new ArrayList();
			  LoginModel loginModel=new LoginModel();  
			  try {
			  String  sql="select *  from ftable   '"+id+"'";
			 System.out.println(sql);
				ResultSet rs=stm.executeQuery(sql);
				if(rs.first()){
					loginModel.setId(rs.getInt("id"));
					loginModel.setName(rs.getString("name"));
					loginModel.setRegNo(rs.getString("reg_no"));
					loginModel.setEmail(rs.getString("email"));
                    loginModel.setPassword(rs.getString("password"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return loginModel;
		  }

}

