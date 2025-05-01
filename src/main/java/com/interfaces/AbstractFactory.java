package com.interfaces;

import com.model.Room;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.enums.Difficulty;
import com.model.enums.Material;


/**
 * Interfaz AbstractFactory para el projecto Escape Room.
 * Define métodos para crear diferentes componentes de un juego de escape room.
 * Siguiendo el patrón Abstract Factory para crear familias de objetos relacionados.
 */
public interface AbstractFactory {
    
    /**
     * Crea una sala con el tema especificado.
     * La dificultad es determinada por el tipo de fábrica.
     * 
     * @param theme El tema de la sala
     * @return Un objeto Room configurado según el nivel de dificultad de la fábrica
     * @throws IllegalArgumentException si el tema es nulo o vacío
     */
    Room createRoom(String theme);
    
    /**
     * Crea una pista con la descripción y el tema especificados.
     * 
     * @param description La pista o descripción de la pista
     * @param theme El tema al que pertenece la pista
     * @return Un objeto Clue configurado según los parámetros especificados
     * @throws IllegalArgumentException si la descripción o el tema es nulo o vacío
     */
    Clue createClue(String description, String theme);
    
    /**
     * Crea un elemento de decoración con el nombre y el material especificados.
     * 
     * @param name El nombre del elemento de decoración
     * @param material El material del que está hecho el elemento de decoración
     * @return Un objeto DecorationItem configurado según los parámetros especificados
     * @throws IllegalArgumentException si el nombre es nulo o vacío, o el material es nulo
     */
    DecorationItem createDecorationItem(String name, Material material);
    
    /**
     * Devuelve el nivel de dificultad que esta fábrica está diseñada para.
     * 
     * @return El nivel de dificultad de la fábrica
     */
    Difficulty getDifficulty();
}
