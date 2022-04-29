package com.trainingcentertastic.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

public class TimeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String time = "<h5>Time : " + gregorianCalendar.getTime() + " </b></h5>";

        try {
            JspWriter out = pageContext.getOut();
            out.print(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
