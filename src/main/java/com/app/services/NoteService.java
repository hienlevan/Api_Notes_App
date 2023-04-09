package com.app.services;

import com.app.models.Note;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface NoteService {
    public Note saveNote(Note note);

    public Note getNoteById(int noteId);

    public List<Note> getAllNote();
}
