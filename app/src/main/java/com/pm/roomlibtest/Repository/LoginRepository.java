package com.pm.roomlibtest.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pm.roomlibtest.RoomDB.LoginDao;
import com.pm.roomlibtest.RoomDB.LoginDatabase;
import com.pm.roomlibtest.RoomDB.LoginTable;

import java.util.List;

public class LoginRepository {
    private LoginDao loginDao;
    private LiveData<List<LoginTable>> allData;

    public  LoginRepository(Application application){

        LoginDatabase db= LoginDatabase.getDatabase(application);
        loginDao= db.loginDao();
        allData=loginDao.getDetails();
    }

    public  void deleteData(){loginDao.deleteAllData();}

    public LiveData<List<LoginTable>> getAllData() {
        return allData;
    }

    public void insertData(LoginTable data){
        new LoginInsertion(loginDao).execute(data);
    }

    private static class LoginInsertion extends AsyncTask<LoginTable, Void, Void> {

        private LoginDao loginDao;

        private  LoginInsertion(LoginDao loginDao){
            this.loginDao=loginDao;
        }
        @Override
        protected Void doInBackground(LoginTable... loginTables) {
            loginDao.deleteAllData();
            loginDao.insertDetails(loginTables[0]);
            return null;
        }
    }
}
