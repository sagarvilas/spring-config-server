package com.example.configserver.configserveraws.propertysource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

import com.example.configserver.configserveraws.propertyloader.AwsS3PropertiesLoader;

@Component
public class AwsS3EnvironmentRepository implements EnvironmentRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(AwsS3EnvironmentRepository.class);

	@Autowired
	private AwsS3PropertiesLoader propertiesLoader;

	@Override
	public Environment findOne(String application, String profile, String label) {
		Environment environment = new Environment(application, profile, label);
		Map<String, String> propertyMap = new HashMap();
		try {
			propertyMap.putAll(propertiesLoader.getProperties());
			environment.add(new PropertySource("aws", propertyMap));
		} catch (IOException e) {
			LOGGER.error("error ", e);
		}
		return environment;
	}

}
