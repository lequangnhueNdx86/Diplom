package com.dsp.shared.base;

public abstract class CommandHandler<C> {
	public abstract void handle(C command) throws Exception;
}
