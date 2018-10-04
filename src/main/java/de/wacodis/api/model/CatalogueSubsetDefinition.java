package de.wacodis.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/** CatalogueSubsetDefinition */
@UserDefinedType("CatalogueSubsetDefinition")
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-04T15:06:06.366+02:00[Europe/Berlin]")
public class CatalogueSubsetDefinition extends AbstractSubsetDefinition {
    @JsonProperty("identifier")
    @Column
    private String identifier = null;

    @JsonProperty("serviceUrl")
    @Column
    private Object serviceUrl = null;

    public CatalogueSubsetDefinition identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * the id of the dataset within the catalogue
     *
     * @return identifier
     */
    @ApiModelProperty(required = true, value = "the id of the dataset within the catalogue ")
    @NotNull
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public CatalogueSubsetDefinition serviceUrl(Object serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }

    /**
     * the base URL of the catalogue
     *
     * @return serviceUrl
     */
    @ApiModelProperty(required = true, value = "the base URL of the catalogue ")
    @NotNull
    public Object getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(Object serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CatalogueSubsetDefinition CatalogueSubsetDefinition = (CatalogueSubsetDefinition) o;
        return Objects.equals(this.identifier, CatalogueSubsetDefinition.identifier)
                && Objects.equals(this.serviceUrl, CatalogueSubsetDefinition.serviceUrl)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, serviceUrl, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CatalogueSubsetDefinition {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
        sb.append("    serviceUrl: ").append(toIndentedString(serviceUrl)).append("\n");
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
