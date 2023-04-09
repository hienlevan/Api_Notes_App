package com.app.services;

import com.app.models.Note;
import com.app.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;
    @Override
    public Note saveNote(Note note) {
        noteRepository.save(note);
        return note;
    }

    @Override
    public Note getNoteById(int noteId) {
        return noteRepository.getById(noteId);
    }

    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAll();
    }
}
