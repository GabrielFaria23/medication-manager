package com.example.demo.dto;

import java.time.LocalDateTime;

public class BaseModelDto {

    private Boolean activated = true;

    private Boolean deleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdate;

    public BaseModelDto(Boolean activated, Boolean deleted, LocalDateTime createdAt, LocalDateTime lastUpdate) {
        this.activated = activated;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public BaseModelDto() {
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
