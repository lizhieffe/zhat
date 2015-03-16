package com.zhat.interfaces;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface IChannelProcessor {
	public void process(SelectionKey key) throws IOException;
}
