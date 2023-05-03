package com.notes_api.controllers;

import com.notes_api.dtos.NoteDto;
import com.notes_api.enntities.Note;
import com.notes_api.services.note.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{userId}/notes")
    public ResponseEntity<List<Note>> getAllNotesByUserId(@PathVariable int userId){
        return new ResponseEntity<>(noteService.getAllNotesByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable int noteId){
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/note")
    public ResponseEntity<Note> createNote(@PathVariable int userId, @RequestBody NoteDto noteDto){
        return new ResponseEntity<>(noteService.createNote(userId, noteDto), HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable int noteId, @RequestBody NoteDto noteDto){
        return new ResponseEntity<>(noteService.updateNote(noteId, noteDto), HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Note> deleteNote(@PathVariable int noteId){
        return new ResponseEntity<>(noteService.deleteNote(noteId), HttpStatus.OK);
    }
}
