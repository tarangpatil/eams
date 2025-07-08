package eams.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eams.user.dto.UsersDTO;
import eams.user.model.Users;
import eams.user.service.JwtService;
import eams.user.service.UsersService;
import lombok.AllArgsConstructor;

@RestController
public class Controller {

	private final UsersService service;

	public Controller(UsersService service) {
		super();
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<UsersCreationDTO> register(@RequestBody Users user) {
		UsersDTO usersDTO = service.createUser(user);
		UsersCreationDTO creationDTO = new UsersCreationDTO(usersDTO, JwtService.createToken(usersDTO));
		return ResponseEntity.ok(creationDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> getById(@PathVariable int id) {
		return ResponseEntity.ok(service.getById(id));
	}
}

@AllArgsConstructor
class UsersCreationDTO {
	public UsersDTO user;
	public String token;
}
