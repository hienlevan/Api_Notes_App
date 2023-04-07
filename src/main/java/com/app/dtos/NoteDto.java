package com.app.dtos;

import com.app.entities.User;

public class NoteDto {

	private Long noteId;
	private String noteTitle;
	private String noteContent;
	private String username;
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUser(String username) {
		this.username = username;
	}
	public NoteDto(Long noteId, String noteTitle, String noteContent, String username) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.username = username;
	}
	public NoteDto() {
		super();
	}
	
	
}
