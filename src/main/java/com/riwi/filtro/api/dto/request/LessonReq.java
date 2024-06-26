package com.riwi.filtro.api.dto.request;


import java.util.List;

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
public class LessonReq {
    @NotBlank(message = "The Lesson title is required")
    private String title;
    @NotBlank(message = "The content lesson is required")
    private String content;

    @NotNull(message = "the class id in lesson is required")
    private Long class_id;
    @NotNull(message = "El estado de la clase es requerido.")
    private Boolean active;
    
    private List<MultimediaReq> multimedias;
}
