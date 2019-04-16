package ramos.jefferson.base.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import ramos.jefferson.base.dto.AbstractBaseDTO;
import ramos.jefferson.base.entity.AbstractBaseEntity;
import ramos.jefferson.base.exception.EmptyParameterException;
import ramos.jefferson.base.exception.InvalidNumberParameterException;
import ramos.jefferson.base.exception.InvalidPageRequestParameterException;
import ramos.jefferson.base.exception.NotSameEntityException;
import ramos.jefferson.base.exception.ResourceNotFounException;

public interface AbstractBaseService<T extends AbstractBaseEntity, DTO extends AbstractBaseDTO> {
    
    public DTO save(DTO userDTO) throws EmptyParameterException;
    
    public DTO update(DTO userDTO, long id) throws ResourceNotFounException, EmptyParameterException, NotSameEntityException;
    
    public DTO findOne(long id) throws ResourceNotFounException;
    
    public T getOne(long id);
    
    public List<DTO> findList(Map<String, String> parameters) throws InvalidPageRequestParameterException, InvalidNumberParameterException;
    
    public Page<DTO> findPage(Map<String, String> parameters) throws InvalidPageRequestParameterException, InvalidNumberParameterException;
    
}
