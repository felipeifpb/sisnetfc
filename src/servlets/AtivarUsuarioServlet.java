package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Usuario;
import fachada.Fachada;

public class AtivarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		HttpSession sessao = request.getSession();
		Usuario usuario = (Usuario)sessao.getAttribute("usuario");
		
		try {
			
			usuario.setStatusPerfil(true);
			Fachada.getInstance().ativarConta(usuario);
			sessao.setAttribute("usuario", usuario);
			response.sendRedirect("testePerfil");//pagina perfil
			
		} catch (ClassNotFoundException e) {
			response.sendRedirect("pages/erroClasse.html");
		} catch (SQLException e) {
			response.sendRedirect("pages/erroSql.html");
		}
	}
	
}


