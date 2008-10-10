package org.seasar.s2csv.csv.message;

import java.text.MessageFormat;

import org.seasar.framework.message.MessageResourceBundle;
import org.seasar.framework.message.MessageResourceBundleFactory;

/**
 * メッセージコードと引数からメッセージを組み立てるクラスです。
 * @author newta
 */
public class CSVMessageFormatter {

    private static final String RESOURCES_FILE_NAME = "csv_application";

    private static final MessageResourceBundle resourceBundle 
    		= MessageResourceBundleFactory.getBundle(RESOURCES_FILE_NAME);
    
    private CSVMessageFormatter() {
    }
    


    /**
     * メッセージを返します。
     * 
     * @param messageCode
     * @param args
     * @return メッセージ
     */
    public static String getMessage(String messageCode, Object[] args) {
        if (messageCode == null) {
            messageCode = "";
        }
        return getFormattedMessage(messageCode, getSimpleMessage(messageCode,
                args));
    }

    /**
     * メッセージコードつきのメッセージを返します。
     * 
     * @param messageCode
     * @param simpleMessage
     * @return メッセージコードつきのメッセージ
     */
    public static String getFormattedMessage(String messageCode,
            String simpleMessage) {
        return "[" + messageCode + "]" + simpleMessage;
    }

    /**
     * メッセージコードなしの単純なメッセージを返します。
     * 
     * @param messageCode
     * @param arguments
     * @return メッセージコードなしの単純なメッセージ
     */
    public static String getSimpleMessage(String messageCode, Object[] arguments) {

        try {
            String pattern = getPattern(messageCode);
            if (pattern != null) {
                return MessageFormat.format(pattern, arguments);
            }
        } catch (Throwable ignore) {
        }
        return getNoPatternMessage(arguments);
    }

    private static String getPattern(String messageCode) {
        
        if (resourceBundle == null) {
            return null;
        }
        return resourceBundle.get(messageCode);
    }

    private static String getNoPatternMessage(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            buffer.append(args[i] + ", ");
        }
        buffer.setLength(buffer.length() - 2);
        return buffer.toString();
    }
}
