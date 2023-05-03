package com.notes_api.repositories;

import com.notes_api.enntities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByUserId(int userId);
}
