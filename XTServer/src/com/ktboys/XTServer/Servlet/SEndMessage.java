package com.ktboys.XTServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktboys.XTServer.Manager.MessageManage;

/**
 * Servlet implementation class SEndMessage
 */
@WebServlet("/SEndMessage")
public class SEndMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SEndMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		
		MessageManage mm = new MessageManage(token);
		int rlt = mm.sendMessage(username, content);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (rlt == 0) {
			String result="{\"status\":0}";
			System.out.println(result);
			pw.write(result);
		}
		else if(rlt==1){
			String result="{\"status\":1,\"reason\":\"∑¢ÀÕ ß∞‹\"}";
			System.out.println(result);
			pw.write(result);
		}
		else if (rlt==2) {
			String result="{\"status\":1,\"reason\":\"∑¢ÀÕ ß∞‹\"}";
			System.out.println(result);
			pw.write(result);
		}
		mm.close();
	}

}
