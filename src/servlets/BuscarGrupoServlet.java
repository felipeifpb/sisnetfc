package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Grupo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.CampoTextoVazioException;
import fachada.Fachada;

public class BuscarGrupoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		nome = request.getParameter("nomeDoGrupo");
		
		if( nome != null && !nome.equals("") && !nome.equals(" ") ){
			try {
				
				List<Grupo> lista = Fachada.getInstance().buscarTodosOsGrupos( nome );
				
				request.getSession().setAttribute("listaDeGruposEncontrados", lista);
				response.sendRedirect("pages/menuPesquisarGrupo.jsp");
				
			} catch (ClassNotFoundException | SQLException	| CampoTextoVazioException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("pages/menuPesquisarGrupo.jsp");
		}
		
	}
	
	
	public static void main(String[] args) {
		
		
		for(int i = 0; i < 9; i++){
			if(i % 3 == 0){
				System.out.println("--------------------");
				for( int j = i; j < (i+3); j++){
					System.out.println( j % 3);
				}
			}
		}
	}
	
}
