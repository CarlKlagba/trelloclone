package org.trelloclone.stage.model;


import java.util.List;
import java.util.stream.Collectors;

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
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items = items.stream()
			.filter(i -> !i.equals(item))
			.collect(Collectors.toList());
	}
	
}
