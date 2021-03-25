package com.petitcl.springdatamongodbsample.repositories;

import com.petitcl.springdatamongodbsample.entities.MutableTestEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutableTestRepository extends ReactiveCrudRepository<MutableTestEntity, String> {

}
