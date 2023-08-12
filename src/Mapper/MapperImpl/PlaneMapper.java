package Mapper.MapperImpl;

import java.util.List;
import java.util.stream.Collectors;

import DTO.PlaneRes;
import Entity.Airline;
import Entity.Plane;
import Mapper.Mapper;
import Repository.IMyRepository;
import Repository.MyRepository;

public class PlaneMapper implements Mapper<Plane, PlaneRes> {
	
	

	@Override
	public Plane toEntity(PlaneRes dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaneRes toDTO(Plane entity) {
		PlaneRes planeRes = new PlaneRes();
		planeRes.setCode(entity.getCode());
		planeRes.setCost(entity.getCost());
		planeRes.setId(entity.getId());
		planeRes.setType(entity.getType());
		planeRes.setMaximumSpeed(entity.getMaximumSpeed());
		 MyRepository<Airline> airlineRepository =  new MyRepository<>().getInstance(Airline.class);
		Airline airline = airlineRepository.getById(entity.getAirlineId());
		String airlineName = airline.getName();
		planeRes.setAirlineName(airlineName);
		return planeRes;
	}

	@Override
	public List<PlaneRes> toDTOList(List<Plane> entityList) {
		var lst = entityList.stream().map(entity ->
			 toDTO(entity)
		).collect(Collectors.toList());
		return lst;
	}

	@Override
	public List<Plane> toEntityList(List<PlaneRes> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

}
