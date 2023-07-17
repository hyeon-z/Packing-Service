package com.hyeonz.packingservice.repository;

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
}
