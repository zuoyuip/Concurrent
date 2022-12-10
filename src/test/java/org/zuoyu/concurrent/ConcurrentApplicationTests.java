package org.zuoyu.concurrent;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.repository.SearchResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConcurrentApplicationTests {

	@Autowired
	private SearchResultRepository searchResultRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void saveSearchResult() {
		SearchResult searchResult = new SearchResult();
		searchResult.setDateTime(new Date());
		searchResult.setStatus(Status.SUCCESS);
		searchResult.setTimeOut(1000L);
		SearchResult save = searchResultRepository.save(searchResult);
		System.out.println(searchResult);
	}

	@Test
	void selectSearchResult() {
		searchResultRepository.findAll().forEach(System.out::println);
	}

	@Test
	void saveRedisTemplate() {

	}
}
