package ramos.jefferson.base.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ramos.jefferson.base.dto.AbstractBaseDTO;
import ramos.jefferson.base.entity.AbstractBaseEntity;
import ramos.jefferson.base.exception.EmptyParameterException;
import ramos.jefferson.base.exception.InvalidNumberParameterException;
import ramos.jefferson.base.exception.InvalidPageRequestParameterException;
import ramos.jefferson.base.exception.NotSameEntityException;
import ramos.jefferson.base.exception.ResourceNotFounException;
import ramos.jefferson.base.exception.UserNotFoundException;
import ramos.jefferson.base.service.AbstractBaseService;
import ramos.jefferson.base.util.Utility;

public abstract class AbstractBaseServiceImplementation<T extends AbstractBaseEntity, DTO extends AbstractBaseDTO, REPO extends JpaRepository>
        implements AbstractBaseService<T, DTO> {

    protected final REPO repository;

    @Autowired
    private Utility utility;

    public AbstractBaseServiceImplementation(REPO repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = {EmptyParameterException.class})
    public DTO save(DTO dto) throws EmptyParameterException {
        T t = convertDtoToType(dto);
        verify(t);
        T entity = (T) repository.saveAndFlush(t);
        return convertTypeToDto(entity);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = {ResourceNotFounException.class, EmptyParameterException.class, NotSameEntityException.class})
    public DTO update(DTO dto, long id) throws ResourceNotFounException, EmptyParameterException, NotSameEntityException {
        T entity = getEntity(id);
        if (entity == null) {
            throwResourceNotFoundException();
        }
        T t = convertDtoToType(dto);
        if (!entity.equals(t)) {
            throw new NotSameEntityException();
        }
        t = (T) repository.saveAndFlush(t);
        return convertTypeToDto(t);
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findOne(long id) throws ResourceNotFounException {
        T t = getEntity(id);
        if (t == null) {
            throw new UserNotFoundException();
        }
        return convertTypeToDto(t);
    }

    @Override
    @Transactional(readOnly = true)
    public T getOne(long id) {
        return getEntity(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> findList(Map<String, String> parameters) throws InvalidPageRequestParameterException, InvalidNumberParameterException {
        Page<T> page = repository.findAll(utility.createPageRequest(parameters));
        return convertTypeListToDtoList(page.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> findPage(Map<String, String> parameters) throws InvalidPageRequestParameterException, InvalidNumberParameterException {
        return repository.findAll(utility.createPageRequest(parameters));
    }

    private List<DTO> convertTypeListToDtoList(List<T> list) {
        List<DTO> listDto = new ArrayList<>();
        list.forEach((t) -> {
            listDto.add(convertTypeToDto(t));
        });
        return listDto;
    }

    private T getEntity(long id) {
        Optional optional = repository.findById(id);
        if (optional.isPresent()) {
            return (T) optional.get();
        }
        return null;
    }

    abstract protected void verify(T t) throws EmptyParameterException;

    abstract protected void throwResourceNotFoundException() throws ResourceNotFounException;

    abstract protected T convertDtoToType(DTO dto);

    abstract protected DTO convertTypeToDto(T t);

}
