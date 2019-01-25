package com.example.springbootstarter.controller;

import com.example.springbootstarter.SpringBootStarterApplication;
import com.example.springbootstarter.api.model.request.LetterRequest;
import com.example.springbootstarter.api.service.JsonService;
import com.example.springbootstarter.model.entity.GenericEntity;
import com.example.springbootstarter.model.repository.GenericEntityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStarterApplication.class)
@WebAppConfiguration
public class LetterControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private JsonService jsonService;

	private MockMvc mockMvc;

	@Before
	public void beforeRunTests(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void findAll_WhenResponseIsOkAndTypeOfResponseIsJson() throws Exception {
		MediaType expectedMediaType = new MediaType(MediaType.APPLICATION_JSON_UTF8.getType(),
				MediaType.APPLICATION_JSON_UTF8.getSubtype(),
				Charset.forName("UTF8"));

		mockMvc.perform(MockMvcRequestBuilders.get("/example/letters"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(expectedMediaType));
	}

	@Test
	public void findAll_WhenResponseIsOkAndExistsOneRegister() throws Exception {
		LetterRequest letterRequest = new LetterRequest.Builder()
				.withLetter("a")
				.build();
		String jsonContent = jsonService.toJson(letterRequest);
		String jsonExpectedContentList = jsonService.toJson(Arrays.asList(letterRequest));

		mockMvc.perform(MockMvcRequestBuilders.post("/example/letters")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent));

		mockMvc.perform(MockMvcRequestBuilders.get("/example/letters"))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.content().string(jsonExpectedContentList));
	}

	@Test
	public void findByLetter_WhenResponseAndFindByLetterBShouldExist() throws Exception {
		LetterRequest letterRequest = new LetterRequest.Builder()
				.withLetter("b")
				.build();
		String jsonContent = jsonService.toJson(letterRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/example/letters")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent));

		//TODO FIX
		mockMvc.perform(MockMvcRequestBuilders.get("/example/letters/b"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
}

