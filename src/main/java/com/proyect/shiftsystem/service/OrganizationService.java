package com.proyect.shiftsystem.service;

import com.proyect.shiftsystem.dto.OrganizationDto;
import com.proyect.shiftsystem.message.MessageResponse;
import com.proyect.shiftsystem.model.Organization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrganizationService {
    OrganizationDto create(OrganizationDto organizationDto);
    OrganizationDto findById(Long id);
    Organization findEntityById(Long id);
    List<OrganizationDto> getListOrganizationDto(Integer page);
    MessageResponse delete(Long id, HttpServletRequest request);
    OrganizationDto updateById(Long id, OrganizationDto organizationDto);
}
