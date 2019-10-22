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

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link AccessRights}.
 */
public class CoordinateTest {

	// test cases that cover valid behavior

	@Test
	public void getDistanceCheck() {
		Coordiante coordiante1 = new Coordiante(1,1,1);
		Coordiante coordiante2 = new Coordiante(1,2,1);
		assert(coordiante1.getDistance(coordiante2)==1d);
		coordiante2 = new Coordiante(1,20,1);
		assert(coordiante1.getDistance(coordiante2)==19d);
		coordiante2 = new Coordiante(20,1,1);
		assert(coordiante1.getDistance(coordiante2)==19d);
		coordiante2 = new Coordiante(1,1,20);
		assert(coordiante1.getDistance(coordiante2)==19d);

		coordiante1 = new Coordiante(2,3,-1);
		coordiante2 = new Coordiante(4,1,-2);
		assert(coordiante1.getDistance(coordiante2)==3d);
	}

	@Test
	public void isEqualCheck() {
		Coordiante coordiante1 = new Coordiante(1,1,1);
		Coordiante coordiante2 = new Coordiante(1,1,1);
		assert(coordiante1.isEqual(coordiante2));
		coordiante1 = new Coordiante(1.4,1.1,1111);
		coordiante2 = new Coordiante(1.4,1.1,1111);
		assert(coordiante1.isEqual(coordiante2));
	}
}
