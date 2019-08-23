package com.jhallat.codeviewide.interpreter.javacodeline;

import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineBaseListener;
import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineParser.CodeLineContext;
import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineParser.ShortcutLineContext;
import com.jhallat.codeviewide.parser.javacodeline.JavaCodeLineParser.ShortcutPrintlnContext;

public class JavaCodeLineListener extends JavaCodeLineBaseListener {

	private StringBuffer translatedLine = new StringBuffer();
	
	@Override
	public void enterCodeLine(CodeLineContext ctx) {
		// TODO Auto-generated method stub
		super.enterCodeLine(ctx);
	}

	
	public String getTranslatedLine() {
		return translatedLine.toString();
	}


	@Override
	public void enterShortcutPrintln(ShortcutPrintlnContext ctx) {
		// TODO Auto-generated method stub
		super.enterShortcutPrintln(ctx);
		translatedLine.append("System.out.println(");
		translatedLine.append(ctx.Identifier());
	}


	@Override
	public void exitShortcutLine(ShortcutLineContext ctx) {
		// TODO Auto-generated method stub
		super.exitShortcutLine(ctx);
		translatedLine.append(");");
	}



	
}
