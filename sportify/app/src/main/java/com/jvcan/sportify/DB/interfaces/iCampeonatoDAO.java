package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.Campeonato;
import com.jvcan.sportify.DB.model.Time;

import java.util.List;

public interface iCampeonatoDAO {
    public boolean create(Campeonato campeonato);
    public Campeonato read(int id_campeonato);
    public boolean update(Campeonato campeonato);
    public boolean delete(int id_campeonato);
    public  List<Campeonato> getAllByUser(String email);
    public List<Time> getAllTimes(int id_campeonato);
    public List<Campeonato> getAll();
}
