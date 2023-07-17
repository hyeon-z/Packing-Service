package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_10;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class PackingListJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void setup() {
        var config = aMysqldConfig(v5_7_10)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-packing_service", ScriptResolver.classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    static void cleanup() {
        embeddedMysql.stop();
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
        assertThat(insertList, equalToObject(packingList));
    }

    @Test
    @Order(2)
    @DisplayName("null 정보를 넣은 패킹리스트를 추가할 수 있다.")
    void insertNullInfoPackingList() {
        PackingList packingList = new PackingList("대만여행", null, LocalDate.now());
        PackingList insertList = repository.insert(packingList);

        List<PackingList> packingLists = repository.findAll();

        assertThat(packingLists.isEmpty(), is(false));
        assertThat(insertList, equalToObject(packingList));
    }
}
