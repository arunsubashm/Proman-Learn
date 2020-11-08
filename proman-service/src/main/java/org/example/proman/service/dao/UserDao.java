package org.example.proman.service.dao;

import org.example.proman.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    public UserEntity getUser(final String userUuid) {
        return entityManager.createNamedQuery("userByUuid", UserEntity.class)
                .setParameter("uuid", userUuid)
                .getSingleResult();
    }
}
