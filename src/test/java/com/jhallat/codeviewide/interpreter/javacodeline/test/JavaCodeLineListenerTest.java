package com.jhallat.codeviewide.interpreter.javacodeline.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jhallat.codeviewide.interpreter.javacodeline.JavaCodeLineListener;
import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineLexer;
import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineParser;

public class JavaCodeLineListenerTest {

	
	@Test
	@DisplayName(".println X shortcut should expand to System.out.println(X)")
	public void testPrintlnX() {
		
		String lineContent = ".println X";
		JavaCodeLineLexer javaCodeLineLexer = new JavaCodeLineLexer(CharStreams.fromString(lineContent));
		CommonTokenStream tokens = new CommonTokenStream(javaCodeLineLexer);
		JavaCodeLineParser parser = new JavaCodeLineParser(tokens);
		ParseTree tree = parser.codeLine();
		ParseTreeWalker walker = new ParseTreeWalker();
		JavaCodeLineListener listener = new JavaCodeLineListener();
		walker.walk(listener, tree);
		String expected = "System.out.println(X);";
		String actual = listener.getTranslatedLine();
		assertEquals(expected, actual);
	}
	
}
