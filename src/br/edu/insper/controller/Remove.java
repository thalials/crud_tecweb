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
 * Servlet implementation class Remove
 */
@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao;
		try {
			dao = new DAO();
			String organization_id = request.getParameter("remove");
			
			Organization organization = dao.getToDo(organization_id);
			int id_user = organization.getId_user();
			dao.remove(organization_id);
			
			List<Organization> organizations = dao.getLista(Integer.valueOf(id_user));
			Usuarios usuario = dao.getUsuario(Integer.valueOf(id_user));
			request.setAttribute("organizations", organizations);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/principal.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
