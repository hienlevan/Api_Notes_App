package com.app.controller;

import com.app.exception.UserNotFoundException;
import com.app.model.Note;
import com.app.repository.NoteRepository;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{username}/notes")
    public ResponseEntity<List<Note>> getAllNotesByUserId(@PathVariable(value = "username") String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException(username);
        }

        List<Note> comments = noteRepository.findByUser_Username(username);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PostMapping("/users/{username}/notes")
    public ResponseEntity<Note> createNote(@PathVariable(value = "username") String username,
                                                 @RequestBody Note noteRequest) {
        Note note = userRepository.findByUsername(username).map(user -> {
            noteRequest.setUser(user);
            return noteRepository.save(noteRequest);
        }).orElseThrow(() -> new UserNotFoundException(username));

        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{username}/notes")
    public ResponseEntity<List<Note>> deleteAllNotesOfUser(@PathVariable(value = "username") String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException(username);
        }
        noteRepository.deleteAllByUserUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
