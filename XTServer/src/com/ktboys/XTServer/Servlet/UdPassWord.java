package com.ktboys.XTServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktboys.XTServer.Manager.UserManage;

/**
 * Servlet implementation class UdPassWord
 */
@WebServlet("/UdPassWord")
public class UdPassWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UdPassWord() {
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
		String bepassword = request.getParameter("bepassword");
		String password = request.getParameter("password");

		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		UserManage um = new UserManage(token);

		int rlt = um.updatePassword(bepassword, password);
		if (rlt == 1) {
			String result = "{\"status\":1,\"reason\":\"�޸�����ʧ��\"}";
			System.out.println(result);
			pw.write(result);
		} else if (rlt == 2) {
			String result = "{\"status\"2,\"reason\":\"�������\"}";
			System.out.println(result);
			pw.write(result);
		} else {
			String result = "{\"status\":0,\"token\":\"" + um.getToken()
					+ "\"}";
			System.out.println(result);
			pw.write(result);
		}
		um.close();
	}

}
