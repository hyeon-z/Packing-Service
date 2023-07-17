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

        List<PackingList> packingLists = repository.findAll();

        assertThat(packingLists.isEmpty(), is(false));
        assertThat(insertList, samePropertyValuesAs(packingList));
    }

    @Test
    @Order(2)
    @DisplayName("null 정보를 넣은 패킹리스트를 추가할 수 있다.")
    void insertNullInfoPackingList() {
        PackingList nullInfoList = new PackingList("대만여행", null, LocalDate.now());
        PackingList insertList = repository.insert(nullInfoList);

        List<PackingList> packingLists = repository.findAll();

        assertThat(packingLists.isEmpty(), is(false));
        assertThat(insertList, samePropertyValuesAs(nullInfoList));
    }


    @Test
    @Order(3)
    @DisplayName("모든 패킹리스트를 조회할 수 있다.")
    void findAllPackingList() {
        repository.insert(packingList);

        List<PackingList> allPackingList = repository.findAll();

        assertThat(allPackingList.size(), is(1));
    }

    @Test
    @Order(4)
    @DisplayName("id 패킹리스트를 조회할 수 있다.")
    void findPackingListById() {
        repository.insert(packingList);
        List<PackingList> beforeList = repository.findAll();

        Optional<PackingList> list = repository.findById(beforeList.get(0).getId());

        assertThat(list.isPresent(), is(true));
        assertThat(list.get(), samePropertyValuesAs(beforeList.get(0)));
    }

    @Test
    @Order(5)
    @DisplayName("id 패킹리스트를 삭제할 수 있다.")
    void deletePackingListById() {
        repository.insert(packingList);
        List<PackingList> beforeList = repository.findAll();

        repository.deleteById(beforeList.get(0).getId());
        List<PackingList> afterList = repository.findAll();

        assertThat(afterList.isEmpty(), is(true));
    }

    @Test
    @Order(6)
    @DisplayName("모든 패킹리스트를 삭제할 수 있다.")
    void deleteAllPackingList() {
        repository.insert(packingList);
        repository.insert(packingList);

        repository.deleteAll();
        List<PackingList> allList = repository.findAll();

        assertThat(allList.isEmpty(), is(true));
    }
}
