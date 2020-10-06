package com.cts.flightsearch.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.flightsearch.model.Flight;
import com.cts.flightsearch.repository.FlightRepository;

@Component
public class FlightDaoImplementation implements FlightDao {
	@Autowired
	private FlightRepository flightRepository;
	
	
	
	
	@Override
	public void saveFlightDetailsFromFile() throws IOException, ParseException{
		//List<Flight> flightDetails= new ArrayList<Flight>();
		String[] airlineString={"IndiGo","Air India","Air Asia","Emirates","Etihad"};
		List<String> airlineList=new ArrayList<String>(Arrays.asList(airlineString));
	    Random rand = new Random();
		File file= new File("C:\\Users\\Pc\\Documents\\workspace-spring-tool-suite-4-4.7.1.RELEASE\\flightsearch\\src\\main\\resources\\flightDetails.txt");
		BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
		String line= bufferedReader.readLine();
		while(line!=null) {
			String[] str= line.split("\t");
			
			String[] flighOriginWithCode=str[0].split(" ");
			String origin=flighOriginWithCode[0];
			String originCode=flighOriginWithCode[1];
			
			String[] flightDestinationWithCode=str[1].split(" ");
			String destination=flightDestinationWithCode[0];
			String destinationCode=flightDestinationWithCode[1];
			
			String flightNumber=str[2];
			
			String flightDeparture=str[3]+":00";
			Time departureTime=java.sql.Time.valueOf(flightDeparture);
			
			String flightArrival=str[4]+":00";
			Time arrivalTime=java.sql.Time.valueOf(flightArrival);
			
			long timeDifference= arrivalTime.getTime()-departureTime.getTime();
			int hourDifference=Math.abs(arrivalTime.getHours()-departureTime.getHours());
			int minuteDifference=Math.abs(arrivalTime.getMinutes()-departureTime.getMinutes());
			
			String flightDuration=hourDifference+"hrs "+minuteDifference+"mins";
			
			String flightType=str[5];
			
			String stops=str[6];
			
			String operationDays=str[7];
			
			long flightFare= (long) (Math.random()*(10000 - 3000) + 3000);
			
			int randomIndex = rand.nextInt(airlineList.size());
			String airline=airlineList.get(randomIndex);
			
			Flight flight= new Flight(origin,destination,flightNumber,departureTime,arrivalTime,flightType,stops,operationDays,flightDuration,flightFare,airline);
			
			originCode=originCode.replace("(", "");
			originCode=originCode.replace(")", "");
			flight.setOriginCode(originCode);
			
			destinationCode=destinationCode.replace("(", "");
			destinationCode=destinationCode.replace(")", "");
			flight.setDestinationCode(destinationCode);
			
			flightRepository.save(flight);
			line=bufferedReader.readLine();
			}
	}
	
	@Override
	public List<Flight> getAllFlightDetails(){
		return flightRepository.findAll();
	}
	
	@Override
	public List<Flight> getFlightDetailsWithPreference(String origin,String destination,int Duration, String noOfStops,int fareLower,int fareUpper, String airline){
		List<Flight> flightDetailsList= new ArrayList<Flight>();
		flightDetailsList = flightRepository.findAll();
		List<Flight> resultList= new ArrayList<Flight>();
		int ch = 0;
		if(!origin.isEmpty() && !destination.isEmpty() && Duration!=0 && !noOfStops.equals("Select noOfStops") && fareUpper!=0 && fareLower!=0 && !airline.equals("Select Airlines"))
		{
			ch=0;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration!=0 && !noOfStops.equals("Select noOfStops") && fareUpper!=0 && fareLower!=0 && airline.equals("Select Airlines")) 
		{
			ch=1;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration!=0 && !noOfStops.equals("Select noOfStops") && fareUpper==0 && fareLower==0 && airline.equals("Select Airlines")) 
		{
			ch=2;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration!=0 && noOfStops.equals("Select noOfStops") && fareUpper==0 && fareLower==0 && airline.equals("Select Airlines")) 
		{
			ch=3;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration==0 && noOfStops.equals("Select noOfStops") && fareUpper==0 && fareLower==0 && airline.equals("Select Airlines")) 
		{
			ch=4;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration==0 && noOfStops.equals("Select noOfStops") && fareUpper==0 && fareLower==0 && !airline.equals("Select Airlines")) 
		{
			ch=5;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration==0 && !noOfStops.equals("Select noOfStops") && fareUpper==0 && fareLower==0 && airline.equals("Select Airlines")) 
		{
			ch=6;
		}
		else if(!origin.isEmpty() && !destination.isEmpty() && Duration==0 && noOfStops.equals("Select noOfStops") && fareUpper!=0 && fareLower!=0 && airline.equals("Select Airlines")) 
		{
			ch=7;
		}
		switch (ch) {
		case 0:for(Flight f:flightDetailsList)
				{
					String flightType=f.getFlightType();
					String[] stops=f.getStop().split("/");
					String[] duration=f.getDuration().split(" ");
					String[] hour=duration[0].split("h");
					int time=Integer.parseInt(hour[0]);
					int numberOfStops=Integer.parseInt(noOfStops);
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && (flightType.equals("Connecting")||flightType.equals("Via")) &&stops.length==numberOfStops && f.getAirline().equals(airline) && f.getFare()>=fareLower && f.getFare()<=fareUpper)
					{
						resultList.add(f);
					}
					else if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && flightType.equals("Direct") && numberOfStops==0 && f.getAirline().equals(airline) && f.getFare()>=fareLower && f.getFare()<=fareUpper)
					{
						resultList.add(f);
					}
				}
				
