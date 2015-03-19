package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.employee.EmployeeDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String userName= request.getParameter("Username");
		String password = request.getParameter("password");
		
		//System.out.println(userName+" and "+password);
		response.setContentType("text");
		String res="";
		try{
			
			MongoClient client= new MongoClient("localhost",27017);
			
			
			if(isConnectedtoMongoDB(client))
			{
				DB mongoDb = client.getDB("User");
				DBCollection users = mongoDb.getCollection("Employee");
				
				//BasicDBObject user= new BasicDBObject("name",userName);
				
				//System.out.println(users.find(user).count());
				DBCursor  cursor = users.find();
				while(cursor.hasNext())
				{
					DBObject obj= cursor.next();
					String username = (String)obj.get("fname");
					String pass = (String)obj.get("pass");

					if(username.equals(userName) && pass.equals(password))
					{
						EmployeeDetails Employee= new EmployeeDetails();
						Employee.setFname(username);
						Employee.setAddress((String)obj.get("Address"));
						Employee.setDob((String)obj.get("DOB"));
						Employee.setEmail((String)obj.get("email"));
						Employee.setLname((String)obj.get("lname"));
						Employee.setPhno((String)obj.get("Phno"));
						Employee.setRole((String)obj.get("Role"));
						HttpSession hs = request.getSession();
						hs.setAttribute("Employee", Employee );
						Cookie c = new Cookie("username", username );
						c.setMaxAge(0);
						response.addCookie(c);
						RequestDispatcher view = request.getRequestDispatcher("./success.jsp");
						view.forward(request, response);
						res="success";
						break;
					}
					else if(username.equals(userName) && !pass.equals(password))
					{
						
						res= "Incorrect Password";
						
						
					}
					else
					{
						res= "No User Exists";
						
					}
				}
			}
			else 
			{
				try{
					
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
				}

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
			e.printStackTrace();
			res= "connection error";
		}
		
		//System.out.println("Response is: "+res);

		/*PrintWriter out= response.getWriter();
		out.println("<html><body onload=\"alert('Hello World')\"></body></html>");*/
		if(!res.equalsIgnoreCase("success"))
		{
			request.setAttribute("passwd", res);
			RequestDispatcher view = request.getRequestDispatcher("./JS1.jsp");
			view.forward(request, response);
		}
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
	
	/*protected boolean isConnectedtoMSSQL() {

		String SQLuserName = "sa";
		String SQLpassword = "info123!";

		String url = "jdbc:sqlserver://localhost:1433;databaseName=ORBackOffice_VSS";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url, SQLuserName, SQLpassword);
		//	ResultSet rs = conn.prepareStatement("select * from Test");
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}*/
	
}
