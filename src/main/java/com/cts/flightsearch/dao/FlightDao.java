package com.cts.flightsearch.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cts.flightsearch.model.Flight;


@Component
public interface FlightDao {
	public void saveFlightDetailsFromFile() throws IOException, ParseException;
	public List<Flight> getAllFlightDetails();
	public List<Flight> getFlightDetailsWithPreference(String origin,String destination,int duration,String noOfStops,int fareLower,int fareUpper,String airline);
	public List<Flight> getFlightWithFare(long fareLower,long fareUpper);
	
}
