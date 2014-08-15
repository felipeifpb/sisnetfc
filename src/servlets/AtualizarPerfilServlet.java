package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.CampoTextoVazioException;
import fachada.Fachada;
import beans.Usuario;

public class AtualizarPerfilServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		
		String nome = request.getParameter("nome");
		String apelido = request.getParameter("apelido");
		String cidade = request.getParameter("cidade");
		
		try {
			
			usuario.setNome(nome);
			usuario.setApelido(apelido);
			usuario.setCidade(cidade);
			
			Fachada.getInstance().atualizarUsuario(usuario);
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("testePerfil");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CampoTextoVazioException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
