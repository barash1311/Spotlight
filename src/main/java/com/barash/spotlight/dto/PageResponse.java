package com.barash.spotlight.dto;

import java.util.List;

public class PageResponse<T> {

    private List<T> content;
    private int     page;
    private int     size;
    private long    totalElements;
    private int     totalPages;
    private boolean last;

    public PageResponse() {}

    public List<T> getContent() { return content; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isLast() { return last; }

    public static <T> PageResponseBuilder<T> builder() {
        return new PageResponseBuilder<>();
    }

    public static class PageResponseBuilder<T> {
        private List<T> content;
        private int     page;
        private int     size;
        private long    totalElements;
        private int     totalPages;
        private boolean last;

        public PageResponseBuilder<T> content(List<T> content) { this.content = content; return this; }
        public PageResponseBuilder<T> page(int page) { this.page = page; return this; }
        public PageResponseBuilder<T> size(int size) { this.size = size; return this; }
        public PageResponseBuilder<T> totalElements(long totalElements) { this.totalElements = totalElements; return this; }
        public PageResponseBuilder<T> totalPages(int totalPages) { this.totalPages = totalPages; return this; }
        public PageResponseBuilder<T> last(boolean last) { this.last = last; return this; }

        public PageResponse<T> build() {
            PageResponse<T> resp = new PageResponse<>();
            resp.content = this.content;
            resp.page = this.page;
            resp.size = this.size;
            resp.totalElements = this.totalElements;
            resp.totalPages = this.totalPages;
            resp.last = this.last;
            return resp;
        }
    }
}
