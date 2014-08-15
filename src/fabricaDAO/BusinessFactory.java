package fabricaDAO;

import business.FotoBusiness;
import business.GrupoBusiness;
import business.TopicoBusiness;
import business.UsuarioBusiness;
import interfaces.FotoBusinessInterface;
import interfaces.GrupoBusinessInterface;
import interfaces.TopicoBusinessInterface;
import interfaces.UsuarioBusinessInterface;

public class BusinessFactory {

	public static UsuarioBusinessInterface createUsuerBusiness(){
		return new UsuarioBusiness();
	}

	public static FotoBusinessInterface createFotoBusiness(){
		return new FotoBusiness();
	}
	
	public static GrupoBusinessInterface createGrupoBusiness(){
		return new GrupoBusiness();
	}
	
	public static TopicoBusinessInterface createTopicoBusiness(){
		return new TopicoBusiness();
	}
	
}
