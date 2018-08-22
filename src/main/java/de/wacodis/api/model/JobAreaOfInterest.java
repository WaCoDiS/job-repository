package de.wacodis.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * JobAreaOfInterest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-20T16:26:51.356+02:00[Europe/Berlin]")
@UserDefinedType("areaOfInterest")
public class JobAreaOfInterest {

    public static final String TABLE_NAME = "areasOfInterest";

    @JsonProperty("extent")
    @Valid
    @Column
    private List<Float> extent = null;

    public JobAreaOfInterest extent(List<Float> extent) {
        this.extent = extent;
        return this;
    }

    public JobAreaOfInterest addExtentItem(Float extentItem) {
        if (this.extent == null) {
            this.extent = new ArrayList<>();
        }
        this.extent.add(extentItem);
        return this;
    }

    /**
     * the coordinates, using EPSG:4326, (in analogy to GeoJSON bbox) in the
     * order \"southwesterly point followed by more northeasterly point\".
     * Schema is [minLon, minLat, maxLon, maxLat]
     *
     * @return extent
     *
     */
    @ApiModelProperty(value = "the coordinates, using EPSG:4326, (in analogy to GeoJSON bbox) in the order \"southwesterly point followed by more northeasterly point\". Schema is [minLon, minLat, maxLon, maxLat] ")

    @Size(min = 4, max = 4)
    public List<Float> getExtent() {
        return extent;
    }

    public void setExtent(List<Float> extent) {
        this.extent = extent;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobAreaOfInterest jobAreaOfInterest = (JobAreaOfInterest) o;
        return Objects.equals(this.extent, jobAreaOfInterest.extent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobAreaOfInterest {\n");

        sb.append("    extent: ").append(toIndentedString(extent)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
