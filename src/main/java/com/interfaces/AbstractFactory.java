package com.interfaces;

import com.model.Room;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.enums.Difficulty;
import com.model.enums.Material;



public interface AbstractFactory {
    
  
    Room createRoom(String theme);

    Clue createClue(String description, String theme);
  
    DecorationItem createDecorationItem(String name, Material material);
   
    Difficulty getDifficulty();
}
