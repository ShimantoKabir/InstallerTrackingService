package com.installertrackingws.installertrackingws.utility.social.facebook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FaceBookServiceImp implements FaceBookService{

    @Value("${spring.social.facebook.app-id}")
    public String fbId;

    @Value("${spring.social.facebook.app-secret}")
    public String fbSecret;

    public FacebookConnectionFactory createFaceBookConnection(){

        return new FacebookConnectionFactory(fbId,fbSecret);

    }

    @Override
    public String login() {

        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri("http://localhost:3307/fb/get-access-token");
        parameters.setScope("public_profile,email");
        return createFaceBookConnection().getOAuthOperations().buildAuthenticateUrl(parameters);

    }

    @Override
    public String getAccessToken(String code) {

        return createFaceBookConnection().getOAuthOperations().exchangeForAccess(code,"http://localhost:3307/fb/get-access-token",null).getAccessToken();

    }

    @Override
    public User getData(String accessToken) {

        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id","first_name","last_name","cover","email"};
        return facebook.fetchObject("me",User.class,fields);

    }

}
