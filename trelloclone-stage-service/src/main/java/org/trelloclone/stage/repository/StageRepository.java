package org.trelloclone.stage.repository;

import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;

public interface StageRepository {

	public Stage getStageById(String id);

	public Stage createStage();

	public Stage deleteItemFromStage(String string, Item item);

	public Stage addItemToStage(String string, Item item);
	
	
}
