package Controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import Config.StatusConstant;
import DTO.FlightDTO;
import DTO.FlightFilterParam;
import DTO.MessageResponse;
import DTO.PlaneDTO;
import DTO.PlaneRes;
import Entity.Flight;
import Entity.Plane;
import Entity.Seat;
import Entity.Ticket;
import Mapper.Mapper;
import Mapper.MapperImpl.FlightMapper;
import Mapper.MapperImpl.PlaneMapper;
import Repository.IMyRepository;
import Repository.MyRepository;

public class FlightController {
	 private IMyRepository<Flight> flightRepository =  new MyRepository<>().getInstance(Flight.class);
	 private IMyRepository<Plane> planeRepository =  new MyRepository<>().getInstance(Plane.class);
	 private IMyRepository<Ticket> ticketRepository =  new MyRepository<>().getInstance(Ticket.class);
	 private IMyRepository<Seat> seatRepository =  new MyRepository<>().getInstance(Seat.class);
	 private Mapper<Flight,FlightDTO> flightMapper = new FlightMapper();
	 public MessageResponse ms = new MessageResponse();
	 public MessageResponse create(FlightDTO flightDTO) {
		
		 try {
			 Flight flight = flightMapper.toEntity(flightDTO);
			int flightId= flightRepository.insertEntityAndReturnId(flight);
			 Ticket ticket = new Ticket();
			 ticket.setFlightId(flightId);
			 ticket.setStatus(StatusConstant.EMPTY);
			 ticket.setCost(flightDTO.getCost());
			 List<Seat> seats = seatRepository.getByQuery("SELECT * FROM seat WHERE planeId="+flight.getPlaneId());
			 for (Seat seat :seats) {
				 ticket.setSeatId(seat.getId());
				 ticketRepository.insertEntity(ticket);
			 }
			 
		 }
		 catch (Exception e) {
			ms.code=500;
			ms.message="Create Flight Faild! Please, Try again!";
		}
		 return ms;
	 }
	 public List<FlightDTO> getAll(){
		 var EntityList = flightRepository.getAll();
		 var resList =  flightMapper.toDTOList(EntityList);
		 
		 return resList;
	 }
	 public MessageResponse deletebyId(int id) {
			try {
				flightRepository.deleteById(id);
			} catch (Exception e) {
				ms.code=500;
				ms.message="Delete Plane faild! Please, try again!";
			}
			return ms;
		}
	 public MessageResponse update(FlightDTO flightDTO) {
			try {
				var planeCode = flightDTO.getPlaneCode();
				Plane plane  = planeRepository.getOneByQuery("SELECT * FROM plane WHERE code='"+planeCode+"'");
				 Flight flight = new Flight(flightDTO.getId(),flightDTO.getCode(),flightDTO.getDepartureLocation(),flightDTO.getDestination(),
						 flightDTO.getDepartureTime(), flightDTO.getLandingTime(),flightDTO.getBoardingGate(),plane.getId());
				
				flightRepository.updateEntity(flight);
			 }
			 catch (Exception e) {
				ms.code=500;
				ms.message="Update Plane faild! Please, try again!";
			}
			 return ms;
	 }
	
	 public List<FlightDTO> getByFilter(FlightFilterParam filter) {
		 String query = "SELECT* FROM flight WHERE ";
		 if (filter.getDepartureLocation()!=null) query = query + "departureLocation LIKE '%" + filter.getDepartureLocation()+ "%' ";
		 if (filter.getDestination()!=null) query = query + " AND destination LIKE '%" + filter.getDestination()+ "%' ";
		 query = query + " ORDER BY departureTime , landingTime desc";
		 List<Flight> lst= flightRepository.getByQuery(query);
		  var results = flightMapper.toDTOList(lst);
		 return results;
	 }
}
