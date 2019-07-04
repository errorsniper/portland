package com.mycom.realestate.web.rest;

import com.mycom.realestate.RealEstateApp;
import com.mycom.realestate.domain.Customerdetail;
import com.mycom.realestate.repository.CustomerdetailRepository;
import com.mycom.realestate.repository.search.CustomerdetailSearchRepository;
import com.mycom.realestate.service.CustomerdetailService;
import com.mycom.realestate.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.mycom.realestate.web.rest.TestUtil.sameInstant;
import static com.mycom.realestate.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CustomerdetailResource} REST controller.
 */
@SpringBootTest(classes = RealEstateApp.class)
public class CustomerdetailResourceIT {

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE_OF_PLOT = "AAAAAAAAAA";
    private static final String UPDATED_SIZE_OF_PLOT = "BBBBBBBBBB";

    private static final String DEFAULT_CONSTRUCTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONSTRUCTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SOIL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOIL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NO_OF_ROOMS_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_NO_OF_ROOMS_REQUIRED = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EXPECTED_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EXPECTED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUD_GET = "AAAAAAAAAA";
    private static final String UPDATED_BUD_GET = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private CustomerdetailRepository customerdetailRepository;

    @Autowired
    private CustomerdetailService customerdetailService;

    /**
     * This repository is mocked in the com.mycom.realestate.repository.search test package.
     *
     * @see com.mycom.realestate.repository.search.CustomerdetailSearchRepositoryMockConfiguration
     */
    @Autowired
    private CustomerdetailSearchRepository mockCustomerdetailSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCustomerdetailMockMvc;

