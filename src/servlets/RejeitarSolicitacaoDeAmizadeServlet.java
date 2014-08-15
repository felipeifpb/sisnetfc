package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;
import beans.Usuario;

public class RejeitarSolicitacaoDeAmizadeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request,response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int idAmigo = Integer.parseInt( request.getParameter("idAmizade")  );
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		try {
			
			Fachada.getInstance().rejeitarAmizade(idAmigo, user.getId() );
			response.sendRedirect("pages/perfil.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
}


