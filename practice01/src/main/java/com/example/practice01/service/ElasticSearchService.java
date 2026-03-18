package com.example.practice01.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.practice01.util.EsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient esClient;

    @Autowired
    private EsUtil esUtil;

    public String matchAllStudentsWithQuery() throws Exception {
        try {
            log.info("Executing matchAllService in ElasticSearchService");
            Supplier<Query> query = esUtil.querySupplier();
            SearchResponse<Map> searchResponse = esClient.search(
                s -> s.index("students")
                        .query(query.get()),
                Map.class
            );
            log.info("Search query looks like: {}", query.get().toString());
            String searchResponseString = searchResponse.hits().hits().toString();
            log.info("Search response: {}", searchResponseString);
            return searchResponseString;

        } catch (Exception e) {
            log.error("Error executing matchAllService: {}", e);
            throw new Exception(e);
        }
    }

    public String matchAllStudentsWithoutQuery() throws Exception {
        try {
            log.info("Executing matchAllStudentsWithoutQuery in ElasticSearchService");
            SearchResponse<Map> searchResponse = esClient.search(
                s -> s.index("students"),
                Map.class
            );
            String searchResponseString = searchResponse.hits().hits().toString();
            log.info("Search response: {}", searchResponseString);
            return searchResponseString;

        } catch (Exception e) {
            log.error("Error executing matchAllStudentsWithoutQuery: {}", e);
            throw new Exception(e);
        }
    }
}
