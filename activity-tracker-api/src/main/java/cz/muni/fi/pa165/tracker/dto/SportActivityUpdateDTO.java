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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof SportActivityUpdateDTO)) {
            return false;
        }
        SportActivityUpdateDTO activityUpdateDTO = (SportActivityUpdateDTO) o;
        return super.equals(activityUpdateDTO) && (id != null && id.equals(activityUpdateDTO.getId()));
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ((id == null) ? 0 : id.hashCode());
    }
}
