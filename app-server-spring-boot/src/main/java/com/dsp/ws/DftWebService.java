package com.dsp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.app.command.dft.CalculationDftCommand;
import com.dsp.app.command.dft.CalculationDftCommandHandler;
import com.dsp.app.finder.dft.DftFinder;
import com.dsp.app.finder.dft.DftGetQuery;
import com.dsp.shared.base.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/dft")
public class DftWebService {
	
	@Autowired
	private CalculationDftCommandHandler calculationDftCommandHandler;
	@Autowired
	private DftFinder dftFinder;
	
	@PostMapping("/calc")
	public BaseResponse<?> calcDft(@RequestBody CalculationDftCommand command) throws Exception {
		return BaseResponse.ofSucceeded(calculationDftCommandHandler.handle(command));
	}
	
	@PostMapping("/getAll")
	public BaseResponse<?> getListDft() throws Exception {
		return BaseResponse.ofSucceeded(dftFinder.getListDft(10));
	}
	
	@PostMapping("/get")
	public BaseResponse<?> getDft(@RequestBody DftGetQuery query) {
		return BaseResponse.ofSucceeded(dftFinder.getDft(query.id));
	}

}
