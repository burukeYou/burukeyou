package burukeyou.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author burukeYou
 * @date 2020-3-15
 * @description 共享SimpleDateFormat是线程不安全的
 */
public class DateUtils {

    private DateUtils(){}

    private static ConcurrentHashMap<String,ThreadLocal<SimpleDateFormat>> sdfMap = new ConcurrentHashMap<>();

    public static SimpleDateFormat getInstance(String format){
        if (!sdfMap.containsKey(format)){
            synchronized (DateUtils.class){
                if (!sdfMap.containsKey(format)){
                    ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat(format));
                    sdfMap.putIfAbsent(format,sdf);
                }
            }
        }
        return sdfMap.get(format).get();
    }


    public static String format(Date date,String format){
        return getInstance(format).format(date);
    }

}
