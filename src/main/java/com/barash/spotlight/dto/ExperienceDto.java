package com.barash.spotlight.dto;

public class ExperienceDto {
    private String id;
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    private boolean current;
    private String description;

    public ExperienceDto() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public boolean isCurrent() { return current; }
    public void setCurrent(boolean current) { this.current = current; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public static ExperienceDtoBuilder builder() { return new ExperienceDtoBuilder(); }

    public static class ExperienceDtoBuilder {
        private ExperienceDto d = new ExperienceDto();
        public ExperienceDtoBuilder id(String id) { d.setId(id); return this; }
        public ExperienceDtoBuilder company(String c) { d.setCompany(c); return this; }
        public ExperienceDtoBuilder position(String p) { d.setPosition(p); return this; }
        public ExperienceDtoBuilder startDate(String s) { d.setStartDate(s); return this; }
        public ExperienceDtoBuilder endDate(String e) { d.setEndDate(e); return this; }
        public ExperienceDtoBuilder current(boolean c) { d.setCurrent(c); return this; }
        public ExperienceDtoBuilder description(String desc) { d.setDescription(desc); return this; }
        public ExperienceDto build() { return d; }
    }
}
