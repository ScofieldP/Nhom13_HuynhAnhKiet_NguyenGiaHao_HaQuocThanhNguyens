package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import io.reactivex.Flowable;

public class CallDetailRepository {

    private CallDetailDAO callDetailDAO;
    private List<CallDetailEntity> callDetailEntities;

    public CallDetailRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        callDetailDAO = db.callDetailDAO();
    }

    public List<CallDetailEntity> getAll() {
        return callDetailDAO.getAll();
    }

    public void insert(CallDetailEntity callDetailEntity) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            callDetailDAO.insert(callDetailEntity);
        });
    }

    public void update(CallDetailEntity callDetailEntity) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            callDetailDAO.update(callDetailEntity);
        });
    }

    public void delete(CallDetailEntity callDetailEntity) {
        AppDatabase.databaseWriteExecutor.execute(() ->{
            callDetailDAO.delete(callDetailEntity);
        });
    }

    public List<CallDetailEntity> getAllCall() throws ExecutionException,InterruptedException{
        Future<List<CallDetailEntity>> data = AppDatabase.databaseWriteExecutor.submit(()->{
          List<CallDetailEntity> dataCall = callDetailDAO.getAll();
          return dataCall;
        });
        return  data.get();
    }
}
