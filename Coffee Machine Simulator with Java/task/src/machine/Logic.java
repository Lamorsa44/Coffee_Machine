package machine;

import java.util.Map;
import java.util.Scanner;

public class Logic {
    Logic logic;
    Scanner scanner = new Scanner(System.in);
    int waterAvailable = 400;
    int milkAvailable = 540;
    int coffeeAvailable = 120;
    int cupsAvailable = 9;
    int money = 550;
    boolean operational = true;

    public void initialize() {
        while (operational) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();

            switch (action) {
                case "buy" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu;");
                    String answer = scanner.next();
                    switch (answer) {
                        case "1" -> {
                            Map<String, Integer> items = Map.ofEntries(
                                    Map.entry("water", waterAvailable - 250),
                                    Map.entry("coffee", coffeeAvailable - 16),
                                    Map.entry("cups", cupsAvailable - 1)
                            );

                            if (checkResources(items)) {
                                System.out.println("I have enough resources, making you a coffee!");

                                waterAvailable -= 250;
                                coffeeAvailable -= 16;
                                cupsAvailable -= 1;
                                money += 4;
                            } else {
                                printMissingItems(items);
                            }
                        }
                        case "2" -> {
                            Map<String, Integer> items = Map.ofEntries(
                                    Map.entry("water", waterAvailable - 350),
                                    Map.entry("milk", milkAvailable - 75),
                                    Map.entry("coffee", coffeeAvailable - 20),
                                    Map.entry("cups", cupsAvailable - 1)
                            );

                            if (checkResources(items)) {
                                System.out.println("I have enough resources, making you a coffee!");

                                waterAvailable -= 350;
                                milkAvailable -= 75;
                                coffeeAvailable -= 20;
                                cupsAvailable -= 1;
                                money += 7;
                            } else {
                                printMissingItems(items);
                            }
                        }
                        case "3" -> {
                            Map<String, Integer> items = Map.ofEntries(
                                    Map.entry("water", waterAvailable - 200),
                                    Map.entry("milk", milkAvailable - 100),
                                    Map.entry("coffee", coffeeAvailable - 12),
                                    Map.entry("cups", cupsAvailable - 1)
                            );

                            if (checkResources(items)) {
                                System.out.println("I have enough resources, making you a coffee!");

                                waterAvailable -= 200;
                                milkAvailable -= 100;
                                coffeeAvailable -= 12;
                                cupsAvailable -= 1;
                                money += 6;
                            } else {
                                printMissingItems(items);
                            }
                        }
                        case "back" -> {
                            continue;
                        }
                    }
                }
                case "fill" -> {
                    System.out.println("Write how many ml of water you want to add: ");
                    waterAvailable += scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    milkAvailable += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    coffeeAvailable += scanner.nextInt();
                    System.out.println("Write how many disposable cups you want to add:");
                    cupsAvailable += scanner.nextInt();
                }
                case "take" -> {
                    System.out.printf("I gave you $%d\n", money);
                    money = 0;
                }
                case "remaining" -> {
                    printStatus();
                }
                case "exit" -> {
                    operational = false;
                }
            }
        }
    }

    private void printMissingItems(Map<String, Integer> items) {
        System.out.print("Sorry, not enough ");
        for (var item : items.entrySet()) {
            if (item.getValue() < 0)
                System.out.print(item.getKey() + ", ");
        }
        System.out.println();
    }

    private boolean checkResources(Map<String, Integer> items) {
        for (int val : items.values()) {
            if (val < 0)
                return false;
        }
        return true;
    }
    private void printStatus() {
        System.out.printf("""
                        
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money
                        """, waterAvailable, milkAvailable,
                coffeeAvailable, cupsAvailable, money);
    }

}
