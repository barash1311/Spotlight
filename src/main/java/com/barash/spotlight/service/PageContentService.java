package com.barash.spotlight.service;

import com.barash.spotlight.entity.PageContent;
import java.util.List;
import java.util.Map;

public interface PageContentService {
    List<PageContent> getPageContent(String page);
    Map<String, List<PageContent>> getPageContentGrouped(String page);
    PageContent updateOrCreateContent(String page, String section, String key, String value, Integer sortOrder);
    void deleteContent(Long id);
    List<PageContent> getAllContent();
}
