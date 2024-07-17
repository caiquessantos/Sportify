package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.Jogador;

import java.util.List;

public interface iJogadorDAO {
    public boolean create(Jogador jogador);
    public Jogador read(int id_jogador);
    public boolean update(Jogador jogador);
    public boolean delete(int id_jogador);
    public List<Jogador> getAllByTimeID(int id_time);
    public List<Jogador> getAll();
}
