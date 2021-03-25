package com.petitcl.springdatamongodbsample.repositories;

import com.petitcl.springdatamongodbsample.entities.MutableTestEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutableTestRepository extends ReactiveMongoRepository<MutableTestEntity, String> {

}
