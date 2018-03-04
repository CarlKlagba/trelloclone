package org.trelloclone.stage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;
import org.trelloclone.stage.service.StageService;

@RestController
@RequestMapping(value = "/stages")
public class StageController {
	private final StageService stageService;
	
	@Autowired
	public StageController(StageService stageService) {
		this.stageService = stageService;
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			value = "/{stageId}", 
			produces = "application/json")
	public Stage getStage(@PathVariable String stageId) {
		return this.stageService.getStageById(stageId);
	}
	
	@RequestMapping(method = RequestMethod.POST, 
			produces = "application/json")
	public Stage createStage() {
		return this.stageService.createStage();
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/{stageId}/item",
			produces = "application/json")
	public Stage addItemToStage(@PathVariable String stageId, @RequestBody Item item) {
		return this.stageService.addItemInStage(stageId, item);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/item",
			params = {"source", "destination"},
			produces = "application/json")
	public ResponseEntity<Void> moveItem(
			@RequestParam("source") String source,
			@RequestParam("destination") String destination,
			@RequestBody Item item) {
		this.stageService.moveItem(source, destination, item);
		return ResponseEntity.ok().build();
	}
	
}
