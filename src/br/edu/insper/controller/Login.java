package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		try {
			dao = new DAO();
			String usuario = request.getParameter("usuariologin");
			Integer senha = Integer.parseInt(request.getParameter("senhalogin"));
			
			try {
				 // dao = new DAO();
				Usuarios usuarioLogin = dao.login(usuario, senha);
				PrintWriter out = response.getWriter();
				
				if (usuarioLogin == null) {
					
					out.print("<body>");
					out.print("<h3>Verifique se escreveu corretamente o usuario ou a senha!</h3>");
					out.print("</body>");
				} else {
					
					List<Organization> organizations = dao.getLista(usuarioLogin.getUsuario_id());
					request.setAttribute("organizations", organizations);
					request.setAttribute("usuario", usuarioLogin);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/principal.jsp");
					rd.forward(request, response);
				
				}
			} catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.print("<body>");
				out.print("<h3>Você não está cadastrado!</h3>");
				out.print("</body>");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
