package com.zhat.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zhat.utils.StringUtils;

public class ZLHttpRequestFactory {
	public static ZLHttpRequest createHttpRequestByParsingInput(byte[] content) throws IOException {
		boolean finishedReadingHeader = false;
		String[] lines = StringUtils.readLines(content);
		ZLHttpRequest request = new ZLHttpRequest();
		
		for (int i = 0; i < lines.length; ++i) {
			if (finishedReadingHeader) {
				StringBuilder builder = new StringBuilder();
				for (int j = i; j < lines.length; ++j)
					builder.append(lines[j]);
				
				break;
			}
		}
	}
}
