package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.CampoTextoVazioException;
import fachada.Fachada;
import beans.Topico;
import beans.Usuario;

public class CriarTopicoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		String nomeTopico = request.getParameter("nome");
		String resultSessao = request.getParameter("num");
		
		int idGrupo = Integer.parseInt( resultSessao );
		
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		Topico topico = new Topico();
		
		topico.setNomeTopico( nomeTopico );
		topico.setUsuario(user);
		topico.getGrupo().setId(idGrupo);
		
		try {
			Fachada.getInstance().cadastrarTopico(topico);
			response.sendRedirect("pages/topico.jsp");
		} catch (ClassNotFoundException | SQLException | CampoTextoVazioException e) {
			e.printStackTrace();
		}
		
		
	}

}
