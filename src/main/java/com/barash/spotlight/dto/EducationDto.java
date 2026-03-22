package com.barash.spotlight.dto;

public class EducationDto {
    private String id;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;

    public EducationDto() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getInstitution() { return institution; }
    public void setInstitution(String inst) { this.institution = inst; }
    public String getDegree() { return degree; }
    public void setDegree(String deg) { this.degree = deg; }
    public String getFieldOfStudy() { return fieldOfStudy; }
    public void setFieldOfStudy(String field) { this.fieldOfStudy = field; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String start) { this.startDate = start; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String end) { this.endDate = end; }

    public static EducationDtoBuilder builder() { return new EducationDtoBuilder(); }

    public static class EducationDtoBuilder {
        private EducationDto d = new EducationDto();
        public EducationDtoBuilder id(String id) { d.setId(id); return this; }
        public EducationDtoBuilder institution(String i) { d.setInstitution(i); return this; }
        public EducationDtoBuilder degree(String deg) { d.setDegree(deg); return this; }
        public EducationDtoBuilder fieldOfStudy(String field) { d.setFieldOfStudy(field); return this; }
        public EducationDtoBuilder startDate(String s) { d.setStartDate(s); return this; }
        public EducationDtoBuilder endDate(String e) { d.setEndDate(e); return this; }
        public EducationDto build() { return d; }
    }
}
