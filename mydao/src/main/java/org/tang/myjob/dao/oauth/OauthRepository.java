package org.tang.myjob.dao.oauth;

import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.oauth.OauthClientDetails;

/**
 * @author Shengzhao Li
 */

@Repository
public interface OauthRepository  {

    OauthClientDetails findOauthClientDetails(String clientId);
}