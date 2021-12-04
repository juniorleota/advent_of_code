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

  class DataParser {
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
    UP(-1), DOWN(1), FORWARD(1), BACK(-1);
    public int multiplier;

    Direction(int multiplier) {
      this.multiplier = multiplier;
    }

    public static Direction parse(String str) {
      return Direction.valueOf(str.toUpperCase());
    }
  }

  static class SubmarineLocation {
    int depth;
    int horizontalPos;

    public SubmarineLocation() {
      this.depth = 0;
      this.horizontalPos = 0;
    }

    public void addMovement(Data movement) {
      Direction direction = movement.direction;
      if (direction == Direction.UP || direction == Direction.DOWN) {
        this.depth += movement.value * direction.multiplier;
      } else {
        this.horizontalPos += movement.value * direction.multiplier;
      }
    }

    public int getLocation() {
      return this.depth * this.horizontalPos;
    }
  }
}
