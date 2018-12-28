package by.alexlevankou.smsmoneymanager.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.alexlevankou.smsmoneymanager.model.Operation;

@Dao
public interface OperationDao {

    @Query("SELECT * FROM operation")
    List<Operation> getAll();

    @Query("SELECT * FROM operation WHERE id = :id")
    Operation getById(long id);

    @Insert
    void insert(Operation operation);

    @Update
    void update(Operation operation);

    @Delete
    void delete(Operation operation);

}
