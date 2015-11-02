package com.ktboys.XTServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktboys.XTServer.Manager.UserDitalsManage;
import com.ktboys.XTServer.Manager.UserManage;

/**
 * Servlet implementation class SetUserDitals
 */
@WebServlet("/SetUserDitals")
public class SetUserDitals extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetUserDitals() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		PrintWriter pw = response.getWriter();
		UserManage um = new UserManage(token);
		if (um.isExist()) {
			int id = um.getUserId();
			UserDitalsManage udm = new UserDitalsManage(id);
			udm.updateUserDitals(request.getParameter("picurl"),
					request.getParameter("nickname"),
					request.getParameter("name"),
					Integer.parseInt(request.getParameter("age")),
					Integer.parseInt(request.getParameter("sex")),
					request.getParameter("school"),
					request.getParameter("college"),
					request.getParameter("email"),
					request.getParameter("phone"));
			String result="{\"status\":0}";
			System.out.println(result);
			pw.write(result);
		}
		else {
			String result="{\"status\":1,\"reason\":\"ÐÞ¸ÄÊ§°Ü\"}";
			System.out.println(result);
			pw.write(result);
		}
		um.close();
	}

}
