package ramos.jefferson.base.audity;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import ramos.jefferson.base.configuration.security.CustomUserDetails;

public class BaseAudityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BaseAudity baseAudity = (BaseAudity) revisionEntity;
        baseAudity.setUser(customUserDetails.getUser());
    }

}
