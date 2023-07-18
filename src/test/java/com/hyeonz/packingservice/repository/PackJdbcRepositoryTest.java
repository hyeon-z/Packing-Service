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
import static org.hamcrest.Matchers.samePropertyValuesAs;
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

    private final PackingList packingList = new PackingList("대만여행", "친구들과 간다.", LocalDate.now());
    private final Pack pack = new Pack(1L, "상의", Category.CLOTHES);
    private final Pack newPack = new Pack(1L, "하의", Category.CLOTHES);

    @BeforeEach
    void dataInitialize() {
        packRepository.deleteAll();

        packingListRepository.insert(packingList);
        packRepository.insert(pack);
    }

    @Test
    @Order(1)
    @DisplayName("짐을 추가할 수 있다.")
    void insertPack() {
        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    @DisplayName("모든 짐을 조회할 수 있다.")
    void findAllPack() {
        packRepository.insert(newPack);

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.size(), is(2));
    }

    @Test
    @Order(3)
    @DisplayName("모든 짐을 삭제할 수 있다.")
    void deleteAllPack() {
        packRepository.deleteAll();

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.isEmpty(), is(true));
    }

    @Test
    @Order(4)
    @DisplayName("카테고리로 짐을 조회할 수 있다.")
    void findPackByCategory() {
        packRepository.insert(newPack);

        List<Pack> foodPacks = packRepository.findByCategory(Category.FOOD);
        List<Pack> clothesPacks = packRepository.findByCategory(Category.CLOTHES);

        assertThat(foodPacks.isEmpty(), is(true));
        assertThat(clothesPacks.size(), is(2));
    }

    @Test
    @Order(5)
    @DisplayName("짐을 수정할 수 있다.")
    void updatePack() {
        Pack pack = packRepository.findAll().get(0);
        pack.setChecked(true);

        packRepository.update(pack);

        Pack updatedPack = packRepository.findAll().get(0);

        assertThat(updatedPack, samePropertyValuesAs(pack));
    }
}
