package org.example.proman.service.business;

import org.example.proman.service.dao.UserDao;
import org.example.proman.service.entity.RoleEntity;
import org.example.proman.service.entity.UserAuthTokenEntity;
import org.example.proman.service.entity.UserEntity;
import org.example.proman.service.exception.ResourceNotFoundException;
import org.example.proman.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAdminBusinessService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity getUser(final String userUuid, final String authorizationToken) throws ResourceNotFoundException, UnauthorizedException {


        UserAuthTokenEntity userAuthTokenEntity = userDao.getUserAuthToken(authorizationToken);
        RoleEntity role = userAuthTokenEntity.getUser().getRole();
        if(role.getUuid() == 101){
            UserEntity userEntity =  userDao.getUser(userUuid);
            if(userEntity == null){
                throw new ResourceNotFoundException("USR-001", "User not found");
            }
            return userEntity;
        }
        throw new UnauthorizedException("ATH-002", "you are not authorized to fetch user details");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity createUser(final UserEntity userEntity){

        String password = userEntity.getPassword();
        if(password == null){
            userEntity.setPassword("test");
        }
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        return userDao.createUser(userEntity);
    }
}
