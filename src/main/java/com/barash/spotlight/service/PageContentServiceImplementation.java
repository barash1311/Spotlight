package com.barash.spotlight.service;

import com.barash.spotlight.entity.PageContent;
import com.barash.spotlight.repository.PageContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageContentServiceImplementation implements PageContentService {

    private final PageContentRepository pageContentRepository;

    public PageContentServiceImplementation(PageContentRepository pageContentRepository) {
        this.pageContentRepository = pageContentRepository;
    }

    @Override
    public List<PageContent> getPageContent(String page) {
        return pageContentRepository.findByPageOrderBySectionAscSortOrderAsc(page.toUpperCase());
    }

    @Override
    public Map<String, List<PageContent>> getPageContentGrouped(String page) {
        List<PageContent> content = getPageContent(page);
        return content.stream().collect(Collectors.groupingBy(PageContent::getSection));
    }

    @Override
    @Transactional
    public PageContent updateOrCreateContent(String page, String section, String key, String value, Integer sortOrder) {
        Optional<PageContent> existing = pageContentRepository.findByPageAndSectionAndContentKey(
                page.toUpperCase(), section.toUpperCase(), key.toUpperCase());

        PageContent content;
        if (existing.isPresent()) {
            content = existing.get();
            content.setContentValue(value);
            content.setSortOrder(sortOrder);
        } else {
            content = PageContent.builder()
                    .page(page.toUpperCase())
                    .section(section.toUpperCase())
                    .contentKey(key.toUpperCase())
                    .contentValue(value)
                    .sortOrder(sortOrder)
                    .build();
        }
        return pageContentRepository.save(content);
    }

    @Override
    @Transactional
    public void deleteContent(Long id) {
        pageContentRepository.deleteById(id);
    }

    @Override
    public List<PageContent> getAllContent() {
        return pageContentRepository.findAll();
    }
}
