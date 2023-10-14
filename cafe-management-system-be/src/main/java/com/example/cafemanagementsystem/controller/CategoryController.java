package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.constants.CafeConstants;
import com.example.cafemanagementsystem.model.dto.CategoryDto;
import com.example.cafemanagementsystem.service.CategoryService;
import com.example.cafemanagementsystem.util.CafeUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    ResponseEntity<String> create(@NotNull @Valid @RequestBody CategoryDto categoryDto){
        try {
            return categoryService.create(categoryDto);
        } catch (Exception ex) {
            log.error("Failed call create: {}", ex);
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> findAll(){
        try {
            return categoryService.findAll();
        } catch (Exception ex) {
            log.error("Failed call findAll: {}", ex);
            return new ResponseEntity<>(new ArrayList<CategoryDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    ResponseEntity<String> update(@RequestBody CategoryDto categoryDto){
        try {
            return categoryService.update(categoryDto);
        } catch (Exception ex) {
            log.error("Failed call update: {}", ex);
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}