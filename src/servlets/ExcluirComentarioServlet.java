package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;

public class ExcluirComentarioServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException{
		
		Integer idC = Integer.parseInt( request.getParameter("c") );
		
		
		try {
			
			Fachada.getInstance().excluirComentario(idC);
			response.sendRedirect("pages/comentario.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
