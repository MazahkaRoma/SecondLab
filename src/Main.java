import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (var fileReader = new FileReader("resources/test.txt");
             var scanner = new Scanner(fileReader)) {

            while (scanner.hasNextLine()) {
                String lexeme = scanner.nextLine();
                lexeme = lexeme.replace(";", "");
                if (isStatement(lexeme)) {
                    System.out.println(lexeme + " is statement");
                }
                splitStatement(lexeme);
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static boolean isStatement(String line) {
        return line.matches("if \\(?[0-9A-Za-z]+ ?[<|>|=] [0-9A-Za-z]+\\)? ?(then [0-9A-Za-z]+ ?([*|+|/]|:=) ?[0-9A-Za-z]+ ?([+\\-/*] [0-9A-Za-z]+)?)? ?(else [0-9A-Za-z]+ ?([*+/\\-]|:=) ?[0-9A-Za-z]+ ([+\\-/*] [0-9A-Za-z]+)?)?");
    }

    public static void splitStatement(String statement) {
        String[] statementMembers = statement.split(" ");

        for (String statementMember : statementMembers) {
            if (statementMember.matches("if|then|else")) {
                System.out.println(statementMember + " is operand");
            } else if (statementMember.matches("[+*/\\-]")) {
                System.out.println(statementMember + " is Math Operation");
            } else if (statementMember.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
                System.out.println(statementMember + " is Roman Number");
            } else if (statementMember.matches("[<|>|=]")) {
                System.out.println(statementMember + " is Compare Operation");
            } else if (statementMember.matches(".*?[()].*")) {
                String parenthesis = statementMember.contains("(") ? statementMember.substring(0, 1) : statementMember.substring(statementMember.length() - 1);
                System.out.println(parenthesis + " is Parenthesis");
                splitStatement(statementMember.replaceAll("[()]", ""));
            } else if (statementMember.matches(":=")) {
                System.out.println(statementMember + " is equals operation");
            } else if (statementMember.matches("/{2}.*")) {
                System.out.println(statementMember + " is comment");
            } else if (statementMember.matches("0[xX][0-9a-fA-F]+")) {
                System.out.println(statementMember + " is hexadecimal number");
            } else if (statementMember.matches("[0-9]+")) {
                System.out.println(statementMember + " is Decimal Number");
            } else {
                System.out.println(statementMember + " is Id");
            }
        }
    }


}
