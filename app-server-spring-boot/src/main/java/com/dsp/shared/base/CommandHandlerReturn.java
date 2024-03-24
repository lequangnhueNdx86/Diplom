package com.dsp.shared.base;

public abstract class CommandHandlerReturn<C, R> {
	public abstract R handle(C command) throws Exception;

}
