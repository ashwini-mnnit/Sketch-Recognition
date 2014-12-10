package com.parser.sousa;
import java.util.UUID;

public class SousaArg {
	private UUID id;
	private String type;

	public SousaArg() {
		super();
	}

	@Override
	public String toString() {
		return "SousaArg [id=" + id + ", type=" + type + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
