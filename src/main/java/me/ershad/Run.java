package me.ershad;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;

@Path("run")
public class Run {

    @GET
    @Path("test/{some_text}")
    public String test(@PathParam("some_text") String someText){
        // If return 0 mean failed and SQl injection protection working
        // If given data is existing data will return its ID
        try {
            return Integer.toString(new TestSql().run(someText));
        } catch (IOException e) {
            return "Some thing wrong";
        }
    }

}
