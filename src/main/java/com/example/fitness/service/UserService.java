package com.example.fitness.service;

import com.example.fitness.core.dto.users.UserLoginDTO;
import com.example.fitness.core.enums.Status;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.dao.UserDao;
import com.example.fitness.service.api.IUserService;
import com.example.fitness.service.api.IEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserService implements IUserService {

	private final UserDao repository;
	private final IEmailService mailService;
	private final PasswordEncoder encoder;

	public UserService(UserDao repository, IEmailService mailService, PasswordEncoder encoder){
		this.repository = repository;
		this.mailService = mailService;
		this.encoder = encoder;
	}

	@Override
	public void register(UserEntity entity) {
		if(repository.findByMail(entity.getMail()).isPresent()){
			throw new IllegalStateException("Пользователь с такие mail уже существует");
		}
		String password = entity.getPassword();
		password = encoder.encode(password);
		entity.setPassword(password);
		repository.saveAndFlush(entity);
		String message = "Уважаемый " + entity.getFio() + "! " + "Для завершения регистрации используйте секретный код: " +
				entity.getCode();
		mailService.sendSimpleMessage(entity.getMail(), "Код активации", message);
	}

	@Override
	public void verify(String code, String mail) {
		Optional<UserEntity> optionalUserByMail = repository.findByMail(mail);

		if(optionalUserByMail.isEmpty()){
			throw new NoSuchElementException("Пользователя с таким eMail нет в базе данных");
		}

		UserEntity user = optionalUserByMail.get();

		if(user.getStatus().equals(Status.ACTIVATED)){
			throw new IllegalStateException("Ваш аккаунт уже был активирован");
		}

		if(user.getStatus().equals(Status.DEACTIVATED)){
			throw new IllegalStateException("Ваш аккаунт заблокирован. Пожалуйста, обратитесь к администратору");
		}

		if(user.getStatus().equals(Status.WAITING_ACTIVATION)){
			if(user.getCode().equals(code)){
				user.setStatus(Status.ACTIVATED);
			} else {
				throw new IllegalStateException("Неверный код активации");
			}
		}

		repository.saveAndFlush(user);
	}

	@Override
	public UserEntity login(UserLoginDTO dto) {
		Optional<UserEntity> optionalUserByMail = repository.findByMail(dto.getMail());
		if(optionalUserByMail.isEmpty()){
			throw new IllegalStateException("Пользователя с таким eMail нет в базе данных");
		}

		UserEntity entity = optionalUserByMail.get();
		String passwordFromDataBase = entity.getPassword();
		if(!encoder.matches(dto.getPassword(), passwordFromDataBase)){
			throw new IllegalStateException("Пароли не совпадают");
		}
		return entity;
	}

	@Override
	public UserEntity getCard(String mail) {
		Optional<UserEntity> optionalUserByMail = repository.findByMail(mail);
		if(optionalUserByMail.isEmpty()){
			throw new IllegalStateException("Пользователя с таким eMail нет в базе данных");
		}
		return optionalUserByMail.get();
	}

	@Override
	public UserEntity getUserByMail(String mail){
		Optional<UserEntity> entityOptional = repository.findByMail(mail);
		if(entityOptional.isEmpty()) {
			throw new IllegalStateException("Пользователя с таким eMail нет в базе данных");
		}
		return entityOptional.get();
	}
}
