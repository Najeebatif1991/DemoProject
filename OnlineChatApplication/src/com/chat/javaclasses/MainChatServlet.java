package com.chat.javaclasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainChatServlet
 */
@WebServlet("/MainChatServlet")
public class MainChatServlet extends HttpServlet {
	
	String chRoomPath;
	String roomListPath;
	String adminChatPath;
	
	public void init(){
		ServletContext servletcontext = getServletContext();
		System.out.println(servletcontext.getAttribute("CHROOM_PATH"));
/*		servletcontext.setAttribute(chRoomPath, servletcontext.getAttribute("CHROOM_PATH"));
		servletcontext.setAttribute(roomListPath, servletcontext.getAttribute("ROOMLIST_PATH"));
		servletcontext.setAttribute(adminChatPath, servletcontext.getAttribute("ADMINCHAT_PATH"));*/
	}
       
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("chRoomPath", chRoomPath);
		session.setAttribute("roomListPath", roomListPath);
		session.setAttribute("adminChatPath", adminChatPath);
		
		System.out.println(adminChatPath);
		HashMap hashmap = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","MySql#123");
			synchronized (getServletContext()) {
				hashmap = (HashMap)getServletContext().getAttribute("roomlist");
				if(hashmap==null){
					hashmap = new HashMap();
					Statement stm = con.createStatement();
					String query = "select * from chatrooms";
					ResultSet rs = stm.executeQuery(query);
					while(rs.next()){
						hashmap.put(rs.getString(1), new chatRooms(rs.getString(1),rs.getString(2),4));
					}
					rs.close();
					getServletContext().setAttribute("roomlist", hashmap);
				}
			}
			con.close();
			
			
		}
		catch(SQLException e){
			
		}
		catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
		RequestDispatcher view = request.getRequestDispatcher("chat.jsp");
		view.forward(request, response);
	}

}
