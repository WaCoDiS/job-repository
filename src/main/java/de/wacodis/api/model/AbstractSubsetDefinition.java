package de.wacodis.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * AbstractSubsetDefinition
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-20T16:26:51.356+02:00[Europe/Berlin]")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "sourceType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SensorWebSubsetDefinition.class, name = "SensorWebSubsetDefinition")
    ,
  @JsonSubTypes.Type(value = CopernicusSubsetDefinition.class, name = "CopernicusSubsetDefinition")
    ,
  @JsonSubTypes.Type(value = GdiDeSubsetDefinition.class, name = "GdiDeSubsetDefinition"),})
@UserDefinedType("abstractSubsetDefinition")
public class AbstractSubsetDefinition {

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AbstractSubsetDefinition {\n");

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

    @Column
    private String dummy;

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

}
