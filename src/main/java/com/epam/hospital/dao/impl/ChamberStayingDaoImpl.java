package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.ChamberStayingDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.constant.database.Column;
import com.epam.hospital.constant.database.Table;
import com.epam.hospital.model.treatment.ChamberStaying;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChamberStayingDaoImpl extends AbstractDaoImpl<ChamberStaying> implements ChamberStayingDao {
    private final static String SAVE_CHAMBER_STAYING_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            Table.CHAMBER_STAYING_TABLE,
            Column.CHAMBER_STAYING_DATE_IN,
            Column.CHAMBER_STAYING_DATE_OUT,
            Column.CHAMBER_STAYING_PRICE,
            Column.CHAMBER_STAYING_CHAMBER_ID,
            Column.CHAMBER_STAYING_HOSPITALIZATION_ID
    );
    private final static String UPDATE_CHAMBER_STAYING_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=? AND %s=?",
            Table.CHAMBER_STAYING_TABLE,
            Column.CHAMBER_STAYING_DATE_IN,
            Column.CHAMBER_STAYING_DATE_OUT,
            Column.CHAMBER_STAYING_PRICE,
            Column.CHAMBER_STAYING_CHAMBER_ID,
            Column.CHAMBER_STAYING_HOSPITALIZATION_ID
    );
    private final static String FIND_CHAMBER_STAYING_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.CHAMBER_STAYING_TABLE,
            Column.CHAMBER_STAYING_HOSPITALIZATION_ID
    );
    
    public ChamberStayingDaoImpl(){
        super(BuilderFactory.getChamberStaying(), Table.CHAMBER_STAYING_TABLE, Column.CHAMBER_STAYING_HOSPITALIZATION_ID);
    }

    @Override
    public void save(ChamberStaying entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_CHAMBER_STAYING_QUERY);) {
            setParams(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber staying.", e);
        }
    }

    @Override
    public void update(ChamberStaying entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_CHAMBER_STAYING_QUERY);) {
            setParams(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update chamber staying.", e);
        }
    }

    @Override
    public ChamberStaying findById(int... id) throws DaoException {
        ChamberStaying chamberStaying = new ChamberStaying();
        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_CHAMBER_STAYING_QUERY);) {
            statement.setInt(1, id[0]);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                chamberStaying = BuilderFactory.getChamberStaying().build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't find chamber staying by id.", e);
        }
        return chamberStaying;
    }


    //        @Override
//    public ChamberStaying findByForeignKeys(int chamberId, int hospitalizationId) throws DaoException {
//        ChamberStaying chamberStaying = new ChamberStaying();
//        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_CHAMBER_STAYING_QUERY);) {
//            statement.setInt(1, chamberId);
//            statement.setInt(2, hospitalizationId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                chamberStaying = BuilderFactory.getChamberStaying().build(resultSet);
//            }
//        } catch (SQLException e) {
////            LOGGER.error("Can't find entity by id.",e);
//            throw new DaoException("Can't find by id.", e);
//        }
//        return chamberStaying;
//    }

    private void setParams(PreparedStatement statement, ChamberStaying chamberStaying) throws SQLException {
        statement.setDate(1, new Date(chamberStaying.getDateIn().getTime()));
        statement.setDate(2, new Date(chamberStaying.getDateOut().getTime()));
        statement.setDouble(3, chamberStaying.getPrice());
        statement.setInt(4, chamberStaying.getChamberId());
        statement.setInt(5, chamberStaying.getHospitalizationId());
    }
}
