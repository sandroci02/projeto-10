package br.com.z2.contas.util;

import com.google.gson.Gson;

public class GsonUtil {

	public static String toJson(Object object) {
		Gson g = new Gson();
		return g.toJson(object);
	}
	
	public static String toJsonLimit(Object object) {
		Gson g = new Gson();
		String retorno = g.toJson(object);
		if (retorno.length() >= 4000) {
			return retorno.substring(0, 4000);
		}
		return retorno;		
	}
	
}
