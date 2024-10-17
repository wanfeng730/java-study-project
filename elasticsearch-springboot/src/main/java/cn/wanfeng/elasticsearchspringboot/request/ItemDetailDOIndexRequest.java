package cn.wanfeng.elasticsearchspringboot.request;

import cn.wanfeng.elasticsearchspringboot.object.ItemDetailDO;
import jakarta.annotation.Resource;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date: 2024-07-25 16:53
 * @author: luozh
 * @description:
 * @since:
 */
@Service
public class ItemDetailDOIndexRequest {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<ItemDetailDO> findItemDetailDO() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        TermsAggregationBuilder agg = AggregationBuilders.terms("cnt").field("department_name");
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withFilter(boolQueryBuilder)
                .addAggregation(agg)
                .withPageable(PageRequest.of(0, 100000))
                .build();
        SearchHits<ItemDetailDO> searchHits = elasticsearchRestTemplate.search(build, ItemDetailDO.class);
        List<SearchHit<ItemDetailDO>> searchHitList = searchHits.getSearchHits();
        return searchHitList.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
