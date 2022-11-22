package com.proyect.shiftsystem.service;
import com.proyect.shiftsystem.dto.OrganizationDto;
import com.proyect.shiftsystem.dto.OrganizationMapper;
import com.proyect.shiftsystem.excepcion.NotFoundException;
import com.proyect.shiftsystem.message.MessageResponse;
import com.proyect.shiftsystem.model.Organization;
import com.proyect.shiftsystem.repository.OrganizationRepository;
import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public MessageResponse delete(Long id, HttpServletRequest request){
    Organization organizationFound = findEntityById(id);
    organizationRepository.delete(organizationFound);
    return new MessageResponse("entity with id " + id + " was deleted successful", 200, request.getRequestURI());
    }
    @Autowired
    private ModelMapper mapper;
    @Override
    public OrganizationDto updateById(Long id, OrganizationDto dto) {
        Organization entity = findEntityById(id);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(dto, entity);
        organizationRepository.save(entity);

// ModelMapper nos permite hacer todas las linas que estan a continuacion indicando que si es null no haga el mapeo
//        Organization organizationFound = findEntityById(id);
//        Stream.of(organizationFound).forEach((entity)-> {
//            if(dto.getName()!= null) entity.setName(dto.getName());
//            if(dto.getPassword()!= null) entity.setPassword(passwordEncoder.encode(dto.getPassword()));
//            if(dto.getShiftDate()!= null) entity.setShiftDate(dto.getShiftDate());
//            if(dto.getShiftDate()!= null) entity.setShiftHourDate(dto.getShiftHourDate());
//            organizationRepository.save(entity);
//        });
        return orgMapper.returnPartDtoFromEntity(entity);
    }
}
