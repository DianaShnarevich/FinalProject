package com.example.fitness.service;

import com.example.fitness.core.exceptions.DuplicatedMailException;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.dao.AdminDao;
import com.example.fitness.service.api.IAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;


public class AdminService implements IAdminService {

	private final AdminDao dao;
	private final PasswordEncoder encoder;

	public AdminService(AdminDao dao, PasswordEncoder encoder) {
		this.dao = dao;
		this.encoder = encoder;
	}

	@Override
	public Page<UserEntity> getPage(Integer page, Integer pageSize) {
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return dao.findAll(pageRequest);
	}

	@Override
	public UserEntity get(UUID uuid) {
		return dao.getReferenceById(uuid);
	}

	@Override
	public void add(UserEntity entity) {
		Optional<UserEntity> user = dao.findByMail(entity.getMail());
		if(user.isPresent()){
			throw new DuplicatedMailException("Такой eMail уже существует");
		}
		String password = entity.getPassword();
		password = encoder.encode(password);
		entity.setPassword(password);
		dao.saveAndFlush(entity);
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		UserEntity entity = dao.getReferenceById(uuid);
		if(!entity.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Пользователь был изменён ранее. Для удаления получите актуальную версию");
		}
		dao.delete(entity);
	}

	@Override
	public void update(UserEntity newUser, UUID uuid, LocalDateTime dtUpdate) {
		Optional<UserEntity> userOptional = dao.findByMail(newUser.getMail());
		if(userOptional.isPresent()){
			throw new DuplicatedMailException("Такой eMail уже существует");
		}
		UserEntity user = dao.getReferenceById(uuid);
		if(!user.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Пользователь был изменён ранее");
		}
		user.setMail(newUser.getMail());
		user.setFio(newUser.getFio());
		user.setRole(newUser.getRole());
		user.setStatus(newUser.getStatus());

		String password = newUser.getPassword();
		password = encoder.encode(password);
		user.setPassword(password);
		user.setPassword(password);

		dao.saveAndFlush(user);
	}
}
