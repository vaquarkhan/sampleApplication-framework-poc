package com.finleash.eserve.web.rest;

import com.finleash.eserve.SampleApplicationApp;

import com.finleash.eserve.domain.MutualFundNav;
import com.finleash.eserve.repository.MutualFundNavRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MutualFundNavResource REST controller.
 *
 * @see MutualFundNavResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplicationApp.class)
public class MutualFundNavResourceIntTest {

    private static final String DEFAULT_SCHEME_CODE = "AAAAA";
    private static final String UPDATED_SCHEME_CODE = "BBBBB";
    private static final String DEFAULT_ISIN_DIV_PAYOUT_ISIN_GROWTH = "AAAAA";
    private static final String UPDATED_ISIN_DIV_PAYOUT_ISIN_GROWTH = "BBBBB";
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";
    private static final String DEFAULT_SCHEME_NAME = "AAAAA";
    private static final String UPDATED_SCHEME_NAME = "BBBBB";
    private static final String DEFAULT_NET_ASSET_VALUE = "AAAAA";
    private static final String UPDATED_NET_ASSET_VALUE = "BBBBB";
    private static final String DEFAULT_REPURCHASE_PRICE = "AAAAA";
    private static final String UPDATED_REPURCHASE_PRICE = "BBBBB";
    private static final String DEFAULT_SALE_PRICE = "AAAAA";
    private static final String UPDATED_SALE_PRICE = "BBBBB";
    private static final String DEFAULT_DATE = "AAAAA";
    private static final String UPDATED_DATE = "BBBBB";
    private static final String DEFAULT_UPDATED_DATE = "AAAAA";
    private static final String UPDATED_UPDATED_DATE = "BBBBB";

    @Inject
    private MutualFundNavRepository mutualFundNavRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restMutualFundNavMockMvc;

