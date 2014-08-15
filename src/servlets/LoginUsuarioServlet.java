package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excecoes.CampoTextoVazioException;
import fachada.Fachada;
import beans.Usuario;

public class LoginUsuarioServlet extends HttpServlet {
		
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		doPost(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException{

		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("emailLogin");
		String senha = request.getParameter("senhaLogin");
		
		try {

			Usuario usuario = Fachada.getInstance().verificaLoginUsuario(email, senha); 
		    
		 	if (usuario == null){
		 		response.sendRedirect("pages/loginInvalido.html");
		 		
		 	}else{
		 		
		 		HttpSession sessao = request.getSession();
		 		sessao.setAttribute("usuario", usuario);
		 		
		 		if( !usuario.getStatusPerfil() ){
		 			
		 			response.sendRedirect("pages/ativaPerfil.html");//página de ativação de perfil
		 			
		 		} else {
		 			
		 			response.sendRedirect("pages/perfil.jsp");
		 			
		 		}
		 	}
		 	
		} catch (ParseException e) {
			response.sendRedirect("pages/erroParse.html");
		} catch ( ClassNotFoundException e){
			response.sendRedirect("pages/erroClasse.html");
		} catch ( SQLException e ){
			response.sendRedirect("pages/erroSql.html");
		} catch (CampoTextoVazioException e) {
			response.sendRedirect("pages/loginInvalido.html");
		}
			
	}

}
