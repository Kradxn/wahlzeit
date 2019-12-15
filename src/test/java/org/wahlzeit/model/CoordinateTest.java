/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import org.junit.Test;
import org.wahlzeitext.model.CartesianCoordinate;
import org.wahlzeitext.model.InvalidCoordinateException;
import org.wahlzeitext.model.SphericCoordinate;

/**
 * All test cases of the class {@link AccessRights}.
 */
public class CoordinateTest {

	// test cases that cover valid behavior

	@Test
	public void getDistanceCheck() throws InvalidCoordinateException {
		CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(1,1,1);
		CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(1,2,1);
		assert(cartesianCoordinate1.getDistance(cartesianCoordinate2)==1d);
		cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(1,20,1);
		assert(cartesianCoordinate1.getDistance(cartesianCoordinate2)==19d);
		cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(20,1,1);
		assert(cartesianCoordinate1.getDistance(cartesianCoordinate2)==19d);
		cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(1,1,20);
		assert(cartesianCoordinate1.getDistance(cartesianCoordinate2)==19d);

		cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(2,3,-1);
		cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(4,1,-2);
		assert(cartesianCoordinate1.getDistance(cartesianCoordinate2)==3d);
	}

	@Test
	public void isEqualCheck() {
		CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(1,1,1);
		CartesianCoordinate cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(1,1,1);
		assert(cartesianCoordinate1.isEqual(cartesianCoordinate2));
		cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(1.4,1.1,1111);
		cartesianCoordinate2 = CartesianCoordinate.constructCartesianCoordinate(1.4,1.1,1111);
		assert(cartesianCoordinate1.isEqual(cartesianCoordinate2));
	}


	@Test
  public void cartesianToSphericalCoordinateCheck(){
	  CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(12,31,34);
	  assert(cartesianCoordinate1.asSphericCoordiante().isEqual(cartesianCoordinate1));
  }


  @Test
	public void greatAngleCheck() throws InvalidCoordinateException {
    CartesianCoordinate zero = CartesianCoordinate.constructCartesianCoordinate(2,2,1);
    CartesianCoordinate cartesianCoordinate1 = CartesianCoordinate.constructCartesianCoordinate(2,2,1);
    assert(zero.getCentralAngle(cartesianCoordinate1)==0);


    SphericCoordinate x = SphericCoordinate.constructSphericCoordinate(Math.PI/4,0,10);
    SphericCoordinate y = SphericCoordinate.constructSphericCoordinate(0,0,10);

    assert(x.getCentralAngle(y)==Math.PI/4);

    x = SphericCoordinate.constructSphericCoordinate(0,Math.PI/4,10);
    y = SphericCoordinate.constructSphericCoordinate(0,0,10);

    assert(x.getCentralAngle(y)==Math.PI/4);
  }

  @Test
	public void sharedValueTest(){
		CartesianCoordinate zero = CartesianCoordinate.constructCartesianCoordinate(2,2,1);
		CartesianCoordinate one = CartesianCoordinate.constructCartesianCoordinate(2,2,1);
		System.out.println(System.identityHashCode(zero));
		System.out.println(System.identityHashCode(one));
		assert(zero == one);
		SphericCoordinate szero =  SphericCoordinate.constructSphericCoordinate(Math.PI/4,0,10);
		SphericCoordinate sone = SphericCoordinate.constructSphericCoordinate(Math.PI/4,0,10);
		System.out.println(System.identityHashCode(szero));
		System.out.println(System.identityHashCode(sone));
		assert(szero == sone);
	}
}
