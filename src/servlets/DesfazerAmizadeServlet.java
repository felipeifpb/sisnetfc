package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;
import beans.Usuario;

public class DesfazerAmizadeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		int idAmigo = Integer.parseInt( request.getParameter("idAmigo") );
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		try {
			
			Fachada.getInstance().desfazerAmizade( user.getId(), idAmigo );
			String cod = request.getParameter("cod");
			
			if( cod == null){
				response.sendRedirect("pages/menuAmigos.jsp");
			} else if( cod.equals("1") ){
				response.sendRedirect("pages/perfilVisitante.jsp?idAmigo="+ idAmigo );
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
