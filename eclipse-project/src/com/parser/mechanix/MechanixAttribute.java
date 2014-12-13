package com.parser.mechanix;

public class MechanixAttribute {
	
	private String key;
	private String value;
	private String simpl_id;
	
	public MechanixAttribute(String key,String value,String simpl_id){
		this.key = key;
		this.value = value;
		this.simpl_id = simpl_id;
	}
	
	public MechanixAttribute(String key,String value){
		this.key = key;
		this.value = value;
		this.simpl_id = "";
	}
	
	public MechanixAttribute() {
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getSimpl_ID() {
		return simpl_id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setSimpl_id(String simpl_id) {
		this.simpl_id = simpl_id;
	}
	
	
}