package com.ss.utopia;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ss.utopia.models.Passenger;

public class MOCKPassengerRepository {

  private static final Passenger testPassenger = new Passenger(1, 6, "AFHAJKFHKAJS", "FirstName1", "LastName1", "MALE", "1987-3-14", "2342 Water Lane 4291 RockCity Virginia", true);

  private static final Passenger[] testPassengerArray = {
    testPassenger,
    new Passenger(2, 7, "bhdjkHKKKAJS", "FirstName2", "LastName2", "MALE", "1988-1-9", "8880 Woodsman Street Marquette, MI 49855", false),
    new Passenger(3, 8, "UOIUHKJAHSAS", "FirstName3", "LastName3", "FEMALE", "1989-7-3", "530 Homestead Rd. North Miami Beach, FL 33160", false),
    new Passenger(4, 9, "WYTWHJKASFHJ", "FirstName4", "LastName4", "MALE", "1988-1-9", "75 Amherst Dr. Raleigh, NC 27603", false),
    new Passenger(5, 10, "PIPOIMBJJSSJ", "FirstName5", "LastName5", "FEMALE", "2003-7-3", "495 Henry Smith Road Rowlett, TX 75088", false),
    new Passenger(6, 11, "RTYCGZNCBCCC", "FirstName6", "LastName6", "MALE", "1956-1-9", "107 Greenrose St. Brownsburg, IN 46112", true),
    new Passenger(7, 12, "MOKJOIASJKHD", "FirstName7", "LastName7", "FEMALE", "1972-7-3", "9329 West Lakeshore St. Parkville, MD 21234", true),
    new Passenger(8, 13, "UIASHASJKKZC", "FirstName8", "LastName8", "MALE", "1994-1-9", "8109 Jefferson Drive Holland, MI 49423", false),
    new Passenger(9, 14, "QQWASNDAJSDK", "FirstName9", "LastName9", "FEMALE", "2011-7-3", "670 Gartner Dr. Shakopee, MN 55379", false),
  };

  private static final List<Passenger> testPassengerList = Arrays.asList(testPassengerArray);


  public static Passenger getTestPassenger() {
    return testPassenger;
  }

  public static List<Passenger> getTestPassengerList() {
    return testPassengerList;
  }

  public static List<Passenger> findAllWithResults() {
    return testPassengerList;
  }

  public static List<Passenger> findAllWithNoResults() {
    List<Passenger> emptyPassengerList = Arrays.asList();
    return emptyPassengerList;
  }

  public static Optional<Passenger> findById(Integer id) {
    List<Passenger> passengerByIdList = testPassengerList.stream()
      .filter(i -> i.getPassengerId().equals(id))
      .collect(Collectors.toList());
    return Optional.of(passengerByIdList.get(0));
  }

  public static Passenger save(Passenger passenger) {
    return passenger;
  }

  public static void deleteById() {}
}