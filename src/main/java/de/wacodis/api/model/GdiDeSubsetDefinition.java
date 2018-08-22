package de.wacodis.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * GdiDeSubsetDefinition
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-20T16:26:51.356+02:00[Europe/Berlin]")
@UserDefinedType("gdiDeSubsetDefinition")
public class GdiDeSubsetDefinition extends AbstractSubsetDefinition {

    /**
     * shall be used to determine the responsible data backend
     */
    public enum SourceTypeEnum {
        GDIDESUBSETDEFINITION("GdiDeSubsetDefinition");

        private String value;

        SourceTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static SourceTypeEnum fromValue(String text) {
            for (SourceTypeEnum b : SourceTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + text + "'");
        }
    }

    @JsonProperty("sourceType")
    @Transient
    private SourceTypeEnum sourceType = null;

    @JsonProperty("catalogueId")
    @Column
    private String catalogueId = null;

    public GdiDeSubsetDefinition sourceType(SourceTypeEnum sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * shall be used to determine the responsible data backend
     *
     * @return sourceType
  *
     */
    @ApiModelProperty(required = true, value = "shall be used to determine the responsible data backend ")
    @NotNull

    public SourceTypeEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }

    public GdiDeSubsetDefinition catalogueId(String catalogueId) {
        this.catalogueId = catalogueId;
        return this;
    }

    /**
     * the id of the dataset within the GDI-DE catalogue
     *
     * @return catalogueId
  *
     */
    @ApiModelProperty(required = true, value = "the id of the dataset within the GDI-DE catalogue ")
    @NotNull

    public String getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(String catalogueId) {
        this.catalogueId = catalogueId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GdiDeSubsetDefinition gdiDeSubsetDefinition = (GdiDeSubsetDefinition) o;
        return Objects.equals(this.sourceType, gdiDeSubsetDefinition.sourceType)
                && Objects.equals(this.catalogueId, gdiDeSubsetDefinition.catalogueId)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceType, catalogueId, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GdiDeSubsetDefinition {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
        sb.append("    catalogueId: ").append(toIndentedString(catalogueId)).append("\n");
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
