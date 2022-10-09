package org.dreamcat.graphetl.runtime.spark.util;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 * @author Jerry Will
 * @version 2022-09-07
 */
public class DatasetUtil {

    public static void f(Dataset<Row> dataset) {
        StructType structType = dataset.schema();
        StructField[] structFields = structType.fields();
     for (StructField structField : structFields) {
      String name = structField.name();
      DataType dataType = structField.dataType();
      dataType.typeName();
     }
    }
}
