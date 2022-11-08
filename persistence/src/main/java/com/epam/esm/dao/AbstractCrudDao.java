package com.epam.esm.dao;

import com.epam.esm.entity.BaseAbstractDomain;
import com.epam.esm.enums.State;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public abstract class AbstractCrudDao<T extends BaseAbstractDomain> {

    private final EntityManager entityManager;
    private final Class<T> persistentClass;

    public AbstractCrudDao(EntityManager entityManager, Class<T> persistentClass) {
        this.entityManager = entityManager;
        this.persistentClass = persistentClass;
    }

    public Long save(T entity){
        if (entity == null){
            return null;
        }
        entityManager.persist(entity);
        //fixme read about flush
        entityManager.flush();
        return entity.getId();
    }

    public Optional<T> findById(Long id){
        return Optional.ofNullable(entityManager.find(persistentClass, id));
    }

    @Transactional
    public T update(T update){
        return entityManager.merge(update);
    }

    public void delete(T entity){
        if (entity == null){
            return;
        }
        entity.setState(State.DELETED);
        entityManager.persist(entity);
    }
}
