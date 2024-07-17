package com.jvcan.sportify.DB.interfaces;


import com.jvcan.sportify.DB.model.Time;

import java.util.List;

public interface iTimeDAO {
    public Long create(Time time);
    public boolean update(Time time);
    public Time read(int id_time);
    public boolean delete(int id_time);
    public List<Time> getAll();
}
