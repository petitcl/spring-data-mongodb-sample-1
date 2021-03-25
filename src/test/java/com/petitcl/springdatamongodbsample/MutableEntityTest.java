package com.petitcl.springdatamongodbsample;

import com.petitcl.springdatamongodbsample.entities.MutableTestEntity;
import com.petitcl.springdatamongodbsample.repositories.MutableTestRepository;
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
public class MutableEntityTest {

	@Autowired
	private MutableTestRepository repository;

	@BeforeEach
	public void beforeEach() {
		repository.deleteAll().block();
	}

	@Test
	public void test_save_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findById_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		entity = repository.findById(entity.getId()).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findAll_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		entity = repository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		final List<MutableTestEntity> entities = repository.findAll()
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_saveAllFlux_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		final Flux<MutableTestEntity> flux = Flux.just(entity);
		final List<MutableTestEntity> entities = repository.saveAll(flux)
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_saveAllIterable_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		final List<MutableTestEntity> entities = repository.saveAll(Collections.singletonList(entity))
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_insertFlux_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		final Flux<MutableTestEntity> flux = Flux.just(entity);
		final List<MutableTestEntity> entities = repository.insert(flux)
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_insertIterable_deserializesWellEntity() throws Exception {
		MutableTestEntity entity = makeEntity();
		final List<MutableTestEntity> entities = repository.insert(Collections.singletonList(entity))
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	private MutableTestEntity makeEntity() {
		return MutableTestEntity.builder()
			.test("test")
			.build();
	}

	private void assertEntityIsCorrectlyDeserialized(MutableTestEntity entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertEquals(entity.getTest(), "test");
		Assertions.assertNotNull(entity.getId());
	}

}
