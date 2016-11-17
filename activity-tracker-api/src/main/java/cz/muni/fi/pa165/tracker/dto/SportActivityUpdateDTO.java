package cz.muni.fi.pa165.tracker.dto;

/**
 * @author Martin Styk
 * @version 17.11.2016
 */
public class SportActivityUpdateDTO extends SportActivityCreateDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
