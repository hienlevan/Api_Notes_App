package com.app.services.note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dtos.NoteDto;
import com.app.entities.Note;
import com.app.entities.User;
import com.app.repositories.NoteRepository;
import com.app.repositories.UserRepository;

public class NoteServiceImpl implements NoteService{

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public String createNote(@RequestBody NoteDto noteDto) {
		
		 User user = userRepository.findByUsername(noteDto.getUsername())
		            .orElseThrow(() -> new RuntimeException("User not found"));
		Note note = new Note(noteDto.getNoteId(), noteDto.getNoteTitle(), noteDto.getNoteContent(), user);
		noteRepository.save(note);
		return note.getNoteId().toString();
	}
}
