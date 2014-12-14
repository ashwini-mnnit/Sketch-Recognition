/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchMl;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author shirsing
 */
public class Speech {
    private ArrayList<Arg> arg;
    private UUID authorId;
    private UUID speechId;
    private long startTime;
    private long endTime;
    private String speechType;
    private UUID sourceId;
    private long score;
    private String word;
    private int wordForm;

    public Speech() {
    	arg = new ArrayList<Arg>();
    }
    
    public Speech(ArrayList<Arg> arg, UUID authorId, UUID speechId, long startTime,
			long endTime, String speechType, UUID sourceId, long score,
			String word, int wordForm) {
		this.arg = arg;
		this.authorId = authorId;
		this.speechId = speechId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.speechType = speechType;
		this.sourceId = sourceId;
		this.score = score;
		this.word = word;
		this.wordForm = wordForm;
	}

	public ArrayList<Arg> getArg() {
        return arg;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getScore() {
        return score;
    }

    public UUID getSourceId() {
        return sourceId;
    }

    public UUID getSpeechId() {
        return speechId;
    }

    public String getSpeechType() {
        return speechType;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getWord() {
        return word;
    }

    public int getWordForm() {
        return wordForm;
    }

    public void setArg(ArrayList<Arg> arg) {
        this.arg = arg;
    }
    
    public void setArg(String argType, UUID argId) {
		
		Arg a= new Arg(argType, argId);
		this.arg.add(a);
	}

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setSourceId(UUID sourceId) {
        this.sourceId = sourceId;
    }

    public void setSpeechId(UUID speechId) {
        this.speechId = speechId;
    }

    public void setSpeechType(String speechType) {
        this.speechType = speechType;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWordForm(int wordForm) {
        this.wordForm = wordForm;
    }

}
