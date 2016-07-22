/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package conor.obrien.jokebackend;

import com.example.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "v4",
  namespace = @ApiNamespace(
    ownerDomain = "jokebackend.obrien.conor",
    ownerName = "jokebackend.obrien.conor",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name  = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();

        JokeProvider jokeProvider = new JokeProvider();
        response.setData(jokeProvider.getJoke());

        return response;
    }

}
