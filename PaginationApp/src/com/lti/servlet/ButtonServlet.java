package com.lti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ButtonServlet
 */
@WebServlet("/ButtonServlet")
public class ButtonServlet extends HttpServlet {
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	LocalTime time = LocalTime.now();
	//LocalTime start = LocalTime.of(10,00);
	//LocalTime end = LocalTime.of(12, 00);
	int currentHr = time.getHour();
	
	/*Calendar cal = Calendar.getInstance();
	int currentHr = cal.get(Calendar.HOUR_OF_DAY);
	*/
	ServletConfig sconf = getServletConfig();
	System.out.println(sconf.getServletName());
	int startTime = Integer.parseInt(sconf.getInitParameter("startTime"));
	int endTime = Integer.parseInt(sconf.getInitParameter("endTime"));

	
	//if( time.isAfter(start) && time.isBefore(end)){
	if(currentHr>=startTime && currentHr<=endTime) {
		 out.print("<h1>You can continue</h1>");
	 }
	 else{
		 out.print("Sorry ! Tatkal Booking permitted only between "+startTime+" AM and "+endTime+" Noon");
	 }
	
		
	}
}
