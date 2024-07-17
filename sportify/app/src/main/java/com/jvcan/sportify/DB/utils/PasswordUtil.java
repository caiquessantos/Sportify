package com.jvcan.sportify.DB.utils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

   /**
 * Utiliza o algoritmo BCrypt para gerar um hash seguro para uma senha.
 * <p>
 * Este método recebe uma senha em texto simples e gera um hash seguro que pode ser armazenado em um banco de dados.
 * </p>
 * 
 * @param plainTextPassword A senha em texto simples a ser criptografada.
 * @return O hash gerado para a senha.
 */
public static String hashPassword(String plainTextPassword) {
    return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
}

/**
 * Verifica se uma senha em texto simples corresponde a um hash gerado.
 * <p>
 * Este método compara uma senha em texto simples com um hash para verificar se elas correspondem.
 * </p>
 * 
 * @param plainTextPassword A senha em texto simples a ser verificada.
 * @param hashedPassword O hash da senha armazenado para comparação.
 * @return {@code true} se a senha em texto simples corresponder ao hash, {@code false} caso contrário.
 */
public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
    return BCrypt.checkpw(plainTextPassword, hashedPassword);
}

}