    private MutualFundNav mutualFundNav;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MutualFundNavResource mutualFundNavResource = new MutualFundNavResource();
        ReflectionTestUtils.setField(mutualFundNavResource, "mutualFundNavRepository", mutualFundNavRepository);
        this.restMutualFundNavMockMvc = MockMvcBuilders.standaloneSetup(mutualFundNavResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MutualFundNav createEntity(EntityManager em) {
        MutualFundNav mutualFundNav = new MutualFundNav()
                .schemeCode(DEFAULT_SCHEME_CODE)
                .isinDivPayoutIsinGrowth(DEFAULT_ISIN_DIV_PAYOUT_ISIN_GROWTH)
                .status(DEFAULT_STATUS)
                .schemeName(DEFAULT_SCHEME_NAME)
                .netAssetValue(DEFAULT_NET_ASSET_VALUE)
                .repurchasePrice(DEFAULT_REPURCHASE_PRICE)
                .salePrice(DEFAULT_SALE_PRICE)
                .date(DEFAULT_DATE)
                .updatedDate(DEFAULT_UPDATED_DATE);
        return mutualFundNav;
    }

    @Before
    public void initTest() {
        mutualFundNav = createEntity(em);
    }

    @Test
    @Transactional
    public void createMutualFundNav() throws Exception {
        int databaseSizeBeforeCreate = mutualFundNavRepository.findAll().size();

        // Create the MutualFundNav

        restMutualFundNavMockMvc.perform(post("/api/mutual-fund-navs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mutualFundNav)))
                .andExpect(status().isCreated());

        // Validate the MutualFundNav in the database
        List<MutualFundNav> mutualFundNavs = mutualFundNavRepository.findAll();
        assertThat(mutualFundNavs).hasSize(databaseSizeBeforeCreate + 1);
        MutualFundNav testMutualFundNav = mutualFundNavs.get(mutualFundNavs.size() - 1);
        assertThat(testMutualFundNav.getSchemeCode()).isEqualTo(DEFAULT_SCHEME_CODE);
        assertThat(testMutualFundNav.getIsinDivPayoutIsinGrowth()).isEqualTo(DEFAULT_ISIN_DIV_PAYOUT_ISIN_GROWTH);
        assertThat(testMutualFundNav.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMutualFundNav.getSchemeName()).isEqualTo(DEFAULT_SCHEME_NAME);
        assertThat(testMutualFundNav.getNetAssetValue()).isEqualTo(DEFAULT_NET_ASSET_VALUE);
        assertThat(testMutualFundNav.getRepurchasePrice()).isEqualTo(DEFAULT_REPURCHASE_PRICE);
        assertThat(testMutualFundNav.getSalePrice()).isEqualTo(DEFAULT_SALE_PRICE);
        assertThat(testMutualFundNav.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testMutualFundNav.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void getAllMutualFundNavs() throws Exception {
        // Initialize the database
        mutualFundNavRepository.saveAndFlush(mutualFundNav);

        // Get all the mutualFundNavs
        restMutualFundNavMockMvc.perform(get("/api/mutual-fund-navs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(mutualFundNav.getId().intValue())))
                .andExpect(jsonPath("$.[*].schemeCode").value(hasItem(DEFAULT_SCHEME_CODE.toString())))
                .andExpect(jsonPath("$.[*].isinDivPayoutIsinGrowth").value(hasItem(DEFAULT_ISIN_DIV_PAYOUT_ISIN_GROWTH.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].schemeName").value(hasItem(DEFAULT_SCHEME_NAME.toString())))
                .andExpect(jsonPath("$.[*].netAssetValue").value(hasItem(DEFAULT_NET_ASSET_VALUE.toString())))
                .andExpect(jsonPath("$.[*].repurchasePrice").value(hasItem(DEFAULT_REPURCHASE_PRICE.toString())))
                .andExpect(jsonPath("$.[*].salePrice").value(hasItem(DEFAULT_SALE_PRICE.toString())))
                .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
                .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getMutualFundNav() throws Exception {
        // Initialize the database
        mutualFundNavRepository.saveAndFlush(mutualFundNav);

        // Get the mutualFundNav
        restMutualFundNavMockMvc.perform(get("/api/mutual-fund-navs/{id}", mutualFundNav.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mutualFundNav.getId().intValue()))
            .andExpect(jsonPath("$.schemeCode").value(DEFAULT_SCHEME_CODE.toString()))
            .andExpect(jsonPath("$.isinDivPayoutIsinGrowth").value(DEFAULT_ISIN_DIV_PAYOUT_ISIN_GROWTH.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.schemeName").value(DEFAULT_SCHEME_NAME.toString()))
            .andExpect(jsonPath("$.netAssetValue").value(DEFAULT_NET_ASSET_VALUE.toString()))
            .andExpect(jsonPath("$.repurchasePrice").value(DEFAULT_REPURCHASE_PRICE.toString()))
            .andExpect(jsonPath("$.salePrice").value(DEFAULT_SALE_PRICE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMutualFundNav() throws Exception {
        // Get the mutualFundNav
        restMutualFundNavMockMvc.perform(get("/api/mutual-fund-navs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMutualFundNav() throws Exception {
        // Initialize the database
        mutualFundNavRepository.saveAndFlush(mutualFundNav);
        int databaseSizeBeforeUpdate = mutualFundNavRepository.findAll().size();

        // Update the mutualFundNav
        MutualFundNav updatedMutualFundNav = mutualFundNavRepository.findOne(mutualFundNav.getId());
        updatedMutualFundNav
                .schemeCode(UPDATED_SCHEME_CODE)
                .isinDivPayoutIsinGrowth(UPDATED_ISIN_DIV_PAYOUT_ISIN_GROWTH)
                .status(UPDATED_STATUS)
                .schemeName(UPDATED_SCHEME_NAME)
                .netAssetValue(UPDATED_NET_ASSET_VALUE)
                .repurchasePrice(UPDATED_REPURCHASE_PRICE)
                .salePrice(UPDATED_SALE_PRICE)
                .date(UPDATED_DATE)
                .updatedDate(UPDATED_UPDATED_DATE);

        restMutualFundNavMockMvc.perform(put("/api/mutual-fund-navs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedMutualFundNav)))
                .andExpect(status().isOk());

        // Validate the MutualFundNav in the database
        List<MutualFundNav> mutualFundNavs = mutualFundNavRepository.findAll();
        assertThat(mutualFundNavs).hasSize(databaseSizeBeforeUpdate);
        MutualFundNav testMutualFundNav = mutualFundNavs.get(mutualFundNavs.size() - 1);
        assertThat(testMutualFundNav.getSchemeCode()).isEqualTo(UPDATED_SCHEME_CODE);
        assertThat(testMutualFundNav.getIsinDivPayoutIsinGrowth()).isEqualTo(UPDATED_ISIN_DIV_PAYOUT_ISIN_GROWTH);
        assertThat(testMutualFundNav.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMutualFundNav.getSchemeName()).isEqualTo(UPDATED_SCHEME_NAME);
        assertThat(testMutualFundNav.getNetAssetValue()).isEqualTo(UPDATED_NET_ASSET_VALUE);
        assertThat(testMutualFundNav.getRepurchasePrice()).isEqualTo(UPDATED_REPURCHASE_PRICE);
        assertThat(testMutualFundNav.getSalePrice()).isEqualTo(UPDATED_SALE_PRICE);
        assertThat(testMutualFundNav.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testMutualFundNav.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void deleteMutualFundNav() throws Exception {
        // Initialize the database
        mutualFundNavRepository.saveAndFlush(mutualFundNav);
        int databaseSizeBeforeDelete = mutualFundNavRepository.findAll().size();

        // Get the mutualFundNav
        restMutualFundNavMockMvc.perform(delete("/api/mutual-fund-navs/{id}", mutualFundNav.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MutualFundNav> mutualFundNavs = mutualFundNavRepository.findAll();
        assertThat(mutualFundNavs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
