package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PackingListJdbcRepository implements PackingListRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<PackingList> packingListRowMapper = (rs, rowNum) -> new PackingList(
            rs.getString("title"),
            rs.getString("description"),
            rs.getDate("departure_date").toLocalDate()
    );

    public PackingListJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PackingList> findAll() {
        return jdbcTemplate.query("SELECT * FROM packing_list", packingListRowMapper);
    }

    @Override
    public PackingList update(PackingList packingList) {
        return null;
    }

    @Override
    public Optional<PackingList> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete() {

    }

    @Override
    public PackingList insert(PackingList packingList) {
        int update = jdbcTemplate.update("INSERT INTO packing_list (title, description, departure_date)"
                + " VALUES (:title, :description, :departureDate)", toParamMap(packingList));

        if (update != 1) {
            logger.error("PackingList의 insert가 제대로 되지 않았습니다.");
            throw new RuntimeException("PackingList의 insert가 제대로 되지 않았습니다.");
        }
        return packingList;
    }

    private Map<String, Object> toParamMap(PackingList packingList) {
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("title", packingList.getTitle());
        paramMap.put("description", packingList.getDescription());
        paramMap.put("departureDate", packingList.getDepartureDate());

        return paramMap;
    }
}
