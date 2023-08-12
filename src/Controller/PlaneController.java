package Controller;

import java.util.List;

import DTO.MessageResponse;
import DTO.PlaneDTO;
import DTO.PlaneRes;
import Entity.Plane;
import Entity.Seat;
import Entity.UserEntity;
import Mapper.Mapper;
import Mapper.MapperImpl.PlaneMapper;
import Repository.IMyRepository;
import Repository.MyRepository;

public class PlaneController {
	 private IMyRepository<Plane> planeRepository =  new MyRepository<>().getInstance(Plane.class);
	 private IMyRepository<Seat> seatRepository =  new MyRepository<>().getInstance(Seat.class);
	 private Mapper<Plane,PlaneRes> planeMapper = new PlaneMapper();
	 public MessageResponse ms = new MessageResponse();
	 public MessageResponse create(PlaneDTO planeDTO) {
		 try {
			 Plane plane = new Plane(planeDTO.getType(),planeDTO.getCost(),planeDTO.getMaximumSpeed(),planeDTO.getAirlineId(),planeDTO.getCode(),planeDTO.getRow(),planeDTO.getColumn());
			 int planeId = planeRepository.insertEntityAndReturnId(plane);
			 for (int i=0;i<planeDTO.getRow();i++) {
				 for (int j=0;j<planeDTO.getColumn();j++) {
					 Seat seat = new Seat(i,j,planeId);
					 seatRepository.insertEntity(seat);
				 }
			 }
		 }
		 catch (Exception e) {
			ms.code=500;
			ms.message="Insert Plane faild! Please, try again!";
		}
		 return ms;
	 }
	 public List<PlaneRes> getAll(){
		 var EntityList = planeRepository.getAll();
		 var resList =  planeMapper.toDTOList(EntityList);
		 
		 return resList;
	 }
	public MessageResponse deletebyId(int id) {
		try {
			planeRepository.deleteById(id);
		} catch (Exception e) {
			ms.code=500;
			ms.message="Delete Plane faild! Please, try again!";
		}
		return ms;
	}
	public MessageResponse update(PlaneDTO planeDTO) {
		try {
			 Plane plane = new Plane(planeDTO.getType(),planeDTO.getCost(),planeDTO.getMaximumSpeed(),planeDTO.getAirlineId(),planeDTO.getCode(),planeDTO.getRow(),planeDTO.getColumn());
			 plane.setId(planeDTO.getId());
			planeRepository.updateEntity(plane);
		 }
		 catch (Exception e) {
			ms.code=500;
			ms.message="Update Plane faild! Please, try again!";
		}
		 return ms;
	}
}
