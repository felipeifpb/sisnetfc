package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Grupo;
import beans.Usuario;
import fachada.Fachada;

public class ParticiparDeUmGrupoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Grupo gru = (Grupo)request.getSession().getAttribute("grupoV");
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		try {
			
			Fachada.getInstance().participarDoGrupo( gru , user );
			response.sendRedirect("pages/menuVisualizarGrupoPesquisado.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
