package com.mycom.realestate.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link CustomerdetailSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CustomerdetailSearchRepositoryMockConfiguration {

    @MockBean
    private CustomerdetailSearchRepository mockCustomerdetailSearchRepository;

}
