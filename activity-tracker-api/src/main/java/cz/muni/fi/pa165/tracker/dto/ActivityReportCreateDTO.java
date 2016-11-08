package cz.muni.fi.pa165.tracker.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO for creating Activity reports.
 *
 * @author Martin Styk
 * @version 07.11.2016
 */
public class ActivityReportCreateDTO {

    @NotNull
    private Long userId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private Long sportActivityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getSportActivityId() {
        return sportActivityId;
    }

    public void setSportActivityId(Long sportActivityId) {
        this.sportActivityId = sportActivityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityReportCreateDTO)) return false;

        ActivityReportCreateDTO that = (ActivityReportCreateDTO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        return sportActivityId != null ? sportActivityId.equals(that.sportActivityId) : that.sportActivityId == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (sportActivityId != null ? sportActivityId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActivityReportCreateDTO{" +
                "userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sportActivityId=" + sportActivityId +
                '}';
    }
}
