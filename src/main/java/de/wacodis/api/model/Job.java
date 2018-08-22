package de.wacodis.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import static de.wacodis.api.model.Job.TABLE_NAME;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Job
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-08-20T16:26:51.356+02:00[Europe/Berlin]")
@Table(value = TABLE_NAME)
public class Job {

    public static final String TABLE_NAME = "jobs";

    @JsonProperty("id")
    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = null;

    @JsonProperty("name")
    @Column
    private String name = null;

    @JsonProperty("description")
    @Column
    private String description = null;

    @JsonProperty("useCase")
    @Column
    private String useCase = null;

    @JsonProperty("created")
    @Column
    private Date created = null;

    @JsonProperty("timeInterval")
    @Column
    private String timeInterval = null;

    @JsonProperty("areaOfInterest")
    @Column
    private JobAreaOfInterest areaOfInterest = null;

    @JsonProperty("processingTool")
    @Column
    private String processingTool = null;

    @JsonProperty("inputs")
    @Column
    @Valid
    private List<AbstractSubsetDefinition> inputs = new ArrayList<>();

    public Job id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     *
     */
    @ApiModelProperty(value = "")

    @Valid

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Job name(String name) {
        this.name = name;
        return this;
    }

    /**
     * a human friendly short name
     *
     * @return name
     *
     */
    @ApiModelProperty(required = true, value = "a human friendly short name ")
    @NotNull

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job description(String description) {
        this.description = description;
        return this;
    }

    /**
     * a more verbose description of the jobs (e.g. purpose, inputs, ...)
     *
     * @return description
     *
     */
    @ApiModelProperty(value = "a more verbose description of the jobs (e.g. purpose, inputs, ...) ")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job useCase(String useCase) {
        this.useCase = useCase;
        return this;
    }

    /**
     * A generic use case reference. This can be used to refer to the use cases
     * identified during the initial phase of WaCoDiS
     *
     * @return useCase
     *
     */
    @ApiModelProperty(value = "A generic use case reference. This can be used to refer to the use cases identified during the initial phase of WaCoDiS ")

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    public Job created(Date created) {
        this.created = created;
        return this;
    }

    /**
     * Get created
     *
     * @return created
     *
     */
    @ApiModelProperty(value = "")

    @Valid

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Job timeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
        return this;
    }

    /**
     * if present, this describe the recurrency of a job. if not present, the
     * job is treated as a one-time job
     *
     * @return timeInterval
     *
     */
    @ApiModelProperty(value = "if present, this describe the recurrency of a job. if not present, the job is treated as a one-time job ")

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Job areaOfInterest(JobAreaOfInterest areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
        return this;
    }

    /**
     * Get areaOfInterest
     *
     * @return areaOfInterest
     *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public JobAreaOfInterest getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(JobAreaOfInterest areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public Job processingTool(String processingTool) {
        this.processingTool = processingTool;
        return this;
    }

    /**
     * the processingTool ID as provided by the WPS tool wrapper
     *
     * @return processingTool
     *
     */
    @ApiModelProperty(required = true, value = "the processingTool ID as provided by the WPS tool wrapper ")
    @NotNull

    public String getProcessingTool() {
        return processingTool;
    }

    public void setProcessingTool(String processingTool) {
        this.processingTool = processingTool;
    }

    public Job inputs(List<AbstractSubsetDefinition> inputs) {
        this.inputs = inputs;
        return this;
    }

    public Job addInputsItem(AbstractSubsetDefinition inputsItem) {
        this.inputs.add(inputsItem);
        return this;
    }

    /**
     * Get inputs
     *
     * @return inputs
     *
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public List<AbstractSubsetDefinition> getInputs() {
        return inputs;
    }

    public void setInputs(List<AbstractSubsetDefinition> inputs) {
        this.inputs = inputs;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(this.id, job.id)
                && Objects.equals(this.name, job.name)
                && Objects.equals(this.description, job.description)
                && Objects.equals(this.useCase, job.useCase)
                && Objects.equals(this.created, job.created)
                && Objects.equals(this.timeInterval, job.timeInterval)
                && Objects.equals(this.areaOfInterest, job.areaOfInterest)
                && Objects.equals(this.processingTool, job.processingTool)
                && Objects.equals(this.inputs, job.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, useCase, created, timeInterval, areaOfInterest, processingTool, inputs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Job {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    useCase: ").append(toIndentedString(useCase)).append("\n");
        sb.append("    created: ").append(toIndentedString(created)).append("\n");
        sb.append("    timeInterval: ").append(toIndentedString(timeInterval)).append("\n");
        sb.append("    areaOfInterest: ").append(toIndentedString(areaOfInterest)).append("\n");
        sb.append("    processingTool: ").append(toIndentedString(processingTool)).append("\n");
        sb.append("    inputs: ").append(toIndentedString(inputs)).append("\n");
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
