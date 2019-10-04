package com.lti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.dao.ProductDao;
import com.lti.exception.DataAccessException;
import com.lti.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	
	private int pageSize = 3;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Integer cursor = (Integer) session.getAttribute("cursor");
		if(cursor == null)
			cursor = 1;
		
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equals("next"))
				cursor += pageSize;
			else if(action.equals("prev"))
				cursor -=pageSize;
		}
		session.setAttribute("cursor", cursor);
		
		
		ProductDao productDao = new ProductDao();
		
		/*try{
			List<Product> p= productDao.fetch(cursor, cursor + pageSize-1);
			out.print("<table border=1 style='width:50%; border-collapse: collapse'>");
			out.print("<tr>");
			out.print("<th> ID </th>");
			out.print("<th> Product </th>");
			out.print("<th> Price </th>");
			out.print("<th> Quantity </th>");
			out.print("</tr>");
			for(Product prod : p) {
				out.print("<tr>");
				out.print("<td>"+prod.getId()+"</td>");
				out.print("<td>"+prod.getName()+"</td>");
				out.print("<td>"+prod.getPrice()+"</td>");
				out.print("<td>"+prod.getQuantity()+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			//out.print("<button type='Submit' name = 'next'>Next</button>");
			//out.print("<button type='Submit' name = 'previous'>Previous</button>");
			out.print("<a href='ProductServlet?action=prev'>Prev</a>\t\t\t\t");
			out.print("<a href='ProductServlet?action=next'>Next</a>");
			
		}
		catch (DataAccessException e) {
			throw new ServletException(e);
		}*/
		
		try {
			List<Product> list= productDao.fetch(cursor, cursor + pageSize-1);
			session.setAttribute("listOfProducts", list);
			
			response.sendRedirect("ViewProduct.jsp");
		}
		catch (DataAccessException e) {
			throw new ServletException(e);
		}
		
	}

}
