package servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import fachada.Fachada;

public class PesquisarAmigosPorNomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException{
		
		String nome = request.getParameter("campoBuscar");
		Usuario user = (Usuario)request.getSession().getAttribute("usuario");
		
		if( nome != null && !nome.equals("") && !nome.equals(" ")){
			try {
				
				List<Usuario> listaAmigosBusca = Fachada.getInstance().buscarUsuarioPorNome(nome);
				
				if( listaAmigosBusca != null ){
					for(Usuario u : listaAmigosBusca){
						if(u.getId() == user.getId()){
							listaAmigosBusca.remove(u);
						}
					}
				}
				
				request.getSession().setAttribute( "listaDeAmigosConsultados", listaAmigosBusca );
				response.sendRedirect("pages/paginaBuscarAmigos.jsp");
								
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}else{
			response.sendRedirect("pages/paginaBuscarAmigos.jsp");
		}
		
	}
	
	
}
