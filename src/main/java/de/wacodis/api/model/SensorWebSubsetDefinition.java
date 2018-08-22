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
 * SensorWebSubsetDefinition
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-20T16:26:51.356+02:00[Europe/Berlin]")
@UserDefinedType("sensorWebSubsetDefinition")
public class SensorWebSubsetDefinition extends AbstractSubsetDefinition {

    /**
     * shall be used to determine the responsible data backend
     */
    public enum SourceTypeEnum {
        SENSORWEBSUBSETDEFINITION("SensorWebSubsetDefinition");

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

    @JsonProperty("offering")
    @Column
    private String offering = null;

    @JsonProperty("featureOfInterest")
    @Column
    private String featureOfInterest = null;

    @JsonProperty("observedProperty")
    @Column
    private String observedProperty = null;

    @JsonProperty("procedure")
    @Column
    private String procedure = null;

    public SensorWebSubsetDefinition sourceType(SourceTypeEnum sourceType) {
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

    public SensorWebSubsetDefinition offering(String offering) {
        this.offering = offering;
        return this;
    }

    /**
     * Get offering
     *
     * @return offering
  *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getOffering() {
        return offering;
    }

    public void setOffering(String offering) {
        this.offering = offering;
    }

    public SensorWebSubsetDefinition featureOfInterest(String featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
        return this;
    }

    /**
     * Get featureOfInterest
     *
     * @return featureOfInterest
  *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(String featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    public SensorWebSubsetDefinition observedProperty(String observedProperty) {
        this.observedProperty = observedProperty;
        return this;
    }

    /**
     * Get observedProperty
     *
     * @return observedProperty
  *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getObservedProperty() {
        return observedProperty;
    }

    public void setObservedProperty(String observedProperty) {
        this.observedProperty = observedProperty;
    }

    public SensorWebSubsetDefinition procedure(String procedure) {
        this.procedure = procedure;
        return this;
    }

    /**
     * Get procedure
     *
     * @return procedure
  *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SensorWebSubsetDefinition sensorWebSubsetDefinition = (SensorWebSubsetDefinition) o;
        return Objects.equals(this.sourceType, sensorWebSubsetDefinition.sourceType)
                && Objects.equals(this.offering, sensorWebSubsetDefinition.offering)
                && Objects.equals(this.featureOfInterest, sensorWebSubsetDefinition.featureOfInterest)
                && Objects.equals(this.observedProperty, sensorWebSubsetDefinition.observedProperty)
                && Objects.equals(this.procedure, sensorWebSubsetDefinition.procedure)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceType, offering, featureOfInterest, observedProperty, procedure, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SensorWebSubsetDefinition {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
        sb.append("    offering: ").append(toIndentedString(offering)).append("\n");
        sb.append("    featureOfInterest: ").append(toIndentedString(featureOfInterest)).append("\n");
        sb.append("    observedProperty: ").append(toIndentedString(observedProperty)).append("\n");
        sb.append("    procedure: ").append(toIndentedString(procedure)).append("\n");
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
