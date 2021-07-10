package com;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ServletCotroller extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DbConnection  obj=new 	DbConnection();
		    String Event=request.getParameter("event"); 
		   if(Event.equals("login")){ 
		    String  Email=request.getParameter("email");
		    String Pass=request.getParameter("password");
		    LoginModel    loginModel=obj.Login(Email, Pass);
		   if(loginModel.getEmail()==null){   
			 request.getRequestDispatcher("index.html").forward(request,response);  
		   }else{
		    out.println("<font color='blue'  size='5'   <i>Login Successfully</i></font>");
		     request.getRequestDispatcher("Portfolio.html").forward(request, response);
		   }
		   }else if(Event.equals("Register")) {
		   String Name=request.getParameter("name");
		   String  dob=request.getParameter("birthday");
		   String  Gender=request.getParameter("gender");
		   String  Reg_no=request.getParameter("reg_no"); 
		   String  Emaill=request.getParameter("email"); 
		   String  Passs=request.getParameter("password");   
		   int row=obj.Ins(Name, dob,Gender,Reg_no, Emaill, Passs);
		 if(row<=0) {   
			 out.println("Register  Success");
			request.getRequestDispatcher("login1.html").forward(request, response);
		 }else {
            out.print("Register  not Success");   
       	 request.getRequestDispatcher("index.html").forward(request,response);  
		 }		     
		    }else if(Event.equals("check_current_record")) {
		    		LoginModel    loginModel=obj.Show_Record();
		    	 out.println("<font color='blue'  size='5'   <i>Data Show Successs</i></font>");
			       out.println("<table  border='4' bgcolor='silver' align='center' ");
		 		   out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th><th colspan='2'>Action</th></tr>"); 
		 		     out.println("<form action='ServletCotroller' method='post'>");
			    	     out.println("<td><input type='text'  name='id' value="+loginModel.getId()+"></td>");
			    	     out.println("<td><input type='text'  name='name'  value="+loginModel.getName()+"></td>");
			    	     out.println("<td><input type='text' name='birthday'   value='"+loginModel.getBirthday()+"'></td>");
			    	     out.println("<td><input type='text' name='gender'    value='"+loginModel.getGender()+"'></td>");
					   out.println("<td><input type='text'  name='reg_no'   value="+loginModel.getRegNo()+"></td>");
					   out.println("<td><input type='text' name='email'      value="+loginModel.getEmail()+"></td>");
					   out.println("<td><input type='text' name='password'  value="+loginModel.getPassword()+"></td>");
					  out.println("<td><input type='submit' name='event'  value='update'></td>");
					  out.println("<td><input type='submit' name='event'  value='delete'></td>");
					  out.println("<td><input type='submit' name='event'  value='next'></td>");		
					 out.println("<td><input type='submit' name='event' value='previous'></td>");   		  
				
					  out.println("</form>");  
			          out.println("</tr>");
			          out.println("</table>");
		    }else if(Event.equals("check_all_records")){
		    	////int 	Idd= Integer.parseInt(request.getParameter("iddd"));
		    	List list=obj.ShowdataforInsert();
		    	out.println("<font color='blue'  size='5'   <i>Data Show Successs</i></font>");
		       out.println("<table  border='4' bgcolor='silver' align='center' ");
	 		    out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th></tr>"); 
	 		   out.println("<form action='ServletCotroller' method='post'>");
	 		    Iterator itr=list.iterator();
	            while(itr.hasNext()){
	        	  out.println("<tr>");
	    	   out.println("<td><input type='checkbox'  name='id'  value= "+itr.next()+"></td>");
			   out.println("<td><input type='text'  name='name'  value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='birthday' value='"+itr.next()+"'></td>");
			   out.println("<td><input type='text'  name='gender'  value='"+itr.next()+"'></td>");
			   out.println("<td><input type='text'  name='reg_no'   value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='email'  value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='password'  value="+itr.next()+"></td>");
			  //out.println("<td><input type='submit' name='event'  value='next'></td>");		
			 //out.println("<td><input type='submit' name='event' value='previous'></td>");
	            
		
			  out.println("</tr>");
	      }
	            out.println("<td><input  type='submit' name='event'  value='delete'></td>");
	      	  out.println("</form>");
		    out.println("</table>");
		    }else if(Event.equals("update")) {
		        String	id[]=(request.getParameterValues("id"));
		        String name=request.getParameter("name");
              String birthday=request.getParameter("birthday");
              String gender=request.getParameter("gender");
		         String 	reg_no=request.getParameter("reg_no");
		        String 	email=request.getParameter("email");
			    String  	password=request.getParameter("password");
				int row=obj.Update(id, name,birthday,gender, reg_no, email, password);
				if(row<=0){
					out.println("<font color='green'  size='5'   <i>Update Success</i></font>");
		    	List list=obj.ShowdataforInsert();	
					out.println("<table  border='4' bgcolor='green' align='center' ");
		 		 out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th></tr>"); 
		 		out.println("<form action='ServletCotroller' method='post'>");
		 		 Iterator itr=list.iterator();
		           while(itr.hasNext()){
		        	  out.println("<tr>");
		    	  out.println("<td><input type='checkbox'  name='id'  value= "+itr.next()+"></td>");
				   out.println("<td><input type='text'  name='name'  value="+itr.next()+"></td>");
				   out.println("<td><input type='text' name='birthday' value='"+itr.next()+"'></td>");
				   out.println("<td><input type='text' name='gender' value='"+itr.next()+"'></td>");
				   out.println("<td><input type='text'  name='reg_no'   value="+itr.next()+"></td>");
				   out.println("<td><input type='text' name='email'  value="+itr.next()+"></td>");
				    out.println("<td><input type='text' name='password'  value="+itr.next()+"></td>");
		          
				    out.println("</tr>");
		      }
		           out.println("</form>");
		           out.println("<td><input type='submit' name='event'  value='delete'></td>");	  
			      out.println("</table>");
		}else {
					out.println("<font color='red'>   <i><h3>Update not  Successs</h3></i></font>");	
				}
		    }else if(Event.equals("delete")) {
			   String	id[]=(request.getParameterValues("id")); 
			   int row=obj.del(id);	
		    	if(row<=0){	
			     out.println("<font  color='blue'    size='5'>  Delete Success</font>");
			List list=obj.ShowdataforInsert();	
				out.println("<table  border='4' bgcolor='blue' align='center' ");
	 		 out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th><th colspan='2'>Action</th></tr>"); 
  	       Iterator itr=list.iterator();
	           while(itr.hasNext()){
	        	  out.println("<tr>");
	    	  out.println("<form action='ServletCotroller' method='post'>");
	    	  out.println("<td><input type='checkbox'  name='id'  value= "+itr.next()+"></td>");
			   out.println("<td><input type='text'  name='name'  value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='birthday' value='"+itr.next()+"'></td>");
			   out.println("<td><input type='text' name='gender' value='"+itr.next()+"'></td>");
			   out.println("<td><input type='text'  name='reg_no'   value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='email'  value="+itr.next()+"></td>");
			   out.println("<td><input type='text' name='password'  value="+itr.next()+"></td>");
				  out.println("<td><input type='submit' name='event'  value='next'></td>");		
			      out.println("<td><input type='submit' name='event' value='previous'></td>");   		  
			 	  out.println("<td><input type='submit' name='event'  value='next'></td>");		
				   out.println("<td><input type='submit' name='event' value='previous'></td>");   		  
			
			   out.println("</tr>");
	      }
	           out.println("<td><input type='submit' name='event'  value='delete'></td>");
	     	  out.println("</form>");
	           out.println("</table>");	     
	    	}else {
			  out.println("<font color='red'  size='5' >Delete not Success</font>");
		  }
		   }else if(Event.equals("next")){
		    	   int 	id=Integer.parseInt(request.getParameter("id"));
		    	    
		    	    out.println(id);
		    	   //// LoginModel    loginModel=obj.Next(id);	
		    		 LoginModel    loginModel=obj.admin(id);	
		        	 out.println("<font color='blue'  size='5'   <i>Id is Increase Successfully</i></font>");
		            out.println("<table  border='4' bgcolor='silver' align='center' ");
	 		        out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th><th colspan='2'>Action</th></tr>");        
		 		    out.println("<tr>"); 
	 		        out.println("<form action='ServletCotroller' method='post'>");
			    	  out.println("<td><input type='text'  name='id' value="+loginModel.getId()+"></td>");
					   out.println("<td><input type='text'  name='name'  value="+loginModel.getName()+"></td>");
					   out.println("<td><input type='text' name='birthday' value='"+loginModel.getBirthday()+"'></td>");
					   out.println("<td><input type='text' name='gender' value='"+loginModel.getGender()+"'></td>");
					   out.println("<td><input type='text'  name='reg_no'   value="+loginModel.getRegNo()+"></td>");
					   out.println("<td><input type='text' name='email'      value="+loginModel.getEmail()+"></td>");
					   out.println("<td><input type='text' name='password'  value="+loginModel.getPassword()+"></td>");
					  out.println("<td><input type='submit' name='event'  value='update'></td>");
					  out.println("<td><input type='submit' name='event'  value='delete'></td>");
						 out.println("<td><input type='submit' name='event'  value='next'></td>");		
					      out.println("<td><input type='submit' name='event' value='previous'></td>");   		  
			         		  out.println("</form>");
	      out.println("</tr>");
	      
		    out.println("</table>");
		    	
		    	}else if(Event.equals("previous")){
		    		int 	id=Integer.parseInt(request.getParameter("id"));
		    	    id--;
		    	    out.println(id);
		    	   //// LoginModel    loginModel=obj.Next(id);	
		    		 LoginModel    loginModel=obj.admin(id);	
		        	 out.println("<font color='blue'  size='5'   <i>Id is Increase Successfully</i></font>");
		            out.println("<table  border='4' bgcolor='silver' align='center' ");
	 		        out.println("<tr><th>Id</th><th>Name</th><th>Birthday</th><th>Gender</th><th>Reg_No</th><th>Email</th><th>Password</th><th colspan='2'>Action</th></tr>");        
		 		    out.println("<tr>");
	 		        out.println("<form action='ServletCotroller' method='post'>");
			    	  out.println("<td><input type='text'  name='id' value="+loginModel.getId()+"></td>");
					   out.println("<td><input type='text'  name='name'  value="+loginModel.getName()+"></td>");
					   out.println("<td><input type='text' name='birthday' value='"+loginModel.getBirthday()+"'></td>");
					   out.println("<td><input type='text' name='gender' value='"+loginModel.getGender()+"'></td>");
					   out.println("<td><input type='text'  name='reg_no'   value="+loginModel.getRegNo()+"></td>");
					   out.println("<td><input type='text' name='email'      value="+loginModel.getEmail()+"></td>");
					   out.println("<td><input type='text' name='password'  value="+loginModel.getPassword()+"></td>");
					  out.println("<td><input type='submit' name='event'  value='update'></td>");
					  out.println("<td><input type='submit' name='event'  value='delete'></td>");
						  out.println("<td><input type='submit' name='event'  value='next'></td>");		
						 out.println("<td><input type='submit' name='event' value='previous'></td>");   		  
					  out.println("</form>");
	               out.println("</tr>");
		    out.println("</table>");
		  
		    	}else {
		  System.out.println("Sorry Invalid ");
		  
		  }		
	}   	  	    
}