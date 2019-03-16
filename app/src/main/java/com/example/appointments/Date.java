// Created by persons listed below (name, UCI ID) following the rules of pair programming. All work in the project was done collaboratively.
// Timothy Lin 29663818
// Elizabeth Budi 83979146

package com.example.appointments;

import java.io.Serializable;

public class Date implements Serializable {

	private int day, month, year;

	public Date(){

	}

	public Date(int amonth, int aday, int ayear) {
		day = aday;
		year = ayear;
		month = amonth;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString() {
		return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + String.format("%04d", year);
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof Date))
			return false;
		
		Date d = (Date) o;
		
		if(d.day == this.day && d.month == this.month && d.year == this.year) {
			return true;
		}else {
			return false;
		}
	}

	public int compareTo(Object o){
		Date d = (Date)o;
		if (this.equals(d)){
			return 0;
		}
		if (year != d.getYear()){
			return d.getYear() - year;
		}else if (month != d.getMonth()){
			return d.getMonth() - month;
		}else {
			return d.getDay() - day;
		}

	}
}
