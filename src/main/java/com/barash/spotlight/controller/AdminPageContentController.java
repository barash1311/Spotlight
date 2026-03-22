package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.entity.PageContent;
import com.barash.spotlight.service.PageContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/content")
public class AdminPageContentController {

    private final PageContentService pageContentService;

    public AdminPageContentController(PageContentService pageContentService) {
        this.pageContentService = pageContentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PageContent>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(pageContentService.getAllContent()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PageContent>> createOrUpdate(@RequestBody PageContent req) {
        PageContent saved = pageContentService.updateOrCreateContent(
            req.getPage(), req.getSection(), req.getContentKey(), req.getContentValue(), req.getSortOrder());
        return ResponseEntity.ok(ApiResponse.ok(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        pageContentService.deleteContent(id);
        return ResponseEntity.ok(ApiResponse.ok("Content deleted successfully"));
    }
}
