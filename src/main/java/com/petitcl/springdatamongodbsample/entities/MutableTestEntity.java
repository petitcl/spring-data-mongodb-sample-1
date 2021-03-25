package com.petitcl.springdatamongodbsample.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@With
@Document("mutableTest")
public class MutableTestEntity {

	@Id
	private String id;

	private String test;

}
