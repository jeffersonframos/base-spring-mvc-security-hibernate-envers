package ramos.jefferson.base.util;

import java.io.Serializable;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ramos.jefferson.base.exception.InvalidNumberParameterException;
import ramos.jefferson.base.exception.InvalidPageRequestParameterException;

@Component
public class Utility implements Serializable {

    public PageRequest createPageRequest(Map<String, String> parameters) throws InvalidPageRequestParameterException, InvalidNumberParameterException {
        if (verifyParameters(parameters)) {
            try {
                int page = Integer.parseInt(parameters.get(Constants.PAGE_REQUEST_PAGE_PARAMETER));
                int quantity = Integer.parseInt(parameters.get(Constants.PAGE_REQUEST_QUANTITY_PARAMETER));
                if (page < 1 || quantity < 1) {
                    throw new InvalidPageRequestParameterException();
                }
                return PageRequest.of(page, quantity);
            } catch (NumberFormatException ex) {
                throw new InvalidNumberParameterException();
            }
        } else {
            return PageRequest.of(0, Integer.MAX_VALUE);
        }
    }

    private boolean verifyParameters(Map<String, String> parameters) {
        if (parameters == null) {
            return false;
        }
        return (parameters.containsKey(Constants.PAGE_REQUEST_PAGE_PARAMETER) && parameters.containsKey(Constants.PAGE_REQUEST_QUANTITY_PARAMETER));
    }

}
