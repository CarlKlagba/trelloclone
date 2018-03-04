package org.trelloclone.stage.service;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;
import org.trelloclone.stage.repository.StageRepository;

@Service
public class StageService {

	private final StageRepository stageRepository;
	
	@Autowired
	public StageService(StageRepository stageRepository) {
		this.stageRepository = stageRepository;
	}

	public Stage getStageById(String stageId) {
		return stageRepository.getStageById(stageId);
	}

	public Stage createStage() {
		return stageRepository.createStage();
	}

	public void moveItem(String sourceIdStage, String destIdStage, Item item) {
		stageRepository.deleteItemFromStage(sourceIdStage, item);
		stageRepository.addItemToStage(destIdStage, item);
	}

	public Stage addItemInStage(String stageId, Item item) {
		return stageRepository.addItemToStage(stageId, item);
	}

}
