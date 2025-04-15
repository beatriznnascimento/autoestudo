package com.npi.autoestudo.repository;

import com.npi.autoestudo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
