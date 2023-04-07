package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
	
	Optional<Note> findById(Long id);

}
