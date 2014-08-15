package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import fachada.Fachada;
import util.CaminhoDasImagens;
import beans.Foto;
import beans.Usuario;

public class CapturarFotoAlbumServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File file;
	private Foto foto;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		try{
			
			Usuario user = (Usuario)request.getSession().getAttribute("usuario");
			
			foto = new Foto();
			foto.setUsuario(user);
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload( factory );
			List<FileItem> lista = upload.parseRequest(request);
			
			for( FileItem item : lista ){
							
				if( !item.isFormField() ){
					
					String caminhoBanco = "";
					String caminho = "";
					
					if( !item.getName().equals("") ){
						
						String caminhoRelativo = CaminhoDasImagens.URL_IMAGENS_USUARIO + user.getId() + "/" + Calendar.getInstance().getTime().getTime() + item.getName();
						caminho = CaminhoDasImagens.URL_IMAGENS_USUARIO_ABSOLUTE + caminhoRelativo;
						caminhoBanco = "../" + caminhoRelativo;
						
						file = new File( caminho );
						item.write( file );
											
						foto.setCaminhoFoto(caminhoBanco);
						
					}
					
				}
				
			}
			
			Fachada.getInstance().salvarFoto(foto);
			response.sendRedirect("pages/menuFoto.jsp");
			
		}catch(Exception e){
						
		}
			
	}
	
}
