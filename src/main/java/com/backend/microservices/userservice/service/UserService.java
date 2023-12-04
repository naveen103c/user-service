package com.backend.microservices.userservice.service;

import com.backend.microservices.userservice.contants.Contants;
import com.backend.microservices.userservice.dto.User;
import com.backend.microservices.userservice.exception.DobNotValidException;
import com.backend.microservices.userservice.exception.NoUserPresentException;
import com.backend.microservices.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUserService() {
        return userRepo.findAll();
    }

    public User getUserByIdService(String userId) throws NoUserPresentException {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()){
                throw new NoUserPresentException(Contants.NO_USER_PRESENT_WITH_USERID + userId);
        }
        return user.get();
    }

    public User addUserService(User User) {
        if(User.getDob().before(get200YearOldDate())){
            log.info(Contants.EXCEPTION_OCCURED +this.getClass().getName() +":"+Thread.currentThread().getStackTrace()[1].getMethodName() + Contants.EXCEPTION_MESSAGE + Contants.DOB_NOT_VALID);
            throw new DobNotValidException(Contants.DOB_NOT_VALID);
        }
        return userRepo.save(User);
    }

    private Date get200YearOldDate() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.YEAR,-201);
        return cal.getTime();
    }

    public User updateUserByIdService(User User) {
        if(User.getDob().before(get200YearOldDate())){
            log.info(Contants.EXCEPTION_OCCURED +this.getClass().getName() +":"+Thread.currentThread().getStackTrace()[1].getMethodName() + Contants.EXCEPTION_MESSAGE + Contants.DOB_NOT_VALID);
            throw new DobNotValidException(Contants.DOB_NOT_VALID);
        }
        return userRepo.save(User);
    }

    public boolean deleteUserByIdService(String userId) {
        userRepo.deleteById(userId);
        return true;
    }
}
