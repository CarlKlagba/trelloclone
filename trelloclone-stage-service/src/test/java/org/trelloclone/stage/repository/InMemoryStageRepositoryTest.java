package org.trelloclone.stage.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;

import static org.assertj.core.api.Assertions.*;

public class InMemoryStageRepositoryTest {
	
	InMemoryStageRepository inMemoryStageRepository;
	HashMap<String, Stage> initialMap = new HashMap<String, Stage>(){{
		put("1", new Stage("1", new ArrayList(Arrays.asList(new Item("itemA"),new Item("itemB")))));
		put("2", new Stage("2", new ArrayList(Arrays.asList(new Item("itemC"),new Item("itemD")))));
	}};
	
	@Before
	public void setup() {
		inMemoryStageRepository = new InMemoryStageRepository(initialMap);
	}
	
	@Test
	public void sould_get_the_stage_with_the_id_1() {
		Stage stage = inMemoryStageRepository.getStageById("1");
		
		assertThat(stage.getId()).isEqualTo("1");
		assertThat(stage.getItems().get(0).getName()).isEqualTo("itemA");
		assertThat(stage.getItems().get(1).getName()).isEqualTo("itemB");
	}
	
	@Test
	public void shoud_create_a_new_stage() {
		Stage stage = inMemoryStageRepository.createStage();
		
		assertThat(stage).isNotNull();
		assertThat(stage.getId()).isEqualTo("3");
		assertThat(stage.getItems()).isEmpty();
		
		assertThat(inMemoryStageRepository.getStageById("3")).isNotNull();
	}
	
	@Test
	public void should_delete_item_from_stage() {
		inMemoryStageRepository.deleteItemFromStage("1", new Item("itemA"));
		
		List<Item> items = inMemoryStageRepository.getStageById("1").getItems();
		assertThat(items.size()).isEqualTo(1);
		assertThat(items.get(0)).isEqualTo(new Item("itemB"));
	}
	@Test
	public void  shoud_add_an_item_in_the_stage() {
		Item itemExpected = new  Item("itemC");
		inMemoryStageRepository.addItemToStage("1", itemExpected);
		
		List<Item> items = inMemoryStageRepository.getStageById("1").getItems();
		assertThat(items.size()).isEqualTo(3);
		assertThat(items.get(2)).isEqualTo(itemExpected);
	}
}
