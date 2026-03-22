package com.barash.spotlight.repository;

import com.barash.spotlight.entity.PageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PageContentRepository extends JpaRepository<PageContent, Long> {

    List<PageContent> findByPageOrderBySectionAscSortOrderAsc(String page);

    List<PageContent> findByPageAndSectionOrderBySortOrderAsc(String page, String section);

    Optional<PageContent> findByPageAndSectionAndContentKey(String page, String section, String contentKey);
}
