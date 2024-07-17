package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.Campeonato;
import com.jvcan.sportify.DB.model.JogaCampeonato;

import java.util.List;

public interface iJogaCampeonatoDAO {
    public boolean create(JogaCampeonato jogaCampeonato);
    public JogaCampeonato read(int id_time, int id_campeonato);
    public boolean delete(int id_time, int id_campeonato);
    public List<JogaCampeonato> getAllByTimeId(int id_time);
    public List<JogaCampeonato> getAllByCampeonatoId(int id_campeonato);
    public List<JogaCampeonato> getAll();
}
