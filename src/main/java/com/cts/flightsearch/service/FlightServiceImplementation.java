package com.cts.flightsearch.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flightsearch.dao.FlightDao;
import com.cts.flightsearch.model.Flight;

@Service
public class FlightServiceImplementation implements FlightService {
	@Autowired
	private FlightDao flightDao;

	@Override
	public void saveFlightDetailsFromFile() throws IOException, ParseException {
		flightDao.saveFlightDetailsFromFile();
		
	}

	@Override
	public List<Flight> getFlightDetailsWithPreference(String origin, String destination, int duration, String noOfStops,int fareLower,int fareUpper,String airline) {
		return flightDao.getFlightDetailsWithPreference(origin, destination,duration, noOfStops, fareLower, fareUpper,airline);
	}

	@Override
	public List<Flight> getAllFlightDetails() {
		return flightDao.getAllFlightDetails();
	}


	@Override
	public List<Flight> getFlightWithFare(long fareLower, long fareUpper) {
		return flightDao.getFlightWithFare(fareLower, fareUpper);
	}
}
