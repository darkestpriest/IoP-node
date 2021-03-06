package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.daos;

import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFilterOperator;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFilterType;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableFilter;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantDeleteRecordException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantInsertRecordException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantTruncateTableException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantUpdateRecordException;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.entities.AbstractBaseEntity;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.exceptions.CantDeleteRecordDataBaseException;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.exceptions.CantInsertRecordDataBaseException;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.exceptions.CantReadRecordDataBaseException;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.exceptions.CantUpdateRecordDataBaseException;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.database.daos.AbstractBaseDao</code> have
 * all methods implementation to access the data base (CRUD)
 * <p/>
 * <p/>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 13/05/2016.
 *
 * @author lnacosta
 * @version 1.0
 * @since Java JDK 1.7
 */
public abstract class AbstractBaseDao<T extends AbstractBaseEntity> {

    private final Database dataBase   ;
    private final String   tableName  ;
    private final String   tableIdName;

    /**
     * Constructor with parameters
     *
     * @param dataBase
     * @param tableName
     */
    public AbstractBaseDao(final Database dataBase   ,
                           final String   tableName  ,
                           final String   tableIdName) {

        this.dataBase    = dataBase   ;
        this.tableName   = tableName  ;
        this.tableIdName = tableIdName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableIdName() {
        return tableIdName;
    }

    /**
     * Return the data base instance
     * @return Database
     */
    private Database getDataBase() {
        return dataBase;
    }

    /**
     * Return the data base table instance
     * @return DatabaseTable
     */
    protected DatabaseTable getDatabaseTable() {
        return getDataBase().getTable(tableName);
    }

    /**
     * Method that find a entity by his id in the data base.
     *
     * @param id entity.
     *
     * @return FermatMessage found.
     *
     * @throws CantReadRecordDataBaseException   if something goes wrong.
     * @throws RecordNotFoundException           if i can't find the record.
     */
    public final T findById(final String id) throws CantReadRecordDataBaseException, RecordNotFoundException {

        if (id == null)
            throw new IllegalArgumentException("The id is required, can not be null.");

        try {

            final DatabaseTable table = getDatabaseTable();
            table.addStringFilter(tableIdName, id, DatabaseFilterType.EQUAL);
            table.loadToMemory();

            List<DatabaseTableRecord> records = table.getRecords();

            if(!records.isEmpty())
                return getEntityFromDatabaseTableRecord(records.get(0));
            else
                throw new RecordNotFoundException("id: " + id, "Cannot find an entity with that id in the table "+tableName);

        } catch (final CantLoadTableToMemoryException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "The data no exist");
        } catch (final InvalidParameterException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "Invalid parameter found, maybe the enum is wrong.");
        }
    }

    /**
     * Method that list the all entities on the data base.
     *
     * @return All entities.
     *
     * @throws CantReadRecordDataBaseException if something goes wrong.
     */
    public final List<T> findAll() throws CantReadRecordDataBaseException {

        try {
            // load the data base to memory
            DatabaseTable table = getDatabaseTable();
            table.loadToMemory();

            final List<DatabaseTableRecord> records = table.getRecords();

            final List<T> list = new ArrayList<>();

            // Convert into entity objects and add to the list.
            for (DatabaseTableRecord record : records)
                list.add(getEntityFromDatabaseTableRecord(record));

            return list;

        } catch (final CantLoadTableToMemoryException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "The data no exist");
        } catch (final InvalidParameterException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "Invalid parameter found, maybe the enum is wrong.");
        }
    }

    /**
     * Method that list the all entities on the data base. The valid value of
     * the column name are the att of the <code>DatabaseConstants</code>
     *
     * @return All entities filtering by the parameter specified.
     *
     * @throws CantReadRecordDataBaseException
     *
     */
    public final List<T> findAll(final String columnName , final String columnValue) throws CantReadRecordDataBaseException {

        if (columnName == null || columnName.isEmpty() || columnValue == null || columnValue.isEmpty())
            throw new IllegalArgumentException("The filter are required, can not be null or empty.");

        try {

            // load the data base to memory with filters
            final DatabaseTable table = getDatabaseTable();

            table.addStringFilter(columnName, columnValue, DatabaseFilterType.EQUAL);
            table.loadToMemory();

            final List<DatabaseTableRecord> records = table.getRecords();

            final List<T> list = new ArrayList<>();

            // Convert into entity objects and add to the list.
            for (DatabaseTableRecord record : records)
                list.add(getEntityFromDatabaseTableRecord(record));

            return list;

        } catch (final CantLoadTableToMemoryException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "The data no exist");
        } catch (final InvalidParameterException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "Invalid parameter found, maybe the enum is wrong.");
        }

    }

    /**
     * Method that list the all entities on the data base. The valid value of
     * the key are the att of the <code>DatabaseConstants</code>
     *
     * @return All entities filtering by the parameters specified.
     *
     * @throws CantReadRecordDataBaseException if something goes wrong.
     *
     */
    public final List<T> findAll(final Map<String, Object> filters) throws CantReadRecordDataBaseException {

        if (filters == null || filters.isEmpty())
            throw new IllegalArgumentException("The filters are required, can not be null or empty.");

        try {

            // Prepare the filters
            final DatabaseTable table = getDatabaseTable();

            final List<DatabaseTableFilter> tableFilters = new ArrayList<>();

            for (String key : filters.keySet()) {

                DatabaseTableFilter newFilter = table.getEmptyTableFilter();
                newFilter.setType(DatabaseFilterType.EQUAL);
                newFilter.setColumn(key);
                newFilter.setValue((String) filters.get(key));

                tableFilters.add(newFilter);
            }


            // load the data base to memory with filters
            table.setFilterGroup(tableFilters, null, DatabaseFilterOperator.OR);
            table.loadToMemory();

            final List<DatabaseTableRecord> records = table.getRecords();

            final List<T> list = new ArrayList<>();

            // Convert into entity objects and add to the list.
            for (DatabaseTableRecord record : records)
                list.add(getEntityFromDatabaseTableRecord(record));

            return list;

        } catch (final CantLoadTableToMemoryException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "The data no exist");
        } catch (final InvalidParameterException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "Invalid parameter found, maybe the enum is wrong.");
        }
    }

    /**
     * Method that create a new entity in the data base.
     *
     * @param entity to create.
     *
     * @throws CantInsertRecordDataBaseException if something goes wrong.
     */
    public final void create(final T entity) throws CantInsertRecordDataBaseException {

        if (entity == null)
            throw new IllegalArgumentException("The entity is required, can not be null");

        try {

            DatabaseTableRecord entityRecord = getDatabaseTableRecordFromEntity(entity);

            getDatabaseTable().insertRecord(entityRecord);

        } catch (final CantInsertRecordException e) {

            throw new CantInsertRecordDataBaseException(e, "Table Name: " + tableName, "The Template Database triggered an unexpected problem that wasn't able to solve by itself");
        }

    }

    /**
     * Method that update an entity in the data base.
     *
     * @param entity to update.
     *
     * @throws CantUpdateRecordDataBaseException  if something goes wrong.
     * @throws RecordNotFoundException            if we can't find the record in db.
     */
    public final void update(final T entity) throws CantUpdateRecordDataBaseException, RecordNotFoundException {

        if (entity == null)
            throw new IllegalArgumentException("The entity is required, can not be null.");

        try {

            final DatabaseTable table = this.getDatabaseTable();

            table.addUUIDFilter(tableIdName, entity.getId(), DatabaseFilterType.EQUAL);

            table.loadToMemory();

            final List<DatabaseTableRecord> records = table.getRecords();

            if (!records.isEmpty())
                table.updateRecord(getDatabaseTableRecordFromEntity(entity));
            else
                throw new RecordNotFoundException("id: " + entity.getId(), "Cannot find an entity with that id.");

        } catch (final CantUpdateRecordException e) {

            throw new CantUpdateRecordDataBaseException(e, "Table Name: " + tableName, "The record do not exist");
        } catch (final CantLoadTableToMemoryException e) {

            throw new CantUpdateRecordDataBaseException(e, "", "Exception not handled by the plugin, there is a problem in database and i cannot load the table.");

        }
    }

    /**
     * Method that delete a entity in the data base.
     *
     * @param id
     *
     * @throws CantDeleteRecordDataBaseException  if something goes wrong.
     * @throws RecordNotFoundException            if we can't find the record in db.
     */
    public final void delete(final String id) throws CantDeleteRecordDataBaseException,
            RecordNotFoundException          {

        if (id == null)
            throw new CantDeleteRecordDataBaseException("id null", "The id is required, can not be null.");

        try {

            final DatabaseTable table = this.getDatabaseTable();

            table.addStringFilter(tableIdName, id, DatabaseFilterType.EQUAL);

            table.loadToMemory();

            final List<DatabaseTableRecord> records = table.getRecords();

            if (!records.isEmpty())
                table.deleteRecord(records.get(0));
            else
                throw new RecordNotFoundException("id: "+id, "Cannot find an outgoing message with that id.");

        } catch (CantDeleteRecordException e) {

            throw new CantDeleteRecordDataBaseException(e, "", "Exception not handled by the plugin, there is a problem in database and i cannot delete the record.");
        } catch (final CantLoadTableToMemoryException e) {

            throw new CantDeleteRecordDataBaseException(e, "", "Exception not handled by the plugin, there is a problem in database and i cannot load the table.");

        }
    }

    /**
     * Method that delete a entity in the data base.
     *
     * @throws CantDeleteRecordDataBaseException  if something goes wrong.
     */
    public final void deleteAll() throws CantDeleteRecordDataBaseException {

        try {

            final DatabaseTable table = this.getDatabaseTable();

            table.truncate();

        } catch (CantTruncateTableException e) {

            throw new CantDeleteRecordDataBaseException(e, "", "Exception not handled by the plugin, there is a problem in database and I cannot delete all records.");
        }
    }

    /**
     * Method that checks if an entity exists in database.
     *
     * @param id entity.
     *
     * @return a boolean value indicating if the entity exists.
     *
     * @throws CantReadRecordDataBaseException   if something goes wrong.
     */
    public final boolean exists(final String id) throws CantReadRecordDataBaseException {

        if (id == null)
            throw new IllegalArgumentException("The id is required, can not be null.");

        try {

            final DatabaseTable table = getDatabaseTable();
            table.addStringFilter(tableIdName, id, DatabaseFilterType.EQUAL);
            table.loadToMemory();

            List<DatabaseTableRecord> records = table.getRecords();

            return !records.isEmpty();

        } catch (final CantLoadTableToMemoryException e) {

            throw new CantReadRecordDataBaseException(e, "Table Name: " + tableName, "The data no exist");
        }
    }

    /**
     * Construct a Entity whit the values of the a DatabaseTableRecord pass
     * by parameter
     *
     * @param record with values from the table
     * @return entity setters the values from table
     */
    abstract protected T getEntityFromDatabaseTableRecord(final DatabaseTableRecord record) throws InvalidParameterException;

    /**
     * Construct a DatabaseTableRecord whit the values of the a entity pass
     * by parameter
     *
     * @param entity the contains the values
     *
     * @return DatabaseTableRecord whit the values
     */
    abstract protected DatabaseTableRecord getDatabaseTableRecordFromEntity(final T entity);
}
