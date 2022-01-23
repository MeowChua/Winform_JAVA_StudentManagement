/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

public class ConvertNumber {
    // chuyen chuoi he radix (he 2, 8, 10, 16) sang so double he 10
    protected double stringRadixToDouble(String str, int radix) {
        str = str.toUpperCase();
        int len = str.length();
        int indexDot = -1;
        double num = -1;
        String radixChar = "0123456789ABCDEF";

        // neu khong phai la chuoi trong he radix thi tra ve -1
        if (!isRadixString(str, radix)) {
            return num;
        }
        // tim vi tri dau cham de phan tach phan nguyen va phan thap phan
        indexDot = str.indexOf(".");
        if (indexDot > 0) {
            // chuoi phan nguyen
            String intString = str.substring(0, indexDot);
            // chuoi phan thap phan
            String floatString = str.substring(indexDot + 1, len);
            // chuyen phan nguyen vao num
            num = Integer.parseInt(intString, radix);
            // cong don phan thap phan
            for (int i = 0; i < floatString.length(); i++) {
                num += radixChar.indexOf(floatString.charAt(i))
                        / Math.pow(radix, i + 1);
            }
        } else {
            num = Integer.parseInt(str, radix);
        }
        return num;
    }

    // chuyen so he 10 sang he radix (2, 8, 16)
    protected String doubleToStringRadix(double num, int radix, int countRount) {
        String str = "";
        String radixChar = "0123456789ABCDEF";
        long intNum = (long) num;
        double floatNum = (num - intNum);
        // doi so nguyen inNumber sang chuoi he radixs
        String intString = Long.toString(intNum, radix).toUpperCase();
        String floatString = "";
        // doi phan thap phan
        while (floatNum > 0 && countRount > 0) {
            floatNum = (floatNum * radix);
            floatString += radixChar.charAt((int) floatNum);
            floatNum = floatNum - (int) floatNum;
            countRount--;
        }
        str = intString;
        if (floatString.length() > 0) {
            str += "." + floatString;
        }
        return str;
    }

    public boolean isRadixString(String str, int radix) {
        str = str.toUpperCase();
        int len = str.length();
        int countDot = 0;
        String radixChar = "";
        if (radix == 2) {
            radixChar = "01.";
        } else if (radix == 8) {
            radixChar = "01234567.";
        } else if (radix == 16) {
            radixChar = "0123456789ABCDEF.";
        } else {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                countDot++;
            }
            if (radixChar.indexOf(c) < 0 || countDot > 1) {
                return false;
            }
        }
        return true;
    }

    // doi so numberDouble sang tich cua cac thua so nguyen to
    protected String primeMulti(double numDouble) {
        if (numDouble >= 0 && (long) numDouble == numDouble) {
            long num = (long) numDouble;
            // powCount la so mu tach duoc
            int powCount = 0, i = 2, m = (int) Math.sqrt(num) + 1;
            String s = "";
            // duyet tu 2 den can bac 2 cua num
            while (i < m) {
                powCount = 0; // so mu cua i
                // tach num thanh i^n
                while (num > 0 && num % i == 0) {
                    powCount++;
                    num /= i;
                }
                // cong them phan tu neu no chia het
                if (powCount > 0) {
                    if (s.length() > 0) {
                        s += "×"; // cong dau nhan
                    }
                    s += i; // cong so vao
                    if (powCount > 1) {
                        s += myPowAxB(powCount); // cong so mu
                    }
                }
                if (i == 2) {
                    i++;
                } else {
                    i += 2;
                }
            }
            // kiem tra num sau vong lap co la so nguyen to ko, 
            //neu la so nguyen to thi cho them vao
            if (num > 1) {
                if (s.length() > 0) {
                    s += "×";
                }
                s += num;
            } else if (s.length() == 0) {
                s += num;
            }
            return s;
        }
        return "-1";
    }

    private String myPowAxB(int number) {
        String numString = number + "";
        String numPow = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        String result = "";
        for (int i = 0; i < numString.length(); i++) {
            result += numPow.charAt(Integer.parseInt(numString.charAt(i) + ""));
        }
        return result;
    }
}


