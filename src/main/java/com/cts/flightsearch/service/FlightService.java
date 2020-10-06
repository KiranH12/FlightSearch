package com.cts.flightsearch.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.flightsearch.model.Flight;

@Service
public interface FlightService {
	public void saveFlightDetailsFromFile() throws IOException, ParseException;;
	public List<Flight> getFlightDetailsWithPreference(String origin,String destination,int duration,String noOfStops,int fareLower,int fareUpper,String airline);
	public List<Flight> getAllFlightDetails();
	public List<Flight> getFlightWithFare(long fareLower,long fareUpper);
}
