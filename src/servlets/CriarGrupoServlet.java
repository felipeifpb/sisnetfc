package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.CampoTextoVazioException;
import excecoes.EmailJaExisteException;
import fachada.Fachada;
import beans.Grupo;
import beans.Usuario;


public class CriarGrupoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		 
		request.setCharacterEncoding("UTF-8");
		
		String descricao = request.getParameter("descricao");
		String nomeGrupo = request.getParameter("nome");
				
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				
		String fundador = usuario.getNome();
	    Grupo grupo = new Grupo();
		 
		 try {
				
				grupo.setNomeDoGrupo(nomeGrupo);
				grupo.setNomeFundador(fundador);
				grupo.setDescricao(descricao);
				grupo.setUsuario(usuario);
				
				grupo.setId( Fachada.getInstance().cadatrarGrupo(grupo) );
				Fachada.getInstance().participarDoGrupo(grupo, usuario);
				
				response.sendRedirect("pages/menuGrupo.jsp");
						
			} catch (ParseException e) {
				
			} catch (ClassNotFoundException e) {
				
			} catch (SQLException e) {
				response.sendRedirect("pages/grupoErro.jsp");
			} catch (CampoTextoVazioException e) {
				
			} catch (EmailJaExisteException e) {
				
			} 
	}
}
