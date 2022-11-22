package com.proyect.shiftsystem.dto;
import com.proyect.shiftsystem.dto.OrganizationDto;
import com.proyect.shiftsystem.model.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    @Autowired
    private ModelMapper mapper;
    public Organization entityFromDto(OrganizationDto organizationDto){
        organizationDto.setCreationDate(null);
        organizationDto.setId(null);
        return mapper.map(organizationDto, Organization.class);
        // ModelMapper hace el mapeo que hago aca abajo
//        return new Organization(null, organizationDto.getName(), organizationDto.getPassword(), null, organizationDto.getShiftDate(), organizationDto.getShiftHourDate());
    }
    public OrganizationDto returnPartDtoFromEntity(Organization organization){
        organization.setPassword(null);
        return mapper.map(organization, OrganizationDto.class);
//        return new OrganizationDto(organization.getId(), organization.getName(), null, organization.getCreationDate(), organization.getShiftDate(), organization.getShiftHourDate());
    }
}
