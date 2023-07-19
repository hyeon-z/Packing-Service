package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;
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
    @Transactional
    public Pack update(Pack pack) {
        int update = jdbcTemplate.update("UPDATE pack SET name = :name, category = :category, checked = :checked, updated_at = CURRENT_TIMESTAMP WHERE id = :id",
                toUpdateParamMap(pack)
        );

        if (update != 1) {
            logger.error("Pack의 update가 제대로 되지 않았습니다.");
            throw new RuntimeException("Pack의 update가 제대로 되지 않았습니다.");
        }

        return findById(pack.getId()).orElseThrow(() -> new RuntimeException("Pack을 찾을 수 없습니다."));
    }

    @Override
    public Optional<Pack> findById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM pack WHERE id = :id",
                Collections.singletonMap("id", id), packRowMapper));
    }

    @Override
    public List<Pack> findByPackingListId(long packingListId) {
        return jdbcTemplate.query("SELECT * FROM pack WHERE packing_list_id = :packingListId",
                Collections.singletonMap("packingListId", packingListId), packRowMapper);
    }

    @Override
    public List<Pack> findByCategory(Category category) {
        return jdbcTemplate.query("SELECT * FROM pack WHERE category = :category",
                Collections.singletonMap("category", category.toString()),
                packRowMapper);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM pack WHERE id = :id", Collections.singletonMap("id", id));
    }

    @Override
    @Transactional
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM pack", Collections.emptyMap());
    }

    @Override
    public List<Pack> findAll() {
        return jdbcTemplate.query("SELECT * FROM pack", packRowMapper);
    }

    @Override
    @Transactional
    public Pack insert(Pack pack) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("pack")
                .usingGeneratedKeyColumns("id");

        try {
            SqlParameterSource params = new BeanPropertySqlParameterSource(pack);

            long id = jdbcInsert.executeAndReturnKey(params).longValue();

            return new Pack(
                    id,
                    pack.getPackingListId(),
                    pack.getName(),
                    pack.getCategory(),
                    pack.isChecked(),
                    pack.getCreatedAt(),
                    pack.getUpdatedAt()
            );

        } catch (DuplicateKeyException e) {
            logger.error("데이터베이스에 이미 중복된 키 값이 존재합니다.");
            throw new RuntimeException("데이터베이스에 이미 중복된 키 값이 존재합니다.", e);
        }
    }

    private Map<String, Object> toUpdateParamMap(Pack pack) {
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("name", pack.getName());
        paramMap.put("category", pack.getCategory().toString());
        paramMap.put("checked", pack.isChecked());
        paramMap.put("id", pack.getId());

        return paramMap;
    }
}
