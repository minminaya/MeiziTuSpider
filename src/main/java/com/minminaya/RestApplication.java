package com.minminaya;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Niwa on 2017/6/16.
 */
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        //很重要，包名错误直接导致访问错误
        packages("com.minminaya");
        register(JacksonJsonProvider.class);
    }
}
