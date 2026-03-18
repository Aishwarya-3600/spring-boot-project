package com.example.practice01.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.function.Supplier;

@Service
public class EsUtil {

    public Supplier<Query> querySupplier() {
        Supplier<Query> querySupplier = () -> Query.of(q->q.matchAll(matchAllQuery()));
        return querySupplier;
    }

    public MatchAllQuery matchAllQuery() {
        val matchAllQueryVariable = new MatchAllQuery.Builder();
        return matchAllQueryVariable.build();

    }
}
