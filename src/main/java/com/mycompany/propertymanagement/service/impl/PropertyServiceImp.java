package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImp implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        //type cast to solve the iterable error
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();

        //Create a new list to hold the new content from conversion entity to dto,
        // so it can be passed to the controller
        List<PropertyDTO> propList = new ArrayList<>();

        //iterate through each property entity, and convert each entity, then place them inside an arraylist

        for (PropertyEntity pe : listOfProps) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        //Optional helps us to do a null check

        PropertyDTO dto = null;

        //if the Data exists, then we will go for the update
        if (optEntity.isPresent()) {

            PropertyEntity pe = optEntity.get(); //data from database

            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());

            //Convert the Entity to DTO so we can return the DTO
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;

        if (optEntity.isPresent()) {

            PropertyEntity pe = optEntity.get();
            pe.setDescription(propertyDTO.getDescription());

            //Convert the Entity to DTO so we can return the DTO
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;

        if (optEntity.isPresent()) {

            PropertyEntity pe = optEntity.get();
            pe.setPrice(propertyDTO.getPrice());

            //Convert the Entity to DTO so we can return the DTO
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public void  deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }


}
