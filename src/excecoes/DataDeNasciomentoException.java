package excecoes;

import java.lang.Exception;

public class DataDeNasciomentoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataDeNasciomentoException(){
		super("Voc� tem menos de 18 ano.");
	}
	
}
