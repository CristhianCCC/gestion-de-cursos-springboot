package com.ccc.gestion_cursos.repository;

import com.ccc.gestion_cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
}
