/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shirsing
 */
@XmlRootElement
public class Speech {
    private ArrayList<Arg> arg;
    private UUID author;
    private UUID id;
    private long startTime;
    private long endTime;
    private String type;
    private UUID source;
    private long score;
    private String word;
    private int wordForm;

    public Speech() {
    	arg = new ArrayList<Arg>();
    	author = UUID.randomUUID();
    	id = UUID.randomUUID();
    	source = UUID.randomUUID();
    	startTime = 0;
    	endTime = 0;
    	score = 0;
    	word = "around";
    	type = "word";
    	wordForm = 0;
    }
    
    public Speech(ArrayList<Arg> arg, UUID authorId, UUID speechId, long startTime,
			long endTime, String speechType, UUID sourceId, long score,
			String word, int wordForm) {
		this.arg = arg;
		this.author = authorId;
		this.id = speechId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = speechType;
		this.source = sourceId;
		this.score = score;
		this.word = word;
		this.wordForm = wordForm;
	}

	public ArrayList<Arg> getArg() {
		return arg;
	}
@XmlElement
	public void setArg(ArrayList<Arg> arg) {
		this.arg = arg;
	}

	public UUID getAuthor() {
		return author;
	}

@XmlAttribute
	public void setAuthor(UUID author) {
		this.author = author;
	}

	public UUID getId() {
		return id;
	}
	@XmlAttribute
	public void setId(UUID id) {
		this.id = id;
	}

	public long getStartTime() {
		return startTime;
	}
	@XmlAttribute
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}
	@XmlAttribute
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public UUID getSource() {
		return source;
	}
	@XmlAttribute
	public void setSource(UUID source) {
		this.source = source;
	}

	public long getScore() {
		return score;
	}
	@XmlAttribute
	public void setScore(long score) {
		this.score = score;
	}

	public String getWord() {
		return word;
	}
	@XmlAttribute
	public void setWord(String word) {
		this.word = word;
	}

	public int getWordForm() {
		return wordForm;
	}
	@XmlAttribute
	public void setWordForm(int wordForm) {
		this.wordForm = wordForm;
	}

	

}
