package com.chao.elastic;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.chao.BaseTest;
import com.kang.mapper.elastic.EsMapper;

import cn.hutool.core.lang.Console;

public class EsUserTest extends BaseTest{

	@Autowired
	private EsMapper esMapper;
	
	/**
	 *<p>Title: testCreateIndexOne</p> 
	 *<p>Description: 测试添加</p>
	 */
	@Test
	public void testCreateIndexOne() {
		 String index="testdb";  //必须为小写
         String type="userinfo";
         JSONObject obj=new JSONObject();
         obj.put("id", 1001);
         obj.put("name","zs");
         obj.put("age",23);
         obj.put("sex","女");
         String [] tags={"标签1","标签2"};
         obj.put("tags",tags);
         esMapper.createIndexOne(index,type,obj);
	}
	
	/**
	 *<p>Title: testFindById</p> 
	 *<p>Description: 测试id查询</p>
	 */
	@Test
	public void testFindById() {
		String index="testdb";  //必须为小写
        String type="userinfo";
        String id="r9r9QXABPjMR5X3kTMrq";
        GetResponse data = esMapper.findById(index,type,id);
        System.out.println("查询结果index："+data.getIndex());
        System.out.println("查询结果type："+data.getType());
        System.out.println("查询结果id："+data.getId());
        System.out.println("查询结果source："+data.getSource());
	}
	
	/**
	 *<p>Title: findByIdexcludes</p> 
	 *<p>Description: 测试条件查询</p>
	 */
	@Test
    public void findByIdexcludes(){
        String index="testdb";  //必须为小写
        String type="userinfo";
        String id="r9r9QXABPjMR5X3kTMrq";
        String [] includes={"name","sex","age"};//不过滤
        String [] excludes={"tags"}; //过滤字段

        System.out.println("查询结果："+esMapper.findById(index,type,id,includes,excludes));
    }
	
	/**
	 *<p>Title: getAllIndex</p> 
	 *<p>Description: 测试查询所有</p>
	 */
	@Test
    public void getAllIndex(){
        String index="testdb";  //必须为小写
        String type="userinfo";
        SearchResponse allIndex = esMapper.getAllIndex(index,type);
        System.out.println("查询结果："+allIndex);
        SearchHits hits = allIndex.getHits();
        SearchHit[] hits2 = hits.getHits();
        for(SearchHit hi : hits2) {
        	Console.log(hi.getSourceAsMap());
        }
        String [] includes={"name","sex",};//不过滤
        String [] excludes={"tags","age"}; //过滤字段
        SearchResponse allIndex2 = esMapper.getAllIndex(index,type,includes,excludes);
        System.out.println("指定过滤字段查询结果："+allIndex2);
    }
	
	/**
	 *<p>Title: getAllIndexByFiled3</p> 
	 *<p>Description: 测试条件查询</p>
	 */
    @Test
    public  void  getAllIndexByFiled3(){
//        String index="testdb";
//        String type="userinfo";
//   
//        SearchSourceBuilder search3 = new SearchSourceBuilder();
////        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name","qxw");
//         //在匹配查询上启用模糊匹配
////        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
////        //在匹配查询上设置前缀长度选项
////        matchQueryBuilder.prefixLength(3); 
////        //设置最大扩展选项以控制查询的模糊过程
////        matchQueryBuilder.maxExpansions(10); 
//        
//        
//        //默认情况下，搜索请求会返回文档的内容,设置fasle不会返回窝
////        search3.fetchSource(false);
//        
//        //也接受一个或多个通配符模式的数组，以控制以更精细的方式包含或排除哪些字段
//        String[] includeFields = new String[] {"name", "age", "tags"};
//        String[] excludeFields = new String[] {"_type","_index"};
//        search3.fetchSource(includeFields, excludeFields);
//        
//        //指定排序
//        search3.sort(new FieldSortBuilder("age").order(SortOrder.DESC));
//
//
//
//
//         //启用模糊查询 fuzziness(Fuzziness.AUTO)
////        search3.query(QueryBuilders.matchQuery("name","qxw").fuzziness(Fuzziness.AUTO));
//
//        //模糊查询，?匹配单个字符，*匹配多个字符
////        search3.query(QueryBuilders.wildcardQuery("name","*qxw*"));
//
//        //搜索name中或tags  中包含有qxw的文档（必须与music一致)
////        search3.query(QueryBuilders.multiMatchQuery("qxw","name","tags"));
//
//
//
//        //多条件查询 相当于and
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //查询age=32
//        TermQueryBuilder termQuery=QueryBuilders.termQuery("age",25);
//
//        //匹配多个值  相当于sql 中in(....)操作
//        TermsQueryBuilder termQuerys=QueryBuilders.termsQuery("_id","sNoxQnABPjMR5X3kGMrq","r9r9QXABPjMR5X3kTMrq");
//
//        //模糊查询name中包含qxw
//        WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*qxw*");
//
//        boolQueryBuilder.must(termQuery);
//        boolQueryBuilder.must(queryBuilder);
//        boolQueryBuilder.must(termQuerys);
//
////        //设置from确定结果索引的选项以开始搜索。默认为0。
////        search3.from(0);
////        //设置size确定要返回的搜索命中数的选项。默认为10。
////        search3.size(1);
//
//        search3.query(boolQueryBuilder);
//
//        SearchResponse result=esMapper.getAllIndex(index,type,search3);
//        //解析SearchHits
//        SearchHits hits = result.getHits();
//        long totalHits = hits.getTotalHits();
//        float maxScore = hits.getMaxScore();
//
//        SearchHit[] searchHits = hits.getHits();
//        for (SearchHit hit : searchHits) {
//            String indexs = hit.getIndex();
//            String types = hit.getType();
//            String ids = hit.getId();
//
//            String sourceAsString = hit.getSourceAsString();
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            System.out.println("id ："+ids+sourceAsMap.toString());
//        }
//
//        System.out.println("查询结果："+esMapper.getAllIndex(index,type,search3));
    }
}
