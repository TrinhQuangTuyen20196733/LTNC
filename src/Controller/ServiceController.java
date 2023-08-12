package Controller;

import java.util.List;

import DTO.MessageResponse;
import Entity.Airline;
import Entity.Service;
import Repository.IMyRepository;
import Repository.MyRepository;

public class ServiceController {
	private IMyRepository<Service> serviceRepository =  new MyRepository<>().getInstance(Service.class);
	 public MessageResponse ms = new MessageResponse();
	 public MessageResponse create(Service service) {
		 try {

			 serviceRepository.insertEntity(service); 
		 }
		 catch (Exception e) {
			ms.code = 500;
			ms.message = "Create Airline faild! Please, try again!";
		}
		 return ms;
	 }
	 public MessageResponse update(Service service) {
		 try {

			 serviceRepository.updateEntity(service); 
		 }
		 catch (Exception e) {
			ms.code = 500;
			ms.message = "Update Airline faild! Please, try again!";
		}
		 return ms;
	 }
	 public List<Service> getAll(){
		 return serviceRepository.getAll();
	 }
	 public MessageResponse deletebyId(int id) {
			try {
				 serviceRepository.deleteById(id);
			}
			catch (Exception e) {
				ms.code=500;
				ms.message = "Delete Faild! Try again!";
			}
			
			return ms;
		}
}
