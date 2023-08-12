package Controller;

import java.util.List;

import DTO.MessageResponse;
import Entity.Airline;
import Entity.UserEntity;
import Repository.IMyRepository;
import Repository.MyRepository;

public class AirlineController {
	private IMyRepository<Airline> airlineRepository =  new MyRepository<>().getInstance(Airline.class);
	 public MessageResponse ms = new MessageResponse();
	 public MessageResponse create(Airline airline) {
		 try {

			 airlineRepository.insertEntity(airline); 
		 }
		 catch (Exception e) {
			ms.code = 500;
			ms.message = "Create Airline faild! Please, try again!";
		}
		 return ms;
	 }
	 public MessageResponse update(Airline airline) {
		 try {

			 airlineRepository.updateEntity(airline); 
		 }
		 catch (Exception e) {
			ms.code = 500;
			ms.message = "Update Airline faild! Please, try again!";
		}
		 return ms;
	 }
	 public List<Airline> getAll(){
		 return airlineRepository.getAll();
	 }
	public MessageResponse deletebyId(int id) {
		try {
			 airlineRepository.deleteById(id);
		}
		catch (Exception e) {
			ms.code=500;
			ms.message = "Delete Faild! Try again!";
		}
		
		return ms;
	}
}
