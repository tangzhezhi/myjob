package org.tang.myjob.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.oauth.OauthRepository;
import org.tang.myjob.dto.oauth.OauthClientDetails;


/**
 * @author Shengzhao Li
 */
@Service("oauthService")
public class OauthService {

    @Autowired
    private OauthRepository oauthRepository;

    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthRepository.findOauthClientDetails(clientId);
    }
}