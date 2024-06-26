package com.riwi.filtro.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro.api.dto.request.ClassReq;
import com.riwi.filtro.api.dto.response.ClassBasicResp;
import com.riwi.filtro.api.dto.response.ClassGetResp;
import com.riwi.filtro.api.dto.response.StudentBasicResp;
import com.riwi.filtro.domain.entities.ClassEntity;
import com.riwi.filtro.domain.entities.StudentEntity;
import com.riwi.filtro.domain.repositories.ClassRepository;
import com.riwi.filtro.utils.exceptions.BadRequestException;
import com.riwi.filtro.utils.messages.ErrorMessages;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassService {
    @Autowired
    private final ClassRepository repository;

    public Page<Object> getAll(int page, int size, String name, String description){
        if(page<0)
            page=0;

            PageRequest pagination = PageRequest.of(page,size);

            return this.repository.findByNameOrDescriptionAndActive(name, description, pagination, true).map(clase -> this.entityBasicToResponse(clase));
    } 
    public ClassGetResp get(Long id) {
        return this.entityGetToResponse(this.find(id));
    }
    
    
    public ClassBasicResp create(ClassReq request) {
        ClassEntity clase = requestToEntity(request);
        
        clase.setLessons(new ArrayList<>());
        clase.setStudents(new ArrayList<>());

        return this.entityBasicToResponse(this.repository.save(clase));
    }    
    

    private ClassBasicResp entityBasicToResponse(ClassEntity entity) {
        return ClassBasicResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreated_at())
                .active(entity.getActive())
                .build();
    }

    private ClassGetResp entityGetToResponse(ClassEntity entity) {
        List<StudentBasicResp> students = 
            entity.getStudents()
            .stream()
            .map(this::studentEntityToResponse)
            .collect(Collectors.toList());       

        return ClassGetResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .created_at(entity.getCreated_at())
                .active(entity.getActive())
                .students(students)
                .build();
    }


    private StudentBasicResp studentEntityToResponse(StudentEntity entity) {
        return StudentBasicResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .createdAt(entity.getCreated_at())
                .active(entity.getActive())
                .build();
    }

    private ClassEntity requestToEntity(ClassReq request) {

        System.out.println(ClassEntity.builder()
        .name(request.getName())
        .description(request.getDescription())
        .active(request.getActive())
        .build());
        return ClassEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .build();
    }

     private ClassEntity find(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Class")));
    }

}
