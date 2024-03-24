package com.dsp.ac.nsp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dsp.dom.nsp.NSP.Require;
import com.dsp.shared.utils.FileType;
import com.dsp.shared.utils.FileUtils;
import com.dsp.shared.utils.UuidUtils;

@Service
public class NspService implements Require {
	@Value("${dsp-service.host}")
	private String dspServer;

	@Override
	public List<String> calc(Integer frequency, Integer noisyFrequency, Integer numberOfSamples, Integer samplingRate) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<NspCommand> request = new HttpEntity<NspCommand>(new NspCommand(frequency, noisyFrequency, numberOfSamples, samplingRate));
		NspResp imageList = restTemplate.postForObject(dspServer + "/nsp", request, NspResp.class);
		return Arrays.asList(imageList.getListResult()).stream().map(v -> {
			String url = dspServer + "/" + v;
			byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
			return FileUtils.save(imageBytes, "/nsp", UuidUtils.generateUUID(), FileType.PNG);
		}).collect(Collectors.toList());
	}

}
