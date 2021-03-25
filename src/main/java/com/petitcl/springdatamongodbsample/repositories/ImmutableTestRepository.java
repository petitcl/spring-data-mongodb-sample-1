package com.petitcl.springdatamongodbsample.repositories;

import com.petitcl.springdatamongodbsample.entities.ImmutableTestEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmutableTestRepository extends ReactiveMongoRepository<ImmutableTestEntity, String> {

}
