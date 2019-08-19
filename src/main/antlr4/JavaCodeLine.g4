grammar JavaCodeLine;

@header {
	package com.jhallat.codeviewide.parser.javacodeline;
}

codeLine: shortcutLine
        | instructionLine;
        
shortcutLine: SHORTCUT_INDICATOR shortcut;

instructionLine: instruction;

shortcut : SHORTCUT;

instruction : INSTRUCTION;

fragment LOWERCASE : [a-z];

fragment UPPERCASE : [A-Z];

fragment UNDERSCORE : '_';

SHORTCUT_INDICATOR: '.';

SHORTCUT_PRINTLN: 'println';

SHORTCUT : SHORTCUT_PRINTLN;

INSTRUCTION: (LOWERCASE | UPPERCASE | UNDERSCORE)+;        