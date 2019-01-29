package de.wacodis.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;

/** PaginatedWacodisJobDefinitionResponse */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2019-01-29T11:23:45.055+01:00[Europe/Berlin]")
public class PaginatedWacodisJobDefinitionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("data")
    @Valid
    private List<WacodisJobDefinition> data = new ArrayList<WacodisJobDefinition>();

    public PaginatedWacodisJobDefinitionResponse page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Get page
     *
     * @return page
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public PaginatedWacodisJobDefinitionResponse size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     *
     * @return size
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PaginatedWacodisJobDefinitionResponse total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * Get total
     *
     * @return total
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public PaginatedWacodisJobDefinitionResponse data(List<WacodisJobDefinition> data) {
        this.data = data;
        return this;
    }

    public PaginatedWacodisJobDefinitionResponse addDataItem(WacodisJobDefinition dataItem) {
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
    public List<WacodisJobDefinition> getData() {
        return data;
    }

    public void setData(List<WacodisJobDefinition> data) {
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
        PaginatedWacodisJobDefinitionResponse paginatedWacodisJobDefinitionResponse =
                (PaginatedWacodisJobDefinitionResponse) o;
        return Objects.equals(this.page, paginatedWacodisJobDefinitionResponse.page)
                && Objects.equals(this.size, paginatedWacodisJobDefinitionResponse.size)
                && Objects.equals(this.total, paginatedWacodisJobDefinitionResponse.total)
                && Objects.equals(this.data, paginatedWacodisJobDefinitionResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, total, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaginatedWacodisJobDefinitionResponse {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
