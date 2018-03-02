package net.worknote.dto;

import net.worknote.domain.User;

public class UserPageDTO {

    private String firstName;

    private String lastName;

    private int yearOfBirth;

    private int monthOfBirth;

    private int dayOfBirth;

    private String country;

    private String city;

    private String job;

    private String locationOfJob;

    private String credo;

    private int rating;

    private String photo;

    public UserPageDTO() {

    }

    public UserPageDTO(String firstName, String lastName,
                       int yearOfBirth, int monthOfBirth,
                       int dayOfBirth, String country, String city,
                       String job, String locationOfJob, String credo,
                       int rating, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.country = country;
        this.city = city;
        this.job = job;
        this.locationOfJob = locationOfJob;
        this.credo = credo;
        this.rating = rating;
        this.photo = photo;
    }
    public UserPageDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.yearOfBirth = user.getYearOfBirth();
        this.monthOfBirth = user.getMonthOfBirth();
        this.dayOfBirth = user.getDayOfBirth();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.job = user.getJob();
        this.locationOfJob = user.getLocationOfJob();
        this.credo = user.getCredo();
        this.rating = user.getRating();
        this.photo = user.getPhoto();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLocationOfJob() {
        return locationOfJob;
    }

    public void setLocationOfJob(String locationOfJob) {
        this.locationOfJob = locationOfJob;
    }

    public String getCredo() {
        return credo;
    }

    public void setCredo(String credo) {
        this.credo = credo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
