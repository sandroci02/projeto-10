
package br.com.z2.contas.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.z2.contas.model.Usuario;
import br.com.z2.contas.repository.UsuarioRepository;
import br.com.z2.contas.sistema.helper.UsuarioHelper;

@Service
public class UserDetaiJWService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario admin = repository.findOneByLogin("admin");
		if (admin == null) { 
			admin =  new Usuario();
			admin.setPerfil(1l);
			admin.setLogin("admin");
			admin.setNome("Admin");
			
			UsuarioHelper.encriptaSenha(admin);
			
			repository.save(admin);
		}
		
		 Usuario user = repository.findOneByLogin(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Usuário '" + username + "' inválido!");
	        }
		
        return org.springframework.security.core.userdetails.User
                .withUsername(username)               
                .password(user.getSenha())
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
	}
	
	
}


