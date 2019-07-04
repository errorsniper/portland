package com.mycom.realestate.repository.search;

import com.mycom.realestate.domain.Customerdetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Customerdetail} entity.
 */
public interface CustomerdetailSearchRepository extends ElasticsearchRepository<Customerdetail, Long> {
}
