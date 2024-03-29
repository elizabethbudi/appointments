// Created by persons listed below (name, UCI ID) following the rules of pair programming. All work in the project was done collaboratively.
// Timothy Lin 29663818
// Elizabeth Budi 83979146

package com.example.appointments;

import java.io.Serializable;

public class Appointment implements Serializable {
	
	private String description;
	private Date date;
	private Time startTime;
	private Time endTime;
	private String uid;
	private boolean fav;

	public Appointment(){
	}

	public Appointment(String aDescription, Date aDate, Time start, Time end, String aUid) {
		description = aDescription;
		date = aDate;
		startTime = start;
		endTime = end;
		uid = aUid;
		fav = false;
	}

	public boolean getFav(){
		return fav;
	}

	public void setFav(boolean abool){
		fav = abool;
	}

	public String getDescription() {
		return description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Time getStart() {
		return startTime;
	}

	public void setStart(Time start){
		startTime = start;
	}
	
	public Time getEnd() {
		return endTime;
	}

	public void setEnd(Time end){
		endTime = end;
	}

	public String getUid(){return uid;}
	
	public boolean overlaps(Appointment appt) {
		if(! (this.date.equals(appt.date) )) {
			return false;
		}
		
		if(this.startTime.compareTo(appt.startTime) <= 0 && this.endTime.compareTo(appt.startTime) >= 0 
				|| this.startTime.compareTo(appt.endTime) <= 0 && this.endTime.compareTo(appt.endTime) >= 0
				|| this.startTime.compareTo(appt.startTime) >= 0 && this.endTime.compareTo(appt.endTime) <= 0) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		return description + " " + date + " " + startTime + " " + endTime;
	}

	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		if (!(o instanceof Appointment)){
			return false;
		}

		Appointment a = (Appointment)o;
		return (this.getDescription().equals(a.getDescription()) && this.getDate().equals(a.getDate())
				&& this.getStart().equals(a.getStart()) && this.getEnd().equals(a.getEnd())
				&& this.getUid().equals(a.getUid()));

	}

	public int compareTo(Object o){
		Appointment a = (Appointment)o;

		if(this.getFav() && !a.getFav())
			return 1;
		else if(a.getFav() && !this.getFav())
			return -1;

		if (!(this.getDate().equals(a.getDate()))){
			return this.getDate().compareTo(a.getDate());
		}
		return this.getStart().compareTo(a.getStart());
	}
	
}
