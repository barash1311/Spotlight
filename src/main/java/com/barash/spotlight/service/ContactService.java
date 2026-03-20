package com.barash.spotlight.service;

import com.barash.spotlight.dto.ContactRequest;
import com.barash.spotlight.dto.ContactResponse;
import com.barash.spotlight.dto.PageResponse;

public interface ContactService {
    ContactResponse          submitMessage(ContactRequest request);
    PageResponse<ContactResponse> getMessages(int page, int size);
    ContactResponse          markAsRead(Long id);
    void                     deleteMessage(Long id);
}
