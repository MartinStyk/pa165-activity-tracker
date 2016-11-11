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
}
