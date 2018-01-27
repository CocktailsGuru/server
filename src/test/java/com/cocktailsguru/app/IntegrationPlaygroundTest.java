package com.cocktailsguru.app;

import com.cocktailsguru.app.cocktail.controller.CocktailController;
import com.cocktailsguru.app.cocktail.domain.Cocktail;
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType;
import com.cocktailsguru.app.ingredient.domain.IngredientType;
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto;
import com.cocktailsguru.app.cocktail.repository.CocktailRepository;
import com.cocktailsguru.app.ingredient.repository.AlcoIngredientRepository;
import com.cocktailsguru.app.ingredient.repository.IngredientTypeRepository;
import com.cocktailsguru.app.ingredient.repository.NonAlcoIngredientRepository;
import com.cocktailsguru.app.cocktail.service.CocktailService;
import com.cocktailsguru.app.common.dto.PagingDto;
import com.cocktailsguru.app.health.controller.HealthController;
import com.cocktailsguru.app.user.domain.UserFavorite;
import com.cocktailsguru.app.user.repository.UserRepository;
import com.cocktailsguru.app.user.service.UserFavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private WebApplicationContext wac;

    @Autowired
    private IngredientTypeRepository ingredientTypeRepository;

    @Autowired
    private NonAlcoIngredientRepository nonAlcoIngredientRepo;

    @Autowired
    private AlcoIngredientRepository alcoIngredientRepo;

    @Autowired
    private CocktailRepository cocktailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFavoriteService userFavoriteService;

    @Autowired
    private CocktailService cocktailService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }


    @Test
    public void shouldGetHealthResponse() throws Exception {
        MvcResult result = mockMvc.perform(
                get(HealthController.HEALTH_PATH)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
    }


    @Test
    public void shouldFindFirstIngredientTypeWithoutException() {
        IngredientType one = ingredientTypeRepository.findOne(1);
        assertNotNull(one);
    }


    @Test
    public void shouldFindIngredientsWithoutException() {
        assertNotNull(nonAlcoIngredientRepo.findOne(1001L));
        assertNotNull(alcoIngredientRepo.findOne(1L));

    }


    @Test
    public void shouldFindFirstCocktailWithouException() {
        assertNotNull(cocktailRepository.findOne(1L));
    }


    @Test
    public void userRepositoryShouldRunWithoutException() {
        assertNotNull(userRepository.findAll());
    }


    @Test
    public void shouldGetCocktailDetail() throws Exception {
        MvcResult result = mockMvc.perform(
                get(CocktailController.COCKTAIL_DETAIL_PATH)
                        .param("id", "1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void shouldReturnRequestedCocktailListSize() throws Exception {
        PagingDto requestDto = new PagingDto(4, 12);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(requestDto);


        MvcResult result = mockMvc.perform(
                post(CocktailController.COCKTAIL_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        CocktailListResponseDto responseDto = objectMapper.readValue(responseJson, CocktailListResponseDto.class);
        assertEquals(requestDto.getPageSize(), responseDto.getCocktailList().size());
        assertEquals(requestDto, responseDto.getPagingInfo());
    }


    @Test
    public void shouldReturnNotNullListOfFavorite() {
        List<UserFavorite> favoriteObjects = userFavoriteService.getFavoriteObjects(CocktailObjectType.COCKTAIL, 54L);
        assertFalse(favoriteObjects.isEmpty());
    }


    @Test
    public void shouldUpdateNumOfFavoritesForCocktailDetail() {
        assertNull(cocktailService.getCocktailDetail(999999L));

        Cocktail margarita = cocktailService.getCocktailDetail(54);
        assertNotNull(margarita);
        assertNotEquals(0, margarita.getNumOfFavorite());
    }
}
