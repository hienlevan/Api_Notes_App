package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.NoteDto;
import com.app.dtos.UserDto;
import com.app.entities.Note;
import com.app.entities.User;
import com.app.repositories.NoteRepository;
import com.app.repositories.UserRepository;

@RestController
@RequestMapping(value = "/rest/v1/note")
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
    private UserRepository userRepository;

	
	@PostMapping("/create")
	public String createNote(@RequestBody NoteDto noteDto) {
		
		 User user = userRepository.findByUsername(noteDto.getUsername())
		            .orElseThrow(() -> new RuntimeException("User not found"));
		Note note = new Note(noteDto.getNoteId(), noteDto.getNoteTitle(), noteDto.getNoteContent(), user);
		noteRepository.save(note);
		return note.getNoteId().toString();
	}
}
