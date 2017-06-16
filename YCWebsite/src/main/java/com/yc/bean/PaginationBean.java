package com.yc.bean;

import java.io.Serializable;
import java.util.List;
/***
 * 通用分页bean
 * @author Administrator
 *
 * @param <T>
 */
public class PaginationBean<T> implements Serializable {
	private static final long serialVersionUID = 4251560620107633888L;
		
		//请求参数
		private Integer currPage = 1;	//当前页
		private Integer totalPage;		//数据的总条数
		private List<T> rows;	//分页的数据
		private Integer total;		//总页数
		
		public PaginationBean() {
		}

		public PaginationBean(Integer currPage, Integer totalPage, List<T> rows, Integer total) {
			super();
			this.currPage = currPage;
			this.totalPage = totalPage;
			this.rows = rows;
			this.total = total;
		}

		public Integer getCurrPage() {
			return currPage;
		}

		public void setCurrPage(Integer currPage) {
			this.currPage = currPage;
		}

		public Integer getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}

		public List<T> getRows() {
			return rows;
		}

		public void setRows(List<T> rows) {
			this.rows = rows;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "PaginationBean [currPage=" + currPage + ", totalPage=" + totalPage + ", rows=" + rows + ", total="
					+ total + "]";
		}

		
}
