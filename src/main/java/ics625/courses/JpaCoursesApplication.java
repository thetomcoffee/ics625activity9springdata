package ics625.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JpaCoursesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx
                = SpringApplication.run(JpaCoursesApplication.class, args);
       
        CoursesApp app = ctx.getBean(CoursesApp.class);
        app.start();
        ctx.stop();
    }


}
