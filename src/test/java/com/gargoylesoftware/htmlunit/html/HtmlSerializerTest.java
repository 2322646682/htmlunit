/*
 * Copyright (c) 2002-2019 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.html;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlSerializer.HtmlSerializerTextBuilder;
import com.gargoylesoftware.htmlunit.html.HtmlSerializer.HtmlSerializerTextBuilder.Mode;

/**
 * Tests for {@link HtmlSerializer}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
public class HtmlSerializerTest {

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void normalized() {
        final String ls = System.lineSeparator();

        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("", Mode.NORMALIZE);
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" \t\r\n ", Mode.NORMALIZE);
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.appendTextBlockSeparator();
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.appendTextBlockSeparator();
        serializer.append(" ", Mode.NORMALIZE);
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append(" ", Mode.NORMALIZE);
        assertEquals("", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" a  ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        assertEquals("a", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" a  ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append("  x ", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append("  ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.appendTextNewLine();
        assertEquals(ls, serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.appendTextNewLine();
        serializer.append(" ", Mode.NORMALIZE);
        assertEquals(ls, serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" ", Mode.NORMALIZE);
        serializer.appendTextNewLine();
        assertEquals(ls, serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(" ", Mode.NORMALIZE);
        serializer.appendTextNewLine();
        serializer.append(" ", Mode.NORMALIZE);
        assertEquals(ls, serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.appendTextNewLine();
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextNewLine();
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.appendTextBlockSeparator();
        serializer.appendTextBlockSeparator();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a" + ls + "x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.append("§blank§", Mode.NORMALIZE);
        serializer.append(" ", Mode.NORMALIZE);
        serializer.append("§blank§", Mode.NORMALIZE);
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a   x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextTab();
        serializer.append(" ", Mode.NORMALIZE);
        serializer.appendTextTab();
        serializer.append("x", Mode.NORMALIZE);
        assertEquals("a\t \tx", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("abc", Mode.NORMALIZE);
        assertEquals("abc", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("abc" + (char) 160 + "x", Mode.NORMALIZE);
        assertEquals("abc x", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a     b \t\t\t c \r \r o \n\n\n", Mode.NORMALIZE);
        assertEquals("a b c o", serializer.getText());
    }

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void cleanUp2() {
        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.appendTextBlockSeparator();
        assertEquals("a", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("a", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        serializer.append("  ", Mode.NORMALIZE);
        serializer.appendTextBlockSeparator();
        assertEquals("a", serializer.getText());
    }

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void cleanUpPre() {
        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("  hello \t abc ", Mode.PRESERVE_BLANK_TAB_NEWLINE);
        assertEquals("  hello \t abc ", serializer.getText());
    }

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void cleanUpTextArea() {
        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("  hello \t abc ", Mode.PRESERVE_BLANK_NEWLINE);
        assertEquals("  hello   abc", serializer.getText());
    }

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void cleanUpPerformanceWhitespace() {

        final int length = 80_000;
        final char[] charArray = new char[length];
        Arrays.fill(charArray, ' ');
        charArray[0] = 'a';
        charArray[length - 1] = 'a';
        final String text = new String(charArray);

        final long time = System.currentTimeMillis();
        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append(text, Mode.NORMALIZE);
        serializer.getText();

        final long runTime = System.currentTimeMillis() - time;
        assertTrue("cleanUp() took too much time", runTime < 1_000);
    }

    /**
     * Test {@link HtmlSerializerTextBuilder}.
     */
    @Test
    public void cleanUpPerformanceManyReplaces() {
        final String ls = System.lineSeparator();
        final String expected = StringUtils.repeat("x" + ls, 20_000).trim();

        final long time = System.currentTimeMillis();

        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();

        for (int i = 0; i < 20_000; i++) {
            serializer.append(" x ", Mode.NORMALIZE);
            serializer.appendTextBlockSeparator();
        }

        assertEquals(expected, serializer.getText());

        final long runTime = System.currentTimeMillis() - time;
        assertTrue("cleanUp() took too much time", runTime < 400);
    }

    /**
     * Test {@link HtmlSerializerTextBuilder} special spaces.
     */
    @Test
    public void specialSpaces() {
        HtmlSerializerTextBuilder serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("\u3000", Mode.NORMALIZE);
        assertEquals("\u3000", serializer.getText());

        // real IE treats this as space, will not consider this for performance reasons
        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("\uFEFF", Mode.NORMALIZE);
        assertEquals("\uFEFF", serializer.getText());

        serializer = new HtmlSerializer.HtmlSerializerTextBuilder();
        serializer.append("\u200B", Mode.NORMALIZE);
        assertEquals("\u200B", serializer.getText());
    }
}
