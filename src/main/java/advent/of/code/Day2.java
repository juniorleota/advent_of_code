package advent.of.code;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day2 {
  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Objects.requireNonNull(Day2.class.getClassLoader()
        .getResource("day2_input.txt")).toURI());

    List<Data> movements = Files.lines(path)
        .map(DataParser::parse)
        .collect(Collectors.toList());
    SubmarineLocation location = new SubmarineLocation();
    movements.forEach(location::addMovement);
    System.out.println(location.getLocation());
  }

  static class DataParser {
    public static Data parse(String line) {
      String[] tokens = line.trim().split(" ");
      Direction direction = Direction.parse(tokens[0]);
      int value = Integer.parseInt(tokens[1]);
      return new Data(direction, value);
    }
  }

  static class Data {
    Direction direction;
    int value;

    public Data(Direction direction, int value) {
      this.direction = direction;
      this.value = value;
    }
  }

  enum Direction {
    UP, DOWN, FORWARD;
    public static Direction parse(String str) {
      return Direction.valueOf(str.toUpperCase());
    }
  }

  static class SubmarineLocation {
    int depth;
    int horizontalPos;
    int aim;

    public SubmarineLocation() {
      this.depth = 0;
      this.horizontalPos = 0;
      this.aim = 0;
    }

    public void addMovement(Data movement) {
      switch (movement.direction) {
        case UP -> this.aim -= movement.value;
        case DOWN -> this.aim += movement.value;
        case FORWARD -> {
          this.horizontalPos += movement.value;
          this.depth += movement.value * this.aim;
        }
      }
    }

    public int getLocation() {
      return this.depth * this.horizontalPos;
    }
  }
}
