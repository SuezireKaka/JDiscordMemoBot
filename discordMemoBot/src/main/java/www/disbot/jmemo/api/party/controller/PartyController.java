package www.disbot.jmemo.api.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.disbot.jmemo.api.party.service.PartyService;
import www.disbot.jmemo.api.security.model.SignUpDTO;
import www.disbot.jmemo.api.security.model.SignUpResultDTO;

@RestController
@CrossOrigin
@RequestMapping("/party")
public class PartyController {
	@Autowired
	private PartyService partyService;

	@GetMapping("/createUser")
	public ResponseEntity<SignUpResultDTO> createUser(@RequestBody SignUpDTO dto) {
		SignUpResultDTO result = partyService.createUser(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
