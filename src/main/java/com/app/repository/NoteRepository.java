package com.app.repository;

import com.app.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUser_Username(String username);

    @Transactional
    void deleteAllByUserUsername(String username);
}
