package App;

import java.sql.SQLException;
import java.text.ParseException;

import excecoes.CampoTextoVazioException;
import excecoes.EmailJaExisteException;
import fachada.Fachada;
import beans.Grupo;
import beans.Topico;
import beans.Usuario;

public class Teste {

	public static void main(String[] args) {
		
		Usuario user = new Usuario("4", "k@k");
		user.setNome("Felipe");
		
		Grupo grupo = new Grupo(user);
		grupo.setId(15);
		grupo.setNomeDoGrupo("PDS");
		grupo.setNomeFundador( user.getNome() );
		grupo.setDescricao("Testes de Aceitação");
		
		Topico topico = new Topico(grupo, user);
		topico.setNomeTopico("Teste");
		
		try {

//			Fachada.getInstance().cadatrarGrupo(grupo);
//			Fachada.getInstance().participarDoGrupo(grupo, user);
			Fachada.getInstance().cadastrarTopico(topico);
			
		} catch (ClassNotFoundException | SQLException | CampoTextoVazioException  e) {
			e.printStackTrace();
		}
		
	}

}
