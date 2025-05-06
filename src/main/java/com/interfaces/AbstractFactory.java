package com.interfaces;

import com.enums.Difficulty;
import com.enums.Material;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Room;



public interface AbstractFactory {
    
  
    Room createRoom(String theme);

    Clue createClue(String description, String theme);
  
    DecorationItem createDecorationItem(String name, Material material);
   
    Difficulty getDifficulty();
}
