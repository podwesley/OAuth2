package com.patternmatch.oauth2blog.repository;

import com.patternmatch.oauth2blog.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
