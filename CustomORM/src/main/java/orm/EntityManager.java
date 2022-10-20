package orm;

import anotations.Column;
import anotations.Entity;
import anotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E>{
   private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object value = idColumn.get(entity);

        if (value == null || (long)value <= 0){
            return doInsert(entity, idColumn);
        }
        return doUpdate(entity, idColumn);
    }

    private boolean doUpdate(E entity, Field idColumn) {
        return false;
    }

    private boolean doInsert(E entity, Field idColumn) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String tableFields = getColumnsWithoutId(entity.getClass());
        String tableValues = getColumnsValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, tableFields, tableValues);
        return  connection.prepareStatement(insertQuery).execute();
    }



    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) {
        return null;
    }
    private Field getIdColumn(Class<?> entity){
      return Arrays.stream(entity.getDeclaredFields())
             .filter(f->f.isAnnotationPresent(Id.class))
             .findFirst()
             .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary kay"));
    }
    private String getColumnsWithoutId(Class<?> aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter( f -> !f.isAnnotationPresent(Id.class))
                .filter( f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.joining(","));
    }

    private String getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(entity);
            if (o instanceof String || o instanceof LocalDate){
                values.add("'" + o + "'");
            }else {
                values.add(o.toString());
            }

        }
        return String.join(",", values);
    }


    private String getTableName(Class<?> aClass) {
        Entity[] annotationsByType = aClass.getAnnotationsByType(Entity.class);
        if (annotationsByType.length == 0){
            throw new UnsupportedOperationException("Class must be Entity");
        }
        return annotationsByType[0].name();
    }
}
