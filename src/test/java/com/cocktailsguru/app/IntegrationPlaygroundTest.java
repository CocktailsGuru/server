package com.cocktailsguru.app;

import com.cocktailsguru.app.cocktail.domain.Cocktail;
import com.cocktailsguru.app.cocktail.domain.ingredient.IngredientType;
import com.cocktailsguru.app.cocktail.domain.ingredient.NonAlcoIngredient;
import com.cocktailsguru.app.cocktail.repository.CocktailRepository;
import com.cocktailsguru.app.cocktail.repository.ingredient.AlcoIngredientRepository;
import com.cocktailsguru.app.cocktail.repository.ingredient.IngredientTypeRepository;
import com.cocktailsguru.app.cocktail.repository.ingredient.NonAlcoIngredientRepository;
import com.cocktailsguru.app.health.controller.HealthController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {IntegrationTestApp.class})
@WebAppConfiguration
@Slf4j
@Transactional
public class IntegrationPlaygroundTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private IngredientTypeRepository ingredientTypeRepository;
	
	
	@Autowired
	private NonAlcoIngredientRepository nonAlcoIngredientRepo;
	
	@Autowired
	private AlcoIngredientRepository alcoIngredientRepo;
	
	@Autowired
	private CocktailRepository cocktailRepository;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
			.build();
	}
	
	
	@Test
	@Ignore
	public void createIntegrationDepTask() throws Exception {
		MvcResult result = mockMvc.perform(
			get(HealthController.HEALTH_PATH)
				.contentType(APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andReturn();
		log.info(result.getResponse().getContentAsString());
	}
	
	
	@Test
	public void name() throws Exception {
		IngredientType one = ingredientTypeRepository.findOne(1);
		log.info(one.toString());
	}
	
	
	@Test
	public void namee() throws Exception {
		NonAlcoIngredient one = nonAlcoIngredientRepo.findOne(1001L);
		log.info(one.toString());
		
		
		log.info(alcoIngredientRepo.findOne(1L).toString());
		
	}
	
	
	@Test
	public void dsadsa() throws Exception {
		Cocktail one = cocktailRepository.findOne(1L);
		log.info(cocktailRepository.findOne(1l).toString());
	}
}
