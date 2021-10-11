package com.ay.filter;

import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {

        String value = getHeader(name);
        return stripXSS(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return stripXSS(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null)
            return null;
        int count = values.length;

        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;

    }

    public String stripXSS(String value) {
        if (value != null) {
            value = StringEscapeUtils.escapeHtml(value);
            String str = StringEscapeUtils.unescapeHtml(value);
            value = StringEscapeUtils.escapeSql(value);
            value = StringEscapeUtils.escapeJavaScript(value);
        }
        return value;
    }
}
