/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zahir
 */
public class RoomTest {
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoomId method, of class Room.
     */
    @Test
    public void testGetRoomId() {
        System.out.println("getRoomId");
        Room instance = new Room();
        Integer expResult = 1;
        instance.setRoomId(expResult);
        Integer result = instance.getRoomId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoomId method, of class Room.
     */
    @Test
    public void testSetRoomId() {
        System.out.println("setRoomId");
        Integer roomId = 1;
        Room instance = new Room();
        instance.setRoomId(roomId);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumber method, of class Room.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        Room instance = new Room();
        int expResult = 0;
        instance.setNumber(expResult);
        int result = instance.getNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumber method, of class Room.
     */
    @Test
    public void testSetNumber() {
        System.out.println("setNumber");
        int number = 0;
        Room instance = new Room();
        instance.setNumber(number);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Room.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Room instance = new Room();
        String expResult = "Hall";
        instance.setName(expResult);
        String result = instance.getName();
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Room.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Hall";
        Room instance = new Room();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getArea method, of class Room.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        Room instance = new Room();
        Integer expResult = 100;
        instance.setArea(expResult);
        Integer result = instance.getArea();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setArea method, of class Room.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        Integer area = 100;
        Room instance = new Room();
        instance.setArea(area);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacity method, of class Room.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Room instance = new Room();
        Integer expResult = 20;
        instance.setCapacity(expResult);
        Integer result = instance.getCapacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setCapacity method, of class Room.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        Integer capacity = 20;
        Room instance = new Room();
        instance.setCapacity(capacity);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Room.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Room instance = new Room();
        String expResult = "multifuntional";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Room.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "multifuntional";
        Room instance = new Room();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFurnitureMobility method, of class Room.
     */
    @Test
    public void testGetFurnitureMobility() {
        System.out.println("getFurnitureMobility");
        Room instance = new Room();
        String expResult = "yes";
        instance.setFurnitureMobility(expResult);
        String result = instance.getFurnitureMobility();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setFurnitureMobility method, of class Room.
     */
    @Test
    public void testSetFurnitureMobility() {
        System.out.println("setFurnitureMobility");
        String furnitureMobility = "yes";
        Room instance = new Room();
        instance.setFurnitureMobility(furnitureMobility);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFurnitureType method, of class Room.
     */
    @Test
    public void testGetFurnitureType() {
        System.out.println("getFurnitureType");
        Room instance = new Room();
        String expResult = "normal";
        instance.setFurnitureType(expResult);
        String result = instance.getFurnitureType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setFurnitureType method, of class Room.
     */
    @Test
    public void testSetFurnitureType() {
        System.out.println("setFurnitureType");
        String furnitureType = "normal";
        Room instance = new Room();
        instance.setFurnitureType(furnitureType);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrientation method, of class Room.
     */
    @Test
    public void testGetOrientation() {
        System.out.println("getOrientation");
        Room instance = new Room();
        String expResult = "N-S";
        instance.setOrientation(expResult);
        String result = instance.getOrientation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrientation method, of class Room.
     */
    @Test
    public void testSetOrientation() {
        System.out.println("setOrientation");
        String orientation = "N-S";
        Room instance = new Room();
        instance.setOrientation(orientation);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFloorPlan method, of class Room.
     */
    @Test
    public void testGetFloorPlan() {
        System.out.println("getFloorPlan");
        Room instance = new Room();
        byte[] expResult = null;
        byte[] result = instance.getFloorPlan();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloorPlan method, of class Room.
     */
    @Test
    public void testSetFloorPlan() {
        System.out.println("setFloorPlan");
        byte[] floorPlan = null;
        Room instance = new Room();
        instance.setFloorPlan(floorPlan);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Room.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Room instance = new Room();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Room.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Room instance = new Room();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Room.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Room instance = new Room();
        String expResult = "Hall";
        instance.setName(expResult);
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
