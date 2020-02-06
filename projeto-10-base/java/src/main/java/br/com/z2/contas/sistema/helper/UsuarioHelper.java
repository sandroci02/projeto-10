package br.com.z2.contas.sistema.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCrypt;

import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.model.UsuarioDto;

public class UsuarioHelper {

	private static final String SENHA_DEFAULT = "123456";

	private static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return (hashed_password);
	}

	public static void encriptaSenha(Usuario usuario) {
		if (usuario.getSenha() == null) {
			usuario.setSenha(SENHA_DEFAULT);
			usuario.setSenhaPadrao(true);
		}
		usuario.setSenha(hashPassword(usuario.getSenha()));
	}

	public static void main(String[] args) {
		System.out.println(UsuarioHelper.hashPassword(SENHA_DEFAULT));
	}

	public static UsuarioDto apresentacao(Usuario usuario) {
		return new UsuarioDto(usuario);
	}

	public static List<UsuarioDto> apresentacao(List<Usuario> usuario) {
		return usuario.stream().map(user -> apresentacao(user)).collect(Collectors.toList());
	}

	public static Usuario popula(Usuario usuario, Usuario atual) {
		usuario.setLogin(atual.getLogin());
		usuario.setNome(atual.getNome());
		usuario.setPerfil(atual.getPerfil());
		return usuario;
	}

}
