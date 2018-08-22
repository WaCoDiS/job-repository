package de.wacodis.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;

/** PaginatedJobResponse */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-08-22T15:27:57.540+02:00[Europe/Berlin]")
public class PaginatedJobResponse extends PaginatedResponse {
    @JsonProperty("data")
    @Valid
    private List<Job> data = new ArrayList<>();

    public PaginatedJobResponse data(List<Job> data) {
        this.data = data;
        return this;
    }

    public PaginatedJobResponse addDataItem(Job dataItem) {
        this.data.add(dataItem);
        return this;
    }

    /**
     * Get data
     *
     * @return data
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public List<Job> getData() {
        return data;
    }

    public void setData(List<Job> data) {
        this.data = data;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaginatedJobResponse paginatedJobResponse = (PaginatedJobResponse) o;
        return Objects.equals(this.data, paginatedJobResponse.data) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaginatedJobResponse {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
