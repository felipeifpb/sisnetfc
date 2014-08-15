package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excecoes.CampoTextoVazioException;
import fachada.Fachada;
import beans.Usuario;

public class RedefinirSenhaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sessao = request.getSession();
		
		String senha = request.getParameter("senha");
		String senhaConfirmada = request.getParameter("senhaConfirmacao");
		
		Usuario usuario = (Usuario)sessao.getAttribute("usuario");
		
			try {
				
				if( senha.equals(senhaConfirmada) ){
					
					usuario.setSenha(senhaConfirmada);
					Fachada.getInstance().redefinirSenha(usuario);
										
					sessao.setAttribute("usuario", usuario);
					response.sendRedirect("perfil.html");//acessar o perfil
					
				} else {
					response.sendRedirect("pages/paginasErro/erroObterNovaSenhaNaoConferem.html");
				}
				
			} catch (ClassNotFoundException e) {
				response.sendRedirect("pages/erroClasse.html");
			} catch (CampoTextoVazioException e) {
				response.sendRedirect("pages/paginasErro/erroObterNovaSenhaCampoVazio.html");
			} catch (SQLException e) {
				response.sendRedirect("pages/erroSql.html");
			}
		
	}
	
}
