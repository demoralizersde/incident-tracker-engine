package incidentengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentCreateRequestDto {

    @NotBlank(message = "Title is Required")
    private String title;

    @NotBlank(message = "Service is Required")
    private String service;

    @NotBlank(message = "Severity is Required")
    private String severity;

    private String owner;

    private String summary;

}
