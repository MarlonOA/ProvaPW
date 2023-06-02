package br.com.sobre.sobremesa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sobre.sobremesa.model.Usuario;
import br.com.sobre.sobremesa.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> user = repository.findByUsername(username);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public List<Usuario> listAll(){
        return repository.findAll();
    }
}
