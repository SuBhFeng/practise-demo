package com.sufeng.esdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
class EsApiTests {

	@Autowired
	RestHighLevelClient restHighLevelClient;

	/**
	 * 创建索引
	 * @throws IOException
	 */
	@Test
	void contextLoads() throws IOException {
		// 创建索引
		CreateIndexRequest test1 = new CreateIndexRequest("test1");
		// 使用restHighLevelClient请求并获取响应结果
		CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(test1, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse);
	}
	/**
	 * 测试索引是否存在
	 * @throws IOException
	 */
	@Test
	void testIndexExist() throws IOException {
		GetIndexRequest request = new GetIndexRequest("test1");
		boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}
	/**
	 * 删除索引
	 * @throws IOException
	 */
	@Test
	void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("test1");
		AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());
	}
	/**
	 * 测试添加文档
	 * @throws IOException
	 */
	@Test
	void testAddDocument() throws IOException {
		// 1创建对象
		User user = new User("kk", 10);
		// 2创建请求  注意索引不能包含大写字母
		IndexRequest request = new IndexRequest("users");
		// 3规则 put /users/_doc/1
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");
		// 4将数据放到json
		request.source(JSONObject.toJSONString(user), XContentType.JSON);
		// 5客户端发送请求，获取响应结果
		IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
		System.out.println(response.toString());
		System.out.println(response.status());
	}

	/**
	 * 测试获取文档
	 * @throws IOException
	 */
	@Test
	void testExistDocument() throws IOException {
		GetRequest request = new GetRequest("users","1");
		GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
		System.out.println(response.getSourceAsString());
		System.out.println(response);
	}
	/**
	 * 测试修改文档
	 * @throws IOException
	 */
	@Test
	void testUpdateDocument() throws IOException {
		UpdateRequest request = new UpdateRequest("users", "1");
		request.timeout("1s");
		// 修改数据
		User user = new User("hh", 11);
		request.doc(JSONObject.toJSONString(user),XContentType.JSON);

		UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
		System.out.println(response);
	}

	/**
	 * 测试删除文档
	 * @throws IOException
	 */
	@Test
	void testDeleteDocument() throws IOException {
		DeleteRequest request = new DeleteRequest("users","1");
		request.timeout("1s");
		DeleteResponse response = restHighLevelClient.delete(request,RequestOptions.DEFAULT);
		System.out.println(response.status());
		System.out.println(response);
	}

	/**
	 * 测试批量添加文档
	 * @throws IOException
	 */
	@Test
	void testBatchAddDocument() throws IOException {
		BulkRequest request = new BulkRequest();
		request.timeout("1s");
		// 构造批量添加的数据
		ArrayList<User> userlist=new ArrayList<User>();
		userlist.add(new User("user1",5));
		userlist.add(new User("user2",6));
		userlist.add(new User("user3",40));
		userlist.add(new User("user4",25));
		userlist.add(new User("user5",15));
		userlist.add(new User("user6",35));
		// 批量处理请求封装至request
		for (int i = 0; i < userlist.size(); i++) {
			request.add(new IndexRequest("users")
						.id(i+"")
						.source(JSONObject.toJSONString(userlist.get(i)), XContentType.JSON)
			);
		}

		BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
		System.out.println(response);
	}

	/**
	 * 测试搜索查询文档
	 * @throws IOException
	 */
	@Test
	void testSearchDocument() throws IOException {
		SearchRequest request = new SearchRequest("users");
		// 构建搜索条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.highlighter();
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "user1");
		searchSourceBuilder.query(termQueryBuilder);
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		// 条件与请求绑定
		request.source(searchSourceBuilder);
		SearchResponse response = restHighLevelClient.search(request,RequestOptions.DEFAULT);
		// 输出相应结果
		System.out.println(JSON.toJSONString(response.getHits()));
		System.out.println("=========================");
		for (SearchHit hit : response.getHits().getHits()) {
			System.out.println(hit.getSourceAsMap());
		}
	}

}
