package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;
import beans.ComentarioTopico;
import beans.Usuario;

public class PublicarComentarioServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		int idTopico = Integer.parseInt( request.getSession().getAttribute("idTopico").toString() );
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		String comentario = request.getParameter("comentario");
		
		ComentarioTopico coment = new ComentarioTopico();
		coment.setUsuario(user);
		coment.getTopico().setId(idTopico);
		coment.setComentario(comentario);
		
		try {
			
			Fachada.getInstance().comentarTopico(coment);
			response.sendRedirect("pages/comentario.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
