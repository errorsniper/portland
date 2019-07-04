package com.mycom.realestate.repository;

import com.mycom.realestate.domain.Customerdetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Customerdetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerdetailRepository extends JpaRepository<Customerdetail, Long> {

}
