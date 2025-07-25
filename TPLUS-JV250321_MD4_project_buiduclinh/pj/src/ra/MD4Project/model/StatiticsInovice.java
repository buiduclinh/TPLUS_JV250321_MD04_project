package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;

import java.util.Scanner;

public class StatiticsInovice {
    private int day;
    private int month;
    private int year;
    double totalAmount;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int inputDay(Scanner scanner) {
        while (true) {
            int dayValue;
            System.out.println("Nhập ngày cần tìm kiếm:");
            String day = scanner.nextLine();
            if (ValidateInput.isEmpty(day)) {
                System.out.println("Không được để trống ngày!");
            } else {
                if (ValidateInput.isInt(day)) {
                    try {
                        dayValue = Integer.parseInt(day);
                        if (dayValue >= 1 && dayValue <= 31) {
                            return dayValue;
                        } else {
                            System.out.println("Ngày nhập phải nhỏ hơn hoặc bằng 31");
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Ngày nhập không hợp lệ!");
                }
            }
        }
    }

    public int inputMonth(Scanner scanner) {
        while (true) {
            int monthValue;
            System.out.println("Nhập tháng cần tìm kiếm:");
            String month = scanner.nextLine();
            if (ValidateInput.isEmpty(month)) {
                System.out.println("Không được để trống tháng!");
            } else {
                if (ValidateInput.isInt(month)) {
                    try {
                        monthValue = Integer.parseInt(month);
                        if (monthValue >= 1 && monthValue <= 12) {
                            return monthValue;
                        } else {
                            System.out.println("Tháng nhập phải nhỏ hơn hoặc bằng 12");
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Tháng nhập không hợp lệ!");
                }
            }
        }
    }

    public int inputYear(Scanner scanner) {
        while (true) {
            int yearValue;
            System.out.println("Nhập năm cần tìm kiếm:");
            String month = scanner.nextLine();
            if (ValidateInput.isEmpty(month)) {
                System.out.println("Không được để trống tháng!");
            } else {
                if (ValidateInput.isInt(month)) {
                    try {
                        yearValue = Integer.parseInt(month);
                        if (yearValue >= 0) {
                            return yearValue;
                        } else {
                            System.out.println("Năm nhập phải lớn hơn hoặc 0");
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Năm nhập không hợp lệ!");
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Ngày: %d, Tháng: %d, Năm: %d, Tổng thu: %.2f",
                this.day, this.month, this.year, this.totalAmount);
    }
}
