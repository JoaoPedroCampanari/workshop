package com.educandoweb.course.services;

import com.educandoweb.course.domain.dtos.UsersDto;
import com.educandoweb.course.domain.entities.Users;
import com.educandoweb.course.exception.users.EmailAlredyExistException;
import com.educandoweb.course.exception.users.UserNotFoundException;
import com.educandoweb.course.repositories.UsersRepository;
import com.educandoweb.course.services.impl.UsersServices;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersServicesImpl implements UsersServices {

    private final UsersRepository usersRepository;

    public UsersServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();

    }

    @Override
    public Users findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public Users save(UsersDto usersDto) {
        if (usersRepository.existsByEmail(usersDto.getEmail())){
            throw new EmailAlredyExistException("Email already exists: " + usersDto.getEmail());
        }
        Users users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        return usersRepository.save(users);
    }

    @Override
    public String deleteById(UUID id) {
        try{
            usersRepository.deleteById(id);
            return ("User deleted successfully. ID: " + id);
        }
        catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public Users update(UUID id, UsersDto usersDto) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        BeanUtils.copyProperties(usersDto, users);
        return usersRepository.save(users);
    }
}
