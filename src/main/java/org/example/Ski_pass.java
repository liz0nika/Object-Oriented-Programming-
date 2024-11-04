package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ski_pass {
    abstract static class Ski_card {
        Integer id_card;
        String type_card;
        LocalDate date_of_expiry;
        Integer number_trips;

        public Ski_card(Integer id_card, String type_card, LocalDate date_of_expiry, Integer number_trips) {
            this.id_card = id_card;
            this.type_card = type_card;
            this.date_of_expiry = date_of_expiry;
            this.number_trips = number_trips;
        }

        public Integer getId_card() {
            return id_card;
        }

        public void setId_card(Integer id_card) {
            this.id_card = id_card;
        }

        public String getType_card() {
            return type_card;
        }

        public void setType_card(String type_card) {
            this.type_card = type_card;
        }

        public LocalDate getDate_of_expiry() {
            return date_of_expiry;
        }

        public void setDate_of_expiry(LocalDate date_of_expiry) {
            this.date_of_expiry = date_of_expiry;
        }

        public Integer getNumber_trips() {
            return number_trips;
        }

        public void setNumber_trips(Integer number_trips) {
            this.number_trips = number_trips;
        }
    }

    static class Pass_workdays extends Ski_card {
        public Pass_workdays(Integer id_card, String type_card, LocalDate date_of_expiry, Integer number_trips) {
            super(id_card, type_card, date_of_expiry, number_trips);
        }

        public static Pass_workdays TenTripPassWD(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_workdays(id_card, "Workdays", date_of_expiry, 10);
        }

        public static Pass_workdays TwentyTripPassWD(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_workdays(id_card, "Workdays", date_of_expiry, 20);
        }

        public static Pass_workdays FiftyTripPassWD(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_workdays(id_card, "Workdays", date_of_expiry, 50);
        }

        public static Pass_workdays HundredTripPassWD(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_workdays(id_card, "Workdays", date_of_expiry, 100);
        }
    }

    static class Pass_weekends extends Ski_card {
        public Pass_weekends(Integer id_card, String type_card, LocalDate date_of_expiry, Integer number_trips) {
            super(id_card, type_card, date_of_expiry, number_trips);
        }

        public static Pass_weekends TenTripPassWeekends(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_weekends(id_card, "Weekends", date_of_expiry, 10);
        }

        public static Pass_weekends TwentyTripPassWeekends(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_weekends(id_card, "Weekends", date_of_expiry, 20);
        }

        public static Pass_weekends FiftyTripPassWeekends(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_weekends(id_card, "Weekends", date_of_expiry, 50);
        }

        public static Pass_weekends HundredTripPassWeekends(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_weekends(id_card, "Weekends", date_of_expiry, 100);
        }
    }

    static class Pass_season extends Ski_card {
        public Pass_season(Integer id_card, String type_card, LocalDate date_of_expiry, Integer number_trips) {
            super(id_card, type_card, date_of_expiry, null);
        }

        public static Pass_season SeasonPass(Integer id_card, LocalDate date_of_expiry) {
            return new Pass_season(id_card, "Season", date_of_expiry, null);
        }
    }

    public static boolean CheckDateExpiration(LocalDate date_of_expiry) {
        return LocalDate.now().isAfter(date_of_expiry);
    }

    public static boolean CheckNumberTrips(Integer id_card, Integer number_trips) {
        if (number_trips != null && number_trips > 0) {
            return true;
        } else {
            System.out.println("Balance is empty. ID: " + id_card);
            return false;
        }
    }

    public String InfoSkiPass_toString(Ski_card skiCard) {
        StringBuilder info = new StringBuilder();
        info.append("Ski Pass Info:\n");
        info.append("ID: ").append(skiCard.getId_card()).append("\n");
        info.append("Type: ").append(skiCard.getType_card()).append("\n");
        info.append("Date of Expiry: ").append(skiCard.getDate_of_expiry()).append("\n");
        if (skiCard.getNumber_trips() != null) {
            info.append("Number of Trips: ").append(skiCard.getNumber_trips()).append("\n");
        }
        return info.toString();
    }

    static class Turnstile {
        private List<Ski_card> allowPasses;
        private int totalPasses;
        private int totalRefusals;

        public Turnstile() {
            this.allowPasses = new ArrayList<>();
            this.totalPasses = 0;
            this.totalRefusals = 0;
        }

        public boolean check_use_pass(Ski_card skiCard) {
            if (skiCard == null || CheckDateExpiration(skiCard.date_of_expiry)) {
                totalRefusals++;
                return false;
            }
            if (skiCard.number_trips != null) {
                if (!CheckNumberTrips(skiCard.id_card, skiCard.number_trips)) {
                    totalRefusals++;
                    return false;
                }
                skiCard.number_trips--;
            }
            totalPasses++;
            allowPasses.add(skiCard);
            return true;
        }


        public void SummaryType() {
            int workdayPasses = 0;
            int weekendPasses = 0;
            int seasonPasses = 0;

            for (Ski_card skiCard : allowPasses) {
                switch (skiCard.getType_card()) {
                    case "Workdays":
                        workdayPasses++;
                        break;
                    case "Weekends":
                        weekendPasses++;
                        break;
                    case "Season":
                        seasonPasses++;
                        break;
                }
            }
            System.out.println("Workday passes: " + workdayPasses);
            System.out.println("Weekends passes: " + weekendPasses);
            System.out.println("Season passes: " + seasonPasses);
        }

        public void printSummary() {
            System.out.println("Total passes: " + totalPasses);
            System.out.println("Total refusals: " + totalRefusals);
        }
    }

    public static void main(String[] args) {
        Turnstile turnstile = new Turnstile();

        LocalDate expiryDate1 = LocalDate.of(2023, 12, 31);
        LocalDate expiryDate2 = LocalDate.of(2024, 1, 15);
        LocalDate expiryDate3 = LocalDate.of(2024, 12, 28);


        Pass_workdays workdayPass = Pass_workdays.TenTripPassWD(1, expiryDate1);
        Pass_weekends weekendPass = Pass_weekends.TwentyTripPassWeekends(2, expiryDate2);
        Pass_season seasonPass = Pass_season.SeasonPass(3, expiryDate3);

        Pass_workdays workdayPass2 = Pass_workdays.TenTripPassWD(4, expiryDate3);
        Pass_weekends weekendPass2 = Pass_weekends.TwentyTripPassWeekends(5, expiryDate3);

        Ski_pass skiPassSystem = new Ski_pass();
        System.out.println(skiPassSystem.InfoSkiPass_toString(workdayPass));
        turnstile.check_use_pass(workdayPass);
        System.out.println(skiPassSystem.InfoSkiPass_toString(weekendPass));
        turnstile.check_use_pass(weekendPass);
        System.out.println(skiPassSystem.InfoSkiPass_toString(seasonPass));
        turnstile.check_use_pass(seasonPass);
        System.out.println(skiPassSystem.InfoSkiPass_toString(workdayPass2));
        turnstile.check_use_pass(workdayPass2);
        System.out.println(skiPassSystem.InfoSkiPass_toString(weekendPass2));
        turnstile.check_use_pass(weekendPass2);
        turnstile.printSummary();
        turnstile.SummaryType();
    }
}
