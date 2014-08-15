package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;

public class ExcluirTopicoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse reponse) throws IOException{
		doPost(request, reponse);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse reponse) throws IOException{
		
		Integer idTo = Integer.parseInt( request.getParameter("t") );
		
		try {
			
			Fachada.getInstance().excluirTopico( idTo );
			reponse.sendRedirect("pages/topico.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}
}
