package com.cts.flightsearch.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "flight")
public class Flight {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int Id;
	@Column(name = "origin")
	private String origin;
	@Column(name = "origincode")
	private String originCode;
	@Column(name = "destinantion")
	private String destination;
	@Column(name = "destinationcode")
	private String destinationCode;
	@Column(name = "flightnumber")
	private String flightNumber;
	@Column(name = "departuretime")
	private Time departureTime;
	@Column(name = "arrivaltime")
	private Time arrivalTime;
	@Column(name = "flighttype")
	private String flightType;
	@Column(name = "stop")
	private String stop;
	@Column(name = "operationdays")
	private String operationDays;
	@Column(name = "duration")
	private String duration;
	@Column(name = "fare")
	private long fare;
	@Column (name="airline")
	private String airline;

	public Flight() {
		super();
	}

	public Flight(String origin, String destination, String flightNumber, Time departureTime, Time arrivalTime,
			String flightType, String stop, String operationDays, String duration, long fare, String airline) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightType = flightType;
		this.stop = stop;
		this.operationDays = operationDays;
		this.duration = duration;
		this.fare = fare;
		this.airline= airline;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightTpe(String flightType) {
		this.flightType = flightType;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getOperationDays() {
		return operationDays;
	}

	public void setOperationDays(String operationDays) {
		this.operationDays = operationDays;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}
	

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}


	@Override
	public String toString() {
		return "Flight [Id=" + Id + ", origin=" + origin + ", originCode=" + originCode + ", destination=" + destination
				+ ", destinationCode=" + destinationCode + ", flightNumber=" + flightNumber + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", flightType=" + flightType + ", stop=" + stop
				+ ", operationDays=" + operationDays + ", duration=" + duration + ", fare=" + fare + "]";
	}
	
	
	
}
