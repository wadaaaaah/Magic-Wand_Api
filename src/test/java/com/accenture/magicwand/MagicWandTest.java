package com.accenture.magicwand;

import com.accenture.magicwand.entity.MagicWand;
import com.accenture.magicwand.repository.MagicWandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MagicWandTest {

    @Autowired
    private MagicWandRepository repo;
    @Autowired
    MagicWand entity;

    @Test
    public void getMagicTest(){
        MagicWand magic = repo.findById(4L).get();
        Assertions.assertThat(magic.getId()).isEqualTo(4L);
    }

    @Test
    @Rollback(value = false)
    public void updateMagicTest(){
        entity = repo.findById(4L).get();
        entity.setAge_limit(50);
        MagicWand entityUpdated = repo.save(entity);
        Assertions.assertThat(entityUpdated.getAge_limit()).isEqualTo(50);
    }

    @Test
    public void deleteMagicTest(){
        repo.deleteById(5L);
        Assertions.assertThat(repo.existsById(5L)).isFalse();
    }


    @Test
    public void addMagicTest(){
        MagicWand magic = new MagicWand();
        magic.setId(7L);
        magic.setName("nur");
        magic.setNarrative("This is junit test no 2");
        magic.setAge_limit(50);
        magic.setStock(400L);

        repo.save(magic);
        System.out.println();
        assertNotNull(repo.findById(7L).get());

    }


}
