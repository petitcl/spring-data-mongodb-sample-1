package com.petitcl.springdatamongodbsample;

import com.petitcl.springdatamongodbsample.entities.ImmutableTestEntity;
import com.petitcl.springdatamongodbsample.repositories.ImmutableTestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ImmutableEntityTest {

	@Autowired
	private ImmutableTestRepository repository;

	@BeforeEach
	public void beforeEach() {
		repository.deleteAll().block();
	}

	@Test
	public void test_save_deserializesWellEntity() throws Exception {
		ImmutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findById_deserializesWellEntity() throws Exception {
		ImmutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		entity = repository.findById(entity.getId()).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findAll_deserializesWellEntity() throws Exception {
		ImmutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		final List<ImmutableTestEntity> entities = repository.findAll()
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	// this test fails because entities.get(0).getId() is null
	@Test
	public void test_saveAllFlux_deserializesWellEntity() throws Exception {
		ImmutableTestEntity entity = makeEntity();
		final Flux<ImmutableTestEntity> flux = Flux.just(entity);
		final List<ImmutableTestEntity> entities = repository.saveAll(flux)
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_saveAllIterable_deserializesWellEntity() throws Exception {
		ImmutableTestEntity entity = makeEntity();
		final List<ImmutableTestEntity> entities = repository.saveAll(Collections.singletonList(entity))
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	private ImmutableTestEntity makeEntity() {
		return ImmutableTestEntity.builder()
			.test("test")
			.build();
	}

	private void assertEntityIsCorrectlyDeserialized(ImmutableTestEntity entity) {
		Assertions.assertNotNull(entity, "Entity is not null");
		Assertions.assertEquals(entity.getTest(), "test", "Entity test property is not null");
		Assertions.assertNotNull(entity.getId(), "Entity id is not null");
	}

}
