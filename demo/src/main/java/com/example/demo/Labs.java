package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;


@Entity
class Labs {
	private String name, address;
	private Double lat, lon;
	// find way to implement this
	private Timestamp[] queue = new Timestamp[100];
	private Integer queuesize;
	//
	private Integer eta;
  private Integer BatchSize;
	private @Id @GeneratedValue Long Id;
	

  
  Labs(){
		name = "NULL";
		queuesize = 0;
	}

	Labs(String name, String address, Double lat, Double lon, int BatchSize){
		this.name = name;
		this.address = address;
		this.lat = lat;
    this.lon = lon;
		this.BatchSize = BatchSize;
		this.queuesize = 0;
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

	public Integer getBatchSize() {
		return BatchSize;
	}

	public Integer getETA() {
		return eta;
	} 

	public Integer getQueueSize() {
		return queuesize;
	}

	public Timestamp[] getTimestamps(){
		return queue;
	}

	public void setQueue(Timestamp[] queue){
		this.queue = queue;
	}

	public void setETA(Integer eta){
		this.eta = eta;
	}

	public void setQueuesize(Integer queuesize) {
		this.queuesize = queuesize;
	}
	
	@Override
	public String toString() {
		return "Lab [name=" + name + ", address = " + address + "]";
	}
}
