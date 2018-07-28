package com.etherbet;

import com.etherbet.core.ApplicationContextHolder;
import com.etherbet.dto.Fixture;
import com.etherbet.ipfs.service.IpfsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ipfs.api.MerkleNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

@SpringBootApplication
//@PropertySource(value = {"file:${vn.fb.appDir}/config/fb.application.properties"})
@EnableScheduling
public class EtherbetApplication implements CommandLineRunner{

    public static IpfsService ipfsService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EtherbetApplication.class, args);

    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    };

    @Override
    public void run(String... strings) throws Exception {
        ipfsService = (IpfsService) ApplicationContextHolder.getApplicationContext().getBean("ipfsServiceImpl");
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/KAI/PROJECT/JAVA/etherbet/src/main/resources/staticData.json"));
        ObjectMapper mapper = new ObjectMapper();
        Fixture fixture = mapper.readValue(bytes, Fixture.class);
        System.out.println(fixture.getMatches().size());
        MerkleNode merkleNode = (MerkleNode) ipfsService.addBytes("hello.txt", String.valueOf(Calendar.getInstance().getTime()).getBytes());
        System.out.println(merkleNode.hash.toBase58());
        byte[] bytes1 = ipfsService.getBytes(merkleNode.hash.toBase58());
        System.out.println(new String(bytes1));
    }
}
