package com.finleash.eserve.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.finleash.eserve.domain.MutualFundNav;

import com.finleash.eserve.repository.MutualFundNavRepository;
import com.finleash.eserve.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MutualFundNav.
 */
@RestController
@RequestMapping("/api")
public class MutualFundNavResource {

    private final Logger log = LoggerFactory.getLogger(MutualFundNavResource.class);
        
    @Inject
    private MutualFundNavRepository mutualFundNavRepository;

    /**
     * POST  /mutual-fund-navs : Create a new mutualFundNav.
     *
     * @param mutualFundNav the mutualFundNav to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mutualFundNav, or with status 400 (Bad Request) if the mutualFundNav has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/mutual-fund-navs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MutualFundNav> createMutualFundNav(@RequestBody MutualFundNav mutualFundNav) throws URISyntaxException {
        log.debug("REST request to save MutualFundNav : {}", mutualFundNav);
        if (mutualFundNav.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("mutualFundNav", "idexists", "A new mutualFundNav cannot already have an ID")).body(null);
        }
        MutualFundNav result = mutualFundNavRepository.save(mutualFundNav);
        return ResponseEntity.created(new URI("/api/mutual-fund-navs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mutualFundNav", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mutual-fund-navs : Updates an existing mutualFundNav.
     *
     * @param mutualFundNav the mutualFundNav to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mutualFundNav,
     * or with status 400 (Bad Request) if the mutualFundNav is not valid,
     * or with status 500 (Internal Server Error) if the mutualFundNav couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/mutual-fund-navs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MutualFundNav> updateMutualFundNav(@RequestBody MutualFundNav mutualFundNav) throws URISyntaxException {
        log.debug("REST request to update MutualFundNav : {}", mutualFundNav);
        if (mutualFundNav.getId() == null) {
            return createMutualFundNav(mutualFundNav);
        }
        MutualFundNav result = mutualFundNavRepository.save(mutualFundNav);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mutualFundNav", mutualFundNav.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mutual-fund-navs : get all the mutualFundNavs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mutualFundNavs in body
     */
    @RequestMapping(value = "/mutual-fund-navs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<MutualFundNav> getAllMutualFundNavs() {
        log.debug("REST request to get all MutualFundNavs");
        List<MutualFundNav> mutualFundNavs = mutualFundNavRepository.findAll();
        return mutualFundNavs;
    }

    /**
     * GET  /mutual-fund-navs/:id : get the "id" mutualFundNav.
     *
     * @param id the id of the mutualFundNav to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mutualFundNav, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/mutual-fund-navs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MutualFundNav> getMutualFundNav(@PathVariable Long id) {
        log.debug("REST request to get MutualFundNav : {}", id);
        MutualFundNav mutualFundNav = mutualFundNavRepository.findOne(id);
        return Optional.ofNullable(mutualFundNav)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /mutual-fund-navs/:id : delete the "id" mutualFundNav.
     *
     * @param id the id of the mutualFundNav to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/mutual-fund-navs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMutualFundNav(@PathVariable Long id) {
        log.debug("REST request to delete MutualFundNav : {}", id);
        mutualFundNavRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mutualFundNav", id.toString())).build();
    }

}
