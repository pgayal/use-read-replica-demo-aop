package com.example.usereadreplicademoaop.repositories;

import com.example.usereadreplicademoaop.model.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is normal JPA repository supporting both read and with operations
 *
 * @author pgayal
 * created on 03/03/2022
 */
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
