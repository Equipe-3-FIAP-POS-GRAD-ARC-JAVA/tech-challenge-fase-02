package br.com.fiap.challenge.restaurant.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "opening_hours")
public class OpeningHours extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday", nullable = false, length = 10)
    private DayOfWeek weekday;

    @Column(name = "opens_at")
    private LocalTime opensAt;

    @Column(name = "closes_at")
    private LocalTime closesAt;

    @Column(name = "is_closed", nullable = false)
    private boolean isClosed;

    protected OpeningHours() {
    }

    public OpeningHours(Restaurant restaurant, DayOfWeek weekday,
                        LocalTime opensAt, LocalTime closesAt, boolean isClosed) {
        this.restaurant = restaurant;
        this.weekday = weekday;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.isClosed = isClosed;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public DayOfWeek getWeekday() {
        return weekday;
    }

    public void setWeekday(DayOfWeek weekday) {
        this.weekday = weekday;
    }

    public LocalTime getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(LocalTime opensAt) {
        this.opensAt = opensAt;
    }

    public LocalTime getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(LocalTime closesAt) {
        this.closesAt = closesAt;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
