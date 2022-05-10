package br.com.nttdata.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nttdata.repository.UserRepository;
import br.com.nttdata.security.AccountCredentialsVO;
import br.com.nttdata.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AuthEndpoint")
@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository repository;

	@ApiOperation(value = "Authenticate a user by credentials")
	@PostMapping(value = "/signin", produces = {"application/json","application/xml","application/x-yaml"}, 
			consumes = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repository.findByUsername(username);		
			var token = "";
			
			if(!(user.equals(null))){
				token = tokenProvider.createToken(username, user.get().getRoles());
			}else {
				throw new UsernameNotFoundException("Username ".concat(username).concat(" not found!"));
			}
			Map<Object,Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return 	ok(model);
		}catch(AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied! ");
		}
	}
}
