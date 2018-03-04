package org.trelloclone.stage.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;
import org.trelloclone.stage.repository.StageRepository;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class StageServiceTest {
	@Mock
	StageRepository stageRepository;
	
	StageService stageService;
	
	
	@Before
	public void setup() {
		stageService = new StageService(stageRepository);
	}
	
	@Test
	public void should_return_the_expected_stage() throws Exception {
		when(stageRepository.getStageById("1"))
			.thenReturn(new Stage("1", new ArrayList<Item>()));
		
		Stage stage = stageService.getStageById("1");
		
		verify(stageRepository).getStageById("1");
		assertThat(stage.getId(), equalTo("1"));
	}
	
	@Test
	public void should_create_a_new_stage() {
		when(stageRepository.createStage())
			.thenReturn(new Stage("1", null));
		
		Stage createdStage = stageService.createStage();
		
		verify(stageRepository).createStage();
		assertThat(createdStage.getId(), equalTo("1"));
	}
	
	@Test
	public void should_move_one_item_from_one_stage_to_another() throws Exception {
		Item item = new Item("itemName");
		when(stageRepository.deleteItemFromStage("1", item))
			.thenReturn(new Stage(new ArrayList<>()));
		when(stageRepository.addItemToStage("1", item))
			.thenReturn(new Stage(new ArrayList<>()));
		
		stageService.moveItem("1", "2", item);
		
		verify(stageRepository).deleteItemFromStage("1", item);
		verify(stageRepository).addItemToStage("2", item);
	}
	
	@Test
	public void sould_add_a_new_item_in_the_stage_1() throws Exception {
		Item item = new Item("itemB");
		Stage expectedStage = new Stage("1", Arrays.asList(new Item("itemA"), new Item("itemB")));
		
		when(stageRepository.addItemToStage("1", item))
		.thenReturn(expectedStage);
		
		Stage actualStage = stageService.addItemInStage("1",  item);
		
		verify(stageRepository).addItemToStage("1", item);
		
		assertThat(actualStage.getId(), equalTo(expectedStage.getId()));
		assertThat(actualStage.getItems().size(), equalTo(2));
	}
}
