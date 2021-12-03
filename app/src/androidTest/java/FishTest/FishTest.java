package FishTest;

import static org.junit.Assert.assertEquals;

import com.aliniacoban.fishingindenmark.model.Fish;

import org.junit.Test;

public class FishTest
{
    Fish fish = new Fish("A","B","C");

    @Test
    public void getFishType(){assertEquals("A", fish.getFishType());}

    @Test
    public void setFishType(){
        fish.setFishType("test");
        assertEquals("test", fish.getFishType());
    }

    @Test
    public void getFishWeight(){assertEquals("B", fish.getFishWeight());}

    @Test
    public void setFishWeight(){
        fish.setFishWeight("test");
        assertEquals("test", fish.getFishWeight());
    }

    @Test
    public void getFishLength(){assertEquals("C", fish.getFishLength());}

    @Test
    public void setFishLength(){
        fish.setFishLength("test");
        assertEquals("test", fish.getFishLength());
    }
}
