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

//@DataJpaTest
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MagicWandTest {

    @Autowired
    private MagicWandRepository repo;

    //private static final Logger log = (Logger) LoggerFactory.getLogger(MagicWandTest.class);
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
    @Rollback(value = false)
    public void deleteMagicTest(){
        entity = repo.findById(5L).get();
        repo.delete(entity);

        MagicWand magicWand = null;
        Optional<MagicWand> optionalMagic = repo.findById(5L);

        if(optionalMagic.isPresent()){
            magicWand = optionalMagic.get();
        }

        Assertions.assertThat(magicWand).isNull();
    }


    @Test
    public void addMagicTest(){
        MagicWand magic = new MagicWand();
        magic.setId(6L);
        magic.setName("Wada");
        magic.setNarrative("This is junit test");
        magic.setAge_limit(50);
        magic.setStock(400L);

        repo.save(magic);
        System.out.println();
        assertNotNull(repo.findById(6L).get());

    }


}
