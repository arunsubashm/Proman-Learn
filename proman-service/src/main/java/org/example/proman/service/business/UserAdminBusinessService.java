package org.example.proman.service.business;

import org.example.proman.service.dao.UserDao;
import org.example.proman.service.entity.UserEntity;
import org.example.proman.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminBusinessService {
    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity getUser(final String userUuid) throws ResourceNotFoundException {

        UserEntity userEntity =  userDao.getUser(userUuid);
        if(userEntity == null){
            throw new ResourceNotFoundException("USR-001", "User not found");
        }
        return userEntity;
    }
}
