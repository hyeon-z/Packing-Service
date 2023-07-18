package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;
import com.hyeonz.packingservice.model.PackingList;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class PackJdbcRepositoryTest {
    static EmbeddedDatabase embeddedDatabase;

    @BeforeAll
    static void setup() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().generateUniqueName(true).setType(H2).setScriptEncoding("UTF-8").ignoreFailedDrops(true).addScript("schema.sql").build();
    }

    @AfterAll
    static void cleanup() {
        embeddedDatabase.shutdown();
    }

    @Autowired
    PackingListRepository packingListRepository;

    @Autowired
    PackRepository packRepository;

    @BeforeEach
    void dataInitialize() {
        packRepository.deleteAll();
    }

    private final PackingList packingList = new PackingList("대만여행", "친구들과 간다.", LocalDate.now());
    private final Pack pack = new Pack(1L, "상의", Category.CLOTHES);

    @Test
    @Order(1)
    @DisplayName("짐을 추가할 수 있다.")
    void insertPack() {
        packingListRepository.insert(packingList);
        packRepository.insert(pack);

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    @DisplayName("모든 짐을 조회할 수 있다.")
    void findAllPack() {
        Pack newPack = new Pack(1L, "하의", Category.CLOTHES);

        packingListRepository.insert(packingList);
        packRepository.insert(pack);
        packRepository.insert(newPack);

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.size(), is(2));
    }
}
