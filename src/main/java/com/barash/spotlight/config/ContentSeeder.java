package com.barash.spotlight.config;

import com.barash.spotlight.entity.PageContent;
import com.barash.spotlight.repository.PageContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ContentSeeder implements CommandLineRunner {

    private final PageContentRepository repository;

    public ContentSeeder(PageContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        List<PageContent> initialContent = Arrays.asList(
            // HOME PAGE HERO
            create("HOME", "HERO", "STATUS", "System Online", 0),
            create("HOME", "HERO", "AFFILIATION", "CUSAT // CSE '26", 1),
            create("HOME", "HERO", "NAME", "Barash Sharma", 2),
            
            // HOME PAGE ROLES (List)
            create("HOME", "ROLES", "VAL", "Software Engineer", 0),
            create("HOME", "ROLES", "VAL", "Fullstack Developer", 1),
            create("HOME", "ROLES", "VAL", "System Architect", 2),
            create("HOME", "ROLES", "VAL", "API Whisperer", 3),
            create("HOME", "ROLES", "VAL", "Solution Architect", 4),
            create("HOME", "ROLES", "VAL", "Creative Coder", 5),

            // CONTACT SECTION
            create("HOME", "CONTACT_CTA", "STATUS", "[ System Status: Accepting Connections ]", 0),
            create("HOME", "CONTACT_CTA", "TITLE", "Initiate Contact", 1),
            create("HOME", "CONTACT_CTA", "BUTTON", "Deploy Message", 2)
        );

        repository.saveAll(initialContent);
    }

    private PageContent create(String page, String section, String key, String value, int order) {
        return PageContent.builder()
                .page(page)
                .section(section)
                .contentKey(key)
                .contentValue(value)
                .sortOrder(order)
                .build();
    }
}
