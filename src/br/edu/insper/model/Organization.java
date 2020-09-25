package br.edu.insper.model;

import java.util.Calendar;

public class Organization {
	private Integer id;
	private Integer id_user;
	private Calendar deadline;
	private String toDo;
	private String doing;
	private String done;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getToDo() {
		return toDo;
	}
	
	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	
	public String getDoing() {
		return doing;
	}
	
	public void setDoing(String doing) {
		this.doing = doing;
	}
	
	public String getDone() {
		return done;
	}
	
	public void setDone(String done) {
		this.done = done;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Calendar getDeadline() {
		return deadline;
	}

	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}
}