    private Customerdetail customerdetail;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerdetailResource customerdetailResource = new CustomerdetailResource(customerdetailService);
        this.restCustomerdetailMockMvc = MockMvcBuilders.standaloneSetup(customerdetailResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customerdetail createEntity(EntityManager em) {
        Customerdetail customerdetail = new Customerdetail()
            .serviceType(DEFAULT_SERVICE_TYPE)
            .sizeOfPlot(DEFAULT_SIZE_OF_PLOT)
            .constructionType(DEFAULT_CONSTRUCTION_TYPE)
            .buildingType(DEFAULT_BUILDING_TYPE)
            .soilType(DEFAULT_SOIL_TYPE)
            .noOfRoomsRequired(DEFAULT_NO_OF_ROOMS_REQUIRED)
/*            .expectedEndDate(DEFAULT_EXPECTED_END_DATE)*/
            .budGet(DEFAULT_BUD_GET)
            /*.createdDate(DEFAULT_CREATED_DATE)*/
            .createdBy(DEFAULT_CREATED_BY);
        return customerdetail;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customerdetail createUpdatedEntity(EntityManager em) {
        Customerdetail customerdetail = new Customerdetail()
            .serviceType(UPDATED_SERVICE_TYPE)
            .sizeOfPlot(UPDATED_SIZE_OF_PLOT)
            .constructionType(UPDATED_CONSTRUCTION_TYPE)
            .buildingType(UPDATED_BUILDING_TYPE)
            .soilType(UPDATED_SOIL_TYPE)
            .noOfRoomsRequired(UPDATED_NO_OF_ROOMS_REQUIRED)
/*            .expectedEndDate(UPDATED_EXPECTED_END_DATE)*/
            .budGet(UPDATED_BUD_GET)
/*            .createdDate(UPDATED_CREATED_DATE)*/
            .createdBy(UPDATED_CREATED_BY);
        return customerdetail;
    }

    @BeforeEach
    public void initTest() {
        customerdetail = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerdetail() throws Exception {
        int databaseSizeBeforeCreate = customerdetailRepository.findAll().size();

        // Create the Customerdetail
        restCustomerdetailMockMvc.perform(post("/api/customerdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerdetail)))
            .andExpect(status().isCreated());

        // Validate the Customerdetail in the database
        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Customerdetail testCustomerdetail = customerdetailList.get(customerdetailList.size() - 1);
        assertThat(testCustomerdetail.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testCustomerdetail.getSizeOfPlot()).isEqualTo(DEFAULT_SIZE_OF_PLOT);
        assertThat(testCustomerdetail.getConstructionType()).isEqualTo(DEFAULT_CONSTRUCTION_TYPE);
        assertThat(testCustomerdetail.getBuildingType()).isEqualTo(DEFAULT_BUILDING_TYPE);
        assertThat(testCustomerdetail.getSoilType()).isEqualTo(DEFAULT_SOIL_TYPE);
        assertThat(testCustomerdetail.getNoOfRoomsRequired()).isEqualTo(DEFAULT_NO_OF_ROOMS_REQUIRED);
        assertThat(testCustomerdetail.getExpectedEndDate()).isEqualTo(DEFAULT_EXPECTED_END_DATE);
        assertThat(testCustomerdetail.getBudGet()).isEqualTo(DEFAULT_BUD_GET);
        assertThat(testCustomerdetail.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomerdetail.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);

        // Validate the Customerdetail in Elasticsearch
        verify(mockCustomerdetailSearchRepository, times(1)).save(testCustomerdetail);
    }

    @Test
    @Transactional
    public void createCustomerdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerdetailRepository.findAll().size();

        // Create the Customerdetail with an existing ID
        customerdetail.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerdetailMockMvc.perform(post("/api/customerdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerdetail)))
            .andExpect(status().isBadRequest());

        // Validate the Customerdetail in the database
        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeCreate);

        // Validate the Customerdetail in Elasticsearch
        verify(mockCustomerdetailSearchRepository, times(0)).save(customerdetail);
    }


    @Test
    @Transactional
    public void checkServiceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerdetailRepository.findAll().size();
        // set the field null
        customerdetail.setServiceType(null);

        // Create the Customerdetail, which fails.

        restCustomerdetailMockMvc.perform(post("/api/customerdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerdetail)))
            .andExpect(status().isBadRequest());

        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomerdetails() throws Exception {
        // Initialize the database
        customerdetailRepository.saveAndFlush(customerdetail);

        // Get all the customerdetailList
        restCustomerdetailMockMvc.perform(get("/api/customerdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerdetail.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sizeOfPlot").value(hasItem(DEFAULT_SIZE_OF_PLOT.toString())))
            .andExpect(jsonPath("$.[*].constructionType").value(hasItem(DEFAULT_CONSTRUCTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].buildingType").value(hasItem(DEFAULT_BUILDING_TYPE.toString())))
            .andExpect(jsonPath("$.[*].soilType").value(hasItem(DEFAULT_SOIL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].noOfRoomsRequired").value(hasItem(DEFAULT_NO_OF_ROOMS_REQUIRED.toString())))
            .andExpect(jsonPath("$.[*].expectedEndDate").value(hasItem(sameInstant(DEFAULT_EXPECTED_END_DATE))))
            .andExpect(jsonPath("$.[*].budGet").value(hasItem(DEFAULT_BUD_GET.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomerdetail() throws Exception {
        // Initialize the database
        customerdetailRepository.saveAndFlush(customerdetail);

        // Get the customerdetail
        restCustomerdetailMockMvc.perform(get("/api/customerdetails/{id}", customerdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerdetail.getId().intValue()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.sizeOfPlot").value(DEFAULT_SIZE_OF_PLOT.toString()))
            .andExpect(jsonPath("$.constructionType").value(DEFAULT_CONSTRUCTION_TYPE.toString()))
            .andExpect(jsonPath("$.buildingType").value(DEFAULT_BUILDING_TYPE.toString()))
            .andExpect(jsonPath("$.soilType").value(DEFAULT_SOIL_TYPE.toString()))
            .andExpect(jsonPath("$.noOfRoomsRequired").value(DEFAULT_NO_OF_ROOMS_REQUIRED.toString()))
            .andExpect(jsonPath("$.expectedEndDate").value(sameInstant(DEFAULT_EXPECTED_END_DATE)))
            .andExpect(jsonPath("$.budGet").value(DEFAULT_BUD_GET.toString()))
            .andExpect(jsonPath("$.createdDate").value(sameInstant(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerdetail() throws Exception {
        // Get the customerdetail
        restCustomerdetailMockMvc.perform(get("/api/customerdetails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerdetail() throws Exception {
        // Initialize the database
        customerdetailService.save(customerdetail);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockCustomerdetailSearchRepository);

        int databaseSizeBeforeUpdate = customerdetailRepository.findAll().size();

        // Update the customerdetail
        Customerdetail updatedCustomerdetail = customerdetailRepository.findById(customerdetail.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerdetail are not directly saved in db
        em.detach(updatedCustomerdetail);
        updatedCustomerdetail
            .serviceType(UPDATED_SERVICE_TYPE)
            .sizeOfPlot(UPDATED_SIZE_OF_PLOT)
            .constructionType(UPDATED_CONSTRUCTION_TYPE)
            .buildingType(UPDATED_BUILDING_TYPE)
            .soilType(UPDATED_SOIL_TYPE)
            .noOfRoomsRequired(UPDATED_NO_OF_ROOMS_REQUIRED)
/*            .expectedEndDate(UPDATED_EXPECTED_END_DATE)*/
            .budGet(UPDATED_BUD_GET)
/*            .createdDate(UPDATED_CREATED_DATE)*/
            .createdBy(UPDATED_CREATED_BY);

        restCustomerdetailMockMvc.perform(put("/api/customerdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomerdetail)))
            .andExpect(status().isOk());

        // Validate the Customerdetail in the database
        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeUpdate);
        Customerdetail testCustomerdetail = customerdetailList.get(customerdetailList.size() - 1);
        assertThat(testCustomerdetail.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testCustomerdetail.getSizeOfPlot()).isEqualTo(UPDATED_SIZE_OF_PLOT);
        assertThat(testCustomerdetail.getConstructionType()).isEqualTo(UPDATED_CONSTRUCTION_TYPE);
        assertThat(testCustomerdetail.getBuildingType()).isEqualTo(UPDATED_BUILDING_TYPE);
        assertThat(testCustomerdetail.getSoilType()).isEqualTo(UPDATED_SOIL_TYPE);
        assertThat(testCustomerdetail.getNoOfRoomsRequired()).isEqualTo(UPDATED_NO_OF_ROOMS_REQUIRED);
        assertThat(testCustomerdetail.getExpectedEndDate()).isEqualTo(UPDATED_EXPECTED_END_DATE);
        assertThat(testCustomerdetail.getBudGet()).isEqualTo(UPDATED_BUD_GET);
        assertThat(testCustomerdetail.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomerdetail.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);

        // Validate the Customerdetail in Elasticsearch
        verify(mockCustomerdetailSearchRepository, times(1)).save(testCustomerdetail);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerdetail() throws Exception {
        int databaseSizeBeforeUpdate = customerdetailRepository.findAll().size();

        // Create the Customerdetail

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerdetailMockMvc.perform(put("/api/customerdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerdetail)))
            .andExpect(status().isBadRequest());

        // Validate the Customerdetail in the database
        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Customerdetail in Elasticsearch
        verify(mockCustomerdetailSearchRepository, times(0)).save(customerdetail);
    }

    @Test
    @Transactional
    public void deleteCustomerdetail() throws Exception {
        // Initialize the database
        customerdetailService.save(customerdetail);

        int databaseSizeBeforeDelete = customerdetailRepository.findAll().size();

        // Delete the customerdetail
        restCustomerdetailMockMvc.perform(delete("/api/customerdetails/{id}", customerdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Customerdetail> customerdetailList = customerdetailRepository.findAll();
        assertThat(customerdetailList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Customerdetail in Elasticsearch
        verify(mockCustomerdetailSearchRepository, times(1)).deleteById(customerdetail.getId());
    }

    @Test
    @Transactional
    public void searchCustomerdetail() throws Exception {
        // Initialize the database
        customerdetailService.save(customerdetail);
        when(mockCustomerdetailSearchRepository.search(queryStringQuery("id:" + customerdetail.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(customerdetail), PageRequest.of(0, 1), 1));
        // Search the customerdetail
        restCustomerdetailMockMvc.perform(get("/api/_search/customerdetails?query=id:" + customerdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerdetail.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE)))
            .andExpect(jsonPath("$.[*].sizeOfPlot").value(hasItem(DEFAULT_SIZE_OF_PLOT)))
            .andExpect(jsonPath("$.[*].constructionType").value(hasItem(DEFAULT_CONSTRUCTION_TYPE)))
            .andExpect(jsonPath("$.[*].buildingType").value(hasItem(DEFAULT_BUILDING_TYPE)))
            .andExpect(jsonPath("$.[*].soilType").value(hasItem(DEFAULT_SOIL_TYPE)))
            .andExpect(jsonPath("$.[*].noOfRoomsRequired").value(hasItem(DEFAULT_NO_OF_ROOMS_REQUIRED)))
            .andExpect(jsonPath("$.[*].expectedEndDate").value(hasItem(sameInstant(DEFAULT_EXPECTED_END_DATE))))
            .andExpect(jsonPath("$.[*].budGet").value(hasItem(DEFAULT_BUD_GET)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(sameInstant(DEFAULT_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customerdetail.class);
        Customerdetail customerdetail1 = new Customerdetail();
        customerdetail1.setId(1L);
        Customerdetail customerdetail2 = new Customerdetail();
        customerdetail2.setId(customerdetail1.getId());
        assertThat(customerdetail1).isEqualTo(customerdetail2);
        customerdetail2.setId(2L);
        assertThat(customerdetail1).isNotEqualTo(customerdetail2);
        customerdetail1.setId(null);
        assertThat(customerdetail1).isNotEqualTo(customerdetail2);
    }
}
