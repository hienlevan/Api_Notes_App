package com.notes_api.services.note;

import com.notes_api.dtos.NoteDto;
import com.notes_api.enntities.Note;
import com.notes_api.enntities.User;
import com.notes_api.exceptions.InvalidException;
import com.notes_api.exceptions.NotFoundException;
import com.notes_api.repositories.NoteRepository;
import com.notes_api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService{

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public NoteServiceImpl(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotesByUserId(int userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()->
                        new NotFoundException(String.format("User có id %s không tồn tại", userId)));
        List<Note> notes = noteRepository.findAllByUserId(userId);
        return notes;
    }

    @Override
    public Note getNoteById(int noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(()->
                        new NotFoundException(String.format("Note có id %s không tồn tại", noteId)));
        return note;
    }

    @Override
    public Note createNote(int userId, NoteDto noteDto) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()->
                        new NotFoundException(String.format("User có id %s không tồn tại", userId)));
        Note note = new Note();
        if(ObjectUtils.isEmpty(noteDto.getTitle())){
            throw new InvalidException("Vui lòng điền tiêu đề của ghi chú!");
        }
        if(ObjectUtils.isEmpty(noteDto.getContent())){
            throw new InvalidException("Vui lòng điền nội dung của ghi chú!");
        }
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setUser(user);
        noteRepository.save(note);
        return note;
    }

    @Override
    public Note updateNote(int noteId, NoteDto noteDto) {
        Note note = getNoteById(noteId);
        if(ObjectUtils.isEmpty(noteDto.getTitle())){
            throw new InvalidException("Vui lòng điền tiêu đề của ghi chú!");
        }
        if(ObjectUtils.isEmpty(noteDto.getContent())){
            throw new InvalidException("Vui lòng điền nội dung của ghi chú!");
        }
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        noteRepository.save(note);
        return note;
    }

    @Override
    public Note deleteNote(int noteId) {
        Note note = getNoteById(noteId);
        noteRepository.delete(note);
        return note;
    }


}
