/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;

/**
 * 
 * @since 2013. 10. 11.
 * @author BS정보시스템/손승범
 */
public class ReflectUtil {
  
  public static Object reflectMap2VO(Map<String, Object> map, Class<?> clazz) throws Exception {
    
    Object vo = clazz.newInstance();
    Field[] fields = vo.getClass().getDeclaredFields();
    Method[] methods = vo.getClass().getDeclaredMethods();
    
    for (Map.Entry<String, Object> keyVal : map.entrySet()) {
      String key = keyVal.getKey();
      Object value = keyVal.getValue();
      
      String setterName = "set" + StringUtil.capitalize(key);
      
      // VO값 적용
      for (Method method : methods) {
        if (setterName.equals(method.getName())) {
          Class<?> typeClass = null;
          
          for (Field f : fields) {
            if (key.equalsIgnoreCase(f.getName())) {
              Class<?> wrapper = ClassUtils.primitiveToWrapper(f.getType());
              String wrapperName = wrapper.getName();
              typeClass = Class.forName(wrapperName);
              break;
            }
          }
          
          Class<?> primitive = ClassUtils.wrapperToPrimitive(typeClass);
          Method m = null;
          String typeName = "";
          // primitive 타입이 없는 경우
          if (primitive != null) {
            m = clazz.getMethod(setterName, primitive);
            typeName = primitive.getSimpleName();
          }
          // primitive 타입이 있는 경우
          else {
            m = clazz.getMethod(setterName, typeClass);
            typeName = typeClass.getSimpleName();
          }
          
          String strVal = value.toString();
          // 값이 비었으면 문자열로 판단
          if ("String".equalsIgnoreCase(typeName)) {
            m.invoke(vo, strVal);
          } else if ("int".equalsIgnoreCase(typeName)) {
            if (StringUtil.isEmpty(strVal)) {
              strVal = "0";
            }
            m.invoke(vo, Integer.parseInt(strVal));
          } else if ("Double".equalsIgnoreCase(typeName)) {
            if (StringUtil.isEmpty(strVal)) {
              strVal = "0";
            }
            m.invoke(vo, Double.parseDouble(strVal));
          } else if ("Date".equalsIgnoreCase(typeName)) {
            if (StringUtil.isEmpty(strVal)) {
              strVal = DateUtil.getDate();
            }
            // TODO Date클래스확인
            m.invoke(vo, Date.parse(strVal));
          } else {
            m.invoke(vo, null);
          }
          break;
        }
      }
    }
    return vo;
  }
  
}
