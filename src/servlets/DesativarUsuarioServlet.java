package servlets;

import java.io.IOException;
import java.sql.SQLException;

import fachada.Fachada;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.Usuario;


public class DesativarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		HttpSession sessao = request.getSession();
		Usuario usuario = (Usuario)sessao.getAttribute("usuario");
		
		try {
			
			usuario.setStatusPerfil(false);
			Fachada.getInstance().desativarConta(usuario);
			sessao.invalidate();
			response.sendRedirect("index.html");//pagina inicial
			
		} catch (ClassNotFoundException e) {
			response.sendRedirect("pages/erroClasse.html");
		} catch (SQLException e) {
			response.sendRedirect("pages/erroSql.html");
		}
	}
	
}
