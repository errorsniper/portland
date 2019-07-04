package com.mycom.realestate.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Customerdetail.
 */
@Entity
@Table(name = "customer_detail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "customerdetail")
public class Customerdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "size_of_plot")
    private String sizeOfPlot;

    @Column(name = "construction_type")
    private String constructionType;

    @Column(name = "building_type")
    private String buildingType;

    @Column(name = "soil_type")
    private String soilType;

    @Column(name = "no_of_rooms_required")
    private String noOfRoomsRequired;

    @Column(name = "expected_end_date")
    private LocalDateTime expectedEndDate;

    @Column(name = "bud_get")
    private String budGet;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Customerdetail serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSizeOfPlot() {
        return sizeOfPlot;
    }

    public Customerdetail sizeOfPlot(String sizeOfPlot) {
        this.sizeOfPlot = sizeOfPlot;
        return this;
    }

    public void setSizeOfPlot(String sizeOfPlot) {
        this.sizeOfPlot = sizeOfPlot;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public Customerdetail constructionType(String constructionType) {
        this.constructionType = constructionType;
        return this;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public Customerdetail buildingType(String buildingType) {
        this.buildingType = buildingType;
        return this;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getSoilType() {
        return soilType;
    }

    public Customerdetail soilType(String soilType) {
        this.soilType = soilType;
        return this;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getNoOfRoomsRequired() {
        return noOfRoomsRequired;
    }

    public Customerdetail noOfRoomsRequired(String noOfRoomsRequired) {
        this.noOfRoomsRequired = noOfRoomsRequired;
        return this;
    }

    public void setNoOfRoomsRequired(String noOfRoomsRequired) {
        this.noOfRoomsRequired = noOfRoomsRequired;
    }


    public LocalDateTime getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDateTime expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getBudGet() {
        return budGet;
    }

    public Customerdetail budGet(String budGet) {
        this.budGet = budGet;
        return this;
    }

    public void setBudGet(String budGet) {
        this.budGet = budGet;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Customerdetail createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customerdetail)) {
            return false;
        }
        return id != null && id.equals(((Customerdetail) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customerdetail{" +
            "id=" + getId() +
            ", serviceType='" + getServiceType() + "'" +
            ", sizeOfPlot='" + getSizeOfPlot() + "'" +
            ", constructionType='" + getConstructionType() + "'" +
            ", buildingType='" + getBuildingType() + "'" +
            ", soilType='" + getSoilType() + "'" +
            ", noOfRoomsRequired='" + getNoOfRoomsRequired() + "'" +
            ", expectedEndDate='" + getExpectedEndDate() + "'" +
            ", budGet='" + getBudGet() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
