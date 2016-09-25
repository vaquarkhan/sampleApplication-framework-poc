package com.finleash.eserve.repository;

import com.finleash.eserve.domain.MutualFundNav;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MutualFundNav entity.
 */
@SuppressWarnings("unused")
public interface MutualFundNavRepository extends JpaRepository<MutualFundNav,Long> {

}
