package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class PackingListJdbcRepository implements PackingListRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<PackingList> packingListRowMapper = (rs, rowNum) -> new PackingList(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getDate("departure_date").toLocalDate(),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
    );


    public PackingListJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<PackingList> findAll() {
        return jdbcTemplate.query("SELECT * FROM packing_list", packingListRowMapper);
    }

    @Override
    @Transactional
    public PackingList update(PackingList packingList) {
        var update = jdbcTemplate.update(
                "UPDATE packing_list SET title = :title, description = :description, departure_date = :departureDate, updated_at = CURRENT_TIMESTAMP" +
                        " WHERE id = :id",
                toParamMapWithId(packingList)
        );
        if (update != 1) {
            logger.error("PackingList의 update가 제대로 되지 않았습니다.");
            throw new RuntimeException("PackingList의 update가 제대로 되지 않았습니다.");
        }
        return packingList;
    }

    @Override
    public Optional<PackingList> findById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM packing_list WHERE id = :id",
                Collections.singletonMap("id", id), packingListRowMapper));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM packing_list WHERE id = :id",
                Collections.singletonMap("id", id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM packing_list",
                Collections.emptyMap());
    }

    @Override
    @Transactional
    public PackingList insert(PackingList packingList) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate()).withTableName("packing_list").usingGeneratedKeyColumns("id");

        try {
            SqlParameterSource params = new BeanPropertySqlParameterSource(packingList);

            long id = jdbcInsert.executeAndReturnKey(params).longValue();

            return new PackingList(id, packingList.getTitle(), packingList.getDescription(), packingList.getDepartureDate(), packingList.getCreatedAt(), packingList.getUpdatedAt());

        } catch (DuplicateKeyException e) {
            logger.error("데이터베이스에 이미 중복된 키 값이 존재합니다.");
            throw new RuntimeException("데이터베이스에 이미 중복된 키 값이 존재합니다.", e);
        }
    }

    private Map<String, Object> toParamMapWithId(PackingList packingList) {
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("id", packingList.getId());
        paramMap.put("title", packingList.getTitle());
        paramMap.put("description", packingList.getDescription());
        paramMap.put("departureDate", packingList.getDepartureDate());

        return paramMap;
    }
}
