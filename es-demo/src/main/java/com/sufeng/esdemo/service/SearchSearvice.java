package com.sufeng.esdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.sufeng.esdemo.domain.Goods;
import com.sufeng.esdemo.util.JsoupUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SearchSearvice {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public boolean BulkGoods(String keyword) throws IOException {
        List<Goods> targetGoods = JsoupUtils.getTargetGoods(keyword);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        targetGoods.forEach(goods -> {
            bulkRequest.add(new IndexRequest("jd_goods").source(JSONObject.toJSONString(goods), XContentType.JSON));
        });
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    public List<Map<String, Object>> searchGoods(String keyword, int pageNum, int pageSize) throws IOException {
        // 存储根据keyword搜到的关键词
        List<Map<String,Object>> goods = new ArrayList<>();

        SearchRequest request = new SearchRequest("jd_goods");
        // 构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        highlightBuilder.field("name");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", keyword);
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(request,RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            // 高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField name = highlightFields.get("name");

            Map<String, Object> good = hit.getSourceAsMap();
            if(name != null){
                Text[] fragments = name.getFragments();
                String title = "";
                for (Text fragment : fragments) {
                    title += fragment;
                }
                good.put("name",title);
            }
            goods.add(good);
        }
        return goods;
    }
}
