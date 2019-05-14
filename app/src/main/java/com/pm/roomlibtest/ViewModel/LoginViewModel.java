package com.pm.roomlibtest.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pm.roomlibtest.Repository.LoginRepository;
import com.pm.roomlibtest.RoomDB.LoginTable;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository repository;
    private LiveData<List<LoginTable>> getAllData;


    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository=new LoginRepository(application);
        getAllData= repository.getAllData();
    }

    public void insert(LoginTable data){
        repository.insertData(data);
    }

    public LiveData<List<LoginTable>> getGetAllData(){
        return getAllData;
    }
}
