package project.controller.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.service.user.RemoveAddCart_Service;

@WebServlet("/Move_to_WishList_Controller")
public class Move_to_WishList_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("pid"));
		System.out.println(id);
		RemoveAddCart_Service ras = new RemoveAddCart_Service();
		
		HttpSession sess = request.getSession();
		String email =(String) sess.getAttribute("userid");
		
		if(sess.getAttribute("wishlist")==null)
		{
			List<Integer> wishlist = new ArrayList<Integer>();
			wishlist.add(id);
			sess.setAttribute("wishlist", wishlist);
			try {
				ras.delete(id, email);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.include(request, response);
			response.getWriter().println("<br><br><br><h4><center>ADD TO WISH LIST</center></h4>");
		}
		else
		{
			List<Integer> wishlist = (List<Integer>) sess.getAttribute("wishlist");
			if(wishlist.contains(id))
			{
				try {
					ras.delete(id, email);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.include(request, response);
				response.getWriter().println("<br><br><br><h4><center>ALREADY AVAILABLE IN WISH LIST</center></h4>");
			}
			else
			{
				wishlist.add(id);
				System.out.println(wishlist);
				System.out.println("0000000");
				try {
					ras.delete(id, email);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.include(request, response);
				response.getWriter().println("<br><br><br><h4><center>ADD TO WISH LIST</center></h4>");
			}
		}
	}

}
