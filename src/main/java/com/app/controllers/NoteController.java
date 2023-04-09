package com.app.controllers;

import com.app.models.Note;
import com.app.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public Note createNote(@RequestBody Note note){
        noteService.saveNote(note);
        return note;
    }
}
