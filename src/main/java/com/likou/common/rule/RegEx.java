package com.likou.common.rule;

import sun.misc.Regexp;

import java.util.regex.Pattern;

/**
 * Created by jiangli on 16/9/22.
 */
public class RegEx {

    /**
     * email正则表达式
     */
    public final static Pattern EMAILREGEX = Pattern.compile("^([a-zA-Z0-9_])+\\@(([a-zA-Z0-9\\-])+\\.)+(cn|com|net|org|gov|cc)$");
    /**
     * mobile正则表达式
     */
    public final static Pattern MOBILREGEX = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$");
    /**
     * userName正则表达式
     */
    public final static Pattern USERNAMEREGEX = Pattern.compile("^(?![a-z]+$)(?![A-Z]+$)[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,15}$");
    /**
     * password正则表达式
     */
    public final static Pattern PASSWORDREGEX = Pattern.compile("^.{6,}$");

}
