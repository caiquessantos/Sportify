package com.jvcan.sportify.DB.controller;

import android.content.Context;

import com.jvcan.sportify.DB.DAO.TimeDAO;
import com.jvcan.sportify.DB.interfaces.iTimeDAO;
import com.jvcan.sportify.DB.model.Time;

import java.util.List;

/**
 * Controlador responsável pela interação entre a camada de apresentação e a camada de dados para a tabela de {@link Time}.
 *
 * Esta classe fornece métodos para criar, ler, atualizar e excluir registros da tabela de times no banco de dados,
 * bem como para recuperar todos os times existentes. O {@link ControllerTime} utiliza um {@link iTimeDAO} para realizar
 * operações de acesso a dados e manipulação das instâncias de {@link Time}.
 */
public class ControllerTime {
    private iTimeDAO timeDAO;

    /**
     * Construtor do {@link ControllerTime}.
     *
     * Este construtor inicializa o {@link iTimeDAO} com uma instância de {@link TimeDAO} para realizar operações no banco de dados.
     *
     * @param context O contexto da aplicação, necessário para criar uma instância do {@link TimeDAO}.
     */
    public ControllerTime(Context context) {
        this.timeDAO = new TimeDAO(context);  // Inicializa o timeDAO com a instância de TimeDAO
    }

    /**
     * Cria um novo time no banco de dados.
     *
     * Este método cria um novo registro na tabela de times com o nome e logo fornecidos.
     *
     * @param nome_time O nome do time a ser criado.
     * @param logo_time O logo do time a ser criado.
     * @return {@code true} se o time foi criado com sucesso, {@code false} caso contrário.
     */
    public Long create(String nome_time, String logo_time) {

        return timeDAO.create(new Time(nome_time, logo_time));
    }

    /**
     * Recupera um time do banco de dados pelo seu ID.
     *
     * Este método lê um registro da tabela de times com base no ID fornecido.
     *
     * @param id_time O ID do time a ser recuperado.
     * @return Um objeto {@link Time} representando o time com o ID fornecido, ou {@code null} se o time não for encontrado.
     */
    public Time read(int id_time) {
        return timeDAO.read(id_time);
    }

    /**
     * Atualiza o nome de um time existente.
     *
     * Este método recupera um time existente pelo ID fornecido, atualiza seu nome e salva as alterações no banco de dados.
     *
     * @param id_time O ID do time a ser atualizado.
     * @param novoNomeTime O novo nome do time.
     * @return {@code true} se o nome do time foi atualizado com sucesso, {@code false} caso contrário.
     */
    public boolean updateNomeTime(int id_time, String novoNomeTime) {
        Time time = timeDAO.read(id_time);
        if (time != null) {
            time.setNome_time(novoNomeTime);
            return timeDAO.update(time);
        }
        return false;
    }

    /**
     * Atualiza o logo de um time existente.
     *
     * Este método recupera um time existente pelo ID fornecido, atualiza seu logo e salva as alterações no banco de dados.
     *
     * @param id_time O ID do time a ser atualizado.
     * @param novoLogoTime O novo logo do time.
     * @return {@code true} se o logo do time foi atualizado com sucesso, {@code false} caso contrário.
     */
    public boolean updateLogoTime(int id_time, String novoLogoTime) {
        Time time = timeDAO.read(id_time);
        if (time != null) {
            time.setLogo_time(novoLogoTime);
            return timeDAO.update(time);
        }
        return false;
    }

    /**
     * Exclui um time do banco de dados.
     *
     * Este método exclui um registro da tabela de times com base no ID fornecido.
     *
     * @param id_time O ID do time a ser excluído.
     * @return {@code true} se o time foi excluído com sucesso, {@code false} caso contrário.
     */
    public boolean delete(int id_time) {
        return timeDAO.delete(id_time);
    }

    /**
     * Recupera todos os times do banco de dados.
     *
     * Este método lê todos os registros da tabela de times e retorna uma lista contendo todos os objetos {@link Time}.
     *
     * @return Uma lista contendo todos os objetos {@link Time} presentes na tabela de times.
     */
    public List<Time> getAll() {
        return timeDAO.getAll();
    }
}

