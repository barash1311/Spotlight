package com.barash.spotlight.service;

import com.barash.spotlight.dto.ContactRequest;
import com.barash.spotlight.dto.ContactResponse;
import com.barash.spotlight.dto.PageResponse;
import com.barash.spotlight.entity.ContactMessage;
import com.barash.spotlight.exception.ResourceNotFoundException;
import com.barash.spotlight.repository.ContactMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImplementation implements ContactService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactServiceImplementation(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public ContactResponse submitMessage(ContactRequest request) {
        ContactMessage msg = ContactMessage.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        return mapToResponse(contactMessageRepository.save(msg));
    }

    @Override
    public PageResponse<ContactResponse> getMessages(int page, int size) {
        Page<ContactMessage> result = contactMessageRepository
                .findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));

        return PageResponse.<ContactResponse>builder()
                .content(result.getContent().stream().map(this::mapToResponse).toList())
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .last(result.isLast())
                .build();
    }

    @Override
    public ContactResponse markAsRead(Long id) {
        ContactMessage msg = findOrThrow(id);
        msg.setRead(true);
        return mapToResponse(contactMessageRepository.save(msg));
    }

    @Override
    public void deleteMessage(Long id) {
        if (!contactMessageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Message not found with id: " + id);
        }
        contactMessageRepository.deleteById(id);
    }

    @Override
    public long countUnreadMessages() {
        return contactMessageRepository.countByReadFalse();
    }

    private ContactMessage findOrThrow(Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    private ContactResponse mapToResponse(ContactMessage msg) {
        return ContactResponse.builder()
                .id(msg.getId())
                .name(msg.getName())
                .email(msg.getEmail())
                .subject(msg.getSubject())
                .message(msg.getMessage())
                .read(msg.isRead())
                .createdAt(msg.getCreatedAt())
                .build();
    }
}
