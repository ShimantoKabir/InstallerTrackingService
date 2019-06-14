package com.installertrackingws.installertrackingws.utility.social.facebook;

import org.springframework.social.facebook.api.User;

public interface FaceBookService {

    String login();

    String getAccessToken(String code);

    User getData(String accessToken);

}
