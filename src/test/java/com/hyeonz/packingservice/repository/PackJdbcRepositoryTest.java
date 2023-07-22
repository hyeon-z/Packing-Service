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
import static org.hamcrest.Matchers.*;
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

    private final PackingList packingList = new PackingList(1L, "대만여행", "친구들과 간다.", LocalDate.now());
    private final Pack pack = new Pack(1L, 1L, "상의", Category.의류);
    private final Pack newPack = new Pack(2L, 1L, "하의", Category.의류);

    @BeforeEach
    void dataInitialize() {
        packRepository.deleteAll();

        packingListRepository.insert(packingList);
    }

    @Test
    @Order(1)
    @DisplayName("짐을 추가할 수 있다.")
    void insertPack() {
        Pack insertPack = packRepository.insert(pack);

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.isEmpty(), is(false));
        assertThat(insertPack, samePropertyValuesAs(pack));
    }

    @Test
    @Order(2)
    @DisplayName("모든 짐을 조회할 수 있다.")
    void findAllPack() {
        packRepository.insert(pack);
        packRepository.insert(newPack);

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.size(), is(2));
    }

    @Test
    @Order(3)
    @DisplayName("모든 짐을 삭제할 수 있다.")
    void deleteAllPack() {
        packRepository.insert(pack);
        packRepository.deleteAll();

        List<Pack> allPack = packRepository.findAll();
        assertThat(allPack.isEmpty(), is(true));
    }

    @Test
    @Order(4)
    @DisplayName("카테고리로 짐을 조회할 수 있다.")
    void findPackByCategory() {
        packRepository.insert(pack);
        packRepository.insert(newPack);

        List<Pack> foodPacks = packRepository.findByCategory(Category.음식);
        List<Pack> clothesPacks = packRepository.findByCategory(Category.의류);

        assertThat(foodPacks.isEmpty(), is(true));
        assertThat(clothesPacks.size(), is(2));
    }

    @Test
    @Order(5)
    @DisplayName("짐을 수정할 수 있다.")
    void updatePack() {
        Pack insertPack = packRepository.insert(pack);
        insertPack.setName("바지");

        Pack updatePack = packRepository.update(insertPack);

        assertThat(updatePack.getId(), is(insertPack.getId()));
        assertThat(updatePack.getName(), is(insertPack.getName()));
        assertThat(updatePack.getUpdatedAt(), not(equalTo(insertPack.getUpdatedAt())));
    }

    @Test
    @Order(6)
    @DisplayName("id로 짐을 삭제할 수 있다.")
    void deletePackById() {
        Pack insertPack = packRepository.insert(pack);

        List<Pack> beforeLists = packRepository.findAll();

        packRepository.deleteById(insertPack.getId());

        List<Pack> afterLists = packRepository.findAll();

        assertThat(beforeLists.isEmpty(), is(false));
        assertThat(afterLists.isEmpty(), is(true));
    }

    @Test
    @Order(7)
    @DisplayName("패킹리스트 id로 짐을 조회할 수 있다.")
    void findPackByPackingListId() {
        Pack insertPack = packRepository.insert(pack);

        List<Pack> findPack = packRepository.findByPackingListId(packingList.getId());

        assertThat(findPack.size(), is(1));
        assertThat(findPack.get(0), samePropertyValuesAs(insertPack));
    }

    @Test
    @Order(8)
    @DisplayName("짐의 컬럼 checked를 수정할 수 있다.")
    void updatePackChecked() {
        Pack insertPack = packRepository.insert(pack);
        insertPack.setChecked(true);

        Pack updatePack = packRepository.updateChecked(insertPack.getId(), insertPack.isChecked());

        assertThat(updatePack.getId(), is(insertPack.getId()));
        assertThat(updatePack.isChecked(), is(insertPack.isChecked()));
        assertThat(updatePack.getUpdatedAt(), not(equalTo(insertPack.getUpdatedAt())));
    }
}
