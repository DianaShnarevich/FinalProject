package com.example.fitness.util.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeToMillisCustomSerializer extends StdSerializer<LocalDateTime> {

	public LocalDateTimeToMillisCustomSerializer(Class<LocalDateTime> t){
		super(t);
	}

	public LocalDateTimeToMillisCustomSerializer(){
		this(null);
	}

	@Override
	public void serialize(LocalDateTime value,
	                      JsonGenerator gen,
	                      SerializerProvider arg2) throws IOException, JsonProcessingException {

		gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	}
}
