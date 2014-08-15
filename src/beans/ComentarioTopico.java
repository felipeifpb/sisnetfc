package beans;

public class ComentarioTopico extends Comentario {

	private Topico topico;
	
	public ComentarioTopico(){
		super();
		this.topico = new Topico();
	}

	public ComentarioTopico(Usuario user){
		super(user);
		this.topico = new Topico();
	}
	
	public Topico getTopico() {
		return topico;
	}

	public void seTopico(Topico topico) {
		this.topico = topico;
	}
	
}
