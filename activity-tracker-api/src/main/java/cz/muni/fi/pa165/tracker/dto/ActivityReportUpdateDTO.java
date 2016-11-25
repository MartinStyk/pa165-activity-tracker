package cz.muni.fi.pa165.tracker.dto;

/**
 * DTO object for updating activity reports.
 *
 * @author Martin Styk
 * @version 11.11.2016
 */
public class ActivityReportUpdateDTO extends ActivityReportCreateDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ActivityReportUpdateDTO)) {
            return false;
        }
        ActivityReportUpdateDTO reportUpdateDTO = (ActivityReportUpdateDTO) o;
        return super.equals(reportUpdateDTO) && (id != null && id.equals(reportUpdateDTO.getId()));
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ((id == null) ? 0 : id.hashCode());
    }
}
