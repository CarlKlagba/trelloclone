package org.trelloclone.stage.controller;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.trelloclone.stage.Application;
import org.trelloclone.stage.model.Item;
import org.trelloclone.stage.model.Stage;
import org.trelloclone.stage.service.StageService;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StageControllerTest {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private StageService stageService;
	
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void readStage() throws Exception {
		when(stageService.getStageById("1"))
			.thenReturn(new Stage("1", new ArrayList()));
		
		this.mockMvc.perform(get("/stages/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(this.contentType))
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.items", empty()));
	}
	
	@Test
	public void createStageTest() throws Exception {
		when(stageService.createStage())
			.thenReturn(new Stage("1", new ArrayList()));
		
		this.mockMvc.perform(post("/stages"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(this.contentType))
			.andExpect(jsonPath("$.id", is("1")))
			.andExpect(jsonPath("$.items", empty()));
	}
	
	@Test
	public void addItemInStageTest() throws Exception {
		when(stageService.addItemInStage(anyString(), any()))
		.thenReturn(new Stage("1", Arrays.asList(new Item("itemA"), new Item("itemB"))));
		
		this.mockMvc.perform(put("/stages/1/item")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"itemB\"}"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(this.contentType))
		.andExpect(jsonPath("$.id", is("1")))
		.andExpect(jsonPath("$.items", hasSize(2)))
		.andExpect(jsonPath("$.items[0].name", is("itemA")))
		.andExpect(jsonPath("$.items[1].name", is("itemB")));
	}
	
	@Test
	public void moveItemTest() throws Exception {
//		ArgumentCaptor<Item> argCaptor = ArgumentCaptor.forClass(Item.class);
		Item item = new Item("item");
		doNothing().when(stageService).moveItem("1", "2", item);
		
		this.mockMvc.perform(put("/stages/item?source=1&destination=2 ")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"item\"}"))
			.andExpect(status().isOk());
		// TODO
//		verify(stageService, times(1)).moveItem(eq("1"), eq("2"), argCaptor.capture());
//		assertThat(item.getName(), is(argCaptor.getValue().getName()));
	}
	
	
}
