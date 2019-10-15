// 
// Decompiled by Procyon v0.5.36
// 

package com.jgoodies.forms.layout;

import com.jgoodies.forms.util.LayoutStyle;
import com.jgoodies.forms.factories.FormFactory;
import java.util.Iterator;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public final class LayoutMap
{
    private static final char VARIABLE_PREFIX_CHAR = '$';
    private static final Map COLUMN_ALIASES;
    private static final Map ROW_ALIASES;
    private static LayoutMap root;
    private final LayoutMap parent;
    private final Map columnMap;
    private final Map columnMapCache;
    private final Map rowMap;
    private final Map rowMapCache;
    
    public LayoutMap() {
        this(getRoot());
    }
    
    public LayoutMap(final LayoutMap parent) {
        this.parent = parent;
        this.columnMap = new HashMap();
        this.rowMap = new HashMap();
        this.columnMapCache = new HashMap();
        this.rowMapCache = new HashMap();
    }
    
    public static LayoutMap getRoot() {
        if (LayoutMap.root == null) {
            LayoutMap.root = createRoot();
        }
        return LayoutMap.root;
    }
    
    public boolean columnContainsKey(final String key) {
        final String resolvedKey = this.resolveColumnKey(key);
        return this.columnMap.containsKey(resolvedKey) || (this.parent != null && this.parent.columnContainsKey(resolvedKey));
    }
    
    public String columnGet(final String key) {
        final String resolvedKey = this.resolveColumnKey(key);
        final String cachedValue = this.columnMapCache.get(resolvedKey);
        if (cachedValue != null) {
            return cachedValue;
        }
        String value = this.columnMap.get(resolvedKey);
        if (value == null && this.parent != null) {
            value = this.parent.columnGet(resolvedKey);
        }
        if (value == null) {
            return null;
        }
        final String expandedString = this.expand(value, true);
        this.columnMapCache.put(resolvedKey, expandedString);
        return expandedString;
    }
    
    public String columnPut(final String key, final String value) {
        final String resolvedKey = this.resolveColumnKey(key);
        if (value == null) {
            throw new NullPointerException("The column expression value must not be null.");
        }
        this.columnMapCache.clear();
        return this.columnMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
    }
    
    public String columnPut(final String key, final ColumnSpec value) {
        return this.columnPut(key, value.encode());
    }
    
    public String columnPut(final String key, final Size value) {
        return this.columnPut(key, value.encode());
    }
    
    public String columnRemove(final String key) {
        final String resolvedKey = this.resolveColumnKey(key);
        this.columnMapCache.clear();
        return this.columnMap.remove(resolvedKey);
    }
    
    public boolean rowContainsKey(final String key) {
        final String resolvedKey = this.resolveRowKey(key);
        return this.rowMap.containsKey(resolvedKey) || (this.parent != null && this.parent.rowContainsKey(resolvedKey));
    }
    
    public String rowGet(final String key) {
        final String resolvedKey = this.resolveRowKey(key);
        final String cachedValue = this.rowMapCache.get(resolvedKey);
        if (cachedValue != null) {
            return cachedValue;
        }
        String value = this.rowMap.get(resolvedKey);
        if (value == null && this.parent != null) {
            value = this.parent.rowGet(resolvedKey);
        }
        if (value == null) {
            return null;
        }
        final String expandedString = this.expand(value, false);
        this.rowMapCache.put(resolvedKey, expandedString);
        return expandedString;
    }
    
    public String rowPut(final String key, final String value) {
        final String resolvedKey = this.resolveRowKey(key);
        if (value == null) {
            throw new NullPointerException("The row expression value must not be null.");
        }
        this.rowMapCache.clear();
        return this.rowMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
    }
    
    public String rowPut(final String key, final RowSpec value) {
        return this.rowPut(key, value.encode());
    }
    
    public String rowPut(final String key, final Size value) {
        return this.rowPut(key, value.encode());
    }
    
    public String rowRemove(final String key) {
        final String resolvedKey = this.resolveRowKey(key);
        this.rowMapCache.clear();
        return this.rowMap.remove(resolvedKey);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(super.toString());
        buffer.append("\n  Column associations:");
        for (final Map.Entry name : this.columnMap.entrySet()) {
            buffer.append("\n    ");
            buffer.append(name.getKey());
            buffer.append("->");
            buffer.append(name.getValue());
        }
        buffer.append("\n  Row associations:");
        for (final Map.Entry name : this.rowMap.entrySet()) {
            buffer.append("\n    ");
            buffer.append(name.getKey());
            buffer.append("->");
            buffer.append(name.getValue());
        }
        return buffer.toString();
    }
    
    String expand(final String expression, final boolean horizontal) {
        int cursor = 0;
        int start = expression.indexOf(36, cursor);
        if (start == -1) {
            return expression;
        }
        final StringBuffer buffer = new StringBuffer();
        do {
            buffer.append(expression.substring(cursor, start));
            final String variableName = this.nextVariableName(expression, start);
            buffer.append(this.expansion(variableName, horizontal));
            cursor = start + variableName.length() + 1;
            start = expression.indexOf(36, cursor);
        } while (start != -1);
        buffer.append(expression.substring(cursor));
        return buffer.toString();
    }
    
    private String nextVariableName(final String expression, final int start) {
        final int length = expression.length();
        if (length <= start) {
            FormSpecParser.fail(expression, start, "Missing variable name after variable char '$'.");
        }
        if (expression.charAt(start + 1) == '{') {
            final int end = expression.indexOf(125, start + 1);
            if (end == -1) {
                FormSpecParser.fail(expression, start, "Missing closing brace '}' for variable.");
            }
            return expression.substring(start + 1, end + 1);
        }
        int end;
        for (end = start + 1; end < length && Character.isUnicodeIdentifierPart(expression.charAt(end)); ++end) {}
        return expression.substring(start + 1, end);
    }
    
    private String expansion(final String variableName, final boolean horizontal) {
        final String key = stripBraces(variableName);
        final String expansion = horizontal ? this.columnGet(key) : this.rowGet(key);
        if (expansion == null) {
            final String orientation = horizontal ? "column" : "row";
            throw new IllegalArgumentException("Unknown " + orientation + " layout variable \"" + key + "\"");
        }
        return expansion;
    }
    
    private static String stripBraces(final String variableName) {
        return (variableName.charAt(0) == '{') ? variableName.substring(1, variableName.length() - 1) : variableName;
    }
    
    private String resolveColumnKey(final String key) {
        if (key == null) {
            throw new NullPointerException("The key must not be null.");
        }
        final String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
        final String defaultKey = LayoutMap.COLUMN_ALIASES.get(lowercaseKey);
        return (defaultKey == null) ? lowercaseKey : defaultKey;
    }
    
    private String resolveRowKey(final String key) {
        if (key == null) {
            throw new NullPointerException("The key must not be null.");
        }
        final String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
        final String defaultKey = LayoutMap.ROW_ALIASES.get(lowercaseKey);
        return (defaultKey == null) ? lowercaseKey : defaultKey;
    }
    
    private static LayoutMap createRoot() {
        final LayoutMap map = new LayoutMap(null);
        map.columnPut("label-component-gap", new String[] { "lcg", "lcgap" }, FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
        map.columnPut("related-gap", new String[] { "rg", "rgap" }, FormFactory.RELATED_GAP_COLSPEC);
        map.columnPut("unrelated-gap", new String[] { "ug", "ugap" }, FormFactory.UNRELATED_GAP_COLSPEC);
        map.columnPut("button", new String[] { "b" }, FormFactory.BUTTON_COLSPEC);
        map.columnPut("growing-button", new String[] { "gb" }, FormFactory.GROWING_BUTTON_COLSPEC);
        map.columnPut("dialog-margin", new String[] { "dm", "dmargin" }, ColumnSpec.createGap(LayoutStyle.getCurrent().getDialogMarginX()));
        map.columnPut("tabbed-dialog-margin", new String[] { "tdm", "tdmargin" }, ColumnSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginX()));
        map.columnPut("glue", FormFactory.GLUE_COLSPEC.toShortString());
        map.rowPut("related-gap", new String[] { "rg", "rgap" }, FormFactory.RELATED_GAP_ROWSPEC);
        map.rowPut("unrelated-gap", new String[] { "ug", "ugap" }, FormFactory.UNRELATED_GAP_ROWSPEC);
        map.rowPut("narrow-line-gap", new String[] { "nlg", "nlgap" }, FormFactory.NARROW_LINE_GAP_ROWSPEC);
        map.rowPut("line-gap", new String[] { "lg", "lgap" }, FormFactory.LINE_GAP_ROWSPEC);
        map.rowPut("paragraph-gap", new String[] { "pg", "pgap" }, FormFactory.PARAGRAPH_GAP_ROWSPEC);
        map.rowPut("dialog-margin", new String[] { "dm", "dmargin" }, RowSpec.createGap(LayoutStyle.getCurrent().getDialogMarginY()));
        map.rowPut("tabbed-dialog-margin", new String[] { "tdm", "tdmargin" }, RowSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginY()));
        map.rowPut("button", new String[] { "b" }, FormFactory.BUTTON_ROWSPEC);
        map.rowPut("glue", FormFactory.GLUE_ROWSPEC);
        return map;
    }
    
    private void columnPut(final String key, final String[] aliases, final ColumnSpec value) {
        this.ensureLowerCase(key);
        this.columnPut(key, value);
        for (int i = 0; i < aliases.length; ++i) {
            this.ensureLowerCase(aliases[i]);
            LayoutMap.COLUMN_ALIASES.put(aliases[i], key);
        }
    }
    
    private void rowPut(final String key, final String[] aliases, final RowSpec value) {
        this.ensureLowerCase(key);
        this.rowPut(key, value);
        for (int i = 0; i < aliases.length; ++i) {
            this.ensureLowerCase(aliases[i]);
            LayoutMap.ROW_ALIASES.put(aliases[i], key);
        }
    }
    
    private void ensureLowerCase(final String str) {
        final String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (!lowerCase.equals(str)) {
            throw new IllegalArgumentException("The string \"" + str + "\" should be lower case.");
        }
    }
    
    static {
        COLUMN_ALIASES = new HashMap();
        ROW_ALIASES = new HashMap();
        LayoutMap.root = null;
    }
}
