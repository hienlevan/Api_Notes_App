package com.notes_api.services.note;

import com.notes_api.dtos.NoteDto;
import com.notes_api.enntities.Note;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NoteService {
    List<Note> getAllNotesByUserId(int userId);

    Note getNoteById(int noteId);

    Note createNote(int userId, NoteDto  noteDto);

    Note updateNote(int noteId, NoteDto noteDto);

    Note deleteNote(int noteId);

}
