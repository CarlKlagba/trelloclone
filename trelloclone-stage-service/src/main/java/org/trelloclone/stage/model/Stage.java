package org.trelloclone.stage.model;

import java.util.List;

public class Stage {
	
	private String id;
	
	private List<Item> items;
	
	public Stage(String id, List<Item> items) {
		super();
		this.id = id;
		this.items = items;
	}
	
	public Stage( List<Item> items) {
		super();
		this.items = items;
	}
	
	public String getId() {
		return id;
	}

	public List<Item> getItems() {
		return items;
	}
	
}
