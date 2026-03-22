package com.barash.spotlight.controller;

import com.barash.spotlight.dto.*;
import com.barash.spotlight.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/messages")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin — Messages", description = "Contact message inbox (ADMIN only)")
public class AdminMessageController {

    private final ContactService contactService;

    public AdminMessageController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(summary = "Get paginated inbox (newest first)")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ContactResponse>>> getMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return ResponseEntity.ok(ApiResponse.ok(contactService.getMessages(page, size)));
    }

    @Operation(summary = "Mark a message as read")
    @PatchMapping("/{id}/read")
    public ResponseEntity<ApiResponse<ContactResponse>> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(contactService.markAsRead(id), "Marked as read"));
    }

    @Operation(summary = "Delete a message")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMessage(@PathVariable Long id) {
        contactService.deleteMessage(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Message deleted")
                .build());
    }

    @Operation(summary = "Get unread messages count")
    @GetMapping("/count-unread")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount() {
        return ResponseEntity.ok(ApiResponse.ok(contactService.countUnreadMessages()));
    }
}
