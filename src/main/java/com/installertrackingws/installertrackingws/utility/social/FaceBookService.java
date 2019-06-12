package com.installertrackingws.installertrackingws.utility.social;

import org.springframework.social.facebook.api.User;

public interface FaceBookService {

    String facebookLogin();

    String getFacebookAccessToken(String code);

    User getFacebookUserProfile(String accessToken);

}
