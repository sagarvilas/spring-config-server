package com.example.configserver.configserveraws.propertyloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class AwsS3PropertiesLoader {
	String bucketName = "<bucket-name>";
	String key = "<object-name>";

	public Map<? extends String, ? extends String> getProperties() throws IOException {
		S3Object fullObject = null;
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new ProfileCredentialsProvider()).build();
		fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
		BufferedReader reader = new BufferedReader(new InputStreamReader(fullObject.getObjectContent()));
		String line = null;
		Map<String, String> properties = new HashMap();
		while ((line = reader.readLine()) != null) {
			properties.put(line.split("=")[0], line.split("=")[1]);
		}
		return properties;
	}

}
