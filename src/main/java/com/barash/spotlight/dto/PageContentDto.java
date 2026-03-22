package com.barash.spotlight.dto;

public class PageContentDto {
    private Long id;
    private String page;
    private String section;
    private String contentKey;
    private String contentValue;
    private Integer sortOrder;

    public PageContentDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPage() { return page; }
    public void setPage(String page) { this.page = page; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getContentKey() { return contentKey; }
    public void setContentKey(String key) { this.contentKey = key; }
    public String getContentValue() { return contentValue; }
    public void setContentValue(String value) { this.contentValue = value; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer order) { this.sortOrder = order; }

    public static PageContentDtoBuilder builder() { return new PageContentDtoBuilder(); }

    public static class PageContentDtoBuilder {
        private PageContentDto d = new PageContentDto();
        public PageContentDtoBuilder id(Long id) { d.setId(id); return this; }
        public PageContentDtoBuilder page(String p) { d.setPage(p); return this; }
        public PageContentDtoBuilder section(String s) { d.setSection(s); return this; }
        public PageContentDtoBuilder contentKey(String k) { d.setContentKey(k); return this; }
        public PageContentDtoBuilder contentValue(String v) { d.setContentValue(v); return this; }
        public PageContentDtoBuilder sortOrder(Integer o) { d.setSortOrder(o); return this; }
        public PageContentDto build() { return d; }
    }
}
