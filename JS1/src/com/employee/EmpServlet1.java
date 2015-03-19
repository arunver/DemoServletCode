package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.ObjectNotFoundException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * Servlet implementation class EmpServlet1
 */
public class EmpServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet1() {
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
		String pass= request.getParameter("Pass");
		String dob= request.getParameter("DOB");
		//System.out.println(userName+" and "+password);

		System.out.println("Inside employee servlet :"+fname+" and "+lname);
		response.setContentType("text");
		String res="";
		try{

			MongoClient client= new MongoClient("localhost",27017);


			if(isConnectedtoMongoDB(client))
			{
				DB mongoDb = client.getDB("User");
				DBCollection users = mongoDb.getCollection("Employee");

				BasicDBObject user= new BasicDBObject("fname",fname).append("lname", lname);

				//System.out.println(users.find(user).count());
				DBCursor  cursor = users.find(user);

				try
				{
					DBObject obj= cursor.next();
					String FirstName = (String)obj.get("fname");
					String LastName = (String)obj.get("lname");
					if(FirstName.equalsIgnoreCase(fname) && LastName.equalsIgnoreCase(lname))
					{
						res= "User Already Exist";
					}
				}
				catch(NoSuchElementException e)
				{
					BasicDBObject doc = new BasicDBObject("fname", fname)
			        .append("lname", lname)
			        .append("pass", pass)
			        .append("Address", add)
			        .append("email", email)
			        .append("Phno", phno)
			        .append("DOB", dob)
					.append("Role","User");
					users.insert(doc, WriteConcern.SAFE);
					res= "Employee Added Successfully";
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
		// TODO Auto-generated method stub
	}
	
	protected boolean isConnectedtoMongoDB(MongoClient client)
	{
		try
		{
			client.getDatabaseNames();
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
}
