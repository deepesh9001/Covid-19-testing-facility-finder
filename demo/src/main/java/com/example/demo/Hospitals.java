package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Hospitals{
	
	private String name, address ;
	private Double lat, lon;
	private @Id @GeneratedValue Long Id;

	Hospitals(){}

	Hospitals(String name, String address, Double lat, Double lon){
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
	}
	
	public String getName() {
		return name;
	}
		
	public String getAddress() {
		return address;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	// fix this
	@Override
	public String toString() {
		return " " + lat + " " + lon + " ";
		// return "Hospitals [name=" + name + ", address = " + address + ", lat = " + lat + ", lon = " + lon + "]";
	}
}