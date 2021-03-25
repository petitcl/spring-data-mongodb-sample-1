package com.petitcl.springdatamongodbsample.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Value
@With
@Document("immutableTest")
public class ImmutableTestEntity {

	@Id
	private final String id;

	private final String test;

}
