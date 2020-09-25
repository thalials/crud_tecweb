package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.DAO;
import br.edu.insper.model.Organization;
import br.edu.insper.model.Usuarios;

/**
 * Servlet implementation class CriaTask
 */
@WebServlet("/CriaTask")
public class CriaTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao;
		String id_user = request.getParameter("id_user");
		String todo = request.getParameter("todo");
		String deadlineStg = request.getParameter("deadline");
		// String deadline;
		
		try {
			dao = new DAO();		
			dao.adicionaTask(id_user, todo, deadlineStg);
			List<Organization> organizations = dao.getLista(Integer.valueOf(id_user));
			Usuarios usuario = dao.getUsuario(Integer.valueOf(id_user));
			request.setAttribute("organizations", organizations);
			request.setAttribute("usuario", usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/principal.jsp");
			rd.forward(request, response);
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
