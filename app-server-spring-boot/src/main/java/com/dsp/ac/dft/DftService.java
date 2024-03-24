package com.dsp.ac.dft;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dsp.dom.dft.CalcResp;
import com.dsp.dom.dft.DFT.Require;
import com.dsp.dom.dft.DftType;
import com.dsp.dom.dft.Function;
import com.dsp.shared.utils.FileType;
import com.dsp.shared.utils.FileUtils;
import com.dsp.shared.utils.UuidUtils;

@Service
public class DftService implements Require {
	@Value("${dsp-service.host}")
	private String dspServer;
	
	@Override
	public CalcResp calc(Function func1, Function func2, DftType type) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<DftCommand> request = new HttpEntity<DftCommand>(new DftCommand(func1.getA(), func1.getOmega(), func2.getA(), func2.getOmega(), type.value));
		DftResp resp = restTemplate.postForObject(dspServer + "/dft", request, DftResp.class);
		List<String> imageList = Arrays.asList(resp.getListResult()).stream().map(v -> {
			String url = dspServer + "/" + v;
			byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
			return FileUtils.save(imageBytes, "/dft", UuidUtils.generateUUID(), FileType.PNG);
		}).collect(Collectors.toList());
		return new CalcResp(imageList, resp.getAlpha(), resp.getBeta());
	}
	
	

}
