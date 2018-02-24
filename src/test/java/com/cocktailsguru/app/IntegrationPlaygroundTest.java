package com.cocktailsguru.app;

import com.cocktailsguru.app.cocktail.controller.CocktailController;
import com.cocktailsguru.app.cocktail.domain.Cocktail;
import com.cocktailsguru.app.cocktail.dto.CocktailDetailDto;
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto;
import com.cocktailsguru.app.cocktail.repository.CocktailRepository;
import com.cocktailsguru.app.cocktail.service.CocktailService;
import com.cocktailsguru.app.comment.domain.Comment;
import com.cocktailsguru.app.common.dto.PagingDto;
import com.cocktailsguru.app.health.controller.HealthController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

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
    private CocktailRepository cocktailRepository;

    @Autowired
    private CocktailService cocktailService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();


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
    public void shouldFindFirstCocktailWithouException() {
        assertNotNull(cocktailRepository.findOne(1L));
    }


    @Test
    public void shouldGetCocktailDetail() throws Exception {
        MvcResult result = mockMvc.perform(
                get(CocktailController.COCKTAIL_DETAIL_PATH)
                        .param("id", "1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        CocktailDetailDto responseDto = objectMapper.readValue(responseJson, CocktailDetailDto.class);
        assertNotNull(responseDto);
        assertFalse(responseDto.getIngredientList().isEmpty());
        assertFalse(responseDto.getSimilarCocktailList().isEmpty());

        log.info(responseJson);
    }

    @Test
    public void shouldReturnRequestedCocktailListSize() throws Exception {
        PagingDto requestDto = new PagingDto(4, 12);

        String requestJson = objectWriter.writeValueAsString(requestDto);


        MvcResult result = mockMvc.perform(
                post(CocktailController.COCKTAIL_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        CocktailListResponseDto responseDto = objectMapper.readValue(responseJson, CocktailListResponseDto.class);
        assertEquals(requestDto.getPageSize(), responseDto.getList().size());
        assertEquals(requestDto, responseDto.getPagingInfo());
    }


    @Test
    public void shouldUpdateNumOfFavoritesForCocktailDetail() {
        assertNull(cocktailService.getCocktailDetail(999999L));

        Cocktail margarita = cocktailService.getCocktailDetail(54);

        assertNotNull(margarita);
        assertNotEquals(0, margarita.getNumOfFavorite());


        Cocktail something = cocktailService.getCocktailDetail(16003);
        for(Comment comment : something.getCommentList()) {
            log.info(comment.toString());
        }
    }
}
