package com.petitcl.springdatamongodbsample.repositories;

import com.petitcl.springdatamongodbsample.entities.ImmutableTestEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmutableTestRepository extends ReactiveCrudRepository<ImmutableTestEntity, String> {

}
