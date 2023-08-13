package Controller;

import java.util.List;

import DTO.LoginReq;
import DTO.MessageResponse;
import Entity.UserEntity;
import Repository.IMyRepository;
import Repository.MyRepository;

public class AuthController {
 private IMyRepository<UserEntity> authRepository =  new MyRepository<>().getInstance(UserEntity.class);
 public MessageResponse ms = new MessageResponse();
 public MessageResponse signIn(LoginReq loginReq) {
	 String query = "SELECT * FROM user WHERE email='"+ loginReq.getUserName()+ "' AND password='"+loginReq.getPassword()+"'";
	 UserEntity user = authRepository.getOneByQuery(query);
     if (user==null) {
    	ms.code = 404;
    	ms.message = "Login faild! Try again!";
     }
     else {

         ms.message = user.getRole();
     }
	return ms;
 }
 public MessageResponse create(UserEntity userEntity) {
	 try {
		authRepository.insertEntity(userEntity);
	} catch (Exception e) {
		// TODO: handle exception
		ms.code = 500;
		ms.message=e.getMessage();
	}
	 return ms;
 }
public List<UserEntity> getAll() {
	return authRepository.getAll();
}
public MessageResponse deletebyId(int id) {
	try {
		authRepository.deleteById(id);
	}
	catch (Exception e) {
		ms.code = 500;
    	ms.message = "Delete faild! Try again!";
	}
	return ms;
	
}
 public MessageResponse update(UserEntity userEntity) {
	 try {
		authRepository.updateEntity(userEntity);
	} catch (Exception e) {
		// TODO: handle exception
		ms.code = 500;
		ms.message=e.getMessage();
	}
	 return ms;
 }
 
}
