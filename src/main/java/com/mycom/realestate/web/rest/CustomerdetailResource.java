package com.mycom.realestate.web.rest;

import com.mycom.realestate.domain.Customerdetail;
import com.mycom.realestate.service.CustomerdetailService;
import com.mycom.realestate.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.mycom.realestate.domain.Customerdetail}.
 */
@RestController
@RequestMapping("/api")
public class CustomerdetailResource {

    private final Logger log = LoggerFactory.getLogger(CustomerdetailResource.class);

    private static final String ENTITY_NAME = "customerdetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerdetailService customerdetailService;

    public CustomerdetailResource(CustomerdetailService customerdetailService) {
        this.customerdetailService = customerdetailService;
    }

    /**
     * {@code POST  /customerdetails} : Create a new customerdetail.
     *
     * @param customerdetail the customerdetail to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerdetail, or with status {@code 400 (Bad Request)} if the customerdetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customerdetails")
    public ResponseEntity<Customerdetail> createCustomerdetail(@Valid @RequestBody Customerdetail customerdetail) throws URISyntaxException {
        log.debug("REST request to save Customerdetail : {}", customerdetail);
        if (customerdetail.getId() != null) {
            throw new BadRequestAlertException("A new customerdetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Customerdetail result = customerdetailService.save(customerdetail);
        return ResponseEntity.created(new URI("/api/customerdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customerdetails} : Updates an existing customerdetail.
     *
     * @param customerdetail the customerdetail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerdetail,
     * or with status {@code 400 (Bad Request)} if the customerdetail is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerdetail couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customerdetails")
    public ResponseEntity<Customerdetail> updateCustomerdetail(@Valid @RequestBody Customerdetail customerdetail) throws URISyntaxException {
        log.debug("REST request to update Customerdetail : {}", customerdetail);
        if (customerdetail.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Customerdetail result = customerdetailService.save(customerdetail);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerdetail.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customerdetails} : get all the customerdetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerdetails in body.
     */
    @GetMapping("/customerdetails")
    public ResponseEntity<List<Customerdetail>> getAllCustomerdetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Customerdetails");
        Page<Customerdetail> page = customerdetailService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customerdetails/:id} : get the "id" customerdetail.
     *
     * @param id the id of the customerdetail to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerdetail, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customerdetails/{id}")
    public ResponseEntity<Customerdetail> getCustomerdetail(@PathVariable Long id) {
        log.debug("REST request to get Customerdetail : {}", id);
        Optional<Customerdetail> customerdetail = customerdetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerdetail);
    }

    /**
     * {@code DELETE  /customerdetails/:id} : delete the "id" customerdetail.
     *
     * @param id the id of the customerdetail to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customerdetails/{id}")
    public ResponseEntity<Void> deleteCustomerdetail(@PathVariable Long id) {
        log.debug("REST request to delete Customerdetail : {}", id);
        customerdetailService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/customerdetails?query=:query} : search for the customerdetail corresponding
     * to the query.
     *
     * @param query the query of the customerdetail search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/customerdetails")
    public ResponseEntity<List<Customerdetail>> searchCustomerdetails(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of Customerdetails for query {}", query);
        Page<Customerdetail> page = customerdetailService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
