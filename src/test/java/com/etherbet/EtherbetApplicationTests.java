package com.etherbet;

import com.etherbet.ipfs.service.IpfsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import io.ipfs.api.MerkleNode;
import lombok.var;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
        "ipfs.multiadress=/ip4/127.0.0.1/tcp/4001"
})
public class EtherbetApplicationTests {

    @InjectMocks
    public IpfsServiceImpl ipfsService;

    @Value("${ipfs.multiadress}")
    private String multiaddress;

    @Test
    public void whenAdd1FileDataToIPFS_shouldReturnHashNumber() throws IOException {
        MerkleNode merkleNode = ipfsService.addBytes("hello.txt", String.valueOf(Calendar.getInstance().getTime()).getBytes());
        byte[] bytes = ipfsService.getBytes(merkleNode.hash.toBase58());
        Assert.assertEquals(String.valueOf(Calendar.getInstance().getTime()).getBytes(), bytes);
    }

    @Test
    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> subSets = Lists.partition(intList, 3);

        subSets.stream().forEach(integers -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                var count = 1111;
                var rs = IntMath.divide(1111, 10, RoundingMode.CEILING);
                System.out.println(rs);
                System.out.println(objectMapper.writeValueAsString(integers));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
