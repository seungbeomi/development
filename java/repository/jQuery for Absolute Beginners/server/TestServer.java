package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServer extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String comments = req.getParameter("comments");
		String result = "<<< entered Server : \nname=" + name + "\nemail=" + email + "\ncomments=" + comments;

		res.getWriter().write("success : " + result);
	}
}
