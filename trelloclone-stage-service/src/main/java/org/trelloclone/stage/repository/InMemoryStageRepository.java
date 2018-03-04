package org.trelloclone.stage.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;

@Component
public class InMemoryStageRepository implements StageRepository {
	
	Map<String, Stage> stageStore;
	
	
	public InMemoryStageRepository() {
		this.stageStore = new HashMap<String, Stage>();
	}

	public InMemoryStageRepository(Map<String, Stage> stageStore) {
		this.stageStore = stageStore;
	}

	public Stage getStageById(String id) {
		return stageStore.get(id);
	}

	public Stage createStage() {
		String nextStageId = String.valueOf(stageStore.keySet().size()+1);
		stageStore.put(nextStageId, 
				new Stage(nextStageId, new ArrayList<Item>()));
		return stageStore.get(nextStageId);
	}

	public Stage deleteItemFromStage(String stageId, Item item) {
		Stage stage = stageStore.get(stageId);
		stage.removeItem(item);
		return stage;
	}

	public Stage addItemToStage(String stageId, Item item) {
		Stage stage = stageStore.get(stageId);
		stage.addItem(item);
		return stage;
	}

}
