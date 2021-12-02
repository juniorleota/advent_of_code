package advent.of.code;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 {
  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Objects.requireNonNull(Day1.class.getClassLoader()
        .getResource("day1_input.txt")).toURI());

    List<Integer> elems = Files.lines(path)
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    part1(elems);
    part2(elems);
  }

  static void part1(List<Integer> elems) {
    int count = 0;
    for (int i = 1; i < elems.size(); i++) {
      Integer prev = elems.get(i - 1);
      Integer curr = elems.get(i);
      boolean biggerThanPrev = curr > prev;
      if(biggerThanPrev) count++;
    }
    System.out.println(count);
  }

  static void part2(List<Integer> elems) {
    int window = 3;
    int prevSum = getInitialSum(elems, window);

    int count = 0;
    for (int i = window; i < elems.size(); i++) {
      Integer windowLastElem = elems.get(i - window);
      Integer curr = elems.get(i);
      int currentSum = prevSum - windowLastElem + curr;

      if (currentSum > prevSum) count++;

      prevSum = currentSum;
    }
    System.out.println(count);
  }

  static int getInitialSum(List<Integer> elems, int window) {
    return IntStream.range(0, window).map(elems::get).sum();
  }
}
