package de.wacodis.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * definition of temporal coverage for which input data (see SubsetDefinitions) is of relevancy.
 * Only one of the properties shall be provided.
 */
@UserDefinedType("temporalCoverage")
@ApiModel(
        description =
                "definition of temporal coverage for which input data (see SubsetDefinitions) is of relevancy. Only one of the properties shall be provided. ")
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2018-10-04T15:06:06.366+02:00[Europe/Berlin]")
public class WacodisJobDefinitionTemporalCoverage {
    @JsonProperty("duration")
    @Column
    private String duration = null;

    @JsonProperty("previousExecution")
    @Column
    private Boolean previousExecution = null;

    public WacodisJobDefinitionTemporalCoverage duration(String duration) {
        this.duration = duration;
        return this;
    }

    /**
     * the duration in ISO8601 duration format (https://en.wikipedia.org/wiki/ISO_8601#Durations)
     * the processing component will treat the duration as backwards. A duration of \"P1M\" will
     * cover the last month before execution
     *
     * @return duration
     */
    @ApiModelProperty(
            value =
                    "the duration in ISO8601 duration format (https://en.wikipedia.org/wiki/ISO_8601#Durations) the processing component will treat the duration as backwards. A duration of \"P1M\" will cover the last month before execution ")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public WacodisJobDefinitionTemporalCoverage previousExecution(Boolean previousExecution) {
        this.previousExecution = previousExecution;
        return this;
    }

    /**
     * input data coverage since the last execution of the job. This is only valid in combination
     * with pattern execution
     *
     * @return previousExecution
     */
    @ApiModelProperty(
            value =
                    "input data coverage since the last execution of the job. This is only valid in combination with pattern execution ")
    public Boolean getPreviousExecution() {
        return previousExecution;
    }

    public void setPreviousExecution(Boolean previousExecution) {
        this.previousExecution = previousExecution;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WacodisJobDefinitionTemporalCoverage wacodisJobDefinitionTemporalCoverage =
                (WacodisJobDefinitionTemporalCoverage) o;
        return Objects.equals(this.duration, wacodisJobDefinitionTemporalCoverage.duration)
                && Objects.equals(
                        this.previousExecution,
                        wacodisJobDefinitionTemporalCoverage.previousExecution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, previousExecution);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WacodisJobDefinitionTemporalCoverage {\n");

        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    previousExecution: ")
                .append(toIndentedString(previousExecution))
                .append("\n");
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