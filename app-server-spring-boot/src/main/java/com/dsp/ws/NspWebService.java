package com.dsp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.app.command.nsp.CalculationNspCommand;
import com.dsp.app.command.nsp.CalculationNspCommandHandler;
import com.dsp.app.finder.nsp.NspFinder;
import com.dsp.app.finder.nsp.NspGetQuery;
import com.dsp.shared.base.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/nsp")
public class NspWebService {
	@Autowired
	private CalculationNspCommandHandler calculationNspCommandHandler;
	@Autowired
	private NspFinder nspFinder;
	
	@PostMapping("/calc")
	public BaseResponse<?> calc(@RequestBody CalculationNspCommand command) throws Exception {
		return BaseResponse.ofSucceeded(calculationNspCommandHandler.handle(command));
	}
	
	@PostMapping("/getAll")
	public BaseResponse<?> getAll() throws Exception {
		return BaseResponse.ofSucceeded(nspFinder.getListNsp(10));
	}
	
	@PostMapping("/get")
	public BaseResponse<?> getNsp(@RequestBody NspGetQuery query) {
		return BaseResponse.ofSucceeded(nspFinder.getNspById(query.getId()));
	}

}