			break;
		case 1:for(Flight f:flightDetailsList)
				{
					String flightType=f.getFlightType();
					String[] stops=f.getStop().split("/");
					String[] duration=f.getDuration().split(" ");
					String[] hour=duration[0].split("h");
					int time=Integer.parseInt(hour[0]);
					int numberOfStops=Integer.parseInt(noOfStops);
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && (flightType.equals("Connecting")||flightType.equals("Via")) && stops.length==numberOfStops && f.getFare()>=fareLower && f.getFare()<=fareUpper)
					{
						resultList.add(f);
					}
					else if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && flightType.equals("Direct") && numberOfStops==0 && f.getFare()>=fareLower && f.getFare()<=fareUpper)
					{
						resultList.add(f);
					}
				}
			break;
			
		case 2:for(Flight f:flightDetailsList)
				{
					String flightType=f.getFlightType();
					String[] stops=f.getStop().split("/");
					String[] duration=f.getDuration().split(" ");
					String[] hour=duration[0].split("h");
					int time=Integer.parseInt(hour[0]);
					int numberOfStops=Integer.parseInt(noOfStops);
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && (flightType.equals("Connecting")||flightType.equals("Via")) && stops.length==numberOfStops)
					{
						resultList.add(f);
					}
					else if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration && flightType.equals("Direct") && numberOfStops==0)
					{
						resultList.add(f);
					}
				}
			break;
			
		case 3:for(Flight f:flightDetailsList)
				{
					String[] duration=f.getDuration().split(" ");
					String[] hour=duration[0].split("h");
					int time=Integer.parseInt(hour[0]);
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && time==Duration)
					{
						resultList.add(f);
					}
				}
			break;
		
		case 4:for(Flight f:flightDetailsList)
				{
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination))
					{
						resultList.add(f);
					}
				}
			break;
		
		case 5:for(Flight f:flightDetailsList)
				{
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && f.getAirline().equals(airline))
					{
						resultList.add(f);
					}
				}
			break;
		
		case 6:for(Flight f:flightDetailsList)
				{
					String flightType=f.getFlightType();
					String[] stops=f.getStop().split("/");
					int numberOfStops=Integer.parseInt(noOfStops);
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination)  && (flightType.equals("Connecting")||flightType.equals("Via")) && stops.length==numberOfStops)
					{
						resultList.add(f);
					}
					else if(f.getOrigin().equals(origin) && f.getDestination().equals(destination)  && flightType.equals("Direct") && numberOfStops==0)
					{
						resultList.add(f);
					}
				}
			break;
		
		case 7:for(Flight f:flightDetailsList)
				{
					if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && f.getFare()>=fareLower && f.getFare()<=fareUpper)
					{
						resultList.add(f);
					}
				}
			break;
		
			
		default:
			break;
		}
		
		return resultList;
	}
	





	@Override
	public List<Flight> getFlightWithFare(long fareLower, long fareUpper) {
		List<Flight> flightDetailsList= new ArrayList<Flight>();
		flightDetailsList = flightRepository.findAll();
		List<Flight> resultList= new ArrayList<Flight>();
		for(Flight f:flightDetailsList)
		{
			if(f.getFare()>=fareLower && f.getFare()<=fareUpper)
			{
				resultList.add(f);
			}
		}
		return resultList;
	}

}
