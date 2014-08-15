package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;

import util.EnviarEmail;
import excecoes.CampoTextoVazioException;
import fachada.Fachada;
import beans.Usuario;

public class PesquisarEmailRecuperarSenhaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		
		try {
			
			Usuario usuario = Fachada.getInstance().pesquisarUsuarioPorEmail(email);
						
			if( usuario != null){
				
				HttpSession sessao = request.getSession();
				Random rand = new Random();
				
				int codigo = rand.nextInt(100000);
				
				EnviarEmail.enviarEmail( email, codigo );
				
				sessao.setAttribute("usuario", usuario);
				sessao.setAttribute("codigo", codigo);
				
				response.sendRedirect("pages/recuperacaoDeSenhaComCodigo.html");//direciona para página se o email existir
				
			} else {
				response.sendRedirect("pages/paginasErro/erroRecuperacaoDeSenha.html");//retorna para página caso o email não exista
			}
			
		} catch ( ClassNotFoundException e ) {
			response.sendRedirect("pages/erroClasse.html");
		} catch ( CampoTextoVazioException e ){
			response.sendRedirect("pages/paginasErro/erroRecuperacaoDeSenhaEmailVazio.html");
		} catch( SQLException e ){
			response.sendRedirect("pages/erroSql.html");
		} catch (IOException e) {
			response.getWriter().println( e.getMessage() );
			e.printStackTrace();
		} catch (EmailException e) {
			response.getWriter().println( e.getMessage() );
			e.printStackTrace();
		}		
	}

}
