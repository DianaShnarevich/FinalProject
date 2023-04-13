package com.example.fitness.web.controllers;

import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.core.dto.users.UserLoginDTO;
import com.example.fitness.core.dto.users.UserRegistrationDTO;
import com.example.fitness.core.dto.users.UserTokenDTO;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.service.UserHolder;
import com.example.fitness.service.api.IUserService;
import com.example.fitness.security.utils.JwtTokenHandler;
import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
@Validated
public class UserController {

	private final IUserService userService;
	private final ConversionService conversionService;
	private final JwtTokenHandler tokenHandler;
	private final UserHolder userHolder;

	public UserController(IUserService userService, ConversionService conversionService,
						  JwtTokenHandler tokenHandler, UserHolder userHolder){
		this.userService = userService;
		this.conversionService = conversionService;
		this.tokenHandler = tokenHandler;
		this.userHolder = userHolder;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/registration")
	public ResponseEntity<?> registration(@Valid @RequestBody UserRegistrationDTO dto){
		UserEntity userEntity = conversionService.convert(dto, UserEntity.class);
		userService.register(userEntity);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body("Пользователь зарегистрирован. " +
				"На почту отправлено письмо с кодом для активации аккаунта");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/verification")
	public ResponseEntity<?> verification(
			@RequestParam(name = "code") String code,
			@RequestParam(name = "mail") String mail
	) {
		userService.verify(code, mail);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Пользователь верифицирован");
	}

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public String login(@Valid @RequestBody UserLoginDTO dto){
		UserEntity entity = userService.login(dto);
		UserTokenDTO userTokenDTO = conversionService.convert(entity, UserTokenDTO.class);
		return tokenHandler.generateAccessToken(userTokenDTO);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/me")
	public ResponseEntity<UserDTO> getMe(){
		String mail = userHolder.getUser().getMail();
		UserEntity userEntity = userService.getCard(mail);
		UserDTO dto = conversionService.convert(userEntity, UserDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
