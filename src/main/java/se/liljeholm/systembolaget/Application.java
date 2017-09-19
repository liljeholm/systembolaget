package se.liljeholm.systembolaget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import se.liljeholm.systembolaget.xml.XmlArticles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(XmlArticles.class);
    }
}
