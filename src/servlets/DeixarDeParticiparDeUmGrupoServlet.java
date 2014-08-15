package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fachada.Fachada;

public class DeixarDeParticiparDeUmGrupoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int codGrupo = Integer.parseInt( request.getParameter("id") );
		int codUser = Integer.parseInt( request.getParameter("user") );
		int codDirecionamento = Integer.parseInt( request.getParameter("codDirect") );
		
		try {
			
			Fachada.getInstance().desvincularDoGrupo(codGrupo, codUser);
			
			if( codDirecionamento == 1){
				response.sendRedirect("pages/menuVisualizarGrupoPesquisado.jsp");
			}else{
				response.sendRedirect("pages/menuGrupo.jsp");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
