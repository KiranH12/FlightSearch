package com.cts.flightsearch.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cts.flightsearch.dao.FlightDaoImplementation;
import com.cts.flightsearch.model.Flight;

import com.cts.flightsearch.service.FlightServiceImplementation;

@Controller
@RequestMapping(value="/flights")
public class FlightController {
	@Autowired
	private FlightServiceImplementation flightServiceImplementation;
	
	@Autowired
	private FlightDaoImplementation flightDaoImplementation;
	
	@RequestMapping(value="/home")
	public String homePage() {
		return ("index");
	}
	
	@PostMapping(value = "/saveFlights")
	public void saveAllFlightsFromFile() throws IOException, ParseException{
		flightDaoImplementation.saveFlightDetailsFromFile();
	}
	
	@RequestMapping(value = "/allFlights")
	public String getAllFights(Model model){
		List<Flight> flights=flightServiceImplementation.getAllFlightDetails();
		model.addAttribute("flights",flights);
		return "flights";
		
	}
	
	@RequestMapping(value = "/getFlight",method=RequestMethod.GET)
	public String getFlightDetailsWithPreference(String origin,String destination,int duration,String noOfStops,int farelower,int fareupper,String airline,Model model)
	{
		System.out.println(noOfStops);
		
		List<Flight> flights=flightServiceImplementation.getFlightDetailsWithPreference(origin, destination,duration, noOfStops,farelower,fareupper,airline);
		if(flights.size()==0)
		{
			return ("noflightfound");
		}
		else
		{
			model.addAttribute("flights",flights);
			return "flights";
		}
	}
	
	
	@RequestMapping(value = "/getFlightWithFare",method=RequestMethod.GET)
	public String getFlightDetailsWithFare(Integer fareLower, Integer fareUpper, Model model)
	{
		List<Flight> flights= new ArrayList<Flight>();
		flights= flightServiceImplementation.getFlightWithFare(fareLower, fareUpper);
		if(flights.size()==0)
		{
			return ("noflightfound");
		}
		else
		{
			model.addAttribute("flights",flights);
			return "flights";
		}
	}
}
