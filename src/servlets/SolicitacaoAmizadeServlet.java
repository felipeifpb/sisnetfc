package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;

public class SolicitacaoAmizadeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response )throws IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		int idUser = Integer.parseInt( request.getParameter( "idUsuario" ) );	
		int idAmigo= Integer.parseInt( request.getParameter( "idUsuarioVisitado" ) );
		
		try {
			
			Fachada.getInstance().solicitaAmizade(idUser, idAmigo);
			response.sendRedirect( "pages/perfilVisitante.jsp?idAmigo="+ idAmigo );
	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
