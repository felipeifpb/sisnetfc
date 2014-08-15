package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatadorDeDatas {
	
	private static SimpleDateFormat sdf;
	
	/**
	 * Recebe uma data em formato string yyyy-mm-dd e converte um Date sql 
	 * @param data
	 * @return Date
	 * @throws ParseException
	 */
	public static Date formatarDataDeEntrada(String data) throws ParseException{
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataN = sdf.parse( convert(data) );
		return new Date( dataN.getTime() );
	}
	
	public static String formatarDataDeSaida(Date data){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format( data );
	}
	
	public static String formatarDataDeSaidaPadraoAmericano(Date data){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}
	
	private static String convert(String data){
		String[] token = data.split("-");
		return token[2] + "/" + token[1] + "/" + token[0];
	}
	
	public static boolean isMenorDeIdade(String data){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new java.util.Date() );
		
		int anoAtual = calendar.get(Calendar.YEAR);
		int mesAtual = calendar.get(Calendar.MONTH) + 1;
		int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		String[] token = data.split("-");
		
		int ano = Integer.parseInt( token[0] );
		int mes = Integer.parseInt( token[1] );
		int dia = Integer.parseInt( token[2] );
		
		int diferenca = anoAtual - ano; 
		
		if( diferenca > 12 ){
			return false;
		} else if(diferenca < 12){
			return true;
		}
		
		
		if( mes < mesAtual ){
			return false;
		} else if( mesAtual == mes){
			if( dia <= diaAtual ){
				return false;
			}
		} 
		
		return true;
		
	}
	
}
