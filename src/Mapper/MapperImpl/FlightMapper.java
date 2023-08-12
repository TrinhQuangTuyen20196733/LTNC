package Mapper.MapperImpl;

import java.util.List;
import java.util.stream.Collectors;

import DTO.FlightDTO;
import Entity.Flight;
import Entity.Plane;
import Mapper.Mapper;
import Repository.IMyRepository;
import Repository.MyRepository;

public class FlightMapper implements Mapper<Flight, FlightDTO> {
 private IMyRepository<Plane> planeRepository =  new MyRepository<>().getInstance(Plane.class);

	@Override
	public Flight toEntity(FlightDTO dto) {
		Flight flight = new Flight();
		flight.setId(dto.getId());
		flight.setCode(dto.getCode());
		flight.setDepartureLocation(dto.getDepartureLocation());
		flight.setDestination(dto.getDestination());
		flight.setDepartureTime(dto.getDepartureTime());
		flight.setLandingTime(dto.getLandingTime());
		flight.setBoardingGate(dto.getBoardingGate());
		var planeCode = dto.getPlaneCode();
		Plane plane  = planeRepository.getOneByQuery("SELECT * FROM plane WHERE code='"+planeCode+"'");
		flight.setPlaneId(plane.getId());
		return flight;
	}

	@Override
	public FlightDTO toDTO(Flight entity) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setId(entity.getId());
		flightDTO.setCode(entity.getCode());
		flightDTO.setDepartureLocation(entity.getDepartureLocation());
		flightDTO.setDestination(entity.getDestination());
		flightDTO.setDepartureTime(entity.getDepartureTime());
		flightDTO.setLandingTime(entity.getLandingTime());
		flightDTO.setBoardingGate(entity.getBoardingGate());
		var planeId = entity.getPlaneId();
		Plane plane = planeRepository.getById(planeId);
		flightDTO.setPlaneCode(plane.getCode());
		return flightDTO;
	}

	@Override
	public List<FlightDTO> toDTOList(List<Flight> entityList) {
		var lst = entityList.stream().map(entity ->
		 toDTO(entity)
	).collect(Collectors.toList());
	return lst;
	}

	@Override
	public List<Flight> toEntityList(List<FlightDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

}
