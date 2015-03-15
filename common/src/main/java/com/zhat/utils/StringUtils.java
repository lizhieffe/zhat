package com.zhat.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	public static String[] readLines(byte[] content) throws IOException {
        
        InputStream is = new ByteArrayInputStream(content);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
        List<String> lines = new ArrayList<String>();
        
        String temp = null;
        while((temp = bfReader.readLine()) != null)
            lines.add(temp);
        
        is.close();
        return (String[])lines.toArray();
	}
}
