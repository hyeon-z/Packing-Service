package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PackingListJdbcRepositoryTest {

    static EmbeddedDatabase embeddedDatabase;

    @BeforeAll
    static void setup() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .build();
    }

    @AfterAll
    static void cleanup() {
        embeddedDatabase.shutdown();
    }

    @Autowired
    PackingListRepository repository;

    @BeforeEach
    void dataInitialize() {
        repository.deleteAll();
    }

    private final PackingList packingList = new PackingList("대만여행", "친구들과 간다.", LocalDate.now());

    @Test
    @Order(1)
    @DisplayName("모든 정보를 넣은 패킹리스트를 추가할 수 있다.")
    void insertAllInfoPackingList() {
        PackingList insertList = repository.insert(packingList);

        List<PackingList> allLists = repository.findAll();

        assertThat(allLists.isEmpty(), is(false));
        assertThat(insertList, samePropertyValuesAs(packingList));
    }

    @Test
    @Order(2)
    @DisplayName("null 정보를 넣은 패킹리스트를 추가할 수 있다.")
    void insertNullInfoPackingList() {
        PackingList nullInfoList = new PackingList("대만여행", null, LocalDate.now());
        PackingList insertList = repository.insert(nullInfoList);

        List<PackingList> allLists = repository.findAll();

        assertThat(allLists.isEmpty(), is(false));
        assertThat(insertList, samePropertyValuesAs(nullInfoList));
    }


    @Test
    @Order(3)
    @DisplayName("모든 패킹리스트를 조회할 수 있다.")
    void findAllPackingList() {
        repository.insert(packingList);

        List<PackingList> allLists = repository.findAll();

        assertThat(allLists.size(), is(1));
    }

    @Test
    @Order(4)
    @DisplayName("id 패킹리스트를 조회할 수 있다.")
    void findPackingListById() {
        repository.insert(packingList);
        List<PackingList> allLists = repository.findAll();

        Optional<PackingList> findList = repository.findById(allLists.get(0).getId());

        assertThat(findList.isPresent(), is(true));
        assertThat(findList.get(), samePropertyValuesAs(allLists.get(0)));
    }

    @Test
    @Order(5)
    @DisplayName("id 패킹리스트를 삭제할 수 있다.")
    void deletePackingListById() {
        repository.insert(packingList);
        List<PackingList> beforeLists = repository.findAll();

        repository.deleteById(beforeLists.get(0).getId());
        List<PackingList> afterLists = repository.findAll();

        assertThat(afterLists.isEmpty(), is(true));
    }

    @Test
    @Order(6)
    @DisplayName("모든 패킹리스트를 삭제할 수 있다.")
    void deleteAllPackingList() {
        repository.insert(packingList);
        repository.insert(packingList);

        repository.deleteAll();
        List<PackingList> allLists = repository.findAll();

        assertThat(allLists.isEmpty(), is(true));
    }
}
