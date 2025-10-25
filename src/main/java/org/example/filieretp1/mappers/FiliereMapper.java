package org.example.filieretp1.mappers;

import org.example.filieretp1.dto.RequestFiliereDto;
import org.example.filieretp1.dto.ResponseFiliereDto;
import org.example.filieretp1.entities.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {
    public Filiere DTO_To_Entity(RequestFiliereDto requestFiliereDto) {
        Filiere f = new Filiere();
        BeanUtils.copyProperties(requestFiliereDto,f);
        return f;
    }
    public ResponseFiliereDto Entity_To_DTO(Filiere f){
        ResponseFiliereDto responseFiliereDto = new ResponseFiliereDto();
        BeanUtils.copyProperties(f,responseFiliereDto);
        return responseFiliereDto;
    }
}
