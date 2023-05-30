package com.restful.booker.model;

import java.util.HashMap;

/**
 * Created by Jay Vaghani
 */
public class BookingPojo {
    private String firstName;
    private String lastName;
    private int totalPrice;
    private Boolean depositPaid;
    private HashMap<String, Object> bookingDates;

    public HashMap<String, Object> getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(HashMap<String, Object> bookingId) {
        this.bookingDates = bookingId;
    }

    private String additionalNeed;



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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(Boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public String getAdditionalNeed() {
        return additionalNeed;
    }

    public void setAdditionalNeed(String additionalNeed) {
        this.additionalNeed = additionalNeed;
    }
}
