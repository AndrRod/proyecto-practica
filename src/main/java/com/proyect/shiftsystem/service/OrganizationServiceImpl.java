package com.proyect.shiftsystem.service;
import com.proyect.shiftsystem.dto.OrganizationDto;
import com.proyect.shiftsystem.dto.OrganizationMapper;
import com.proyect.shiftsystem.excepcion.NotFoundException;
import com.proyect.shiftsystem.model.Organization;
import com.proyect.shiftsystem.repository.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper orgMapper;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        organizationDto.setPassword(passwordEncoder.encode(organizationDto.getPassword()));
        Organization organizationEntity = organizationRepository.save(orgMapper.entityFromDto(organizationDto));
        OrganizationDto organizationDtoReturn = orgMapper.returnPartDtoFromEntity(organizationEntity);
        return organizationDtoReturn;
    }
    @Override
    public OrganizationDto findById(Long id) {
        return orgMapper.returnPartDtoFromEntity(findEntityById(id));
    }
    @Override
    public Organization findEntityById(Long id){
        return organizationRepository.findById(id).orElseThrow(()-> new NotFoundException("organizacion no encontrada"));
    }
    @Override
    public List<OrganizationDto> getListOrganizationDto(Integer page){
        return organizationRepository.findAll(PageRequest.of(page, 20)).map(orgMapper::returnPartDtoFromEntity).getContent();
    }
    @Override
    public Map<String, String> delete(Long id){
    Organization organizationFound = findEntityById(id);
    organizationRepository.delete(organizationFound);
    return Map.of("Message", "entity with id " + id + " was deleted successful");
    }
    @Autowired
    private ModelMapper mapper;
    @Override
    public OrganizationDto updateById(Long id, OrganizationDto dto) {
        mapper.getConfiguration().setSkipNullEnabled(true);
        Organization organizationFound = findEntityById(id);
        mapper.map(dto, organizationFound);
        organizationRepository.save(organizationFound);

// ModelMapper nos permite hacer todas las linas que estan a continuacion indicando que si es null no haga el mapeo
//        Organization organizationFound = findEntityById(id);
//        Stream.of(organizationFound).forEach((entity)-> {
//            if(dto.getName()!= null) entity.setName(dto.getName());
//            if(dto.getPassword()!= null) entity.setPassword(passwordEncoder.encode(dto.getPassword()));
//            if(dto.getShiftDate()!= null) entity.setShiftDate(dto.getShiftDate());
//            if(dto.getShiftDate()!= null) entity.setShiftHourDate(dto.getShiftHourDate());
//            organizationRepository.save(entity);
//        });
        return orgMapper.returnPartDtoFromEntity(organizationFound);
    }
}
