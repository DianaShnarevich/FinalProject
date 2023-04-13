package com.example.fitness.core.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PageDTO<T> {
	private Integer number;
	private Integer size;
	private Integer total_pages;
	private Long total_elements;
	private boolean first;
	private Integer number_of_elements;
	private boolean last;
	private Set<T> content;

	private PageDTO(PageDTO<T> dto) {
		this.number = dto.number;
		this.size = dto.size;
		this.total_pages = dto.total_pages;
		this.total_elements = dto.total_elements;
		this.first = dto.first;
		this.number_of_elements = dto.number_of_elements;
		this.last = dto.last;
		this.content = dto.content;
	}

	private PageDTO(){

	}

	public PageDTO(T page, T size, T total_eages, T total_elements, T first, T number_of_elements, T last, T auditDTOS) {
	}

	public static <T> PageBuilder<T> builder(){
		return new PageBuilder<T>();
	}

	public static class PageBuilder<T> {

		private final PageDTO<T> page = new PageDTO<>();

		public PageBuilder<T> setNumber(Integer number) {
			page.number = number;
			return this;
		}

		public PageBuilder<T> setSize(Integer size) {
			page.size = size;
			return this;
		}

		public PageBuilder<T> setTotal_pages(Integer total_pages) {
			page.total_pages = total_pages;
			return this;
		}

		public PageBuilder<T> setTotal_elements(Long total_elements) {
			page.total_elements = total_elements;
			return this;
		}

		public PageBuilder<T> setFirst(boolean first) {
			page.first = first;
			return this;
		}

		public PageBuilder<T> setNumber_of_elements(Integer number_of_elements) {
			page.number_of_elements = number_of_elements;
			return this;
		}

		public PageBuilder<T> setLast(boolean last) {
			page.last = last;
			return this;
		}

		public PageBuilder<T> setContent(Set<T> content) {
			page.content = content;
			return this;
		}

		public PageDTO<T> build(){
			return new PageDTO<T>(page);
		}
	}
}
