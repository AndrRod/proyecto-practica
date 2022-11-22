package com.proyect.shiftsystem.controller;

import com.proyect.shiftsystem.dto.OrganizationDto;
import com.proyect.shiftsystem.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.findById(id));
    }
    @PostMapping
    public ResponseEntity<OrganizationDto> create(@RequestBody OrganizationDto organizationDto){
        return ResponseEntity.status(201).body(organizationService.create(organizationDto));
    }
    @GetMapping
    public  ResponseEntity<List<OrganizationDto>> getListOrganizationDto(@RequestParam Integer page){
        return ResponseEntity.ok(organizationService.getListOrganizationDto(page));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.delete(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> update(@PathVariable Long id, @RequestBody OrganizationDto organizationDto){
        return ResponseEntity.ok(organizationService.updateById(id,organizationDto));
    }
}

