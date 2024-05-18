package client.utility;

import BaseModel.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Checker {
    public static class CheckException extends Exception{}

    public static City checkCity(Console console, Integer id) throws CheckException{
        try {

            console.print("name: ");
            String name;
            while (true) {
                name = console.readln().trim();
                if (name.equals("exit")) throw new CheckException();
                if (!name.isEmpty()) break;
                console.print("name: ");
            }

            Coordinates coordinates = checkCoordinates(console);
            long area = checkArea(console);
            long population = checkPopulation(console);
            Integer metersAboveSeaLevel = checkMetersAboveSeaLevel(console);
            boolean capital = checkCapital(console);
            Climate climate = checkClimate(console);
            StandardOfLiving standard = checkStandardOfLiving(console);
            Integer governorAge = checkGovernorAge(console);



            return new City(name, coordinates, area, population, metersAboveSeaLevel, capital, climate, standard, new Human(governorAge));



        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static Integer checkGovernorAge(Console console) throws CheckException{
        try {
            console.print("governor.age: ");
            Integer age;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { age = Integer.parseInt(line); if (age > 0) break; } catch (NumberFormatException e) { }
                }
                console.print("governor.age - целое число, большее 0: ");
            }

            return age;

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения. ");
            return checkMetersAboveSeaLevel(console);
        }
    }

    private static StandardOfLiving checkStandardOfLiving(Console console) throws CheckException {
        try {
            console.print("StandardOIfLiving ("+ Arrays.toString(StandardOfLiving.values()) +"): ");
            StandardOfLiving climate;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { climate = StandardOfLiving.valueOf(line); break; } catch (NullPointerException | IllegalArgumentException  e) { }
                }
                console.print("StandardOIfLiving должен быть одним из значений из списка ("
                        + Arrays.toString(StandardOfLiving.values()) + "): ");
            }
            return climate;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static Climate checkClimate(Console console) throws CheckException {
        try {
            console.print("Climate ("+ Arrays.toString(Climate.values()) +"): ");
            Climate climate;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { climate = Climate.valueOf(line); break; } catch (NullPointerException | IllegalArgumentException  e) { }
                }
                console.print("Climate должен быть одним из значений из списка ("
                        + Arrays.toString(Climate.values()) + "): ");
            }
            return climate;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static boolean checkCapital(Console console) throws CheckException{
        try {
            console.print("capital: ");
            boolean meters;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { meters = Boolean.parseBoolean(line); break; } catch (InputMismatchException e) { }
                }
                console.print("capital - булевое значение(true, false):");
            }

            return meters;

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения. ");
            return checkCapital(console);
        }

    }

    private static Integer checkMetersAboveSeaLevel(Console console) throws CheckException{
        try {
            console.print("meters above sea: ");
            Integer meters;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { meters = Integer.parseInt(line); break; } catch (NumberFormatException e) { }
                }
                console.print("meters above sea - целое число: ");
            }

            return meters;

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения. ");
            return checkMetersAboveSeaLevel(console);
        }
    }

    private static long checkPopulation(Console console) throws CheckException{
        try {
            console.print("population: ");
            long population;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { population = Long.parseLong(line); if (population>0) break; } catch (NumberFormatException e) { }
                }
                console.print("population - целое число > 0: ");
            }

            return population;

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения. ");
            return checkPopulation(console);
        }
    }

    private static long checkArea(Console console) throws CheckException{
        try {
            console.print("area: ");
            long area;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { area = Long.parseLong(line); if (area>0) break; } catch (NumberFormatException e) { }
                }
                console.print("area - целое число > 0: ");
            }

          return area;

        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения. ");
            return checkArea(console);
        }
    }

    private static Coordinates checkCoordinates(Console console) throws CheckException{
        try {
            console.print("coordinates.x: ");
            float x;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { x = Float.parseFloat(line); if (x>-584) break; } catch (NumberFormatException e) { }
                }
                console.print("coordinates.x - целое число > -584: ");
            }
            console.print("coordinates.y: ");
            float y;
            while (true) {
                String line = console.readln().trim();
                if (line.equals("exit")) throw new CheckException();
                if (!line.equals("")) {
                    try { y = Float.parseFloat(line); if (y>-469) break; } catch (NumberFormatException e) { }
                }
                console.print("coordinates.y - целое число > -469: ");
            }

            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}
