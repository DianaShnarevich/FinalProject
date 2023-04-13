package com.example.fitness.util.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class LongToLocalDateTime implements Converter<Long, LocalDateTime> {
	@Override
	public LocalDateTime convert(Long source) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(source), TimeZone.getDefault().toZoneId());
	}
}
