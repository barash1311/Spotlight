package com.barash.spotlight.controller;

import com.barash.spotlight.dto.ApiResponse;
import com.barash.spotlight.entity.PageContent;
import com.barash.spotlight.service.PageContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
public class PublicPageContentController {

    private final PageContentService pageContentService;

    public PublicPageContentController(PageContentService pageContentService) {
        this.pageContentService = pageContentService;
    }

    @GetMapping("/{page}")
    public ResponseEntity<ApiResponse<Map<String, List<PageContent>>>> getByPage(@PathVariable String page) {
        return ResponseEntity.ok(ApiResponse.ok(pageContentService.getPageContentGrouped(page)));
    }
}
