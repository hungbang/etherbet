package com.etherbet;

import com.etherbet.dto.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class EtherbetApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EtherbetApplication.class, args);

        byte[] bytes = Files.readAllBytes(Paths.get("/Users/KAI/PROJECT/JAVA/etherbet/src/main/resources/staticData.json"));
        ObjectMapper mapper = new ObjectMapper();
        Fixture fixture = mapper.readValue(bytes, Fixture.class);
        System.out.println(fixture.getMatches().size());
    }
}
