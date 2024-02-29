package com.m2i.TL_Interne_Application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.m2i.TL_Interne_Application.entities.Restaurant;
import com.m2i.TL_Interne_Application.entities.Table;

@SpringBootTest
class TableServiceTest {

    @Autowired
    private TableService tableService;
    
    @Autowired
    private RestaurantService restaurantService;   
    
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
 
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
 
	@BeforeEach
	void setUp() throws Exception {
	}
 
	@AfterEach
	void tearDown() throws Exception {
	}

    @Test
    @Sql("classpath:table_insertion.sql")
    void FindByRestaurant_tableInsertion_ReturnFiveTablesForRestaurantOne() {
       
    	Restaurant restaurant = restaurantService.getById(1);

        List<Table> tables = tableService.findByRestaurant(restaurant);
        assertNotNull(tables);
        
        assertEquals(5, tables.size());
        
    }
    
    @Test
    @Sql("classpath:table_insertion.sql")
    void FindByRestaurant_tableInsertionAndRestaurantId2_ReturnEmptyTablesForRestaurantTwo() {
       
    	Restaurant restaurant = restaurantService.getById(2);

        List<Table> tables = tableService.findByRestaurant(restaurant);
        assertNotNull(tables);
        
        assertEquals(0, tables.size());
        
    }
    
    ///////////// Restaurant et etat 
    
    
    @Test
    @Sql("classpath:table_insertion.sql")
    void findByRestaurantAndEtat_tableInsertionAndValidValues_ReturnThreeTables() {
        
    	Restaurant restaurant = restaurantService.getById(1);
        List<Table> tables = tableService.findByRestaurantAndEtat(restaurant, "Libre");
        assertNotNull(tables);
        assertEquals(3, tables.size());
    }

    @Test
    @Sql("classpath:table_insertion.sql")
    void findByRestaurantAndEtat_tableInsertionAndInvalidValues_ReturnNull() {
    	
    	Restaurant restaurant = restaurantService.getById(1);

        List<Table> tables = tableService.findByRestaurantAndEtat(restaurant, "Disponible");

        assertNull(tables);
    }


}
