package cz.muni.fi.pa165.tracker.dto;

import java.util.Map;
import java.util.Objects;

/**
 * DTO for statistics of user
 *
 * @author Martin Styk
 * @version 13.11.2016
 */
public class UserStatisticsDTO {

    private UserDTO userDTO;

    private int totalCalories;

    private int caloriesLastWeek;

    private int caloriesLastMonth;

    private int totalSportActivities;

    private int sportActivitiesLastWeek;

    private int sportActivitiesLastMonth;

    private Map<SportActivityDTO, Integer> sportActivities;

    private Map<SportActivityDTO, Integer> caloriesForActivities;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public int getCaloriesLastWeek() {
        return caloriesLastWeek;
    }

    public void setCaloriesLastWeek(int caloriesLastWeek) {
        this.caloriesLastWeek = caloriesLastWeek;
    }

    public int getCaloriesLastMonth() {
        return caloriesLastMonth;
    }

    public void setCaloriesLastMonth(int caloriesLastMonth) {
        this.caloriesLastMonth = caloriesLastMonth;
    }

    public int getTotalSportActivities() {
        return totalSportActivities;
    }

    public void setTotalSportActivities(int totalSportActivities) {
        this.totalSportActivities = totalSportActivities;
    }

    public int getSportActivitiesLastWeek() {
        return sportActivitiesLastWeek;
    }

    public void setSportActivitiesLastWeek(int sportActivitiesLastWeek) {
        this.sportActivitiesLastWeek = sportActivitiesLastWeek;
    }

    public int getSportActivitiesLastMonth() {
        return sportActivitiesLastMonth;
    }

    public void setSportActivitiesLastMonth(int sportActivitiesLastMonth) {
        this.sportActivitiesLastMonth = sportActivitiesLastMonth;
    }

    public Map<SportActivityDTO, Integer> getSportActivities() {
        return sportActivities;
    }

    public void setSportActivities(Map<SportActivityDTO, Integer> sportActivities) {
        this.sportActivities = sportActivities;
    }

    public Map<SportActivityDTO, Integer> getCaloriesForActivities() {
        return caloriesForActivities;
    }

    public void setCaloriesForActivities(Map<SportActivityDTO, Integer> caloriesForActivities) {
        this.caloriesForActivities = caloriesForActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatisticsDTO that = (UserStatisticsDTO) o;

        if (totalCalories != that.totalCalories) return false;
        if (caloriesLastWeek != that.caloriesLastWeek) return false;
        if (caloriesLastMonth != that.caloriesLastMonth) return false;
        if (totalSportActivities != that.totalSportActivities) return false;
        if (sportActivitiesLastWeek != that.sportActivitiesLastWeek) return false;
        if (sportActivitiesLastMonth != that.sportActivitiesLastMonth) return false;
        if (userDTO != null ? !userDTO.equals(that.userDTO) : that.userDTO != null) return false;
        if (sportActivities != null ? !sportActivities.equals(that.sportActivities) : that.sportActivities != null)
            return false;
        return caloriesForActivities != null ? caloriesForActivities.equals(that.caloriesForActivities) :
                that.caloriesForActivities == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(userDTO,
                totalCalories,
                caloriesLastWeek,
                caloriesLastMonth,
                totalSportActivities,
                sportActivitiesLastMonth,
                sportActivitiesLastWeek,
                sportActivities,
                caloriesForActivities);
    }

    @Override
    public String toString() {
        return "UserStatisticsDTO{" +
                "userDTO=" + userDTO +
                ", totalCalories=" + totalCalories +
                ", caloriesLastWeek=" + caloriesLastWeek +
                ", caloriesLastMonth=" + caloriesLastMonth +
                ", totalSportActivities=" + totalSportActivities +
                ", sportActivitiesLastWeek=" + sportActivitiesLastWeek +
                ", sportActivitiesLastMonth=" + sportActivitiesLastMonth +
                ", sportActivities=" + sportActivities +
                ", caloriesForActivities=" + caloriesForActivities +
                '}';
    }
}
