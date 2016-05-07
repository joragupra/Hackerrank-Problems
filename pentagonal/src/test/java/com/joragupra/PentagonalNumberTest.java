package com.joragupra;

import org.junit.Assert;
import org.junit.Test;

public class PentagonalNumberTest {

    private Pentagonal pentagonal = new Pentagonal();

    @Test
    public void testPentagonalOf1() {
        Assert.assertEquals(1, pentagonal.pentagonal(1));
    }

    @Test
    public void testPentagonalOf2() {
        Assert.assertEquals(5, pentagonal.pentagonal(2));
    }

    @Test
    public void testPentagonalOf3() {
        Assert.assertEquals(12, pentagonal.pentagonal(3));
    }

    @Test
    public void testPentagonalOf4() {
        Assert.assertEquals(22, pentagonal.pentagonal(4));
    }

    @Test
    public void testPentagonalOf5() {
        Assert.assertEquals(35, pentagonal.pentagonal(5));
    }

    @Test
    public void testFormNewPentagonOf2() {
        Assert.assertEquals(5, pentagonal.formNewPentagon(2));
    }

    @Test
    public void testFormNewPentagonOf3() {
        Assert.assertEquals(10, pentagonal.formNewPentagon(3));
    }

    @Test
    public void testFormNewPentagonOf4() {
        Assert.assertEquals(15, pentagonal.formNewPentagon(4));
    }

    @Test
    public void testFormNewPentagonOf5() {
        Assert.assertEquals(20, pentagonal.formNewPentagon(5));
    }

    @Test
    public void testAssemble() {
        Assert.assertEquals(2, pentagonal.assemble(2, 1, 0));
        Assert.assertEquals(3, pentagonal.assemble(2, 2, 0));
        Assert.assertEquals(4, pentagonal.assemble(2, 3, 0));
        Assert.assertEquals(5, pentagonal.assemble(2, 4, 0));
        Assert.assertEquals(6, pentagonal.assemble(2, 5, 0));
    }

}
