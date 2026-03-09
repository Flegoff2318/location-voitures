package com.accenture.locationvoitures.fake;

import com.accenture.locationvoitures.model.Bike;
import com.accenture.locationvoitures.repository.BikeRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class FakeBikeRepository implements BikeRepository {
    public final Map<UUID, Bike> store = new HashMap<>();


    @Override
    public List<Bike> findByVehicleMetaDataActive(boolean active) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Bike> findByVehicleMetaDataOutOfFleet(boolean outOfFleet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Bike> findByVehicleMetaDataActiveAndVehicleMetaDataOutOfFleet(boolean active, boolean outOfFleet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch(Iterable<Bike> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bike getOne(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bike getById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bike getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Bike> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Bike> S save(S entity) {
        store.put(entity.getUuid(), entity);
        return entity;
    }

    @Override
    public <S extends Bike> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Bike> findById(UUID uuid) {
        Optional<Bike> optBike = Optional.empty();
        try{
            Bike bike = store.get(uuid);
            optBike = Optional.of(bike);
        } catch (Exception _) {
            /* For test purposes */
        }
        return optBike;
    }

    @Override
    public boolean existsById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Bike> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public List<Bike> findAllById(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(UUID uuid) {
        store.remove(uuid);
    }

    @Override
    public void delete(Bike entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Bike> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Bike> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Bike> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
