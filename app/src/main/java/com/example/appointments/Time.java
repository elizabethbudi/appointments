// Created by persons listed below (name, UCI ID) following the rules of pair programming. All work in the project was done collaboratively.
// Timothy Lin 29663818
// Elizabeth Budi 83979146

package com.example.appointments;

import java.io.Serializable;

public class Time implements Serializable {
	
	private int hour, minute;

	public Time(){

	}

	public Time(int ahour, int aminute) {
		hour = ahour;
		minute = aminute;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public boolean equals(Time t) {
		return ( (this == t) || (hour == t.hour && minute == t.minute) );
	}
	
	public int compareTo(Object o) {
		Time t1 = (Time) o;
		if(this.equals(t1))
			return 0;
		
		if(hour != t1.hour) {
			return t1.hour - hour;
		}
		
		return t1.minute - minute;
	}
	
	public String toString() {
		return String.format("%02d", hour) + ":" + String.format("%02d", minute);
	}
	
}
