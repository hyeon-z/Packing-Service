package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PackJdbcRepository implements PackRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Pack> packRowMapper = (rs, rowNum) -> new Pack(
            rs.getLong("id"),
            rs.getLong("packing_list_id"),
            rs.getString("name"),
            Category.valueOf(rs.getString("category")),
            rs.getBoolean("checked"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
    );

    public PackJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pack update(Pack pack) {
        return null;
    }

    @Override
    public List<Pack> findByCategory(Category category) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM pack", Collections.emptyMap());
    }

    @Override
    public List<Pack> findAll() {
        return jdbcTemplate.query("SELECT * FROM pack", packRowMapper);
    }

    @Override
    public Pack insert(Pack pack) {
        int update = jdbcTemplate.update("INSERT INTO pack(packing_list_id, name, category) "
                + "VALUES (:packingListId, :name, :category)", toParamMap(pack)
        );

        if (update != 1) {
            logger.error("Packi의 insert가 제대로 되지 않았습니다.");
            throw new RuntimeException("Pack의 insert가 제대로 되지 않았습니다.");
        }
        return pack;
    }

    private Map<String, Object> toParamMap(Pack pack) {
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("packingListId", pack.getPackingListId());
        paramMap.put("name", pack.getName());
        paramMap.put("category", pack.getCategory().toString());

        return paramMap;
    }
}
