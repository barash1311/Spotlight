package com.barash.spotlight.repository;

import com.barash.spotlight.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    /** Admin inbox — newest first, paginated */
    Page<ContactMessage> findAllByOrderByCreatedAtDesc(Pageable pageable);

    /** Count of unread messages for the admin dashboard badge */
    long countByReadFalse();
}
