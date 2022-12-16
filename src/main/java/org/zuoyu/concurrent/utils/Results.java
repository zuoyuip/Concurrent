package org.zuoyu.concurrent.utils;

import java.util.List;

import lombok.Getter;
import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.lang.NonNull;

/**
 * @Description TODO 包装
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/16 16:49
 * @Version 1.0
 */
public class Results {

	@Getter
	public static class Search {
		/**
		 * 询价结果集
		 */
		private List<SearchResult> searchResults;

		/**
		 * 游标
		 */
		private String scrollId;

		private Search() {

		}

		private Search(List<SearchResult> searchResults, String scrollId) {
			this.searchResults = searchResults;
			this.scrollId = scrollId;
		}

		@NonNull
		public static Search of(List<SearchResult> searchResults, String scrollId) {
			return new Search(searchResults, scrollId);
		}
	}
}
