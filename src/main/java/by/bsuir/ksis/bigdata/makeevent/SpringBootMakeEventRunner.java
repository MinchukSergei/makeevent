package by.bsuir.ksis.bigdata.makeevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMakeEventRunner extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMakeEventRunner.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootMakeEventRunner.class);
    }
}
