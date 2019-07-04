package com.mycom.realestate.service;

import com.mycom.realestate.domain.Customerdetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Customerdetail}.
 */
public interface CustomerdetailService {

    /**
     * Save a customerdetail.
     *
     * @param customerdetail the entity to save.
     * @return the persisted entity.
     */
    Customerdetail save(Customerdetail customerdetail);

    /**
     * Get all the customerdetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Customerdetail> findAll(Pageable pageable);


    /**
     * Get the "id" customerdetail.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Customerdetail> findOne(Long id);

    /**
     * Delete the "id" customerdetail.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the customerdetail corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Customerdetail> search(String query, Pageable pageable);
}
