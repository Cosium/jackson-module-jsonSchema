package com.fasterxml.jackson.module.jsonSchema;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jsonSchema.types.*;

public class JsonSchemaIdResolver extends TypeIdResolverBase
{
     /* This is Wrong: should not use defaultInstance() for anything.
      * But has to work for now...
      */
     private static JavaType any = TypeFactory.defaultInstance().constructType(AnySchema.class);
     private static JavaType array = TypeFactory.defaultInstance().constructType(ArraySchema.class);
     private static JavaType booleanboolean = TypeFactory.defaultInstance().constructType(BooleanSchema.class);
     private static JavaType integer = TypeFactory.defaultInstance().constructType(IntegerSchema.class);
     private static JavaType nullnull = TypeFactory.defaultInstance().constructType(NullSchema.class);
     private static JavaType number = TypeFactory.defaultInstance().constructType(NumberSchema.class);
     private static JavaType object = TypeFactory.defaultInstance().constructType(ObjectSchema.class);
     private static JavaType string = TypeFactory.defaultInstance().constructType(StringSchema.class);

     public JsonSchemaIdResolver() { }
     
     @Override
     public String idFromValue(Object value) {
         if ( value instanceof JsonSchema) {
             return ((JsonSchema)value).getType().value();
         }
         return null;
     }

     @Override
     public String idFromValueAndType(Object value, Class<?> suggestedType) {
         return idFromValue(value);
     }

     @Override
     public JavaType typeFromId(DatabindContext context, String id) {
 		switch (JsonFormatTypes.forValue(id)) {
 		case ANY: return any;
 		case ARRAY: return array;
 		case BOOLEAN: return booleanboolean;
 		case INTEGER: return integer;
 		case NULL: return nullnull;
 		case NUMBER: return number;
 		case OBJECT: return object;
 		case STRING: return string;
 		default:
 		    return null;
 		}
     }

     @Override
     public Id getMechanism() {
         return Id.CUSTOM;
     }

     @Override
     public void init(JavaType baseType) { }

     @Override
     public String idFromBaseType() {
         return null;
     }
 }