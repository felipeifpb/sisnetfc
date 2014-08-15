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
import util.FormatadorDeDatas;
import beans.Usuario;

public class CadastrarUsuarioServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException {
		doPost(request, response);
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{
		
		request.setCharacterEncoding("UTF-8");
		
		String nome = request.getParameter("nome");
		String apelido = request.getParameter("apelido");
		String cidade = request.getParameter("cidade");
		String data = request.getParameter("dataNascimento");
	//	String email = request.getParameter("email");
	//	String senha = request.getParameter("senha");
		String emailConfirmado = request.getParameter("confirmeEmail");
		String senhaConfimada = request.getParameter("confirmeSenha");
		
		/*
		if( !email.equals(emailConfirmado) ){
			response.sendRedirect("");
		}
		
		if( !senha.equals(senhaConfimada) ){
			response.sendRedirect("");
		}
		
		if( FormatadorDeDatas.isMenorDeIdade(data) ){
			response.sendRedirect("");
		}
		*/
		
		Usuario usuario = new Usuario(senhaConfimada, emailConfirmado);
		
		try {
			
			usuario.setNome(nome);
			usuario.setApelido(apelido);
			usuario.setCidade(cidade);
			usuario.setDataDeNascimento( FormatadorDeDatas.formatarDataDeEntrada( data ) );
			
			Fachada.getInstance().cadatrarUsuario(usuario);
			
			request.getSession().setAttribute( "usuario", usuario );
			response.sendRedirect("pages/selecionarFotoPerfilCadastro.html");
			
		} catch (ParseException e) {
			response.sendRedirect("pages/erroParse.html");
		} catch (ClassNotFoundException e) {
			response.sendRedirect("pages/erroClasse.html");
		} catch (SQLException e) {
			response.sendRedirect("pages/erroSql.html");
		} catch (CampoTextoVazioException e) {
			response.sendRedirect("pages/paginasErro/erroCampoEmBranco.html");
		} catch (EmailJaExisteException e) {
			response.sendRedirect("pages/paginasErro/erroEmailJaCadastrado.html");
		}

	}
	
}
