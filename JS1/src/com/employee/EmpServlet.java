package com.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.ObjectNotFoundException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class EmpServlet
 */
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fname= request.getParameter("fName");
		String lname = request.getParameter("lName");
		String add= request.getParameter("Address");
		String phno= request.getParameter("Phno");
		String email= request.getParameter("Email");
		String pass= request.getParameter("Password");
		String dob= request.getParameter("DOB");
		//System.out.println(userName+" and "+password);
		response.setContentType("text");
		String res="";
try{
			
			MongoClient client= new MongoClient("localhost",27017);
			
			
			if(isConnectedtoMongoDB(client))
			{
				DB mongoDb = client.getDB("User");
				DBCollection users = mongoDb.getCollection("users");
				
				//BasicDBObject user= new BasicDBObject("name",userName);
				
				//System.out.println(users.find(user).count());
				DBCursor  cursor = users.find();
				while(cursor.hasNext())
				{
					DBObject obj= cursor.next();
					String FirstName = (String)obj.get("name");
					String LastName = (String)obj.get("name");
					if(FirstName.equalsIgnoreCase(fname) && LastName.equalsIgnoreCase(lname))
					{
						res= "User Already Exist";
						break;
					}
					else
					{
						
					}

				}
			}
			else 
			{
				try{/*
					
					SessionFactory sf = new Configuration().configure().buildSessionFactory();
					Session s = sf.openSession();
					if(s.isConnected())
					{
						Employee e = new Employee();
						e=(Employee)s.load(Employee.class,userName);
						if(userName.equals(e.getEmpName()) && password.equals(e.getEmpPass()))
						{
							res= "success";
						}
						else
						{
							res= "Incorrect Password";
						}
					}
					else
					{
						res= "connection error";
					}
				*/}

				catch(ObjectNotFoundException e)
				{
					res= "No Username Found Error";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					res= "connection error";
				}
			}
			
		}
		catch(Exception e)
		{
			res= "connection error";
		}
		
		//System.out.println("Response is: "+res);

		PrintWriter out= response.getWriter();
		out.println(res);
	}
	
	protected boolean isConnectedtoMongoDB(MongoClient client)
	{
		try
		{
		//	client.getDatabaseNames();
		}
		catch(Exception e)
		{
			return false;
		}
		
		return false;
	}
	
	

}
