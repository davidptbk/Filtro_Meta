package com.riwi.filtro.api.dto.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassReq {
    @NotBlank(message = "The class name is required")
    private String name;

    @NotBlank(message = "The description class is required")
    private String description;


    @NotNull(message = "The state is required")
    private Boolean active;

}