package com.example.jwtandwebsocket.dao;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

@Slf4j
public abstract class AbstractJpaDao<E extends BaseEntity<T>, T> implements Dao<T> { // E: entity, T: dto ( domain )

    protected abstract Class<E> getEntityClass();

    protected abstract CrudRepository<E, UUID> getCrudRepository();

    @Override
    public T findById(UUID id) {
        log.debug("Get entity by id {}", id);
        Optional<E> entity = getCrudRepository().findById(id);
        return DaoUtil.getData(entity);
    }

    @Override
    public List<T> findAll() {
        log.debug("Find all entity");
        Iterable<E> iterable = getCrudRepository().findAll();
        Iterator<E> iterator = iterable.iterator();
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next().toData());
        }
        return result;
    }

    @Override
    public T save(T t) {
        E entity;
        try {
            entity = getEntityClass().getConstructor(t.getClass()).newInstance(t); // entity can co ham construct Entity(Dto dto)
        } catch (Exception e) {
            log.error("Can't create entity for domain object {}", t, e);
            throw new IllegalArgumentException("Can't create entity for domain object {" + t + "}", e);
        }
        if (entity.getUuid() == null) {
            UUID uuid = UUID.randomUUID();
            entity.setUuid(uuid);
            entity.setCreatedTime(System.currentTimeMillis());
        }
        entity = getCrudRepository().save(entity);
        return DaoUtil.getData(entity);
    }

    @Override
    public boolean deleteById(UUID id) throws MyAppException {
        try {
            getCrudRepository().deleteById(id);
        } catch (Exception e) {
            throw new MyAppException("Entity with id " + id + " does not exist!", RespCode.ITEM_NOT_FOUND);
        }
        log.debug("Delete request: {}", id);
        return !getCrudRepository().existsById(id);
    }

}
