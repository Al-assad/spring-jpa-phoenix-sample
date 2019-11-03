package site.assad.jpa.phoenix.convert;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.persistence.AttributeConverter;

/**
 * 通用 json 转化器，用于基本类型、String、Collection、Map
 *
 * @author yulinying
 * @since 2019-11-02
 */
public class GenericJsonConverter<X> implements AttributeConverter<X, String> {
    
    @Override
    public String convertToDatabaseColumn(X attribute) {
        return JSON.toJSONString(attribute);
    }
    
    @Override
    public X convertToEntityAttribute(String dbData) {
        return JSON.parseObject(dbData, new TypeReference<X>() {});
    }
}
