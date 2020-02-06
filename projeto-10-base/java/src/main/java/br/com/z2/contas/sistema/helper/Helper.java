package br.com.z2.contas.sistema.helper;

public class Helper {

	public static String getMessage(String mensagem, String parametro) {
		return String.format(mensagem,parametro);
	}
}
