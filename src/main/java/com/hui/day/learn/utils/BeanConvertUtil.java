package com.hui.day.learn.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.util.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class BeanConvertUtil {

    public static <T> List<T> mapToBeansHump (List<Map<String, Object>> mapList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(mapList)) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            result.add(mapToBean(map, clazz, true));
        }
        return result;
    }

    private static <T> T mapToBean(Map<String, Object> map, Class<T> clazz, boolean hasHumpName) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (String key : map.keySet()) {
                String attrName = key.toLowerCase();
                for (PropertyDescriptor prop : props) {
                    String propName = prop.getName();
                    if (propName.equalsIgnoreCase("class")) {
                        continue;
                    }
                    if (hasHumpName) {
                        attrName = UnderlineToHump(attrName);
                    } else {
                        propName = propName.toLowerCase();
                    }
                    if (!attrName.equals(propName)) {
                        continue;
                    }
                    Method method = prop.getWriteMethod();
                    Object value = map.get(key);
                    if (value != null) {
                        if (value instanceof Timestamp && (prop.getPropertyType()).toString().contains("java.lang.String") ){
                            value = DateUtil.toStringYMDHMS((Date)value);
                        }else{
                            value = ConvertUtils.convert(value, prop.getPropertyType());
                        }
                        method.invoke(bean, value);
                    }else{
                        if (prop.getPropertyType() == String.class){
                            method.invoke(bean, "");
                        }
                    }
                }

            }
        } catch (IntrospectionException e) {
            log.error("获取类属性异常:{}", e.getStackTrace());
            //e.printStackTrace();
            //throw new RuntimeException("数据转换错误");
        } catch (InstantiationException e) {
            log.error("创建类实例异常:{}", e.getStackTrace());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            log.error("创建类实例异常:{}", e.getStackTrace());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error("类字段属性反射异常:{}", e.getStackTrace());
            //e.printStackTrace();
        }
        return bean;
    }

    private static String UnderlineToHump(String para) {
        if(para.contains("_")) {
            StringBuilder sb = new StringBuilder();
            String[] names = para.split("_");
            for (int i = 0; i < names.length; i++) {
                if (i == 0) {
                    sb.append(names[i].toLowerCase());
                } else {
                    //首字母
                    sb.append(names[i].substring(0, 1).toUpperCase());
                    sb.append(names[i].substring(1).toLowerCase());
                }
            }
            return sb.toString();
        }
        return para;
    }
}
