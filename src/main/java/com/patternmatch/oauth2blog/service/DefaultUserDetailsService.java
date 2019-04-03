package com.patternmatch.oauth2blog.service;

import com.patternmatch.oauth2blog.entity.Usuario;
import com.patternmatch.oauth2blog.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class DefaultUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DefaultUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<Usuario> userEntity = usuarioRepository.findById(username);

        if (userEntity.isPresent()) {
            final Usuario usuario = userEntity.get();

            return new User(usuario.getUserEmail(),
                    passwordNoEncoding(usuario),
                    Collections.singletonList(new SimpleGrantedAuthority(usuario.getUserRole())));
        }

        return null;
    }

    private String passwordNoEncoding(Usuario usuario) {
        // you can use one of bcrypt/noop/pbkdf2/scrypt/sha256
        // more: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
        return "{noop}" + usuario.getUserPass();
    }
}
