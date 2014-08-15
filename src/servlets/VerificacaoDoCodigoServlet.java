package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerificacaoDoCodigoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		String codigoFormulario = request.getParameter("codigo");
		
		if( codigoFormulario.equals("") ){
			response.sendRedirect("pages/paginasErro/erroRecuperacaoDeSenhaComCodigoVazio.html");
		}
		
		int codigoSessao = (int)request.getSession().getAttribute("codigo");
		int codigoUsuario = Integer.parseInt( codigoFormulario );
		
		if( codigoSessao == codigoUsuario ){
			response.sendRedirect("pages/obterNovaSenha.html");
		} else {
			response.sendRedirect("pages/paginasErro/erroRecuperacaoDeSenhaComCodigo.html");
		}
		
	}

}
