package com.mycom.realestate.service.impl;

import com.mycom.realestate.service.CustomerdetailService;
import com.mycom.realestate.domain.Customerdetail;
import com.mycom.realestate.repository.CustomerdetailRepository;
import com.mycom.realestate.repository.search.CustomerdetailSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Customerdetail}.
 */
@Service
@Transactional
public class CustomerdetailServiceImpl implements CustomerdetailService {

    private final Logger log = LoggerFactory.getLogger(CustomerdetailServiceImpl.class);

    private final CustomerdetailRepository customerdetailRepository;

    private final CustomerdetailSearchRepository customerdetailSearchRepository;

    public CustomerdetailServiceImpl(CustomerdetailRepository customerdetailRepository, CustomerdetailSearchRepository customerdetailSearchRepository) {
        this.customerdetailRepository = customerdetailRepository;
        this.customerdetailSearchRepository = customerdetailSearchRepository;
    }

    /**
     * Save a customerdetail.
     *
     * @param customerdetail the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Customerdetail save(Customerdetail customerdetail) {
        log.debug("Request to save Customerdetail : {}", customerdetail);
        Customerdetail result = customerdetailRepository.save(customerdetail);
        customerdetailSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the customerdetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Customerdetail> findAll(Pageable pageable) {
        log.debug("Request to get all Customerdetails");
        return customerdetailRepository.findAll(pageable);
    }


    /**
     * Get one customerdetail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Customerdetail> findOne(Long id) {
        log.debug("Request to get Customerdetail : {}", id);
        return customerdetailRepository.findById(id);
    }

    /**
     * Delete the customerdetail by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customerdetail : {}", id);
        customerdetailRepository.deleteById(id);
        customerdetailSearchRepository.deleteById(id);
    }

    /**
     * Search for the customerdetail corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Customerdetail> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Customerdetails for query {}", query);
        return customerdetailSearchRepository.search(queryStringQuery(query), pageable);    }
}
