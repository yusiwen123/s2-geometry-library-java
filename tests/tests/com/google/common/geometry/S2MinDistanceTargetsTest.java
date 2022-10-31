/*
 * Copyright 2022 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.geometry;

import static com.google.common.geometry.S2TextFormat.makePointOrDie;
import static com.google.common.geometry.S2TextFormat.parsePointsOrDie;

import java.util.List;

/** Tests for {@link S2MinDistanceTargets}. */
public final class S2MinDistanceTargetsTest extends GeometryTestCase {

  /**
   * Verifies that updateBestDistance from an edge to a {@link S2MinDistanceTargets.PointTarget}
   * with a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the
   * edge to the point target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromEdgeToPointWhenEqual() {
    S2MinDistanceTargets.PointTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.PointTarget<>(makePointOrDie("1:0"));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    List<S2Point> edge = parsePointsOrDie("0:-1, 0:1");
    assertTrue(target.updateBestDistance(edge.get(0), edge.get(1), dist));
    assertFalse(target.updateBestDistance(edge.get(0), edge.get(1), dist));
  }

  /**
   * Verifies that updateBestDistance from a cell to a {@link S2MinDistanceTargets.PointTarget} with
   * a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the cell to
   * the point target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromCellToPointWhenEqual() {
    S2MinDistanceTargets.PointTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.PointTarget<>(makePointOrDie("1:0"));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    S2Cell cell = new S2Cell(makePointOrDie("0:0"));
    assertTrue(target.updateBestDistance(cell, dist));
    assertFalse(target.updateBestDistance(cell, dist));
  }

  /**
   * Verifies that updateBestDistance from an edge to a {@link S2MinDistanceTargets.EdgeTarget} with
   * a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the edge to
   * the edge target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromEdgeToEdgeWhenEqual() {
    S2MinDistanceTargets.EdgeTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.EdgeTarget<>(makePointOrDie("1:0"), makePointOrDie("1:1"));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    List<S2Point> edge = parsePointsOrDie("0:-1, 0:1");
    assertTrue(target.updateBestDistance(edge.get(0), edge.get(1), dist));
    assertFalse(target.updateBestDistance(edge.get(0), edge.get(1), dist));
  }

  /**
   * Verifies that updateBestDistance from a cell to a {@link S2MinDistanceTargets.EdgeTarget} with
   * a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the cell to
   * the edge target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromCellToEdgeWhenEqual() {
    S2MinDistanceTargets.EdgeTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.EdgeTarget<>(makePointOrDie("1:0"), makePointOrDie("1:1"));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    S2Cell cell = new S2Cell(makePointOrDie("0:0"));
    assertTrue(target.updateBestDistance(cell, dist));
    assertFalse(target.updateBestDistance(cell, dist));
  }

  /**
   * Verifies that updateBestDistance from an edge to a {@link S2MinDistanceTargets.CellTarget} with
   * a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the edge to
   * the cell target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromEdgeToCellWhenEqual() {
    S2MinDistanceTargets.CellTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.CellTarget<>(new S2Cell(makePointOrDie("0:1")));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    List<S2Point> edge = parsePointsOrDie("0:-1, 0:1");
    assertTrue(target.updateBestDistance(edge.get(0), edge.get(1), dist));
    assertFalse(target.updateBestDistance(edge.get(0), edge.get(1), dist));
  }

  /**
   * Verifies that updateBestDistance from a cell to a {@link S2MinDistanceTargets.CellTarget} with
   * a {@link S1ChordAngle#minCollector()} only returns true when the new distance from the cell to
   * the cell target is less than the old distance, not less than or equal to.
   */
  public void testUpdateMinDistanceFromCellToCellWhenEqual() {
    S2MinDistanceTargets.CellTarget<S1ChordAngle> target =
        new S2MinDistanceTargets.CellTarget<>(new S2Cell(makePointOrDie("0:1")));
    DistanceCollector<S1ChordAngle> dist = S1ChordAngle.minCollector();
    S2Cell cell = new S2Cell(makePointOrDie("0:0"));
    assertTrue(target.updateBestDistance(cell, dist));
    assertFalse(target.updateBestDistance(cell, dist));
  }
}
