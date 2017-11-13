package com.wikse.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.jibx.schema.codegen.extend.NameConverter;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static NameConverter nameTools = new DefaultNameConverter();
	
    public static void main( String[] args ) {
    	String input = null;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese texto:");
        
        try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        Gson gson = new Gson();
        
        CountWordsDelegate countWordsDelegate = new CountWordsDelegate(nameTools);
        System.out.println(gson.toJson(countWordsDelegate.retrieveJsonStructureWord(input)));
    }
	
}
