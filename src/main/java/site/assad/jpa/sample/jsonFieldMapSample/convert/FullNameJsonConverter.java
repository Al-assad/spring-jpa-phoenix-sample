package site.assad.jpa.sample.jsonFieldMapSample.convert;

import com.alibaba.fastjson.JSON;
import site.assad.jpa.phoenix.convert.GenericJsonConverter;
import site.assad.jpa.sample.jsonFieldMapSample.entity.FullName;

public class FullNameJsonConverter extends GenericJsonConverter<FullName> {
    @Override
    public String convertToDatabaseColumn(FullName attribute) {
        return JSON.toJSONString(attribute);
    }
    
    @Override
    public FullName convertToEntityAttribute(String dbData) {
        return JSON.parseObject(dbData, FullName.class);
    }
}
